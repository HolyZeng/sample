package com.hixtrip.sample.app.strategy;

import com.hixtrip.sample.app.convertor.OrderConvertor;
import com.hixtrip.sample.app.result.ResultVO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PayCallbackStrategy
 * @Package com.hixtrip.sample.app
 * @description 回调策略
 * @Author li
 * @Date 2024/4/9 21:34
 * @Version 1.0
 */
public class PayCallback {
    private static PayCallback instance = new PayCallback();

    private Map<String,PayCallbackStrategy> map;

    private PayCallback(){
        map = new HashMap<>(){{
            put(PayStatusEnum.SUCCESS.getStatus(),new SuccessPayCallbackStrategy());
            put(PayStatusEnum.FAIL.getStatus(), new FailPayCallbackStrategy());
            put(PayStatusEnum.REPEAT.getStatus(), new RepeatPayCallbackStrategy());
        }};
    }

    public static PayCallback getInstance(){
        return instance;
    }

    public ResultVO hard(CommandPayDTO commandPayDTO){
        //获取实列
        PayCallbackStrategy payCallbackStrategy = map.get(commandPayDTO.getPayStatus());
        CommandPay commandPay = OrderConvertor.INSTANCE.CommandPayDTOToCommandPay(commandPayDTO);
        return payCallbackStrategy.hard(commandPay);
    }
}
