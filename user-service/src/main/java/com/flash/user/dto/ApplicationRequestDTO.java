package com.flash.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationRequestDTO {
    @Schema(description = "申请编号", example = "1")
    private Integer applicationId;
    @Schema(description = "处理管理员编号", example = "1")
    private Integer adminId;
    @Schema(description = "处理态度, 1:通过 | 0:驳回", example = "1")
    private Integer status;
}
