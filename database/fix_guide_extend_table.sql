-- 为 guide_extend 表添加缺失的字段

ALTER TABLE `guide_extend` 
ADD COLUMN `real_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
ADD COLUMN `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
ADD COLUMN `location` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '所在地区',
ADD COLUMN `reject_reason` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '驳回原因';

-- 更新现有数据
UPDATE `guide_extend` SET 
    `real_name` = '王五',
    `phone` = '13900139006',
    `location` = '海南省三亚市'
WHERE `guide_id` = 20001;

UPDATE `guide_extend` SET 
    `real_name` = '赵六',
    `phone` = '13900139007',
    `location` = '北京市朝阳区'
WHERE `guide_id` = 20002;
