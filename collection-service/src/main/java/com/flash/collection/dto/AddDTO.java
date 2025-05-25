package com.flash.collection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author zlp
 * @Discription 前端传来的数据
 * @date 2024/11/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "添加收藏实体")
public class AddDTO {
    @Schema(description = "用户Id", example = "1")
    private Integer uid;
    @Schema(description = "学术成果序号", example = "1")
    private String achievementId;
    @Schema(description = "收藏夹序号", example = "2")
    private List<Integer> fid;
    @Schema(description = "标题", example = "示范论文")
    private String title;
    @Schema(description = "作者", example = "张三")
    private String authors;
    @Schema(description = "期刊", example = "示范期刊")
    private String journal;
    @Schema(description = "引用次数", example = "1")
    private Integer quoteTime;
    @Schema(description = "年份", example = "2024")
    private Date date;

}
