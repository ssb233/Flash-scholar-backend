package com.flash.collection.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "添加收藏返回结果")
public class AddResponse {
    @Schema(description = "收藏夹名称", example = "示例收藏夹")
    private String fName;
}
