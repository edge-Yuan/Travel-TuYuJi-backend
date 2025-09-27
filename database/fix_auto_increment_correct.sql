-- 修复自增主键问题的正确SQL语法
USE tour_management_system;

-- 方法1：分步执行（推荐）
-- 第一步：查看当前最大ID
SELECT MAX(itinerary_id) as max_id FROM product_daily_itinerary;

-- 第二步：手动设置自增值（将下面的数字替换为上面查询结果+1）
-- 例如：如果最大ID是10003，则设置为10004
ALTER TABLE product_daily_itinerary AUTO_INCREMENT = 10004;

-- 方法2：使用存储过程（一次性解决）
DELIMITER $$
CREATE PROCEDURE FixAutoIncrement()
BEGIN
    DECLARE max_id INT DEFAULT 0;
    SELECT MAX(itinerary_id) INTO max_id FROM product_daily_itinerary;
    SET @sql = CONCAT('ALTER TABLE product_daily_itinerary AUTO_INCREMENT = ', max_id + 1);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$
DELIMITER ;

-- 执行存储过程
CALL FixAutoIncrement();

-- 删除存储过程（可选）
DROP PROCEDURE IF EXISTS FixAutoIncrement;

-- 验证修复结果
SELECT 'Current AUTO_INCREMENT value:' as info;
SELECT AUTO_INCREMENT 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'tour_management_system' 
AND TABLE_NAME = 'product_daily_itinerary';

-- 测试插入
INSERT INTO product_daily_itinerary (product_id, day_seq, title, description, time_period) 
VALUES (40001, 1, '测试行程', '测试描述', '下午');
