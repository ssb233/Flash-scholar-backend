package com.flash.user.controller;


import com.flash.user.dao.Comment;
import com.flash.user.dto.AddDTO;
import com.flash.user.dto.CommentDTO;
import com.flash.user.dto.LikeRequestDTO;
import com.flash.user.response.CustomResponse;
import com.flash.user.service.CommentService;
import com.flash.user.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
@Tag(name = "评论", description = "评论信息")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @Operation(
            summary = "添加评论",
            description = "对学术成果进行评论 | 对评论进行回复。" +
                    "当对学术成果进行评论时, parentCommentId = 0, 表明为一级评论" +
                    "当对评论进行回复时, 只需要设置parentCommentId"
    )
    public CustomResponse addComment(@RequestBody AddDTO addDTO){
        CustomResponse response = new CustomResponse();
        Integer parentCommentId = addDTO.getParentCommentId();
        Integer userId = addDTO.getUserId();
        String achievementId = addDTO.getAchievementId();
        String content = addDTO.getContent();
        try{
            Comment comment = commentService.addComment(parentCommentId, userId, achievementId, content);
            response.setData(comment);
            response.setMessage("success");
        } catch (BusinessException e){
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setCode(e.getCode());
        }
        return response;
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "删除评论",
            description = "根据评论Id对评论进行删除"
    )
    @Parameters(value = {
            @Parameter(name = "commentId", description = "评论Id", in = ParameterIn.PATH, example = "1")
    })
    public CustomResponse deleteComment(@RequestParam Integer commentId) throws BusinessException{
        CustomResponse response = new CustomResponse();
        try {
            commentService.deleteComment(commentId);
            response.setMessage("success");
        } catch (BusinessException e){
            e.printStackTrace();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Operation(
            summary = "点赞评论",
            description = "用户对评论进行点赞"
    )
    @PostMapping("/like")
    public CustomResponse likeComment(@RequestBody LikeRequestDTO likeRequest) throws BusinessException{
        Integer userId = likeRequest.getUserId();
        Integer commentId = likeRequest.getCommentId();
        CustomResponse response = new CustomResponse();
        try {
            Comment comment = commentService.likeComment(userId, commentId);
            response.setMessage("success");
            response.setData(comment);
        } catch (BusinessException e){
            e.printStackTrace();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Operation(
            summary = "取消点赞评论",
            description = "用户对评论进行取消点赞"
    )
    @PostMapping("/unlike")
    public CustomResponse unlikeComment(@RequestBody LikeRequestDTO likeRequest) throws BusinessException{
        Integer userId = likeRequest.getUserId();
        Integer commentId = likeRequest.getCommentId();
        CustomResponse response = new CustomResponse();
        try {
            commentService.unlikeComment(userId, commentId);
            response.setMessage("success");
        } catch (BusinessException e){
            e.printStackTrace();
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }



    @GetMapping("/achievement/{userId}/{achievementId}")
    @Operation(
            summary = "获取学术成果的评论",
            description = "根据achievementId获取学术成果的评论, userId判断评论是否被当前用户点赞"
    )
    @Parameters(value = {
            @Parameter(name = "userId", description = "评论Id", in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "achievementId", description = "成果Id", in = ParameterIn.PATH, example = "1"),
    })
    public CustomResponse getComment(@PathVariable Integer userId, @PathVariable String achievementId){
        CustomResponse customResponse = new CustomResponse();
        List<CommentDTO> comments = commentService.getCommentByAchievement(userId, achievementId);
        customResponse.setData(comments);
        customResponse.setMessage("success");
        return customResponse;
    }

    @GetMapping("/reply/{userId}/{commentId}")
    @Operation(
            summary = "获取评论的回复",
            description = "根据commentId获取评论的回复, userId判断评论是否被当前用户点赞"
    )
    @Parameters(value = {
            @Parameter(name = "userId", description = "用户Id", in = ParameterIn.PATH, example = "1"),
            @Parameter(name = "commentId", description = "评论Id", in = ParameterIn.PATH, example = "3")
    })
    public CustomResponse getComment(@PathVariable Integer userId, @PathVariable Integer commentId){
        CustomResponse customResponse = new CustomResponse();
        List<CommentDTO> comments = commentService.getCommentByParent(userId, commentId);
        customResponse.setData(comments);
        customResponse.setMessage("success");
        return customResponse;
    }
}
