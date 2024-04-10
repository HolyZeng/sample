package com.hixtrip.sample.domain.inventory.repository;

/**
 *
 */
public interface InventoryRepository {


    Integer getInventory(String skuId);

    void changeInventory(String skuId, Integer quantity);
}
