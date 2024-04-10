package com.hixtrip.sample.app.strategy;

import com.hixtrip.sample.app.result.ResultVO;
import com.hixtrip.sample.domain.pay.model.CommandPay;

/**
 * @ClassName PayCallbackStrategy
 * @Package com.hixtrip.sample.app
 * @description 回调策略
 * @Author li
 * @Date 2024/4/9 21:34
 * @Version 1.0
 */
public class RepeatPayCallbackStrategy implements PayCallbackStrategy{
    @Override
    public ResultVO hard(CommandPay commandPay) {
        return ResultVO.FAIL("repeat pay");
    }
}
