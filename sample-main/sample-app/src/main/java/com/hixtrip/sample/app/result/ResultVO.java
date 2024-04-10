package com.hixtrip.sample.app.result;

import lombok.Data;

/**
 * @ClassName ResultVO
 * @Package com.hixtrip.sample.app.result
 * @description 返回对象
 * @Author li
 * @Date 2024/4/9 19:03
 * @Version 1.0
 */
@Data
public class ResultVO<T> {
    private String message;
    private ResultCodeEnum resultCodeEnum;
    private T data;

    public ResultVO(String message, ResultCodeEnum resultCodeEnum, T data) {
        this.message = message;
        this.resultCodeEnum = resultCodeEnum;
        this.data = data;
    }

    public static <T> ResultVO<T> SUCCESS(String message, T data){
        return new ResultVO(message,ResultCodeEnum.SUCCESS,data);
    }

    public static <T> ResultVO<T> SUCCESS(String message){
        return new ResultVO(message,ResultCodeEnum.SUCCESS,null);
    }

    public static <T> ResultVO<T> FAIL(String message){
        return new ResultVO(message,ResultCodeEnum.FAIL,null);
    }

}
