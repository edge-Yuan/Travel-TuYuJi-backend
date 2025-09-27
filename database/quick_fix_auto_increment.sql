-- 快速修复自增主键问题
USE tour_management_system;

-- 方法1：重置自增主键为当前最大ID + 1
ALTER TABLE product_daily_itinerary AUTO_INCREMENT = (SELECT MAX(itinerary_id) + 1 FROM product_daily_itinerary);

-- 方法2：如果方法1不工作，手动设置一个更大的值
-- ALTER TABLE product_daily_itinerary AUTO_INCREMENT = 20000;

-- 验证修复
SELECT 'Fixed AUTO_INCREMENT value:' as info;
SELECT AUTO_INCREMENT 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'tour_management_system' 
AND TABLE_NAME = 'product_daily_itinerary';
