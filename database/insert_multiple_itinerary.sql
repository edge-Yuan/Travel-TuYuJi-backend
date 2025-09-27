-- 为同一个产品插入多个行程数据的解决方案
USE tour_management_system;

-- 1. 首先检查当前表状态
SELECT 'Current table status:' as info;
SELECT COUNT(*) as total_records FROM product_daily_itinerary;
SELECT MAX(itinerary_id) as max_id FROM product_daily_itinerary;

-- 2. 检查自增主键当前值
SELECT 'Current AUTO_INCREMENT:' as info;
SELECT AUTO_INCREMENT 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'tour_management_system' 
AND TABLE_NAME = 'product_daily_itinerary';

-- 3. 修复自增主键（如果存在问题）
-- 方法1：直接设置一个较大的值
ALTER TABLE product_daily_itinerary AUTO_INCREMENT = 20000;

-- 4. 验证修复结果
SELECT 'After fix - AUTO_INCREMENT:' as info;
SELECT AUTO_INCREMENT 
FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'tour_management_system' 
AND TABLE_NAME = 'product_daily_itinerary';

-- 5. 测试插入多个行程数据
-- 为产品ID 40001 插入多个行程
INSERT INTO product_daily_itinerary (product_id, day_seq, title, description, meals, traffic, accommodation, time_period) 
VALUES 
(40001, 1, '第一天-抵达三亚', '抵达三亚凤凰机场，前往酒店办理入住', '含晚餐', '旅游大巴', '三亚海景度假酒店', '下午'),
(40001, 1, '第一天-海滩漫步', '下午在海滩漫步，欣赏海景', '含晚餐', '步行', '三亚海景度假酒店', '下午'),
(40001, 2, '第二天-蜈支洲岛', '前往蜈支洲岛，体验海岛风情', '含早中晚餐', '旅游大巴+轮渡', '三亚海景度假酒店', '早上'),
(40001, 2, '第二天-海上活动', '参与海上娱乐项目', '含早中晚餐', '轮渡', '三亚海景度假酒店', '下午'),
(40001, 3, '第三天-亚龙湾', '游览亚龙湾热带天堂森林公园', '含早中餐', '旅游大巴', '三亚海景度假酒店', '早上'),
(40001, 3, '第三天-自由活动', '自由活动时间，可购物或休息', '含早中餐', '步行', '三亚海景度假酒店', '下午');

-- 6. 验证插入结果
SELECT 'Inserted records:' as info;
SELECT * FROM product_daily_itinerary WHERE product_id = 40001 ORDER BY day_seq, time_period;

-- 7. 查看所有记录
SELECT 'All records in table:' as info;
SELECT * FROM product_daily_itinerary ORDER BY product_id, day_seq;
