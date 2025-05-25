package com.flash.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "评论实体")
public class AddDTO {
    @Schema(description = "父评论, 一级评论：0 | 回复：对应的评论id", example = "0")
    private Integer parentCommentId;
    @Schema(description = "进行评论的用户", example = "2")
    private Integer userId;
    @Schema(description = "学术成果序号", example = "1")
    private String achievementId;
    @Schema(description = "评论的具体内容", example = "这文章可太棒啦")
    private String content;
}
