/*
 Navicat MySQL Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 90100 (9.1.0)
 Source Host           : localhost:3306
 Source Schema         : tour_management_system

 Target Server Type    : MySQL
 Target Server Version : 90100 (9.1.0)
 File Encoding         : 65001

 Date: 28/09/2025 13:41:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for guide_extend
-- ----------------------------
DROP TABLE IF EXISTS `guide_extend`;
CREATE TABLE `guide_extend` (
  `guide_id` bigint NOT NULL AUTO_INCREMENT COMMENT '导游唯一标识',
  `user_id` bigint NOT NULL COMMENT '关联用户表',
  `guide_card` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '导游证号（唯一）',
  `health_cert` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '健康证明路径',
  `no_crime_prove` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '无不良记录证明路径',
  `qualification_status` tinyint NOT NULL DEFAULT '0' COMMENT '资质状态：0-待审核，1-已通过，2-已驳回',
  `service_score` decimal(3,2) NOT NULL DEFAULT '0.00' COMMENT '服务评分（0-5分）',
  `good_at_area` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '擅长目的地',
  `service_lang` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '服务语言',
  `base_fee` decimal(10,2) NOT NULL COMMENT '基础服务费',
  `holiday_fee_rate` decimal(5,2) DEFAULT NULL COMMENT '节假日加价比例',
  `bank_card` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '结算银行卡号',
  `bank_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '开户银行',
  `real_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '手机号',
  `location` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '所在地区',
  `reject_reason` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '驳回原因',
  PRIMARY KEY (`guide_id`),
  UNIQUE KEY `idx_guide_card` (`guide_card`),
  KEY `fk_guide_user` (`user_id`),
  CONSTRAINT `fk_guide_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20003 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='导游扩展信息表';

-- ----------------------------
-- Records of guide_extend
-- ----------------------------
BEGIN;
INSERT INTO `guide_extend` (`guide_id`, `user_id`, `guide_card`, `health_cert`, `no_crime_prove`, `qualification_status`, `service_score`, `good_at_area`, `service_lang`, `base_fee`, `holiday_fee_rate`, `bank_card`, `bank_name`, `real_name`, `phone`, `location`, `reject_reason`) VALUES (20001, 10008, 'GD20230001', '/cert/health/wangwu.jpg', '/cert/nocrime/wangwu.jpg', 1, 4.90, '三亚,海口', '中文,英语,日语', 600.00, 1.50, '6222021234567890123', '工商银行', '王五', '13900139006', '海南省三亚市', NULL);
INSERT INTO `guide_extend` (`guide_id`, `user_id`, `guide_card`, `health_cert`, `no_crime_prove`, `qualification_status`, `service_score`, `good_at_area`, `service_lang`, `base_fee`, `holiday_fee_rate`, `bank_card`, `bank_name`, `real_name`, `phone`, `location`, `reject_reason`) VALUES (20002, 10009, 'GD20230002', '/cert/health/zhaoliu.jpg', '/cert/nocrime/zhaoliu.jpg', 1, 4.70, '北京,天津', '中文,英语', 550.00, 1.30, '6222021234567890124', '建设银行', '赵六', '13900139007', '北京市朝阳区', NULL);
COMMIT;

-- ----------------------------
-- Table structure for guide_match
-- ----------------------------
DROP TABLE IF EXISTS `guide_match`;
CREATE TABLE `guide_match` (
  `match_id` bigint NOT NULL AUTO_INCREMENT COMMENT '匹配需求唯一标识',
  `user_id` bigint NOT NULL COMMENT '发起需求游客',
  `destination` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '目的地',
  `travel_date` date NOT NULL COMMENT '出行日期',
  `travel_days` int NOT NULL COMMENT '行程天数',
  `person_count` int NOT NULL COMMENT '出行人数',
  `budget` decimal(10,2) NOT NULL COMMENT '导游服务预算（元）',
  `service_require` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '特殊需求',
  `match_status` tinyint NOT NULL DEFAULT '0' COMMENT '匹配状态：0-待接单，1-已匹配，2-已取消',
  `guide_id` bigint DEFAULT NULL COMMENT '匹配成功的导游ID',
  `deposit_amount` decimal(10,2) DEFAULT NULL COMMENT '支付定金金额',
  `deposit_time` datetime DEFAULT NULL COMMENT '定金支付时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '需求创建时间',
  PRIMARY KEY (`match_id`),
  KEY `fk_match_user` (`user_id`),
  KEY `fk_match_guide` (`guide_id`),
  CONSTRAINT `fk_match_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`),
  CONSTRAINT `fk_match_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120003 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='导游匹配需求表';

-- ----------------------------
-- Records of guide_match
-- ----------------------------
BEGIN;
INSERT INTO `guide_match` (`match_id`, `user_id`, `destination`, `travel_date`, `travel_days`, `person_count`, `budget`, `service_require`, `match_status`, `guide_id`, `deposit_amount`, `deposit_time`, `create_time`) VALUES (120001, 10003, '上海', '2024-09-01', 2, 2, 1200.00, '需要熟悉迪士尼乐园的导游', 1, 20002, 300.00, '2024-06-20 11:00:00', '2025-09-26 11:28:36');
INSERT INTO `guide_match` (`match_id`, `user_id`, `destination`, `travel_date`, `travel_days`, `person_count`, `budget`, `service_require`, `match_status`, `guide_id`, `deposit_amount`, `deposit_time`, `create_time`) VALUES (120002, 10004, '成都', '2024-08-15', 3, 3, 1800.00, '会说四川话，熟悉美食景点', 0, NULL, NULL, NULL, '2025-09-26 11:28:36');
COMMIT;

-- ----------------------------
-- Table structure for merchant_extend
-- ----------------------------
DROP TABLE IF EXISTS `merchant_extend`;
CREATE TABLE `merchant_extend` (
  `merchant_id` bigint NOT NULL AUTO_INCREMENT COMMENT '旅行商唯一标识',
  `user_id` bigint NOT NULL COMMENT '关联用户表',
  `merchant_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '旅行商名称（唯一）',
  `merchant_type` tinyint NOT NULL COMMENT '类型：1-酒店，2-景区，3-旅行社',
  `license` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '营业执照路径',
  `contact_person` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '联系电话',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`merchant_id`),
  UNIQUE KEY `idx_merchant_name` (`merchant_name`),
  KEY `fk_merchant_user` (`user_id`),
  CONSTRAINT `fk_merchant_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='旅行商扩展信息表';

-- ----------------------------
-- Records of merchant_extend
-- ----------------------------
BEGIN;
INSERT INTO `merchant_extend` (`merchant_id`, `user_id`, `merchant_name`, `merchant_type`, `license`, `contact_person`, `contact_phone`, `address`) VALUES (30001, 10005, '三亚海景度假酒店', 1, '/licenses/hotel1.jpg', '孙经理', '13800138010', '海南省三亚市亚龙湾路128号');
INSERT INTO `merchant_extend` (`merchant_id`, `user_id`, `merchant_name`, `merchant_type`, `license`, `contact_person`, `contact_phone`, `address`) VALUES (30002, 10006, '黄山风景区管理处', 2, '/licenses/scenic1.jpg', '刘主任', '13800138011', '安徽省黄山市黄山区黄山风景区');
INSERT INTO `merchant_extend` (`merchant_id`, `user_id`, `merchant_name`, `merchant_type`, `license`, `contact_person`, `contact_phone`, `address`) VALUES (30003, 10007, '环球国际旅行社', 3, '/licenses/travel1.jpg', '张经理', '13800138012', '北京市朝阳区建国路88号');
COMMIT;

-- ----------------------------
-- Table structure for order_status_log
-- ----------------------------
DROP TABLE IF EXISTS `order_status_log`;
CREATE TABLE `order_status_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `from_status` tinyint DEFAULT NULL COMMENT '变更前状态',
  `to_status` tinyint NOT NULL COMMENT '变更后状态',
  `reason` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '变更原因',
  `operator_id` bigint DEFAULT NULL COMMENT '操作人ID',
  `operator_type` tinyint DEFAULT NULL COMMENT '操作人类型：1-用户，2-导游，3-管理员',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`log_id`),
  KEY `fk_log_order` (`order_id`),
  CONSTRAINT `fk_log_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80001 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='订单状态变更记录表';

-- ----------------------------
-- Records of order_status_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_booking_notice
-- ----------------------------
DROP TABLE IF EXISTS `product_booking_notice`;
CREATE TABLE `product_booking_notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT COMMENT '须知ID',
  `product_id` bigint NOT NULL COMMENT '关联产品',
  `booking_conditions` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '预订条件（如：需提供身份证号）',
  `validity_period` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '有效期（如：购买后30天内有效）',
  `notes` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '其他注意事项',
  PRIMARY KEY (`notice_id`),
  KEY `fk_notice_product` (`product_id`),
  CONSTRAINT `fk_notice_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品预订须知表';

-- ----------------------------
-- Records of product_booking_notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类唯一标识',
  `category_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '分类名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父分类ID',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序序号',
  `creator_id` bigint NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `idx_category_name` (`category_name`),
  KEY `fk_category_parent` (`parent_id`),
  KEY `fk_category_creator` (`creator_id`),
  CONSTRAINT `fk_category_creator` FOREIGN KEY (`creator_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `fk_category_parent` FOREIGN KEY (`parent_id`) REFERENCES `product_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=140008 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品分类表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
BEGIN;
INSERT INTO `product_category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `creator_id`, `create_time`, `update_time`) VALUES (140001, '国内游', NULL, 1, 10001, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `product_category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `creator_id`, `create_time`, `update_time`) VALUES (140002, '国外游', NULL, 2, 10001, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `product_category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `creator_id`, `create_time`, `update_time`) VALUES (140003, '周边游', NULL, 3, 10001, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `product_category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `creator_id`, `create_time`, `update_time`) VALUES (140004, '三亚线路', 140001, 1, 10001, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `product_category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `creator_id`, `create_time`, `update_time`) VALUES (140005, '北京线路', 140001, 2, 10001, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `product_category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `creator_id`, `create_time`, `update_time`) VALUES (140006, '酒店住宿', NULL, 4, 10001, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `product_category` (`category_id`, `category_name`, `parent_id`, `sort_order`, `creator_id`, `create_time`, `update_time`) VALUES (140007, '景区门票', NULL, 5, 10001, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
COMMIT;

-- ----------------------------
-- Table structure for product_cost_explanation
-- ----------------------------
DROP TABLE IF EXISTS `product_cost_explanation`;
CREATE TABLE `product_cost_explanation` (
  `cost_id` bigint NOT NULL AUTO_INCREMENT COMMENT '费用ID',
  `product_id` bigint NOT NULL COMMENT '关联产品',
  `include_items` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '包含费用（如：机票、酒店）',
  `exclude_items` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '不含费用（如：自费项目、景区小交通）',
  `refund_policy` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '退改政策',
  PRIMARY KEY (`cost_id`),
  KEY `fk_cost_product` (`product_id`),
  CONSTRAINT `fk_cost_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品费用说明表';

-- ----------------------------
-- Records of product_cost_explanation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_daily_itinerary
-- ----------------------------
DROP TABLE IF EXISTS `product_daily_itinerary`;
CREATE TABLE `product_daily_itinerary` (
  `itinerary_id` bigint NOT NULL AUTO_INCREMENT COMMENT '行程ID',
  `product_id` bigint NOT NULL COMMENT '关联产品',
  `day_seq` int NOT NULL COMMENT '第几天（1开始）',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '当日标题（如：抵达三亚-海滩漫步）',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '当日详情（含时间安排、景点介绍）',
  `meals` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '餐饮安排（如：含早中晚餐）',
  `traffic` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '交通方式',
  `accommodation` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '住宿安排',
  `time_period` varchar(20) COLLATE utf8mb3_bin DEFAULT '全天' COMMENT '时间段：早上、中午、下午、晚上、全天',
  PRIMARY KEY (`itinerary_id`),
  KEY `fk_daily_product` (`product_id`),
  CONSTRAINT `fk_daily_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`),
  CONSTRAINT `chk_time_period` CHECK ((`time_period` in (_utf8mb3'早上',_utf8mb3'中午',_utf8mb3'下午',_utf8mb3'晚上',_utf8mb3'全天')))
) ENGINE=InnoDB AUTO_INCREMENT=20003 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品固定每日行程表';

-- ----------------------------
-- Records of product_daily_itinerary
-- ----------------------------
BEGIN;
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (10003, 40001, 1, 'title', '111111111', 'aaaaa', '公交车', 'abc', '早上');
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (20000, 40001, 1, '第一天-抵达三亚', '抵达三亚凤凰机场，前往酒店办理入住', '含晚餐', '旅游大巴', '三亚海景度假酒店', '下午');
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (20001, 40001, 1, '第一天-海滩漫步', '下午在海滩漫步，欣赏海景', '含晚餐', '步行', '三亚海景度假酒店', '下午');
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (20002, 40001, 2, '第二天-蜈支洲岛', '前往蜈支洲岛，体验海岛风情', '含早中晚餐', '旅游大巴+轮渡', '三亚海景度假酒店', '早上');
COMMIT;

-- ----------------------------
-- Table structure for product_guide_rel
-- ----------------------------
DROP TABLE IF EXISTS `product_guide_rel`;
CREATE TABLE `product_guide_rel` (
  `rel_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联唯一标识',
  `product_id` bigint NOT NULL COMMENT '关联产品',
  `guide_id` bigint NOT NULL COMMENT '关联导游',
  `coop_fee` decimal(10,2) NOT NULL COMMENT '导游服务费',
  `coop_start_date` date NOT NULL COMMENT '合作开始日期',
  `coop_end_date` date NOT NULL COMMENT '合作结束日期',
  `coop_status` tinyint NOT NULL DEFAULT '0' COMMENT '合作状态：0-待确认，1-已绑定，2-已拒绝，3-已结束',
  `reject_reason` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '拒绝原因',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '合作邀请创建时间',
  PRIMARY KEY (`rel_id`),
  KEY `fk_rel_product` (`product_id`),
  KEY `fk_rel_guide` (`guide_id`),
  CONSTRAINT `fk_rel_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`),
  CONSTRAINT `fk_rel_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品-导游关联表';

-- ----------------------------
-- Records of product_guide_rel
-- ----------------------------
BEGIN;
INSERT INTO `product_guide_rel` (`rel_id`, `product_id`, `guide_id`, `coop_fee`, `coop_start_date`, `coop_end_date`, `coop_status`, `reject_reason`, `create_time`) VALUES (50001, 40004, 20001, 800.00, '2024-06-01', '2024-10-31', 1, NULL, '2025-09-26 11:28:36');
INSERT INTO `product_guide_rel` (`rel_id`, `product_id`, `guide_id`, `coop_fee`, `coop_start_date`, `coop_end_date`, `coop_status`, `reject_reason`, `create_time`) VALUES (50002, 40004, 20002, 750.00, '2024-06-01', '2024-12-31', 1, NULL, '2025-09-26 11:28:36');
INSERT INTO `product_guide_rel` (`rel_id`, `product_id`, `guide_id`, `coop_fee`, `coop_start_date`, `coop_end_date`, `coop_status`, `reject_reason`, `create_time`) VALUES (50003, 40003, 20001, 900.00, '2024-06-01', '2024-10-31', 1, NULL, '2025-09-26 11:28:36');
COMMIT;

-- ----------------------------
-- Table structure for refund_apply
-- ----------------------------
DROP TABLE IF EXISTS `refund_apply`;
CREATE TABLE `refund_apply` (
  `refund_id` bigint NOT NULL AUTO_INCREMENT COMMENT '退款申请唯一标识',
  `order_id` bigint NOT NULL COMMENT '关联订单',
  `user_id` bigint NOT NULL COMMENT '申请游客',
  `refund_amount` decimal(10,2) NOT NULL COMMENT '申请退款金额',
  `refund_reason` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '退款原因',
  `proof_urls` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '证明材料',
  `refund_status` tinyint NOT NULL DEFAULT '0' COMMENT '审核状态：0-待审核，1-已同意，2-已拒绝',
  `auditor_id` bigint DEFAULT NULL COMMENT '审核人',
  `audit_remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '审核备注',
  `apply_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款到账时间',
  PRIMARY KEY (`refund_id`),
  KEY `fk_refund_order` (`order_id`),
  KEY `fk_refund_user` (`user_id`),
  KEY `fk_refund_auditor` (`auditor_id`),
  CONSTRAINT `fk_refund_auditor` FOREIGN KEY (`auditor_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `fk_refund_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`),
  CONSTRAINT `fk_refund_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110002 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='退款申请表';

-- ----------------------------
-- Records of refund_apply
-- ----------------------------
BEGIN;
INSERT INTO `refund_apply` (`refund_id`, `order_id`, `user_id`, `refund_amount`, `refund_reason`, `proof_urls`, `refund_status`, `auditor_id`, `audit_remark`, `apply_time`, `audit_time`, `refund_time`) VALUES (110001, 60004, 10004, 899.00, '突发疾病无法出行', '/images/proof/medical1.jpg', 1, 10002, '同意全额退款', '2025-09-26 11:28:36', '2024-06-18 10:00:00', '2024-06-18 14:30:00');
COMMIT;

-- ----------------------------
-- Table structure for sys_announcement
-- ----------------------------
DROP TABLE IF EXISTS `sys_announcement`;
CREATE TABLE `sys_announcement` (
  `announce_id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告唯一标识',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '公告内容',
  `publisher_id` bigint NOT NULL COMMENT '发布人',
  `start_time` datetime NOT NULL COMMENT '生效时间',
  `end_time` datetime NOT NULL COMMENT '失效时间',
  `announce_status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-未生效，1-展示中，2-已失效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`announce_id`),
  KEY `fk_announcement_publisher` (`publisher_id`),
  CONSTRAINT `fk_announcement_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=130004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='系统公告表';

-- ----------------------------
-- Records of sys_announcement
-- ----------------------------
BEGIN;
INSERT INTO `sys_announcement` (`announce_id`, `title`, `content`, `publisher_id`, `start_time`, `end_time`, `announce_status`, `create_time`) VALUES (130001, '暑期旅游安全提示', '尊敬的用户，暑期出行请注意防暑防晒，提前查询天气情况，祝您旅途愉快！', 10001, '2024-07-01 00:00:00', '2024-08-31 23:59:59', 1, '2025-09-26 11:28:36');
INSERT INTO `sys_announcement` (`announce_id`, `title`, `content`, `publisher_id`, `start_time`, `end_time`, `announce_status`, `create_time`) VALUES (130002, '平台退款政策调整通知', '自2024年7月1日起，退款申请将在3个工作日内处理完毕，感谢您的理解与支持。', 10001, '2024-06-20 00:00:00', '2024-09-30 23:59:59', 1, '2025-09-26 11:28:36');
INSERT INTO `sys_announcement` (`announce_id`, `title`, `content`, `publisher_id`, `start_time`, `end_time`, `announce_status`, `create_time`) VALUES (130003, '春节假期平台安排', '2024年春节期间（2月10日-2月17日）客服工作时间调整为9:00-17:00。', 10001, '2024-01-01 00:00:00', '2024-02-17 23:59:59', 2, '2025-09-26 11:28:36');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '登录账号（唯一）',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '加密存储的密码',
  `real_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号（唯一）',
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '邮箱',
  `user_role` tinyint NOT NULL COMMENT '角色类型：1-游客，2-系统管理员，3-财务管理员，4-旅行商，5-导游',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '账号状态：0-禁用，1-正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册/创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10011 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10001, 'adddmm1', 'e10adc3949ba59abbe56e057f20f883e', '系统管理员', '1380013801', 'admin@tour11.com', 2, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10002, 'finance', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '财务管理员', '13800138001', 'finance@tour.com', 3, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10003, 'tourist01', 'e10adc3949ba59abbe56e057f20f883e', '张三', '13900139001', 'zhangsan@example.com', 1, 1, '2025-09-26 11:28:36', '2025-09-26 13:25:07');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10004, 'tourist02', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '李四', '13900139002', 'lisi@example.com', 1, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10005, 'merchant01', 'e10adc3949ba59abbe56e057f20f883e', '王五', '13900139003', 'hotel@example.com', 4, 1, '2025-09-26 11:28:36', '2025-09-27 13:10:32');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10006, 'merchant_scenic', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '山水景区', '13900139004', 'scenic@example.com', 4, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10007, 'merchant_travel', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '环球旅行社', '13900139005', 'travel@example.com', 4, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10008, 'guide01', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '王五', '13900139006', 'wangwu@example.com', 5, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10009, 'guide02', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '赵六', '13900139007', 'zhaoliu@example.com', 5, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10010, 'testuser', 'e10adc3949ba59abbe56e057f20f883e', '测试用户', '13800138000', 'test@example.com', 1, 1, '2025-09-26 20:14:38', '2025-09-26 20:14:38');
COMMIT;

-- ----------------------------
-- Table structure for tour_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `tour_evaluation`;
CREATE TABLE `tour_evaluation` (
  `eval_id` bigint NOT NULL AUTO_INCREMENT COMMENT '评价唯一标识',
  `order_id` bigint NOT NULL COMMENT '关联订单',
  `eval_type` tinyint NOT NULL COMMENT '评价对象：1-产品，2-导游',
  `target_id` bigint NOT NULL COMMENT '评价目标ID',
  `overall_score` tinyint NOT NULL COMMENT '总体评分（1-5分）',
  `service_score` tinyint DEFAULT NULL COMMENT '服务评分（1-5分）',
  `environment_score` tinyint DEFAULT NULL COMMENT '环境评分（1-5分）',
  `cost_eff_score` tinyint DEFAULT NULL COMMENT '性价比评分（1-5分）',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '评价内容',
  `img_urls` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '评价图片',
  `eval_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评价提交时间',
  `reply_content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`eval_id`),
  KEY `fk_evaluation_order` (`order_id`),
  CONSTRAINT `fk_evaluation_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='评价表';

-- ----------------------------
-- Records of tour_evaluation
-- ----------------------------
BEGIN;
INSERT INTO `tour_evaluation` (`eval_id`, `order_id`, `eval_type`, `target_id`, `overall_score`, `service_score`, `environment_score`, `cost_eff_score`, `content`, `img_urls`, `eval_time`, `reply_content`, `reply_time`) VALUES (80001, 60001, 1, 40003, 5, 5, 5, 4, '行程安排合理，酒店环境很好，非常满意的一次旅行', '/images/eval/eval1.jpg', '2025-09-26 11:28:36', '感谢您的好评，期待再次为您服务', '2024-07-20 10:30:00');
INSERT INTO `tour_evaluation` (`eval_id`, `order_id`, `eval_type`, `target_id`, `overall_score`, `service_score`, `environment_score`, `cost_eff_score`, `content`, `img_urls`, `eval_time`, `reply_content`, `reply_time`) VALUES (80002, 60001, 2, 20001, 5, 5, NULL, NULL, '王导游服务热情，讲解专业，推荐的餐厅也很美味', NULL, '2025-09-26 11:28:36', '感谢您的认可，很高兴为您服务', '2024-07-20 11:15:00');
INSERT INTO `tour_evaluation` (`eval_id`, `order_id`, `eval_type`, `target_id`, `overall_score`, `service_score`, `environment_score`, `cost_eff_score`, `content`, `img_urls`, `eval_time`, `reply_content`, `reply_time`) VALUES (80003, 60003, 1, 40002, 4, 4, 5, 4, '黄山风景壮丽，景区管理有序，就是人有点多', '/images/eval/eval2.jpg', '2025-09-26 11:28:36', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tour_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tour_feedback`;
CREATE TABLE `tour_feedback` (
  `feedback_id` bigint NOT NULL AUTO_INCREMENT COMMENT '反馈唯一标识',
  `user_id` bigint NOT NULL COMMENT '反馈游客',
  `order_id` bigint DEFAULT NULL COMMENT '关联订单',
  `feedback_type` tinyint NOT NULL COMMENT '类型：1-投诉，2-建议',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '反馈内容',
  `img_urls` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '凭证图片',
  `feedback_status` tinyint NOT NULL DEFAULT '0' COMMENT '处理状态：0-已收到，1-处理中，2-已解决，3-已驳回',
  `handler_id` bigint DEFAULT NULL COMMENT '处理人',
  `handle_content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '处理结果',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '反馈提交时间',
  `handle_time` datetime DEFAULT NULL COMMENT '处理完成时间',
  PRIMARY KEY (`feedback_id`),
  KEY `fk_feedback_user` (`user_id`),
  KEY `fk_feedback_order` (`order_id`),
  KEY `fk_feedback_handler` (`handler_id`),
  CONSTRAINT `fk_feedback_handler` FOREIGN KEY (`handler_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `fk_feedback_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`),
  CONSTRAINT `fk_feedback_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90003 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='反馈投诉表';

-- ----------------------------
-- Records of tour_feedback
-- ----------------------------
BEGIN;
INSERT INTO `tour_feedback` (`feedback_id`, `user_id`, `order_id`, `feedback_type`, `content`, `img_urls`, `feedback_status`, `handler_id`, `handle_content`, `create_time`, `handle_time`) VALUES (90001, 10003, 60001, 2, '建议增加更多海上活动项目选择', NULL, 2, 10001, '感谢您的建议，我们已与旅行社沟通，将增加更多活动选项', '2025-09-26 11:28:36', '2024-07-18 15:30:00');
INSERT INTO `tour_feedback` (`feedback_id`, `user_id`, `order_id`, `feedback_type`, `content`, `img_urls`, `feedback_status`, `handler_id`, `handle_content`, `create_time`, `handle_time`) VALUES (90002, 10004, 60004, 1, '预订的房间与图片不符，设施陈旧', '/images/feedback/room1.jpg,/images/feedback/room2.jpg', 2, 10001, '已协调酒店为客人升级房间并赔偿200元优惠券', '2025-09-26 11:28:36', '2024-06-19 11:20:00');
COMMIT;

-- ----------------------------
-- Table structure for tour_itinerary
-- ----------------------------
DROP TABLE IF EXISTS `tour_itinerary`;
CREATE TABLE `tour_itinerary` (
  `itinerary_id` bigint NOT NULL AUTO_INCREMENT COMMENT '行程唯一标识',
  `order_id` bigint NOT NULL COMMENT '关联订单',
  `guide_id` bigint DEFAULT NULL COMMENT '执行导游',
  `itinerary_date` date NOT NULL COMMENT '行程日期',
  `day_seq` int NOT NULL COMMENT '行程天数序号',
  `spots` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '当日景点',
  `start_time` time NOT NULL COMMENT '当日集合时间',
  `end_time` time NOT NULL COMMENT '当日结束时间',
  `traffic` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '交通方式',
  `dining` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '餐饮安排',
  `accommodation` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '住宿安排',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '行程状态：0-未开始，1-进行中，2-已完成',
  `progress_note` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '进度备注',
  `img_urls` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '行程照片',
  PRIMARY KEY (`itinerary_id`),
  KEY `fk_itinerary_order` (`order_id`),
  KEY `fk_itinerary_guide` (`guide_id`),
  CONSTRAINT `fk_itinerary_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`),
  CONSTRAINT `fk_itinerary_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='行程安排表';

-- ----------------------------
-- Records of tour_itinerary
-- ----------------------------
BEGIN;
INSERT INTO `tour_itinerary` (`itinerary_id`, `order_id`, `guide_id`, `itinerary_date`, `day_seq`, `spots`, `start_time`, `end_time`, `traffic`, `dining`, `accommodation`, `status`, `progress_note`, `img_urls`) VALUES (70001, 60001, 20001, '2024-07-15', 1, '三亚凤凰机场、亚龙湾海滩', '10:00:00', '18:00:00', '旅游大巴', '含午餐、晚餐自理', '三亚海景度假酒店', 2, '游客已顺利抵达，入住酒店', '/images/trip/day1_1.jpg,/images/trip/day1_2.jpg');
INSERT INTO `tour_itinerary` (`itinerary_id`, `order_id`, `guide_id`, `itinerary_date`, `day_seq`, `spots`, `start_time`, `end_time`, `traffic`, `dining`, `accommodation`, `status`, `progress_note`, `img_urls`) VALUES (70002, 60001, 20001, '2024-07-16', 2, '蜈支洲岛', '08:30:00', '17:30:00', '旅游大巴+轮渡', '含早中晚餐', '三亚海景度假酒店', 2, '全天海岛游玩，游客满意', '/images/trip/day2_1.jpg');
INSERT INTO `tour_itinerary` (`itinerary_id`, `order_id`, `guide_id`, `itinerary_date`, `day_seq`, `spots`, `start_time`, `end_time`, `traffic`, `dining`, `accommodation`, `status`, `progress_note`, `img_urls`) VALUES (70003, 60002, 20002, '2024-08-01', 1, '天安门广场、故宫', '08:00:00', '17:00:00', '旅游大巴', '含午餐', '北京饭店', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tour_order
-- ----------------------------
DROP TABLE IF EXISTS `tour_order`;
CREATE TABLE `tour_order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单唯一标识',
  `user_id` bigint NOT NULL COMMENT '下单游客',
  `product_id` bigint NOT NULL COMMENT '购买产品',
  `guide_id` bigint DEFAULT NULL COMMENT '绑定导游',
  `order_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `pay_type` tinyint DEFAULT NULL COMMENT '支付方式：1-微信，2-支付宝',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态：0-待支付，1-已支付，2-已退款',
  `order_status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态：0-待确认，1-已确认，2-已完成，3-已取消，4-退款中',
  `booking_date` date NOT NULL COMMENT '预订使用日期',
  `person_count` int NOT NULL COMMENT '出行人数',
  `special_needs` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '特殊需求',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `refund_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  PRIMARY KEY (`order_id`),
  KEY `fk_order_user` (`user_id`),
  KEY `fk_order_product` (`product_id`),
  KEY `fk_order_guide` (`guide_id`),
  CONSTRAINT `fk_order_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`),
  CONSTRAINT `fk_order_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60005 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='旅游订单表';

-- ----------------------------
-- Records of tour_order
-- ----------------------------
BEGIN;
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `order_amount`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`) VALUES (60001, 10003, 40003, 20001, 3699.00, 1, 1, 2, '2024-07-15', 1, '需要安排无烟房', '2025-09-26 11:28:36', '2024-06-10 09:30:00', NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `order_amount`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`) VALUES (60002, 10004, 40004, 20002, 5798.00, 2, 1, 1, '2024-08-01', 2, '希望安排资深导游', '2025-09-26 11:28:36', '2024-06-15 14:20:00', NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `order_amount`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`) VALUES (60003, 10003, 40002, NULL, 380.00, 1, 1, 2, '2024-07-05', 2, '无特殊需求', '2025-09-26 11:28:36', '2024-06-05 10:15:00', NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `order_amount`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`) VALUES (60004, 10004, 40001, NULL, 899.00, 1, 1, 3, '2024-07-20', 1, '需要高层房间', '2025-09-26 11:28:36', '2024-06-12 16:45:00', '2024-06-18 09:20:00', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tour_product
-- ----------------------------
DROP TABLE IF EXISTS `tour_product`;
CREATE TABLE `tour_product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT COMMENT '产品唯一标识',
  `merchant_id` bigint NOT NULL COMMENT '所属旅行商',
  `product_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品名称',
  `product_type` tinyint NOT NULL COMMENT '类型：1-旅行路线，2-酒店客房，3-景区门票',
  `price` decimal(10,2) NOT NULL COMMENT '售价',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `stock` int NOT NULL COMMENT '库存',
  `start_date` date NOT NULL COMMENT '产品生效日期',
  `end_date` date NOT NULL COMMENT '产品失效日期',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '产品详情描述',
  `img_urls` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '产品图片URL',
  `audit_admin_id` bigint DEFAULT NULL COMMENT '审核管理员ID',
  `audit_status` tinyint NOT NULL DEFAULT '0' COMMENT '审核状态：0-待审核，1-已通过，2-已驳回',
  `product_status` tinyint NOT NULL DEFAULT '0' COMMENT '上架状态：0-下架，1-上架',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `product_tags` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '产品标签（逗号分隔，如：超值,豪华）',
  `sold_count` int NOT NULL DEFAULT '0' COMMENT '已售数量',
  `service_guarantees` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '服务保障（逗号分隔，如：退改无忧,安全可靠）',
  `main_img_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '主图URL（优先展示）',
  `product_selling_points` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '核心卖点（富文本）',
  `category_id` bigint DEFAULT NULL COMMENT '所属分类ID（关联product_category）',
  `supplier` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '供应商',
  `features` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '产品特色',
  `features_imgs` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '产品特色图片',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_merchant` (`merchant_id`),
  KEY `fk_product_admin` (`audit_admin_id`),
  KEY `fk_product_category` (`category_id`),
  CONSTRAINT `fk_product_admin` FOREIGN KEY (`audit_admin_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`category_id`),
  CONSTRAINT `fk_product_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant_extend` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40006 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='旅游产品表';

-- ----------------------------
-- Records of tour_product
-- ----------------------------
BEGIN;
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`) VALUES (40001, 30001, '三亚海景大床房', 2, 899.00, 1099.00, 20, '2024-06-01', '2024-12-31', '豪华海景大床房，含双早，免费WiFi，游泳池使用权', '/images/hotel/sanya1.jpg,/images/hotel/sanya2.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-09-28 12:45:13', '超值，豪华，不错', 400, '退改无忧，安全可靠', NULL, '1111111', 140006, NULL, 'aaaaaaaaaaaaaaaa', '111111111');
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`) VALUES (40002, 30002, '黄山风景区门票', 3, 190.00, 190.00, 1000, '2024-06-01', '2024-12-31', '黄山风景区成人票，含景区交通', '/images/scenic/huangshan1.jpg,/images/scenic/huangshan2.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36', NULL, 0, NULL, NULL, NULL, 140007, NULL, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`) VALUES (40003, 30003, '三亚5日4晚自由行', 1, 3699.00, 4299.00, 50, '2024-06-01', '2024-10-31', '含往返机票+四星酒店+接送机，自由活动', '/images/tour/sanya3.jpg,/images/tour/sanya4.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36', NULL, 0, NULL, NULL, NULL, 140004, NULL, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`) VALUES (40004, 30003, '北京4日3晚跟团游', 1, 2899.00, 3299.00, 40, '2024-06-01', '2024-12-31', '含故宫、长城、颐和园等景点，导游全程陪同', '/images/tour/beijing1.jpg,/images/tour/beijing2.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36', NULL, 0, NULL, NULL, NULL, 140005, NULL, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`) VALUES (40005, 30003, '肇庆3日游', 1, 2300.00, 3400.00, 30, '2025-07-05', '2025-04-02', '1111111111111111111111111111111111111', '/images/tour/beijing1.jpg,/images/tour/beijing2.jpg', 10001, 1, 1, '2025-09-26 20:35:27', '2025-09-26 20:36:10', '很好，不错，喜欢', 0, 'abcd，bbbb', NULL, NULL, 140004, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection` (
  `collect_id` bigint NOT NULL AUTO_INCREMENT COMMENT '收藏唯一标识',
  `user_id` bigint NOT NULL COMMENT '收藏游客',
  `product_id` bigint NOT NULL COMMENT '收藏产品',
  `collect_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`collect_id`),
  UNIQUE KEY `idx_user_product` (`user_id`,`product_id`),
  KEY `fk_collection_product` (`product_id`),
  CONSTRAINT `fk_collection_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`),
  CONSTRAINT `fk_collection_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100005 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='用户收藏表';

-- ----------------------------
-- Records of user_collection
-- ----------------------------
BEGIN;
INSERT INTO `user_collection` (`collect_id`, `user_id`, `product_id`, `collect_time`) VALUES (100001, 10003, 40004, '2024-06-01 10:00:00');
INSERT INTO `user_collection` (`collect_id`, `user_id`, `product_id`, `collect_time`) VALUES (100002, 10003, 40002, '2024-06-02 15:30:00');
INSERT INTO `user_collection` (`collect_id`, `user_id`, `product_id`, `collect_time`) VALUES (100003, 10004, 40003, '2024-06-03 09:45:00');
INSERT INTO `user_collection` (`collect_id`, `user_id`, `product_id`, `collect_time`) VALUES (100004, 10004, 40001, '2024-06-05 16:20:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
