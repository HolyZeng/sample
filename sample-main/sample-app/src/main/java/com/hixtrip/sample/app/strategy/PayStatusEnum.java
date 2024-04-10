package com.hixtrip.sample.app.strategy;

/**
 * @ClassName payStatusEnum
 * @Package PACKAGE_NAME
 * @description 支付状态枚举
 * @Author li
 * @Date 2024/4/9 22:05
 * @Version 1.0
 */
public enum PayStatusEnum {
    /**
     * 等待支付
     */
    WAITING("1"),
    /**
     * 支付成功
     */
    SUCCESS("2"),
    /**
     * 支付失败
     */
    FAIL("3"),
    /**
     * 重复支付
     */
    REPEAT("4"),
    ;

    private String status;

    PayStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
