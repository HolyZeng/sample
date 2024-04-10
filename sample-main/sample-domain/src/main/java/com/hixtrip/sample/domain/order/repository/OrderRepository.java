package com.hixtrip.sample.domain.order.repository;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;

/**
 *
 */
public interface OrderRepository {
    int createOrder(Order order);

    void orderPaySuccess(CommandPay commandPay);

    void orderPayFail(CommandPay commandPay);
}
