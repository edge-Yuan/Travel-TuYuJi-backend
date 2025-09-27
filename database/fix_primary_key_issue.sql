-- 修复product_daily_itinerary表主键冲突问题
-- 执行时间：2025-01-XX

USE tour_management_system;

-- 1. 查看当前表结构和数据
SELECT 'Current table structure:' as info;
DESCRIBE product_daily_itinerary;

SELECT 'Current data count:' as info;
SELECT COUNT(*) as total_records FROM product_daily_itinerary;

SELECT 'Current max itinerary_id:' as info;
SELECT MAX(itinerary_id) as max_id FROM product_daily_itinerary;

-- 2. 查看自增主键的当前值
SELECT 'Current AUTO_INCREMENT value:' as info;
SELECT AUTO_INCREMENT 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'tour_management_system' 
AND TABLE_NAME = 'product_daily_itinerary';

-- 3. 重置自增主键的当前值（设置为最大ID + 1）
SET @max_id = (SELECT MAX(itinerary_id) FROM product_daily_itinerary);
SET @sql = CONCAT('ALTER TABLE product_daily_itinerary AUTO_INCREMENT = ', @max_id + 1);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 4. 验证修复结果
SELECT 'After fix - AUTO_INCREMENT value:' as info;
SELECT AUTO_INCREMENT 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'tour_management_system' 
AND TABLE_NAME = 'product_daily_itinerary';

-- 5. 测试插入新记录（可选）
-- INSERT INTO product_daily_itinerary (product_id, day_seq, title, description, time_period) 
-- VALUES (40001, 1, '测试行程', '测试描述', '下午');

-- 6. 如果仍有问题，可以删除冲突的记录（谨慎操作）
-- DELETE FROM product_daily_itinerary WHERE itinerary_id = 10003;

-- 7. 查看所有记录
SELECT 'All records in table:' as info;
SELECT * FROM product_daily_itinerary ORDER BY itinerary_id;
