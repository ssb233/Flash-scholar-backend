package com.flash.user.converter;

import com.flash.user.dao.Comment;
import com.flash.user.dto.CommentDTO;

public class CommentConverter {
    public static Comment convertComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentCommentId(commentDTO.getParentCommentId());
        comment.setLikes(commentDTO.getLikes());
        comment.setContent(commentDTO.getContent());
        comment.setCommentTime(commentDTO.getCommentTime());
        comment.setCommentId(commentDTO.getCommentId());
        comment.setReplyCount(commentDTO.getReplyCount());
        return comment;
    }

    public static CommentDTO convertComment(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setParentCommentId(comment.getParentCommentId());
        commentDTO.setLikes(comment.getLikes());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCommentTime(comment.getCommentTime());
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setReplyCount(comment.getReplyCount());
        return commentDTO;
    }
}
