-- 为product_daily_itinerary表添加time_period字段
-- 执行时间：2025-01-XX

USE tour_management_system;

-- 添加time_period字段
ALTER TABLE `product_daily_itinerary` 
ADD COLUMN `time_period` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '时间段（如：早上、中午、下午、晚上）' 
AFTER `accommodation`;

-- 为现有数据设置默认时间段（可选）
-- 根据day_seq设置默认时间段
UPDATE `product_daily_itinerary` 
SET `time_period` = CASE 
    WHEN `day_seq` % 4 = 1 THEN '早上'
    WHEN `day_seq` % 4 = 2 THEN '中午' 
    WHEN `day_seq` % 4 = 3 THEN '下午'
    WHEN `day_seq` % 4 = 0 THEN '晚上'
    ELSE '早上'
END
WHERE `time_period` IS NULL;

-- 添加索引以提高查询性能（可选）
CREATE INDEX `idx_time_period` ON `product_daily_itinerary` (`time_period`);

-- 验证字段添加成功
DESCRIBE `product_daily_itinerary`;
