package com.flash.records.utils;

import lombok.Getter;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */

@Getter
public class BusinessException extends Exception{

    int code;
    String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}