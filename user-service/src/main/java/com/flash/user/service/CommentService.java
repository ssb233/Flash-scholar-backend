package com.flash.user.service;

import com.flash.user.dao.Comment;
import com.flash.user.dto.CommentDTO;
import com.flash.user.utils.BusinessException;

import java.util.List;

public interface CommentService {
    /**
     * 增加评论
     */
    Comment addComment(Integer parentCommentId, Integer userId, String achievementId, String content) throws BusinessException;

    /**
     * 删除评论
     */
    void deleteComment(Integer commentId) throws BusinessException;

    /**
     * 获取成果的评论
     */
    List<CommentDTO> getCommentByAchievement(Integer userId, String achievementId);

    List<CommentDTO> getCommentByParent(Integer userId, Integer commentId);

    Comment likeComment(Integer userId, Integer commentId) throws BusinessException;

    void unlikeComment(Integer userId, Integer commentId) throws BusinessException;


}
