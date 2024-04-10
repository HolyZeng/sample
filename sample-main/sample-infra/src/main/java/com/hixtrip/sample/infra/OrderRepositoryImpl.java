package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.inventory.repository.InventoryRepository;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import com.hixtrip.sample.infra.db.convertor.OrderDOConvertor;
import com.hixtrip.sample.infra.db.dataobject.OrderDO;
import com.hixtrip.sample.infra.db.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderRepositoryImpl
 * @Package com.hixtrip.sample.infra
 * @description
 * @Author li
 * @Date 2024/4/9 16:12
 * @Version 1.0
 */
@Component
public class OrderRepositoryImpl implements OrderRepository {


    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int createOrder(Order order) {
        return orderMapper.insert(OrderDOConvertor.INSTANCE.orderToOrderDO(order));
    }

    @Override
    public void orderPaySuccess(CommandPay commandPay) {
    }

    @Override
    public void orderPayFail(CommandPay commandPay) {
        //支付失败，恢复库存
        OrderDO orderDO = orderMapper.selectById(commandPay.getOrderId());
        Integer sellableQuality = inventoryRepository.getInventory("sellable_" + orderDO.getSkuId());
        inventoryRepository.changeInventory("sellable_" + orderDO.getSkuId(),sellableQuality+orderDO.getAmount());
        Integer withholdingQuality = inventoryRepository.getInventory("sellable_" + orderDO.getSkuId());
        inventoryRepository.changeInventory("withholding_" + orderDO.getSkuId(),withholdingQuality+orderDO.getAmount());
    }
}
