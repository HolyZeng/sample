#todo 你的建表语句,包含索引
-- 创建订单表
CREATE TABLE `order` (
                          `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                          `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                          `user_id` VARCHAR(32) NOT NULL COMMENT '购买人',
                          `seller_id` VARCHAR(32) NOT NULL COMMENT '卖家',
                          `sku_id` VARCHAR(32) NOT NULL COMMENT 'SkuId',
                          `amount` BIGINT(20) NOT NULL COMMENT '数量',
                          `money` DECIMAL(10,2) UNSIGNED NOT NULL COMMENT '总金额',
                          `pay_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '购买时间',
                          `pay_status` VARCHER(2) NOT NULL DEFAULT '01' COMMENT '购买状态',
                          `pay_status` BIGINT(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
                          `create_by` VARCHAR(32) NOT NULL DEFAULT '0' COMMENT '创建人',
                          `create_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                          `update_by` VARCHAR(32) NOT NULL DEFAULT '0' COMMENT '修改人',
                          `update_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uniq_order_no` (`order_no`),
                          KEY `idx_buyer_id` (`user_id`),
                          KEY `idx_seller_id_create_time` (`seller_id`, `create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表'
PARTITION BY RANGE (YEAR(create_time)) (
 PARTITION p_2023 VALUES LESS THAN (2024),
 PARTITION p_2024 VALUES LESS THAN (2025),
 PARTITION p_2025 VALUES LESS THAN (2026),
 PARTITION p_max VALUES LESS THAN MAXVALUE
);

-- 分库分表设计思路:
--    订单表按年份进行分区，每年一个分区，可以快速过期老数据，提高查询效率。
--    既然同时满足买卖双方查询需求方案,我们可以考虑使用双写的方式,将订单数据同时写入两套分库分表系统。
--    一套系统使用user_id作为分库键,order_no作为分表键,针对买家查询场景进行优化。
--    另一套系统使用seller_id作为分库键,create_time作为分表键,针对卖家查询场景进行优化。
--    通过双写的方式,可以同时满足买卖双方的查询需求,但代价是写入开销增加一倍,存储开销也增加一倍。在海量数据场景下,这可能会带来较大的成本和运维压力。
--    如果双写方案代价过高,也可以考虑在应用层通过缓存或预先计算的方式,来加速买家查询或卖家查询,从而避免直接在数据库层面进行优化。


