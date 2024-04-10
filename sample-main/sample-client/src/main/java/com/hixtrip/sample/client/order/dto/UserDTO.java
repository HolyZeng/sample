package com.hixtrip.sample.client.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserDTO
 * @Package com.hixtrip.sample.client.order.dto
 * @description 用户信息表
 * @Author li
 * @Date 2024/4/9 21:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String userId;

    public static UserDTO getUser(String userId) {
        //模拟获取用户信息
        return new UserDTO("zhangsan",userId);
    }
}
