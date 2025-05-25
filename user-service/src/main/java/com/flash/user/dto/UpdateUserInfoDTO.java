package com.flash.user.dto;

import com.alibaba.nacos.shaded.javax.annotation.concurrent.NotThreadSafe;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yury
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户信息更改入参")
public class UpdateUserInfoDTO {
    @NotNull
    @Schema(description = "用户id", example = "1")
    private Integer uid;

    @Schema(description = "昵称", example = "张三")
    private String nickname;

    @Schema(description = "领域", example = "AI")
    private String fields;
}
