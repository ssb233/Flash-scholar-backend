package com.flash.redis.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private int code = 200;
    private String message = "OK";
    private Object data;
}
