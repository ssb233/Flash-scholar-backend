package com.flash.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "返回论文的序列化结果")
public class CommentDTO {
    @Schema(description = "评论Id, 为了回复时传参", example = "3")
    private Integer commentId;
    @Schema(description = "父评论编号", example = "0")
    private Integer parentCommentId;
    @Schema(description = "进行评论的用户昵称", example = "示例用户")
    private String username;
    @Schema(description = "评论内容", example = "这论文可太棒啦")
    private String content;
    @Schema(description = "被点赞数", example = "2")
    private Integer likes;
    @Schema(description = "回复数量", example = "0")
    private Integer replyCount;
    @Schema(description = "是否被当前用户点赞", example = "true")
    private Boolean isLiked;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date commentTime;
}
