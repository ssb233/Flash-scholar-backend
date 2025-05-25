package com.flash.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "处理认领请求")
public class ClaimRequestDTO {
    @Schema(description = "认领请求编号", example = "1")
    private Integer claimId;
    @Schema(description = "处理管理员编号", example = "1")
    private Integer adminId;
    @Schema(description = "处理态度, 1:通过 | 0:驳回", example = "1")
    private Integer status;
}
