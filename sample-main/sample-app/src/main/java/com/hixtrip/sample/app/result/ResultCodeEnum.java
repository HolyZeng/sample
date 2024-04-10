package com.hixtrip.sample.app.result;

/**
 * @ClassName resultCodeEnum
 * @Package com.hixtrip.sample.app.result
 * @description 返回状态枚举
 * @Author li
 * @Date 2024/4/9 19:04
 * @Version 1.0
 */
public enum ResultCodeEnum {
    /**
     * 请求成功
     */
    SUCCESS("100001","请求成功"),
    /**
     * 请求失败
     */
    FAIL("100002","请求失败"),
    ;

    private String code;
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
