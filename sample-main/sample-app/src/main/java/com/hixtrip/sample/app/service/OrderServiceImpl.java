package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.convertor.OrderConvertor;
import com.hixtrip.sample.app.result.ResultVO;
import com.hixtrip.sample.app.strategy.FailPayCallbackStrategy;
import com.hixtrip.sample.app.strategy.PayCallback;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.client.order.dto.UserDTO;
import com.hixtrip.sample.domain.inventory.InventoryDomainService;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

/**
 * app层负责处理request请求，调用领域服务
 */
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private OrderDomainService orderDomainService;

    @Autowired
    private InventoryDomainService inventoryDomainService;

    @Override
    public ResultVO order(CommandOderCreateDTO commandOderCreateDTO, UserDTO userDTO) {

        //开启redis事务
        redisTemplate.multi();
        try {
            //判断库存是否充足，库存不足直接返回
            String sellableQuantityKey = "sellable_"+commandOderCreateDTO.getSkuId();
            Integer sellableQuantity = inventoryDomainService.getInventory(sellableQuantityKey);
            if (sellableQuantity < commandOderCreateDTO.getAmount()){
                return ResultVO.FAIL("Insufficient inventory");
            }

            //获取预占库存
            String withholdingQuantityKey = "sellable_"+commandOderCreateDTO.getSkuId();
            Integer withholdingQuantity = inventoryDomainService.getInventory(withholdingQuantityKey);

            //修改库存(提交订单时先减少库存，支付失败后恢复库存)
            inventoryDomainService.changeInventory(commandOderCreateDTO.getSkuId(),sellableQuantity,withholdingQuantity,commandOderCreateDTO.getAmount());

            redisTemplate.exec();
        }catch (Exception e){
            redisTemplate.discard();
            return ResultVO.FAIL("create order fail");
        }

        //插入订单
        Order order = OrderConvertor.INSTANCE.commandOderCreateDTOToOrder(commandOderCreateDTO,userDTO);
        if (orderDomainService.createOrder(order)>0){
            return ResultVO.SUCCESS("create order success");
        };

        return ResultVO.FAIL("create order fail");
    }

    @Override
    public ResultVO payCallback(CommandPayDTO commandPayDTO) {


        return PayCallback.getInstance().hard(commandPayDTO);
    }
}
