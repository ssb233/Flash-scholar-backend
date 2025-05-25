package com.flash.collection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "添加收藏夹", description = "用户编号, 收藏夹名以及收藏夹描述")
public class FavouriteAddDTO {
    @Schema(description = "用户编号", example = "1")
    private Integer uid;
    @Schema(description = "收藏夹名", example = "默认收藏夹")
    private String fName;
    @Schema(description = "收藏夹描述", example = "这是默认的收藏夹哦")
    private String description;
}
