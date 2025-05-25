package com.flash.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户对评论进行点赞")
public class LikeRequestDTO {
    @Schema(description = "用户Id", example = "1")
    private Integer userId;
    @Schema(description = "评论序号", example = "3")
    private Integer commentId;
}
