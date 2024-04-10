package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Integer getInventory(String skuId) {
        Integer num = (Integer) redisTemplate.opsForValue().get(skuId);
        return Optional.ofNullable(num).orElse(0);
    }

    @Override
    public void changeInventory(String skuId, Integer quantity) {
        redisTemplate.opsForValue().set(skuId,quantity);
    }
}
