package com.flash.collection.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "收藏实体")
public class CollectionDTO {
    @Schema(description = "学术成果序号", example = "1")
    private String achievementId;
    @Schema(description = "收藏时间", example = "2024-11-30 00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date time;
    @Schema(description = "标题", example = "示例成果")
    private String title; // 成果名称
    @Schema(description = "作者", example = "张三")
    private String authors; // 作者
    @Schema(description = "期刊", example = "示例期刊")
    private String journal; // 期刊
    @Schema(description = "引用次数", example = "1")
    private Integer quoteTime;
    @Schema(description = "年份", example = "2024")
    @JsonFormat(pattern = "yyyy", timezone = "Asia/Shanghai")
    private Date date;
    @Schema(description = "收藏夹序号", example = "1")
    private Integer fid;

}
