package com.flash.redis.utils;

import lombok.Getter;

@Getter
public class BusinessException extends Exception{
    int code;
    String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
