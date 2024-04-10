package com.hixtrip.sample.app.strategy;

import com.hixtrip.sample.app.result.ResultVO;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName PayCallbackStrategy
 * @Package com.hixtrip.sample.app
 * @description 回调策略
 * @Author li
 * @Date 2024/4/9 21:34
 * @Version 1.0
 */
public interface PayCallbackStrategy {

    ResultVO hard(CommandPay commandPay);
}
