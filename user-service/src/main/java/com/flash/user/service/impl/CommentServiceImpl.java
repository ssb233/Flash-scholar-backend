package com.flash.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.user.converter.CommentConverter;
import com.flash.user.dao.Comment;
import com.flash.user.dao.Likes;
import com.flash.user.dao.User;
import com.flash.user.dto.CommentDTO;
import com.flash.user.mapper.CommentMapper;
import com.flash.user.mapper.LikeMapper;
import com.flash.user.mapper.MsgMapper;
import com.flash.user.mapper.UserMapper;
import com.flash.user.service.CommentService;
import com.flash.user.service.MsgService;
import com.flash.user.utils.BusinessException;
import com.flash.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MsgService msgService;


    @Override
    public Comment addComment(Integer parentCommentId, Integer userId, String achievementId, String content) throws BusinessException {
        checkEmpty(parentCommentId, userId, achievementId, content);
        Date now = new Date();
        Comment newComment = new Comment(
                null,
                parentCommentId,
                userId,
                achievementId,
                content,
                0,
                0,
                now
        );
        commentMapper.insert(newComment);
        // 如果有父评论，则将父评论的回复数+1
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id", newComment.getParentCommentId());
        Comment parentComment = commentMapper.selectOne(wrapper);
        if (parentComment != null) {
            parentComment.setReplyCount(parentComment.getReplyCount() + 1);
            commentMapper.updateById(parentComment);


            // 发送回复信息
            User user = userMapper.selectById(userId);
            msgService.sendMsg(Long.valueOf(parentComment.getUserId()), "@" + user.getNickname() + "回复了你的评论", achievementId, 1);

        }
        return newComment;
    }

    @Override
    public void deleteComment(Integer commentId) throws BusinessException{
        // 查找父评论
        Comment comment = commentMapper.selectById(commentId);
        Comment parentComment = commentMapper.selectById(comment.getParentCommentId());
        if(parentComment != null){
            parentComment.setReplyCount(parentComment.getReplyCount()-1);
            commentMapper.updateById(parentComment);
        }

        List<Integer> allCommentIds = new ArrayList<>();
        allCommentIds.add(commentId);

        // 查找子评论
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_comment_id", commentId);
        List<Comment> childComments = commentMapper.selectList(queryWrapper);
        for(Comment child:childComments){
            allCommentIds.add(child.getCommentId());
        }

        QueryWrapper<Comment> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.in("comment_id", allCommentIds);
        int rows = commentMapper.delete(deleteWrapper);
        if(rows == 0) {
            throw new BusinessException(400, "该评论不存在");
        }
    }

    @Override
    public List<CommentDTO> getCommentByAchievement(Integer userId, String achievementId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("achievement_id", achievementId)
                .eq("parent_comment_id",0);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        // 查找当前用户所有点赞的评论
        QueryWrapper<Likes> likeQueryWrapper = new QueryWrapper<>();
        likeQueryWrapper.eq("user_id", userId);
        List<Likes> likes = likeMapper.selectList(likeQueryWrapper);
        Set<Integer> allLikedComments = likes.stream().map(Likes::getCommentId).collect(Collectors.toSet());
        return comments.stream().map(
                comment -> {
                    CommentDTO commentDTO = CommentConverter.convertComment(comment);
                    commentDTO.setIsLiked(allLikedComments.contains(commentDTO.getCommentId()));
                    commentDTO.setUsername(userMapper.selectById(comment.getUserId()).getNickname());
                    return commentDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getCommentByParent(Integer userId, Integer commentId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_comment_id", commentId);
        List<Comment> comments = new ArrayList<>();
        comments.add(commentMapper.selectById(commentId));
        comments.addAll(commentMapper.selectList(queryWrapper));

        // 查找当前用户所有点赞的评论
        QueryWrapper<Likes> likeQueryWrapper = new QueryWrapper<>();
        likeQueryWrapper.eq("user_id", userId);
        List<Likes> likes = likeMapper.selectList(likeQueryWrapper);
        Set<Integer> allLikedComments = likes.stream().map(Likes::getCommentId).collect(Collectors.toSet());



        return comments.stream().map(
                comment -> {
                    CommentDTO commentDTO = CommentConverter.convertComment(comment);
                    commentDTO.setIsLiked(allLikedComments.contains(commentDTO.getCommentId()));
                    User user = userMapper.findByIndex(comment.getUserId());
                    commentDTO.setUsername(user.getNickname());
                    return commentDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Comment likeComment(Integer userId, Integer commentId) throws BusinessException{
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        Comment comment= commentMapper.selectOne(queryWrapper);
        if(comment == null){
            throw new BusinessException(400, "评论不存在");
        }

        QueryWrapper<Likes> queryLike = new QueryWrapper<>();
        queryLike.eq("user_id", userId)
                        .eq("comment_id", commentId);
        Likes like = likeMapper.selectOne(queryLike);
        if(like != null){
            throw new BusinessException(400, "已点赞");
        }
        Likes newLike = new Likes(
                null,
                commentId,
                userId
        );
        likeMapper.insert(newLike);
        comment.setLikes(comment.getLikes()+1);
        commentMapper.updateById(comment);
        return comment;
    }

    @Override
    public void unlikeComment(Integer userId, Integer commentId) throws BusinessException {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_id", commentId);
        Comment comment= commentMapper.selectOne(queryWrapper);
        if(comment == null){
            throw new BusinessException(400, "评论不存在");
        }

        QueryWrapper<Likes> queryLike = new QueryWrapper<>();
        queryLike.eq("user_id", userId)
                .eq("comment_id", commentId);
        int rows = likeMapper.delete(queryLike);
        if(rows == 0){
            throw new BusinessException(400, "未点赞过");
        }
        comment.setLikes(comment.getLikes()-1);
        commentMapper.updateById(comment);
    }

    private void checkEmpty(Integer parentCommentId, Integer userId, String achievementId, String content) throws BusinessException{
        if(parentCommentId == null || userId == null || achievementId == null || content == null){
            throw new BusinessException(400, "内容不能为空");
        }
    }
}
