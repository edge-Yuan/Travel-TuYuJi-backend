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

 Date: 07/10/2025 22:31:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `author` varchar(100) NOT NULL COMMENT '作者',
  `category` varchar(50) NOT NULL COMMENT '文章分类：hotel-酒店推荐，attraction-景点介绍，food-美食攻略，guide-旅游攻略',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  `summary` text COMMENT '文章摘要',
  `content` longtext NOT NULL COMMENT '文章内容（支持Markdown）',
  `tags` json DEFAULT NULL COMMENT '文章标签（JSON数组）',
  `region` varchar(50) DEFAULT NULL COMMENT '地区',
  `featured` tinyint(1) DEFAULT '0' COMMENT '是否精选：0-否，1-是',
  `status` varchar(20) DEFAULT 'draft' COMMENT '发布状态：draft-草稿，published-已发布，pending-待审核',
  `views` int DEFAULT '0' COMMENT '浏览次数',
  `likes` int DEFAULT '0' COMMENT '点赞数',
  `comments` int DEFAULT '0' COMMENT '评论数',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `seo_title` varchar(200) DEFAULT NULL COMMENT 'SEO标题',
  `seo_keywords` varchar(500) DEFAULT NULL COMMENT 'SEO关键词',
  `seo_description` varchar(500) DEFAULT NULL COMMENT 'SEO描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_status` (`status`),
  KEY `idx_region` (`region`),
  KEY `idx_featured` (`featured`),
  KEY `idx_publish_time` (`publish_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章主表';

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (1, '三亚最值得入住的5家海景酒店', '张编辑', 'hotel', '/src/assets/images/travel.jpg', '精选三亚最受欢迎的海景酒店，为您推荐性价比最高的住宿选择。', '# 三亚最值得入住的5家海景酒店\n\n三亚作为中国最著名的海滨度假城市，拥有众多优质的海景酒店。本文将为您推荐5家最值得入住的海景酒店，让您的三亚之旅更加完美。\n\n## 1. 三亚亚龙湾瑞吉度假酒店\n\n### 酒店特色\n- 坐拥亚龙湾一线海景\n- 私人海滩和游艇码头\n- 管家式服务\n\n### 客房设施\n- 海景套房面积宽敞\n- 私人阳台俯瞰海景\n- 高端洗浴用品\n\n## 2. 三亚海棠湾天房洲际度假酒店\n\n### 酒店亮点\n- 海棠湾核心位置\n- 海洋主题设计\n- 丰富的亲子设施\n\n### 推荐理由\n- 距离免税店仅5分钟车程\n- 拥有海底餐厅\n- 儿童俱乐部设施完善\n\n## 预订建议\n\n建议提前1-2个月预订，特别是节假日期间。可以通过官网、携程、飞猪等平台比较价格。', '[\"三亚\", \"酒店\", \"海景\", \"度假\"]', '华南', 1, 'published', 1234, 89, 23, '2024-01-15 10:30:00', '三亚海景酒店推荐', '三亚,酒店,海景,度假', '精选三亚最受欢迎的海景酒店推荐', '2025-10-07 13:34:56', '2025-10-07 13:34:56');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (2, '北京故宫深度游攻略', '李编辑', 'attraction', '/src/assets/images/travel2.jpg', '详细介绍故宫的游览路线、必看景点和参观技巧。', '# 北京故宫深度游攻略\n\n故宫，又称紫禁城，是明清两代的皇家宫殿，也是世界文化遗产。本文将为您提供详细的故宫游览攻略。\n\n## 基本信息\n\n### 开放时间\n- 旺季（4月1日-10月31日）：8:30-17:00\n- 淡季（11月1日-次年3月31日）：8:30-16:30\n- 周一闭馆（法定节假日除外）\n\n### 门票价格\n- 成人票：60元\n- 学生票：30元\n- 老人票：30元\n\n## 游览路线推荐\n\n### 经典路线（2-3小时）\n1. 午门 → 太和殿 → 中和殿 → 保和殿\n2. 乾清宫 → 交泰殿 → 坤宁宫\n3. 御花园 → 神武门\n\n### 深度游路线（4-6小时）\n1. 午门 → 武英殿 → 文华殿\n2. 太和殿 → 中和殿 → 保和殿\n3. 乾清宫 → 交泰殿 → 坤宁宫\n4. 东六宫 → 西六宫\n5. 御花园 → 神武门\n\n## 必看景点\n\n### 太和殿\n- 故宫最大的宫殿\n- 皇帝举行大典的地方\n- 建筑气势恢宏\n\n### 乾清宫\n- 皇帝居住的宫殿\n- 内部陈设精美\n- 历史意义重大\n\n## 游览贴士\n\n1. **提前购票**：建议提前在网上购票，避免现场排队\n2. **避开高峰**：上午9-11点和下午2-4点是游览高峰\n3. **穿着舒适**：故宫面积很大，建议穿舒适的鞋子\n4. **携带物品**：可以带水和小食品，但不要带太多\n5. **拍照注意**：部分区域禁止拍照，请注意标识\n\n## 交通指南\n\n### 地铁\n- 1号线：天安门东站或天安门西站\n- 2号线：前门站\n\n### 公交\n- 1路、2路、52路等多路公交可达\n\n### 自驾\n- 故宫不提供停车位，建议乘坐公共交通', '[\"北京\", \"故宫\", \"历史\", \"文化\"]', '华北', 1, 'published', 2567, 156, 45, '2024-01-10 14:20:00', '故宫旅游攻略', '北京,故宫,旅游,攻略', '北京故宫深度游攻略，详细介绍游览路线和必看景点', '2025-10-07 13:34:56', '2025-10-07 13:34:56');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (3, '上海美食地图：必吃清单', '王编辑', 'food', '/src/assets/images/travel3.jpg', '上海本地人推荐的美食清单，从街头小吃到米其林餐厅。', '# 上海美食地图：必吃清单\n\n上海作为国际大都市，汇聚了世界各地的美食。本文将为您推荐上海本地人最爱的美食清单。\n\n## 本帮菜代表\n\n### 老正兴菜馆\n- **招牌菜**：白切鸡、红烧肉、糖醋小排\n- **人均消费**：150-200元\n- **推荐理由**：百年老字号，正宗本帮菜\n\n### 德兴馆\n- **招牌菜**：生煎包、小笼包、蟹粉小笼\n- **人均消费**：80-120元\n- **推荐理由**：传统点心，味道正宗\n\n## 街头小吃\n\n### 南翔小笼包\n- **位置**：城隍庙\n- **特色**：皮薄汁多，鲜香可口\n- **价格**：15-25元/笼\n\n### 生煎包\n- **推荐店铺**：大壶春、丰裕生煎\n- **特色**：底部金黄酥脆，顶部松软\n- **价格**：8-12元/4个\n\n## 网红餐厅\n\n### 外滩18号\n- **菜系**：融合菜\n- **人均消费**：300-500元\n- **推荐理由**：外滩景观，环境优雅\n\n### 新天地\n- **菜系**：各国料理\n- **人均消费**：200-400元\n- **推荐理由**：时尚氛围，选择多样\n\n## 用餐贴士\n\n1. **预订**：热门餐厅建议提前预订\n2. **时间**：避开用餐高峰（12:00-13:00，18:00-19:00）\n3. **支付**：大部分餐厅支持移动支付\n4. **小费**：一般不需要小费，但服务费可能包含在账单中\n\n## 美食街区推荐\n\n### 田子坊\n- 文艺小资，各种创意餐厅\n- 适合拍照和休闲\n\n### 新天地\n- 国际化程度高，各国美食\n- 夜生活丰富\n\n### 城隍庙\n- 传统小吃集中地\n- 体验老上海风情', '[\"上海\", \"美食\", \"小吃\", \"餐厅\"]', '华东', 0, 'draft', 1890, 234, 67, NULL, '上海美食推荐', '上海,美食,小吃,餐厅', '上海美食地图，本地人推荐必吃清单', '2025-10-07 13:34:56', '2025-10-07 13:34:56');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (4, '三亚最值得入住的5家海景酒店', '张编辑', 'hotel', '/src/assets/images/travel.jpg', '精选三亚最受欢迎的海景酒店，为您推荐性价比最高的住宿选择。', '# 三亚最值得入住的5家海景酒店\n\n三亚作为中国最著名的海滨度假城市，拥有众多优质的海景酒店。本文将为您推荐5家最值得入住的海景酒店，让您的三亚之旅更加完美。\n\n## 1. 三亚亚龙湾瑞吉度假酒店\n\n### 酒店特色\n- 坐拥亚龙湾一线海景\n- 私人海滩和游艇码头\n- 管家式服务\n\n### 客房设施\n- 海景套房面积宽敞\n- 私人阳台俯瞰海景\n- 高端洗浴用品\n\n## 2. 三亚海棠湾天房洲际度假酒店\n\n### 酒店亮点\n- 海棠湾核心位置\n- 海洋主题设计\n- 丰富的亲子设施\n\n### 推荐理由\n- 距离免税店仅5分钟车程\n- 拥有海底餐厅\n- 儿童俱乐部设施完善\n\n## 预订建议\n\n建议提前1-2个月预订，特别是节假日期间。可以通过官网、携程、飞猪等平台比较价格。', '[\"三亚\", \"酒店\", \"海景\", \"度假\"]', '华南', 1, 'published', 1234, 89, 23, '2024-01-15 10:30:00', '三亚海景酒店推荐', '三亚,酒店,海景,度假', '精选三亚最受欢迎的海景酒店推荐', '2025-10-07 13:38:15', '2025-10-07 13:38:15');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (5, '北京故宫深度游攻略', '李编辑', 'attraction', '/src/assets/images/travel2.jpg', '详细介绍故宫的游览路线、必看景点和参观技巧。', '# 北京故宫深度游攻略\n\n故宫，又称紫禁城，是明清两代的皇家宫殿，也是世界文化遗产。本文将为您提供详细的故宫游览攻略。\n\n## 基本信息\n\n### 开放时间\n- 旺季（4月1日-10月31日）：8:30-17:00\n- 淡季（11月1日-次年3月31日）：8:30-16:30\n- 周一闭馆（法定节假日除外）\n\n### 门票价格\n- 成人票：60元\n- 学生票：30元\n- 老人票：30元\n\n## 游览路线推荐\n\n### 经典路线（2-3小时）\n1. 午门 → 太和殿 → 中和殿 → 保和殿\n2. 乾清宫 → 交泰殿 → 坤宁宫\n3. 御花园 → 神武门\n\n### 深度游路线（4-6小时）\n1. 午门 → 武英殿 → 文华殿\n2. 太和殿 → 中和殿 → 保和殿\n3. 乾清宫 → 交泰殿 → 坤宁宫\n4. 东六宫 → 西六宫\n5. 御花园 → 神武门\n\n## 必看景点\n\n### 太和殿\n- 故宫最大的宫殿\n- 皇帝举行大典的地方\n- 建筑气势恢宏\n\n### 乾清宫\n- 皇帝居住的宫殿\n- 内部陈设精美\n- 历史意义重大\n\n## 游览贴士\n\n1. **提前购票**：建议提前在网上购票，避免现场排队\n2. **避开高峰**：上午9-11点和下午2-4点是游览高峰\n3. **穿着舒适**：故宫面积很大，建议穿舒适的鞋子\n4. **携带物品**：可以带水和小食品，但不要带太多\n5. **拍照注意**：部分区域禁止拍照，请注意标识\n\n## 交通指南\n\n### 地铁\n- 1号线：天安门东站或天安门西站\n- 2号线：前门站\n\n### 公交\n- 1路、2路、52路等多路公交可达\n\n### 自驾\n- 故宫不提供停车位，建议乘坐公共交通', '[\"北京\", \"故宫\", \"历史\", \"文化\"]', '华北', 1, 'published', 2567, 156, 45, '2024-01-10 14:20:00', '故宫旅游攻略', '北京,故宫,旅游,攻略', '北京故宫深度游攻略，详细介绍游览路线和必看景点', '2025-10-07 13:38:15', '2025-10-07 13:38:15');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (6, '上海美食地图：必吃清单', '王编辑', 'food', '/src/assets/images/travel3.jpg', '上海本地人推荐的美食清单，从街头小吃到米其林餐厅。', '# 上海美食地图：必吃清单\n\n上海作为国际大都市，汇聚了世界各地的美食。本文将为您推荐上海本地人最爱的美食清单。\n\n## 本帮菜代表\n\n### 老正兴菜馆\n- **招牌菜**：白切鸡、红烧肉、糖醋小排\n- **人均消费**：150-200元\n- **推荐理由**：百年老字号，正宗本帮菜\n\n### 德兴馆\n- **招牌菜**：生煎包、小笼包、蟹粉小笼\n- **人均消费**：80-120元\n- **推荐理由**：传统点心，味道正宗\n\n## 街头小吃\n\n### 南翔小笼包\n- **位置**：城隍庙\n- **特色**：皮薄汁多，鲜香可口\n- **价格**：15-25元/笼\n\n### 生煎包\n- **推荐店铺**：大壶春、丰裕生煎\n- **特色**：底部金黄酥脆，顶部松软\n- **价格**：8-12元/4个\n\n## 网红餐厅\n\n### 外滩18号\n- **菜系**：融合菜\n- **人均消费**：300-500元\n- **推荐理由**：外滩景观，环境优雅\n\n### 新天地\n- **菜系**：各国料理\n- **人均消费**：200-400元\n- **推荐理由**：时尚氛围，选择多样\n\n## 用餐贴士\n\n1. **预订**：热门餐厅建议提前预订\n2. **时间**：避开用餐高峰（12:00-13:00，18:00-19:00）\n3. **支付**：大部分餐厅支持移动支付\n4. **小费**：一般不需要小费，但服务费可能包含在账单中\n\n## 美食街区推荐\n\n### 田子坊\n- 文艺小资，各种创意餐厅\n- 适合拍照和休闲\n\n### 新天地\n- 国际化程度高，各国美食\n- 夜生活丰富\n\n### 城隍庙\n- 传统小吃集中地\n- 体验老上海风情', '[\"上海\", \"美食\", \"小吃\", \"餐厅\"]', '华东', 0, 'draft', 1890, 234, 67, NULL, '上海美食推荐', '上海,美食,小吃,餐厅', '上海美食地图，本地人推荐必吃清单', '2025-10-07 13:38:15', '2025-10-07 13:38:15');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (7, '三亚最值得入住的5家海景酒店', '张编辑', 'hotel', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/f4fecbe35d0746fa9b38bdafa03ce397.png', '精选三亚最受欢迎的海景酒店，为您推荐性价比最高的住宿选择。', '# 三亚最值得入住的5家海景酒店\n\n三亚作为中国最著名的海滨度假城市，拥有众多优质的海景酒店。本文将为您推荐5家最值得入住的海景酒店，让您的三亚之旅更加完美。\n\n## 1. 三亚亚龙湾瑞吉度假酒店\n\n### 酒店特色\n- 坐拥亚龙湾一线海景\n- 私人海滩和游艇码头\n- 管家式服务\n\n### 客房设施\n- 海景套房面积宽敞\n- 私人阳台俯瞰海景\n- 高端洗浴用品\n\n## 2. 三亚海棠湾天房洲际度假酒店\n\n### 酒店亮点\n- 海棠湾核心位置\n- 海洋主题设计\n- 丰富的亲子设施\n\n### 推荐理由\n- 距离免税店仅5分钟车程\n- 拥有海底餐厅\n- 儿童俱乐部设施完善\n\n## 预订建议\n\n建议提前1-2个月预订，特别是节假日期间。可以通过官网、携程、飞猪等平台比较价格。', '[\"三亚\", \"酒店\", \"海景\", \"度假\"]', '华南', 1, 'published', 1234, 89, 23, '2025-10-07 15:42:31', '三亚海景酒店推荐', '三亚,酒店,海景,度假', '精选三亚最受欢迎的海景酒店推荐', '2025-10-07 13:40:15', '2025-10-07 15:57:15');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (8, '北京故宫深度游攻略', '李编辑', 'attraction', '/src/assets/images/travel2.jpg', '详细介绍故宫的游览路线、必看景点和参观技巧。', '# 北京故宫深度游攻略\n\n故宫，又称紫禁城，是明清两代的皇家宫殿，也是世界文化遗产。本文将为您提供详细的故宫游览攻略。\n\n## 基本信息\n\n### 开放时间\n- 旺季（4月1日-10月31日）：8:30-17:00\n- 淡季（11月1日-次年3月31日）：8:30-16:30\n- 周一闭馆（法定节假日除外）\n\n### 门票价格\n- 成人票：60元\n- 学生票：30元\n- 老人票：30元\n\n## 游览路线推荐\n\n### 经典路线（2-3小时）\n1. 午门 → 太和殿 → 中和殿 → 保和殿\n2. 乾清宫 → 交泰殿 → 坤宁宫\n3. 御花园 → 神武门\n\n### 深度游路线（4-6小时）\n1. 午门 → 武英殿 → 文华殿\n2. 太和殿 → 中和殿 → 保和殿\n3. 乾清宫 → 交泰殿 → 坤宁宫\n4. 东六宫 → 西六宫\n5. 御花园 → 神武门\n\n## 必看景点\n\n### 太和殿\n- 故宫最大的宫殿\n- 皇帝举行大典的地方\n- 建筑气势恢宏\n\n### 乾清宫\n- 皇帝居住的宫殿\n- 内部陈设精美\n- 历史意义重大\n\n## 游览贴士\n\n1. **提前购票**：建议提前在网上购票，避免现场排队\n2. **避开高峰**：上午9-11点和下午2-4点是游览高峰\n3. **穿着舒适**：故宫面积很大，建议穿舒适的鞋子\n4. **携带物品**：可以带水和小食品，但不要带太多\n5. **拍照注意**：部分区域禁止拍照，请注意标识\n\n## 交通指南\n\n### 地铁\n- 1号线：天安门东站或天安门西站\n- 2号线：前门站\n\n### 公交\n- 1路、2路、52路等多路公交可达\n\n### 自驾\n- 故宫不提供停车位，建议乘坐公共交通', '[\"北京\", \"故宫\", \"历史\", \"文化\"]', '华北', 1, 'published', 2567, 156, 45, '2024-01-10 14:20:00', '故宫旅游攻略', '北京,故宫,旅游,攻略', '北京故宫深度游攻略，详细介绍游览路线和必看景点', '2025-10-07 13:40:15', '2025-10-07 13:40:15');
INSERT INTO `article` (`id`, `title`, `author`, `category`, `cover_image`, `summary`, `content`, `tags`, `region`, `featured`, `status`, `views`, `likes`, `comments`, `publish_time`, `seo_title`, `seo_keywords`, `seo_description`, `create_time`, `update_time`) VALUES (9, '上海美食地图：必吃清单', '王编辑', 'food', '/src/assets/images/travel3.jpg', '上海本地人推荐的美食清单，从街头小吃到米其林餐厅。', '# 上海美食地图：必吃清单\n\n上海作为国际大都市，汇聚了世界各地的美食。本文将为您推荐上海本地人最爱的美食清单。\n\n## 本帮菜代表\n\n### 老正兴菜馆\n- **招牌菜**：白切鸡、红烧肉、糖醋小排\n- **人均消费**：150-200元\n- **推荐理由**：百年老字号，正宗本帮菜\n\n### 德兴馆\n- **招牌菜**：生煎包、小笼包、蟹粉小笼\n- **人均消费**：80-120元\n- **推荐理由**：传统点心，味道正宗\n\n## 街头小吃\n\n### 南翔小笼包\n- **位置**：城隍庙\n- **特色**：皮薄汁多，鲜香可口\n- **价格**：15-25元/笼\n\n### 生煎包\n- **推荐店铺**：大壶春、丰裕生煎\n- **特色**：底部金黄酥脆，顶部松软\n- **价格**：8-12元/4个\n\n## 网红餐厅\n\n### 外滩18号\n- **菜系**：融合菜\n- **人均消费**：300-500元\n- **推荐理由**：外滩景观，环境优雅\n\n### 新天地\n- **菜系**：各国料理\n- **人均消费**：200-400元\n- **推荐理由**：时尚氛围，选择多样\n\n## 用餐贴士\n\n1. **预订**：热门餐厅建议提前预订\n2. **时间**：避开用餐高峰（12:00-13:00，18:00-19:00）\n3. **支付**：大部分餐厅支持移动支付\n4. **小费**：一般不需要小费，但服务费可能包含在账单中\n\n## 美食街区推荐\n\n### 田子坊\n- 文艺小资，各种创意餐厅\n- 适合拍照和休闲\n\n### 新天地\n- 国际化程度高，各国美食\n- 夜生活丰富\n\n### 城隍庙\n- 传统小吃集中地\n- 体验老上海风情', '[\"上海\", \"美食\", \"小吃\", \"餐厅\"]', '华东', 0, 'draft', 1890, 234, 67, NULL, '上海美食推荐', '上海,美食,小吃,餐厅', '上海美食地图，本地人推荐必吃清单', '2025-10-07 13:40:15', '2025-10-07 13:40:15');
COMMIT;

-- ----------------------------
-- Table structure for article_package
-- ----------------------------
DROP TABLE IF EXISTS `article_package`;
CREATE TABLE `article_package` (
  `rel_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联唯一标识',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `package_name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '套餐名称',
  `package_image` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '套餐图片',
  `package_price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序权重',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`rel_id`),
  KEY `idx_article_id` (`article_id`),
  KEY `idx_product_id` (`product_id`),
  CONSTRAINT `fk_article_package_article` FOREIGN KEY (`article_id`) REFERENCES `scenic_article` (`article_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_article_package_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章关联套餐表';

-- ----------------------------
-- Records of article_package
-- ----------------------------
BEGIN;
INSERT INTO `article_package` (`rel_id`, `article_id`, `product_id`, `package_name`, `package_image`, `package_price`, `sort_order`, `create_time`) VALUES (13, 1, 40002, '豪华海景套房', '/src/assets/images/hotel1.jpg', 1288.00, 1, '2025-10-07 13:40:15');
INSERT INTO `article_package` (`rel_id`, `article_id`, `product_id`, `package_name`, `package_image`, `package_price`, `sort_order`, `create_time`) VALUES (14, 1, 40003, '三亚三日游', '/src/assets/images/tour1.jpg', 2588.00, 2, '2025-10-07 13:40:15');
INSERT INTO `article_package` (`rel_id`, `article_id`, `product_id`, `package_name`, `package_image`, `package_price`, `sort_order`, `create_time`) VALUES (15, 2, 40004, '故宫门票', '/src/assets/images/ticket1.jpg', 60.00, 1, '2025-10-07 13:40:15');
COMMIT;

-- ----------------------------
-- Table structure for article_statistics
-- ----------------------------
DROP TABLE IF EXISTS `article_statistics`;
CREATE TABLE `article_statistics` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `stat_date` date NOT NULL COMMENT '统计日期',
  `total_articles` int DEFAULT '0' COMMENT '总文章数',
  `published_articles` int DEFAULT '0' COMMENT '已发布文章数',
  `draft_articles` int DEFAULT '0' COMMENT '草稿文章数',
  `pending_articles` int DEFAULT '0' COMMENT '待审核文章数',
  `total_views` int DEFAULT '0' COMMENT '总阅读量',
  `total_likes` int DEFAULT '0' COMMENT '总点赞数',
  `total_comments` int DEFAULT '0' COMMENT '总评论数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_stat_date` (`stat_date`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章统计数据表';

-- ----------------------------
-- Records of article_statistics
-- ----------------------------
BEGIN;
INSERT INTO `article_statistics` (`id`, `stat_date`, `total_articles`, `published_articles`, `draft_articles`, `pending_articles`, `total_views`, `total_likes`, `total_comments`, `create_time`, `update_time`) VALUES (1, '2025-10-07', 3, 2, 1, 0, 5691, 479, 135, '2025-10-07 13:34:57', '2025-10-07 13:34:57');
COMMIT;

-- ----------------------------
-- Table structure for article_template
-- ----------------------------
DROP TABLE IF EXISTS `article_template`;
CREATE TABLE `article_template` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '模板ID',
  `name` varchar(255) NOT NULL COMMENT '模板名称',
  `description` text COMMENT '模板描述',
  `category` varchar(50) NOT NULL COMMENT '适用分类',
  `tags` json DEFAULT NULL COMMENT '模板标签（JSON数组）',
  `content` longtext NOT NULL COMMENT '模板内容',
  `is_active` tinyint(1) DEFAULT '1' COMMENT '是否启用：0-否，1-是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_is_active` (`is_active`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章模板表';

-- ----------------------------
-- Records of article_template
-- ----------------------------
BEGIN;
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (1, '酒店推荐模板', '适用于酒店推荐类文章，包含酒店介绍、设施、服务等结构', 'hotel', '[\"酒店\", \"推荐\", \"住宿\"]', '# {{酒店名称}} - 完美度假体验\n\n## 酒店概览\n{{酒店简介}}\n\n## 地理位置\n{{地理位置优势}}\n\n## 客房设施\n- 豪华客房\n- 海景套房\n- 行政楼层\n\n## 酒店设施\n- 游泳池\n- 健身房\n- 餐厅\n- 会议室\n\n## 服务特色\n{{服务亮点}}\n\n## 预订信息\n{{预订方式}}', 1, '2025-10-07 13:34:56', '2025-10-07 13:34:56');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (2, '景点攻略模板', '适用于景点介绍类文章，包含景点信息、游览路线、注意事项等', 'attraction', '[\"景点\", \"攻略\", \"游览\"]', '# {{景点名称}} - 深度游览攻略\n\n## 景点介绍\n{{景点历史和文化背景}}\n\n## 开放时间\n- 开放时间：{{开放时间}}\n- 门票价格：{{门票价格}}\n- 最佳游览时间：{{最佳时间}}\n\n## 游览路线\n### 经典路线\n{{经典游览路线}}\n\n### 深度游路线\n{{深度游览路线}}\n\n## 必看景点\n1. {{景点1}}\n2. {{景点2}}\n3. {{景点3}}\n\n## 游览贴士\n{{游览注意事项}}', 1, '2025-10-07 13:34:56', '2025-10-07 13:34:56');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (3, '美食攻略模板', '适用于美食推荐类文章，包含餐厅介绍、特色菜品、用餐体验等', 'food', '[\"美食\", \"餐厅\", \"推荐\"]', '# {{城市名称}}美食地图 - 必吃清单\n\n## 美食概览\n{{城市美食特色}}\n\n## 必吃餐厅\n### {{餐厅1名称}}\n- 特色菜品：{{菜品1}}\n- 人均消费：{{价格}}\n- 推荐理由：{{推荐理由}}\n\n### {{餐厅2名称}}\n- 特色菜品：{{菜品2}}\n- 人均消费：{{价格}}\n- 推荐理由：{{推荐理由}}\n\n## 街头小吃\n{{街头小吃推荐}}\n\n## 用餐贴士\n{{用餐注意事项}}', 1, '2025-10-07 13:34:56', '2025-10-07 13:34:56');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (4, '酒店推荐模板', '适用于酒店推荐类文章，包含酒店介绍、设施、服务等结构', 'hotel', '[\"酒店\", \"推荐\", \"住宿\"]', '# {{酒店名称}} - 完美度假体验\n\n## 酒店概览\n{{酒店简介}}\n\n## 地理位置\n{{地理位置优势}}\n\n## 客房设施\n- 豪华客房\n- 海景套房\n- 行政楼层\n\n## 酒店设施\n- 游泳池\n- 健身房\n- 餐厅\n- 会议室\n\n## 服务特色\n{{服务亮点}}\n\n## 预订信息\n{{预订方式}}', 1, '2025-10-07 13:38:15', '2025-10-07 13:38:15');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (5, '景点攻略模板', '适用于景点介绍类文章，包含景点信息、游览路线、注意事项等', 'attraction', '[\"景点\", \"攻略\", \"游览\"]', '# {{景点名称}} - 深度游览攻略\n\n## 景点介绍\n{{景点历史和文化背景}}\n\n## 开放时间\n- 开放时间：{{开放时间}}\n- 门票价格：{{门票价格}}\n- 最佳游览时间：{{最佳时间}}\n\n## 游览路线\n### 经典路线\n{{经典游览路线}}\n\n### 深度游路线\n{{深度游览路线}}\n\n## 必看景点\n1. {{景点1}}\n2. {{景点2}}\n3. {{景点3}}\n\n## 游览贴士\n{{游览注意事项}}', 1, '2025-10-07 13:38:15', '2025-10-07 13:38:15');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (6, '美食攻略模板', '适用于美食推荐类文章，包含餐厅介绍、特色菜品、用餐体验等', 'food', '[\"美食\", \"餐厅\", \"推荐\"]', '# {{城市名称}}美食地图 - 必吃清单\n\n## 美食概览\n{{城市美食特色}}\n\n## 必吃餐厅\n### {{餐厅1名称}}\n- 特色菜品：{{菜品1}}\n- 人均消费：{{价格}}\n- 推荐理由：{{推荐理由}}\n\n### {{餐厅2名称}}\n- 特色菜品：{{菜品2}}\n- 人均消费：{{价格}}\n- 推荐理由：{{推荐理由}}\n\n## 街头小吃\n{{街头小吃推荐}}\n\n## 用餐贴士\n{{用餐注意事项}}', 1, '2025-10-07 13:38:15', '2025-10-07 13:38:15');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (7, '酒店推荐模板', '适用于酒店推荐类文章，包含酒店介绍、设施、服务等结构', 'hotel', '[\"酒店\", \"推荐\", \"住宿\"]', '# {{酒店名称}} - 完美度假体验\n\n## 酒店概览\n{{酒店简介}}\n\n## 地理位置\n{{地理位置优势}}\n\n## 客房设施\n- 豪华客房\n- 海景套房\n- 行政楼层\n\n## 酒店设施\n- 游泳池\n- 健身房\n- 餐厅\n- 会议室\n\n## 服务特色\n{{服务亮点}}\n\n## 预订信息\n{{预订方式}}', 1, '2025-10-07 13:40:15', '2025-10-07 13:40:15');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (8, '景点攻略模板', '适用于景点介绍类文章，包含景点信息、游览路线、注意事项等', 'attraction', '[\"景点\", \"攻略\", \"游览\"]', '# {{景点名称}} - 深度游览攻略\n\n## 景点介绍\n{{景点历史和文化背景}}\n\n## 开放时间\n- 开放时间：{{开放时间}}\n- 门票价格：{{门票价格}}\n- 最佳游览时间：{{最佳时间}}\n\n## 游览路线\n### 经典路线\n{{经典游览路线}}\n\n### 深度游路线\n{{深度游览路线}}\n\n## 必看景点\n1. {{景点1}}\n2. {{景点2}}\n3. {{景点3}}\n\n## 游览贴士\n{{游览注意事项}}', 1, '2025-10-07 13:40:15', '2025-10-07 13:40:15');
INSERT INTO `article_template` (`id`, `name`, `description`, `category`, `tags`, `content`, `is_active`, `create_time`, `update_time`) VALUES (9, '美食攻略模板', '适用于美食推荐类文章，包含餐厅介绍、特色菜品、用餐体验等', 'food', '[\"美食\", \"餐厅\", \"推荐\"]', '# {{城市名称}}美食地图 - 必吃清单\n\n## 美食概览\n{{城市美食特色}}\n\n## 必吃餐厅\n### {{餐厅1名称}}\n- 特色菜品：{{菜品1}}\n- 人均消费：{{价格}}\n- 推荐理由：{{推荐理由}}\n\n### {{餐厅2名称}}\n- 特色菜品：{{菜品2}}\n- 人均消费：{{价格}}\n- 推荐理由：{{推荐理由}}\n\n## 街头小吃\n{{街头小吃推荐}}\n\n## 用餐贴士\n{{用餐注意事项}}', 1, '2025-10-07 13:40:15', '2025-10-07 13:40:15');
COMMIT;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `coupon_id` bigint NOT NULL AUTO_INCREMENT COMMENT '优惠券唯一标识',
  `coupon_name` varchar(100) COLLATE utf8mb3_bin NOT NULL COMMENT '优惠券名称',
  `coupon_type` tinyint NOT NULL DEFAULT '1' COMMENT '优惠券类型：1-满减券，2-折扣券',
  `discount_value` decimal(10,2) NOT NULL COMMENT '优惠金额或折扣值',
  `min_spend` decimal(10,2) DEFAULT '0.00' COMMENT '最低消费金额',
  `max_discount` decimal(10,2) DEFAULT NULL COMMENT '最大折扣金额（折扣券使用）',
  `start_date` date NOT NULL COMMENT '有效期开始日期',
  `end_date` date NOT NULL COMMENT '有效期结束日期',
  `total_count` int NOT NULL DEFAULT '0' COMMENT '发放总数',
  `used_count` int DEFAULT '0' COMMENT '已使用数量',
  `status` tinyint DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='优惠券表';

-- ----------------------------
-- Records of coupon
-- ----------------------------
BEGIN;
INSERT INTO `coupon` (`coupon_id`, `coupon_name`, `coupon_type`, `discount_value`, `min_spend`, `max_discount`, `start_date`, `end_date`, `total_count`, `used_count`, `status`, `create_time`) VALUES (1, '满1000减50', 1, 50.00, 1000.00, NULL, '2023-01-01', '2023-12-31', 1000, 0, 1, '2025-10-02 15:08:06');
INSERT INTO `coupon` (`coupon_id`, `coupon_name`, `coupon_type`, `discount_value`, `min_spend`, `max_discount`, `start_date`, `end_date`, `total_count`, `used_count`, `status`, `create_time`) VALUES (2, '满5000减300', 1, 300.00, 5000.00, NULL, '2023-01-01', '2023-11-30', 500, 0, 1, '2025-10-02 15:08:06');
INSERT INTO `coupon` (`coupon_id`, `coupon_name`, `coupon_type`, `discount_value`, `min_spend`, `max_discount`, `start_date`, `end_date`, `total_count`, `used_count`, `status`, `create_time`) VALUES (3, '9折优惠券', 2, 0.90, 500.00, NULL, '2023-01-01', '2023-12-31', 2000, 0, 1, '2025-10-02 15:08:06');
COMMIT;

-- ----------------------------
-- Table structure for evaluation_coupon
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_coupon`;
CREATE TABLE `evaluation_coupon` (
  `coupon_id` bigint NOT NULL AUTO_INCREMENT COMMENT '优惠券ID',
  `eval_id` bigint NOT NULL COMMENT '评价ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `coupon_code` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '优惠券编码',
  `amount` decimal(10,2) NOT NULL COMMENT '优惠券金额',
  `status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'active' COMMENT '状态(active-有效,used-已使用,expired-已过期)',
  `claim_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `used_time` datetime DEFAULT NULL COMMENT '使用时间',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`coupon_id`),
  UNIQUE KEY `uk_eval_user` (`eval_id`,`user_id`),
  UNIQUE KEY `uk_coupon_code` (`coupon_code`),
  KEY `idx_eval_id` (`eval_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_coupon_code` (`coupon_code`),
  KEY `idx_status` (`status`),
  KEY `idx_expire_time` (`expire_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评价优惠券表';

-- ----------------------------
-- Records of evaluation_coupon
-- ----------------------------
BEGIN;
INSERT INTO `evaluation_coupon` (`coupon_id`, `eval_id`, `user_id`, `coupon_code`, `amount`, `status`, `claim_time`, `expire_time`, `used_time`, `created_time`, `updated_time`) VALUES (1, 1, 10002, 'EVAL20240115001', 20.00, 'active', '2025-10-05 15:43:05', '2025-11-04 15:43:05', NULL, '2025-10-05 15:43:05', '2025-10-05 15:43:05');
INSERT INTO `evaluation_coupon` (`coupon_id`, `eval_id`, `user_id`, `coupon_code`, `amount`, `status`, `claim_time`, `expire_time`, `used_time`, `created_time`, `updated_time`) VALUES (2, 2, 10003, 'EVAL20240115002', 20.00, 'active', '2025-10-05 15:43:05', '2025-11-04 15:43:05', NULL, '2025-10-05 15:43:05', '2025-10-05 15:43:05');
COMMIT;

-- ----------------------------
-- Table structure for evaluation_reply
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_reply`;
CREATE TABLE `evaluation_reply` (
  `reply_id` bigint NOT NULL AUTO_INCREMENT COMMENT '回复ID',
  `eval_id` bigint NOT NULL COMMENT '评价ID',
  `reply_content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '回复内容',
  `reply_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `replier_id` bigint NOT NULL COMMENT '回复者ID',
  `replier_name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '回复者姓名',
  `replier_role` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '回复者角色(merchant/admin)',
  `status` tinyint DEFAULT '1' COMMENT '状态(1-正常,0-删除)',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`reply_id`),
  KEY `idx_eval_id` (`eval_id`),
  KEY `idx_replier_id` (`replier_id`),
  KEY `idx_reply_time` (`reply_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评价回复表';

-- ----------------------------
-- Records of evaluation_reply
-- ----------------------------
BEGIN;
INSERT INTO `evaluation_reply` (`reply_id`, `eval_id`, `reply_content`, `reply_time`, `replier_id`, `replier_name`, `replier_role`, `status`, `created_time`, `updated_time`) VALUES (1, 1, '感谢您的评价，我们会继续努力提供更好的服务！', '2025-10-05 15:43:05', 10001, '商家客服', 'merchant', 1, '2025-10-05 15:43:05', '2025-10-05 15:43:05');
INSERT INTO `evaluation_reply` (`reply_id`, `eval_id`, `reply_content`, `reply_time`, `replier_id`, `replier_name`, `replier_role`, `status`, `created_time`, `updated_time`) VALUES (2, 2, '您的建议很宝贵，我们会认真改进。', '2025-10-05 15:43:05', 10001, '商家客服', 'merchant', 1, '2025-10-05 15:43:05', '2025-10-05 15:43:05');
COMMIT;

-- ----------------------------
-- Table structure for evaluation_useful
-- ----------------------------
DROP TABLE IF EXISTS `evaluation_useful`;
CREATE TABLE `evaluation_useful` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `eval_id` bigint NOT NULL COMMENT '评价ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `useful_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '标记时间',
  `status` tinyint DEFAULT '1' COMMENT '状态(1-有用,0-取消)',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_eval_user` (`eval_id`,`user_id`),
  KEY `idx_eval_id` (`eval_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_useful_time` (`useful_time`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评价有用表';

-- ----------------------------
-- Records of evaluation_useful
-- ----------------------------
BEGIN;
INSERT INTO `evaluation_useful` (`id`, `eval_id`, `user_id`, `useful_time`, `status`, `created_time`, `updated_time`) VALUES (1, 1, 10002, '2025-10-05 15:43:05', 1, '2025-10-05 15:43:05', '2025-10-05 15:43:05');
INSERT INTO `evaluation_useful` (`id`, `eval_id`, `user_id`, `useful_time`, `status`, `created_time`, `updated_time`) VALUES (2, 1, 10003, '2025-10-05 15:43:05', 1, '2025-10-05 15:43:05', '2025-10-05 15:43:05');
INSERT INTO `evaluation_useful` (`id`, `eval_id`, `user_id`, `useful_time`, `status`, `created_time`, `updated_time`) VALUES (3, 2, 10002, '2025-10-05 15:43:05', 1, '2025-10-05 15:43:05', '2025-10-05 15:43:05');
INSERT INTO `evaluation_useful` (`id`, `eval_id`, `user_id`, `useful_time`, `status`, `created_time`, `updated_time`) VALUES (9, 80013, 10003, '2025-10-05 17:08:20', 1, '2025-10-05 17:02:47', '2025-10-05 17:08:20');
INSERT INTO `evaluation_useful` (`id`, `eval_id`, `user_id`, `useful_time`, `status`, `created_time`, `updated_time`) VALUES (10, 80012, 10003, '2025-10-05 17:04:22', 1, '2025-10-05 17:04:22', '2025-10-05 17:04:22');
INSERT INTO `evaluation_useful` (`id`, `eval_id`, `user_id`, `useful_time`, `status`, `created_time`, `updated_time`) VALUES (11, 80004, 10003, '2025-10-05 17:08:51', 1, '2025-10-05 17:08:51', '2025-10-05 17:08:51');
COMMIT;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `file_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件唯一标识',
  `original_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '原始文件名',
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '存储文件名',
  `file_path` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件存储路径',
  `file_url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件访问URL',
  `file_size` bigint NOT NULL COMMENT '文件大小（字节）',
  `file_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件MIME类型',
  `file_extension` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件扩展名',
  `upload_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '上传类型：avatar-头像，qualification-资质证明，product-产品图片，article-文章图片',
  `related_id` bigint DEFAULT NULL COMMENT '关联ID（用户ID、产品ID等）',
  `is_main` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为主文件',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-已删除，1-正常',
  `upload_user_id` bigint NOT NULL COMMENT '上传用户ID',
  `upload_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`file_id`),
  KEY `idx_upload_type` (`upload_type`),
  KEY `idx_related_id` (`related_id`),
  KEY `idx_upload_user_id` (`upload_user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_upload_time` (`upload_time`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件信息表';

-- ----------------------------
-- Records of file_info
-- ----------------------------
BEGIN;
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (1, 'avatar1.jpg', 'avatar_10001_20241219_001.jpg', '/uploads/avatars/2024/12/19/avatar_10001_20241219_001.jpg', 'https://example.com/uploads/avatars/2024/12/19/avatar_10001_20241219_001.jpg', 1024000, 'image/jpeg', 'jpg', 'avatar', 10001, 1, 1, 10001, '2025-10-04 16:41:41', '2025-10-04 16:41:41', '2025-10-04 16:41:41');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (2, 'guide_cert.pdf', 'guide_cert_20001_20241219_001.pdf', '/uploads/qualifications/2024/12/19/guide_cert_20001_20241219_001.pdf', 'https://example.com/uploads/qualifications/2024/12/19/guide_cert_20001_20241219_001.pdf', 2048000, 'application/pdf', 'pdf', 'qualification', 20001, 1, 1, 20001, '2025-10-04 16:41:41', '2025-10-04 16:41:41', '2025-10-04 16:41:41');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (3, 'product1.jpg', 'product_1_20241219_001.jpg', '/uploads/products/2024/12/19/product_1_20241219_001.jpg', 'https://example.com/uploads/products/2024/12/19/product_1_20241219_001.jpg', 1536000, 'image/jpeg', 'jpg', 'product', 1, 1, 1, 30001, '2025-10-04 16:41:41', '2025-10-04 16:41:41', '2025-10-04 16:41:41');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (4, '图标.jpg', 'avatar_1759567986898_da2795a5.jpg', '/uploads/avatars/2025/10/04/avatar_1759567986898_da2795a5.jpg', 'https://example.com/uploads/avatars/2025/10/04/avatar_1759567986898_da2795a5.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 16:53:07', '2025-10-04 16:53:06', '2025-10-04 16:53:06');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (5, '图标.jpg', 'avatar_1759568091554_931ed6e9.jpg', '/uploads/avatars/2025/10/04/avatar_1759568091554_931ed6e9.jpg', 'https://example.com/uploads/avatars/2025/10/04/avatar_1759568091554_931ed6e9.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 16:54:52', '2025-10-04 16:54:51', '2025-10-04 16:54:51');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (8, '图标.jpg', 'avatar_1759568759880_ef807b38.jpg', '/uploads/avatars/2025/10/04/avatar_1759568759880_ef807b38.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759568759880_ef807b38.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:06:00', '2025-10-04 17:05:59', '2025-10-04 17:05:59');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (9, '图标.jpg', 'avatar_1759568881252_0873eed8.jpg', '/uploads/avatars/2025/10/04/avatar_1759568881252_0873eed8.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759568881252_0873eed8.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:08:01', '2025-10-04 17:08:01', '2025-10-04 17:08:01');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (10, '图标.jpg', 'avatar_1759568943568_403af488.jpg', '/uploads/avatars/2025/10/04/avatar_1759568943568_403af488.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759568943568_403af488.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:09:04', '2025-10-04 17:09:03', '2025-10-04 17:09:03');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (11, '图标.jpg', 'avatar_1759569038217_91323d42.jpg', '/uploads/avatars/2025/10/04/avatar_1759569038217_91323d42.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759569038217_91323d42.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:10:38', '2025-10-04 17:10:38', '2025-10-04 17:10:38');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (12, '图标.jpg', 'avatar_1759569229359_727b66aa.jpg', '/uploads/avatars/2025/10/04/avatar_1759569229359_727b66aa.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759569229359_727b66aa.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:13:49', '2025-10-04 17:13:49', '2025-10-04 17:13:49');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (13, '图标.jpg', 'avatar_1759569376792_118bfbf9.jpg', '/uploads/avatars/2025/10/04/avatar_1759569376792_118bfbf9.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759569376792_118bfbf9.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:16:17', '2025-10-04 17:16:16', '2025-10-04 17:16:16');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (14, 'test.png', 'avatar_1759569629485_e30362cf.png', '/uploads/avatars/2025/10/04/avatar_1759569629485_e30362cf.png', '/uploads/uploads/avatars/2025/10/04/avatar_1759569629485_e30362cf.png', 70, 'image/png', '.png', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:20:29', '2025-10-04 17:20:29', '2025-10-04 17:20:29');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (15, '图标.jpg', 'avatar_1759569785656_793fad33.jpg', '/uploads/avatars/2025/10/04/avatar_1759569785656_793fad33.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759569785656_793fad33.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:23:06', '2025-10-04 17:23:05', '2025-10-04 17:23:05');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (16, '图标.jpg', 'avatar_1759569878048_482ccdbb.jpg', '/uploads/avatars/2025/10/04/avatar_1759569878048_482ccdbb.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759569878048_482ccdbb.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:24:38', '2025-10-04 17:24:38', '2025-10-04 17:24:38');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (17, '图标.jpg', 'avatar_1759569919822_e24d51fd.jpg', '/uploads/avatars/2025/10/04/avatar_1759569919822_e24d51fd.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759569919822_e24d51fd.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:25:20', '2025-10-04 17:25:19', '2025-10-04 17:25:19');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (18, '图标.jpg', 'avatar_1759570035659_7d39d9e4.jpg', '/uploads/avatars/2025/10/04/avatar_1759570035659_7d39d9e4.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759570035659_7d39d9e4.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 17:27:16', '2025-10-04 17:27:15', '2025-10-04 17:27:15');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (19, '图标.jpg', 'avatar_1759579646955_d706f82d.jpg', '/uploads/avatars/2025/10/04/avatar_1759579646955_d706f82d.jpg', '/uploads/uploads/avatars/2025/10/04/avatar_1759579646955_d706f82d.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 20:07:27', '2025-10-04 20:07:26', '2025-10-04 20:07:26');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (20, '图标.jpg', 'avatar_1759580253331_b251eb18.jpg', '/uploads//avatars/2025/10/04/avatar_1759580253331_b251eb18.jpg', '/uploads/uploads//avatars/2025/10/04/avatar_1759580253331_b251eb18.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 20:17:33', '2025-10-04 20:17:33', '2025-10-04 20:17:33');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (21, '截屏2025-09-28 13.51.41.png', 'avatar_1759581593997_6d29d479.png', '/uploads//avatars/2025/10/04/avatar_1759581593997_6d29d479.png', '/uploads//avatars/2025/10/04/avatar_1759581593997_6d29d479.png', 36383, 'image/png', '.png', 'avatar', 10003, 1, 1, 10003, '2025-10-04 20:39:54', '2025-10-04 20:39:54', '2025-10-04 20:39:54');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (22, '图标.jpg', 'avatar_1759581643973_27be4fd3.jpg', '/uploads//avatars/2025/10/04/avatar_1759581643973_27be4fd3.jpg', '/uploads//avatars/2025/10/04/avatar_1759581643973_27be4fd3.jpg', 42957, 'image/jpeg', '.jpg', 'avatar', 10003, 1, 1, 10003, '2025-10-04 20:40:44', '2025-10-04 20:40:43', '2025-10-04 20:40:43');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (23, '截屏2025-09-28 13.51.41.png', 'avatar_1759581670714_ba87d95b.png', '/uploads//avatars/2025/10/04/avatar_1759581670714_ba87d95b.png', '/uploads//avatars/2025/10/04/avatar_1759581670714_ba87d95b.png', 36383, 'image/png', '.png', 'avatar', 10003, 1, 1, 10003, '2025-10-04 20:41:11', '2025-10-04 20:41:10', '2025-10-04 20:41:10');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (24, '截屏2025-09-26 13.46.38.png', 'evaluation_1759590974038_288f7ada.png', '/uploads//others/2025/10/04/evaluation_1759590974038_288f7ada.png', 'http://localhost:8086/travelManagementSystem/uploads//others/2025/10/04/evaluation_1759590974038_288f7ada.png', 848750, 'image/png', '.png', 'evaluation', 60026, 0, 1, 60026, '2025-10-04 23:16:14', '2025-10-04 23:16:14', '2025-10-04 23:16:14');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (25, '截屏2025-09-28 13.51.41.png', '523bc46b9c8d439b89cdbcc6114a1af3.png', '/products/2025/10/06/523bc46b9c8d439b89cdbcc6114a1af3.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/523bc46b9c8d439b89cdbcc6114a1af3.png', 36383, 'image/png', 'png', 'product', 40007, 0, 1, 40007, '2025-10-06 20:07:00', '2025-10-06 20:07:00', '2025-10-06 21:06:42');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (26, '截屏2025-09-28 13.51.41.png', '450390cfd9514b45858bad1827e9d9ba.png', '/products/2025/10/06/450390cfd9514b45858bad1827e9d9ba.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/450390cfd9514b45858bad1827e9d9ba.png', 36383, 'image/png', 'png', 'product', 40007, 0, 1, 40007, '2025-10-06 20:07:26', '2025-10-06 20:07:26', '2025-10-06 21:06:42');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (27, '截屏2025-09-28 13.51.41.png', 'bacde375e9dc4c3f948b0eee5a9296e6.png', '/products/2025/10/06/bacde375e9dc4c3f948b0eee5a9296e6.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/bacde375e9dc4c3f948b0eee5a9296e6.png', 36383, 'image/png', 'png', 'product', 40007, 0, 1, 40007, '2025-10-06 20:19:25', '2025-10-06 20:19:25', '2025-10-06 21:06:42');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (28, '截屏2025-09-28 13.51.41.png', 'b638d1d07b934ae680dc97b952bbe28b.png', '/products/2025/10/06/b638d1d07b934ae680dc97b952bbe28b.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/b638d1d07b934ae680dc97b952bbe28b.png', 36383, 'image/png', 'png', 'product', 40007, 0, 1, 40007, '2025-10-06 20:19:39', '2025-10-06 20:19:39', '2025-10-06 21:06:42');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (29, '截屏2025-09-28 13.51.41.png', '89487e9019fd4267a0ad263b627b1039.png', '/products/2025/10/06/89487e9019fd4267a0ad263b627b1039.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/89487e9019fd4267a0ad263b627b1039.png', 36383, 'image/png', 'png', 'product', 40007, 1, 1, 40007, '2025-10-06 20:24:01', '2025-10-06 20:24:01', '2025-10-06 21:06:42');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (30, '截屏2025-09-28 13.51.41.png', '9edae2335b2e4341aa2bc5c353d7ca6e.png', '/products/2025/10/06/9edae2335b2e4341aa2bc5c353d7ca6e.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/9edae2335b2e4341aa2bc5c353d7ca6e.png', 36383, 'image/png', 'png', 'product', 40001, 0, 1, 40001, '2025-10-06 20:26:10', '2025-10-06 20:26:10', '2025-10-06 20:26:10');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (31, '截屏2025-09-28 13.51.41.png', '07faaa2fe49640d89f7da274ef25e0da.png', '/products/2025/10/06/07faaa2fe49640d89f7da274ef25e0da.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/07faaa2fe49640d89f7da274ef25e0da.png', 36383, 'image/png', 'png', 'product', 40001, 0, 1, 40001, '2025-10-06 20:27:09', '2025-10-06 20:27:09', '2025-10-06 20:27:09');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (32, 'test.png', '3567845d92094e539028590986b2bef1.png', '/products/2025/10/06/3567845d92094e539028590986b2bef1.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/3567845d92094e539028590986b2bef1.png', 70, 'image/png', 'png', 'product', 40008, 1, 1, 40008, '2025-10-06 20:51:16', '2025-10-06 20:51:16', '2025-10-06 20:51:16');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (33, '截屏2025-09-28 16.29.24.png', 'a6ecace582e74c6b96ddaf112a843239.png', '/products/2025/10/06/a6ecace582e74c6b96ddaf112a843239.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/a6ecace582e74c6b96ddaf112a843239.png', 222653, 'image/png', 'png', 'product', 40007, 0, 1, 40007, '2025-10-06 21:07:18', '2025-10-06 21:07:18', '2025-10-06 21:07:18');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (34, '截屏2025-09-28 13.51.41.png', '264b19da969b45358fc962049af8eba0.png', '/products/2025/10/06/264b19da969b45358fc962049af8eba0.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/264b19da969b45358fc962049af8eba0.png', 36383, 'image/png', 'png', 'product', 40007, 0, 1, 40007, '2025-10-06 21:12:18', '2025-10-06 21:12:18', '2025-10-06 21:12:18');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (35, '截屏2025-09-26 20.26.55.png', 'aedafc942b134025a2ccbd89784d035f.png', '/products/2025/10/06/aedafc942b134025a2ccbd89784d035f.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/aedafc942b134025a2ccbd89784d035f.png', 147781, 'image/png', 'png', 'product', 40010, 0, 1, 40010, '2025-10-06 21:22:38', '2025-10-06 21:22:38', '2025-10-06 21:23:33');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (36, 'test2.png', 'c8ded7f546da4c6396c674b506229735.png', '/products/2025/10/06/c8ded7f546da4c6396c674b506229735.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/c8ded7f546da4c6396c674b506229735.png', 70, 'image/png', 'png', 'product', 40010, 1, 1, 40010, '2025-10-06 21:23:33', '2025-10-06 21:23:33', '2025-10-06 21:23:33');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (37, 'test2.png', '634938630eb84948a0e5ceeebb6b27df.png', '/products/2025/10/06/634938630eb84948a0e5ceeebb6b27df.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/634938630eb84948a0e5ceeebb6b27df.png', 70, 'image/png', 'png', 'product', 40011, 1, 1, 40011, '2025-10-06 21:25:09', '2025-10-06 21:25:09', '2025-10-06 21:25:09');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (38, '截屏2025-09-28 13.51.41.png', '4742346464384d8480527a69576ff670.png', '/products/2025/10/06/4742346464384d8480527a69576ff670.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/4742346464384d8480527a69576ff670.png', 36383, 'image/png', 'png', 'product', 40011, 0, 1, 40011, '2025-10-06 21:26:47', '2025-10-06 21:26:47', '2025-10-06 21:26:47');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (39, '截屏2025-09-28 13.51.41.png', 'e91ccaf0848e46ecb10776468bd353d1.png', '/products/2025/10/06/e91ccaf0848e46ecb10776468bd353d1.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/e91ccaf0848e46ecb10776468bd353d1.png', 36383, 'image/png', 'png', 'product', 40012, 1, 1, 40012, '2025-10-06 22:01:20', '2025-10-06 22:01:20', '2025-10-06 22:01:20');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (40, '截屏2025-09-26 20.26.55.png', '70493db1ef2046a7a1c9b592c498baba.png', '/products/2025/10/06/70493db1ef2046a7a1c9b592c498baba.png', 'http://localhost:8082/travel-admin/uploads/products/2025/10/06/70493db1ef2046a7a1c9b592c498baba.png', 147781, 'image/png', 'png', 'product', 40012, 0, 1, 40012, '2025-10-06 22:01:20', '2025-10-06 22:01:20', '2025-10-06 22:01:20');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (41, '截屏2025-09-28 13.51.41.png', '6d7d46e66af349ddab07f286e2756507.png', '/articles/2025/10/07/6d7d46e66af349ddab07f286e2756507.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/6d7d46e66af349ddab07f286e2756507.png', 36383, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:04:41', '2025-10-07 14:04:41', '2025-10-07 14:04:41');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (42, '截屏2025-09-28 13.51.41.png', '4293a6de383c4d008939d116728fa856.png', '/articles/2025/10/07/4293a6de383c4d008939d116728fa856.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/4293a6de383c4d008939d116728fa856.png', 36383, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:05:44', '2025-10-07 14:05:44', '2025-10-07 14:05:44');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (43, '截屏2025-09-26 13.46.38.png', '5d7894f9550044ed8e14bf99fd373453.png', '/articles/2025/10/07/5d7894f9550044ed8e14bf99fd373453.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/5d7894f9550044ed8e14bf99fd373453.png', 848750, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:06:52', '2025-10-07 14:06:52', '2025-10-07 14:06:52');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (44, '截屏2025-09-26 13.46.38.png', 'b6a9b1e19f304fb7b93a3264905f57f6.png', '/articles/2025/10/07/b6a9b1e19f304fb7b93a3264905f57f6.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/b6a9b1e19f304fb7b93a3264905f57f6.png', 848750, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:10:27', '2025-10-07 14:10:27', '2025-10-07 14:10:27');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (45, '截屏2025-09-28 13.51.41.png', 'b207f3c178fa48f2b4e9807b0f1e1cdd.png', '/articles/2025/10/07/b207f3c178fa48f2b4e9807b0f1e1cdd.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/b207f3c178fa48f2b4e9807b0f1e1cdd.png', 36383, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:12:46', '2025-10-07 14:12:46', '2025-10-07 14:12:46');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (46, '截屏2025-09-28 13.51.41.png', '39d13cc63011497e87de0d32a3412de5.png', '/articles/2025/10/07/39d13cc63011497e87de0d32a3412de5.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/39d13cc63011497e87de0d32a3412de5.png', 36383, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:14:53', '2025-10-07 14:14:53', '2025-10-07 14:14:53');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (47, '截屏2025-09-28 13.51.41.png', 'b8123a45789b4e80b215eab2105db31b.png', '/articles/2025/10/07/b8123a45789b4e80b215eab2105db31b.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/b8123a45789b4e80b215eab2105db31b.png', 36383, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:17:57', '2025-10-07 14:17:57', '2025-10-07 14:17:57');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (48, '截屏2025-09-28 13.51.41.png', '3b60a789f51a4cbfa4cea92d403ca6b5.png', '/articles/2025/10/07/3b60a789f51a4cbfa4cea92d403ca6b5.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/3b60a789f51a4cbfa4cea92d403ca6b5.png', 36383, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:21:08', '2025-10-07 14:21:08', '2025-10-07 14:21:08');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (49, '截屏2025-09-28 13.51.41.png', '22c95fed193941f7b842d369be110f87.png', '/articles/2025/10/07/22c95fed193941f7b842d369be110f87.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/22c95fed193941f7b842d369be110f87.png', 36383, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 14:23:41', '2025-10-07 14:23:41', '2025-10-07 14:23:41');
INSERT INTO `file_info` (`file_id`, `original_name`, `file_name`, `file_path`, `file_url`, `file_size`, `file_type`, `file_extension`, `upload_type`, `related_id`, `is_main`, `status`, `upload_user_id`, `upload_time`, `create_time`, `update_time`) VALUES (50, '截屏2025-09-28 16.29.24.png', 'f4fecbe35d0746fa9b38bdafa03ce397.png', '/articles/2025/10/07/f4fecbe35d0746fa9b38bdafa03ce397.png', 'http://localhost:8082/travel-admin/uploads/articles/2025/10/07/f4fecbe35d0746fa9b38bdafa03ce397.png', 222653, 'image/png', 'png', 'article', 7, 0, 1, 7, '2025-10-07 15:57:12', '2025-10-07 15:57:12', '2025-10-07 15:57:12');
COMMIT;

-- ----------------------------
-- Table structure for guide_cooperation
-- ----------------------------
DROP TABLE IF EXISTS `guide_cooperation`;
CREATE TABLE `guide_cooperation` (
  `cooperation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '合作邀请唯一标识',
  `merchant_id` bigint NOT NULL COMMENT '发起邀请的旅行商ID',
  `guide_id` bigint NOT NULL COMMENT '被邀请的导游ID',
  `cooperation_type` tinyint NOT NULL COMMENT '合作类型：1-独家合作，2-合作伙伴，3-项目合作',
  `project_ids` text COLLATE utf8mb3_bin COMMENT '合作项目ID列表（JSON格式）',
  `start_date` date NOT NULL COMMENT '合作开始日期',
  `end_date` date NOT NULL COMMENT '合作结束日期',
  `conditions` text COLLATE utf8mb3_bin COMMENT '合作条件',
  `notes` text COLLATE utf8mb3_bin COMMENT '备注',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '合作状态：0-待确认，1-已接受，2-已拒绝，3-已结束',
  `reject_reason` varchar(500) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '拒绝原因',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `response_time` datetime DEFAULT NULL COMMENT '响应时间',
  PRIMARY KEY (`cooperation_id`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_guide_id` (`guide_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_cooperation_merchant_status` (`merchant_id`,`status`),
  KEY `idx_cooperation_guide_status` (`guide_id`,`status`),
  KEY `idx_cooperation_type` (`cooperation_type`),
  CONSTRAINT `fk_cooperation_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`),
  CONSTRAINT `fk_cooperation_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant_extend` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='导游合作邀请表';

-- ----------------------------
-- Records of guide_cooperation
-- ----------------------------
BEGIN;
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (1, 30001, 20001, 2, '[40001, 40002]', '2024-06-01', '2024-12-31', '提供优质导游服务，确保游客满意度', '希望建立长期合作关系', 1, NULL, '2025-10-07 13:08:38', '2025-10-07 13:08:38', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (2, 30001, 20002, 1, '[40003]', '2024-07-01', '2024-10-31', '独家合作，不得与其他旅行商合作', '独家合作项目', 0, NULL, '2025-10-07 13:08:38', '2025-10-07 13:08:38', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (3, 30002, 20001, 3, '[40004]', '2024-08-01', '2024-09-30', '项目合作，按项目结算', '临时项目合作', 1, NULL, '2025-10-07 13:08:38', '2025-10-07 13:08:38', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (4, 30001, 20001, 2, '[40001, 40002]', '2024-06-01', '2024-12-31', '提供优质导游服务，确保游客满意度', '希望建立长期合作关系', 1, NULL, '2025-10-07 13:08:45', '2025-10-07 13:08:45', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (5, 30001, 20002, 1, '[40003]', '2024-07-01', '2024-10-31', '独家合作，不得与其他旅行商合作', '独家合作项目', 0, NULL, '2025-10-07 13:08:45', '2025-10-07 13:08:45', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (6, 30002, 20001, 3, '[40004]', '2024-08-01', '2024-09-30', '项目合作，按项目结算', '临时项目合作', 1, NULL, '2025-10-07 13:08:45', '2025-10-07 13:08:45', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (7, 30001, 20001, 2, '[40001, 40002]', '2024-06-01', '2024-12-31', '提供优质导游服务，确保游客满意度', '希望建立长期合作关系', 1, NULL, '2025-10-07 13:08:53', '2025-10-07 13:08:53', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (8, 30001, 20002, 1, '[40003]', '2024-07-01', '2024-10-31', '独家合作，不得与其他旅行商合作', '独家合作项目', 0, NULL, '2025-10-07 13:08:53', '2025-10-07 13:08:53', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (9, 30002, 20001, 3, '[40004]', '2024-08-01', '2024-09-30', '项目合作，按项目结算', '临时项目合作', 1, NULL, '2025-10-07 13:08:53', '2025-10-07 13:08:53', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (10, 30001, 20001, 2, '[40001, 40002]', '2024-06-01', '2024-12-31', '提供优质导游服务，确保游客满意度', '希望建立长期合作关系', 1, NULL, '2025-10-07 13:09:01', '2025-10-07 13:09:01', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (11, 30001, 20002, 1, '[40003]', '2024-07-01', '2024-10-31', '独家合作，不得与其他旅行商合作', '独家合作项目', 0, NULL, '2025-10-07 13:09:01', '2025-10-07 13:09:01', NULL);
INSERT INTO `guide_cooperation` (`cooperation_id`, `merchant_id`, `guide_id`, `cooperation_type`, `project_ids`, `start_date`, `end_date`, `conditions`, `notes`, `status`, `reject_reason`, `create_time`, `update_time`, `response_time`) VALUES (12, 30002, 20001, 3, '[40004]', '2024-08-01', '2024-09-30', '项目合作，按项目结算', '临时项目合作', 1, NULL, '2025-10-07 13:09:01', '2025-10-07 13:09:01', NULL);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=120007 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='导游匹配需求表';

-- ----------------------------
-- Records of guide_match
-- ----------------------------
BEGIN;
INSERT INTO `guide_match` (`match_id`, `user_id`, `destination`, `travel_date`, `travel_days`, `person_count`, `budget`, `service_require`, `match_status`, `guide_id`, `deposit_amount`, `deposit_time`, `create_time`) VALUES (120001, 10003, '上海', '2024-09-01', 2, 2, 1200.00, '需要熟悉迪士尼乐园的导游', 1, 20002, 300.00, '2024-06-20 11:00:00', '2025-09-26 11:28:36');
INSERT INTO `guide_match` (`match_id`, `user_id`, `destination`, `travel_date`, `travel_days`, `person_count`, `budget`, `service_require`, `match_status`, `guide_id`, `deposit_amount`, `deposit_time`, `create_time`) VALUES (120002, 10004, '成都', '2024-08-15', 3, 3, 1800.00, '会说四川话，熟悉美食景点', 0, NULL, NULL, NULL, '2025-09-26 11:28:36');
COMMIT;

-- ----------------------------
-- Table structure for hot_destination
-- ----------------------------
DROP TABLE IF EXISTS `hot_destination`;
CREATE TABLE `hot_destination` (
  `dest_id` bigint NOT NULL AUTO_INCREMENT COMMENT '目的地唯一标识',
  `destination_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '目的地名称',
  `region` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属地区',
  `image_url` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目的地图片',
  `description` text COLLATE utf8mb4_unicode_ci COMMENT '目的地描述',
  `view_count` int NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序权重',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-下架，1-上架',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dest_id`),
  UNIQUE KEY `uk_destination_name` (`destination_name`),
  KEY `idx_region` (`region`),
  KEY `idx_sort_order` (`sort_order`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='热门目的地表';

-- ----------------------------
-- Records of hot_destination
-- ----------------------------
BEGIN;
INSERT INTO `hot_destination` (`dest_id`, `destination_name`, `region`, `image_url`, `description`, `view_count`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (1, '三亚', '华南', 'https://picsum.photos/id/1015/300/200', '热带海滨度假胜地，拥有美丽的海滩和丰富的海洋资源', 1250, 1, 1, '2025-10-04 15:42:23', '2025-10-04 15:42:23');
INSERT INTO `hot_destination` (`dest_id`, `destination_name`, `region`, `image_url`, `description`, `view_count`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (2, '成都', '西南', 'https://picsum.photos/id/1025/300/200', '天府之国，美食之都，拥有悠久的历史文化和现代都市魅力', 980, 2, 1, '2025-10-04 15:42:23', '2025-10-04 15:42:23');
INSERT INTO `hot_destination` (`dest_id`, `destination_name`, `region`, `image_url`, `description`, `view_count`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (3, '西安', '西北', 'https://picsum.photos/id/1035/300/200', '古都西安，兵马俑的故乡，拥有深厚的历史文化底蕴', 1150, 3, 1, '2025-10-04 15:42:23', '2025-10-04 15:42:23');
INSERT INTO `hot_destination` (`dest_id`, `destination_name`, `region`, `image_url`, `description`, `view_count`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (4, '杭州', '华东', 'https://picsum.photos/id/1045/300/200', '人间天堂，西湖美景，江南水乡的典型代表', 890, 4, 1, '2025-10-04 15:42:23', '2025-10-04 15:42:23');
INSERT INTO `hot_destination` (`dest_id`, `destination_name`, `region`, `image_url`, `description`, `view_count`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (5, '厦门', '华南', 'https://picsum.photos/id/1055/300/200', '海上花园，鼓浪屿风情，闽南文化的重要载体', 750, 5, 1, '2025-10-04 15:42:23', '2025-10-04 15:42:23');
INSERT INTO `hot_destination` (`dest_id`, `destination_name`, `region`, `image_url`, `description`, `view_count`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (6, '丽江', '西南', 'https://picsum.photos/id/1065/300/200', '古城丽江，纳西文化，雪山脚下的浪漫之都', 680, 6, 1, '2025-10-04 15:42:23', '2025-10-04 15:42:23');
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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建人ID',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序序号',
  `title` varchar(200) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '须知标题',
  `booking_deadline` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '预订截止时间说明',
  `cancellation_policy` text COLLATE utf8mb3_bin COMMENT '取消政策',
  `refund_policy` text COLLATE utf8mb3_bin COMMENT '退款政策',
  `special_requirements` text COLLATE utf8mb3_bin COMMENT '特殊要求说明',
  `contact_info` varchar(500) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系方式',
  `emergency_contact` varchar(500) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '紧急联系方式',
  PRIMARY KEY (`notice_id`),
  KEY `fk_notice_product` (`product_id`),
  CONSTRAINT `fk_notice_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品预订须知表';

-- ----------------------------
-- Records of product_booking_notice
-- ----------------------------
BEGIN;
INSERT INTO `product_booking_notice` (`notice_id`, `product_id`, `booking_conditions`, `validity_period`, `notes`, `create_time`, `update_time`, `creator_id`, `status`, `sort_order`, `title`, `booking_deadline`, `cancellation_policy`, `refund_policy`, `special_requirements`, `contact_info`, `emergency_contact`) VALUES (10001, 40001, '需提供身份证号', '111111', '1111', '2025-09-28 16:32:16', '2025-09-28 16:33:03', 20001, 1, 0, 'angdgd', '2023-01-03', 'cuhsdcucfueiagfuihueidiafefcenh', 'dewdwdwqd', 'sqsqs', 'dwdw', 'dwdw');
INSERT INTO `product_booking_notice` (`notice_id`, `product_id`, `booking_conditions`, `validity_period`, `notes`, `create_time`, `update_time`, `creator_id`, `status`, `sort_order`, `title`, `booking_deadline`, `cancellation_policy`, `refund_policy`, `special_requirements`, `contact_info`, `emergency_contact`) VALUES (10007, 40012, 'hh', 'n', 'ff', '2025-10-07 12:34:27', '2025-10-07 12:34:27', NULL, 1, 0, NULL, 'k', 'l', 'kk', 'kk', 'll', 'kk');
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
  `adult_price` decimal(10,2) DEFAULT NULL COMMENT '成人价格',
  `child_price` decimal(10,2) DEFAULT NULL COMMENT '儿童价格',
  `infant_price` decimal(10,2) DEFAULT NULL COMMENT '婴儿价格',
  `single_room_supplement` decimal(10,2) DEFAULT NULL COMMENT '单房差',
  `optional_items` text COLLATE utf8mb3_bin COMMENT '自费项目及价格',
  `price_notes` text COLLATE utf8mb3_bin COMMENT '价格说明',
  `payment_terms` text COLLATE utf8mb3_bin COMMENT '付款条件',
  `cancellation_policy` text COLLATE utf8mb3_bin COMMENT '取消政策',
  `refund_deadline` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '退款截止时间',
  `refund_rate` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '退款比例',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`cost_id`),
  KEY `fk_cost_product` (`product_id`),
  CONSTRAINT `fk_cost_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品费用说明表';

-- ----------------------------
-- Records of product_cost_explanation
-- ----------------------------
BEGIN;
INSERT INTO `product_cost_explanation` (`cost_id`, `product_id`, `include_items`, `exclude_items`, `refund_policy`, `adult_price`, `child_price`, `infant_price`, `single_room_supplement`, `optional_items`, `price_notes`, `payment_terms`, `cancellation_policy`, `refund_deadline`, `refund_rate`, `create_time`, `update_time`) VALUES (1, 40001, '• 往返机票（经济舱）\n• 3晚酒店住宿（双人标准间）\n• 每日早餐\n• 景点门票（含首道门票）\n• 专业导游服务\n• 旅游大巴接送\n• 旅游保险', '• 个人消费\n• 自费项目\n• 景区内小交通\n• 午餐和晚餐\n• 单房差\n• 签证费用（如需要）', '• 出发前7天以上取消：全额退款\n• 出发前3-7天取消：扣除30%费用\n• 出发前1-3天取消：扣除50%费用\n• 出发当天取消：扣除80%费用', 2999.00, 1999.00, 0.00, 800.00, '• 自费项目A：¥200/人\n• 自费项目B：¥150/人\n• 自费项目C：¥300/人', '• 价格包含所有基础服务\n• 儿童价格适用于2-12周岁\n• 婴儿价格适用于2周岁以下\n• 单房差需额外支付', '• 预订时需支付30%定金\n• 出发前7天支付全款\n• 支持支付宝、微信、银行卡支付', '• 因不可抗力因素取消：全额退款\n• 因个人原因取消：按退改政策执行\n• 团队人数不足取消：全额退款', '出发前7天', '按退改政策执行', '2025-09-28 14:15:38', '2025-09-28 14:15:38');
INSERT INTO `product_cost_explanation` (`cost_id`, `product_id`, `include_items`, `exclude_items`, `refund_policy`, `adult_price`, `child_price`, `infant_price`, `single_room_supplement`, `optional_items`, `price_notes`, `payment_terms`, `cancellation_policy`, `refund_deadline`, `refund_rate`, `create_time`, `update_time`) VALUES (7, 40012, 'ji', 'kk', 'k', 1.00, 1.00, 1.00, 1.00, 'j', NULL, 'k', NULL, NULL, NULL, '2025-10-07 12:34:27', '2025-10-07 12:34:27');
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
) ENGINE=InnoDB AUTO_INCREMENT=20009 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品固定每日行程表';

-- ----------------------------
-- Records of product_daily_itinerary
-- ----------------------------
BEGIN;
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (10003, 40001, 1, 'title', '111111111', 'aaaaa', '公交车', 'abc', '早上');
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (20000, 40001, 1, '第一天-抵达三亚', '抵达三亚凤凰机场，前往酒店办理入住', '含晚餐', '旅游大巴', '三亚海景度假酒店', '下午');
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (20001, 40001, 1, '第一天-海滩漫步', '下午在海滩漫步，欣赏海景', '含晚餐', '步行', '三亚海景度假酒店', '下午');
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (20002, 40001, 2, '第二天-蜈支洲岛', '前往蜈支洲岛，体验海岛风情', '含早中晚餐', '旅游大巴+轮渡', '三亚海景度假酒店', '早上');
INSERT INTO `product_daily_itinerary` (`itinerary_id`, `product_id`, `day_seq`, `title`, `description`, `meals`, `traffic`, `accommodation`, `time_period`) VALUES (20008, 40012, 1, 'hh', 'é', 'j', 'hh', 'gg', '早上');
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
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image` (
  `image_id` bigint NOT NULL AUTO_INCREMENT COMMENT '图片唯一标识',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `file_id` bigint NOT NULL COMMENT '文件ID',
  `image_url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片URL',
  `image_type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'normal' COMMENT '图片类型：main-主图，detail-详情图，cover-封面图',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序权重',
  `is_main` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为主图',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`image_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_image_type` (`image_type`),
  KEY `idx_sort_order` (`sort_order`),
  CONSTRAINT `fk_product_image_file` FOREIGN KEY (`file_id`) REFERENCES `file_info` (`file_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_product_image_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='产品图片表';

-- ----------------------------
-- Records of product_image
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_import_export_log
-- ----------------------------
DROP TABLE IF EXISTS `product_import_export_log`;
CREATE TABLE `product_import_export_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `operation_type` varchar(20) NOT NULL COMMENT '操作类型：import-导入，export-导出',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(500) DEFAULT NULL COMMENT '文件路径',
  `total_count` int DEFAULT '0' COMMENT '总数量',
  `success_count` int DEFAULT '0' COMMENT '成功数量',
  `failed_count` int DEFAULT '0' COMMENT '失败数量',
  `failed_details` text COMMENT '失败详情',
  `status` tinyint DEFAULT '1' COMMENT '状态：0-失败，1-成功，2-处理中',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`log_id`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_operation_type` (`operation_type`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品导入导出日志表';

-- ----------------------------
-- Records of product_import_export_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_promotion
-- ----------------------------
DROP TABLE IF EXISTS `product_promotion`;
CREATE TABLE `product_promotion` (
  `promotion_id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `type` varchar(20) NOT NULL COMMENT '活动类型：discount-折扣，buy2get1-买二送一，coupon-满减券，earlybird-早鸟价',
  `name` varchar(200) NOT NULL COMMENT '活动名称',
  `discount` decimal(5,2) DEFAULT NULL COMMENT '折扣率（如：20.00表示8折）',
  `min_amount` decimal(10,2) DEFAULT NULL COMMENT '满减条件-最低金额',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '满减条件-减免金额',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `status` tinyint DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `description` text COMMENT '活动描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`promotion_id`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_status` (`status`),
  KEY `idx_time_range` (`start_time`,`end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品优惠活动表';

-- ----------------------------
-- Records of product_promotion
-- ----------------------------
BEGIN;
INSERT INTO `product_promotion` (`promotion_id`, `merchant_id`, `type`, `name`, `discount`, `min_amount`, `discount_amount`, `start_time`, `end_time`, `status`, `description`, `create_time`, `update_time`) VALUES (1, 1001, 'discount', '春节特惠', 20.00, NULL, NULL, '2024-01-20 00:00:00', '2024-02-20 23:59:59', 1, '春节期间所有产品8折优惠', '2025-10-06 16:06:16', '2025-10-06 16:06:16');
INSERT INTO `product_promotion` (`promotion_id`, `merchant_id`, `type`, `name`, `discount`, `min_amount`, `discount_amount`, `start_time`, `end_time`, `status`, `description`, `create_time`, `update_time`) VALUES (2, 1001, 'coupon', '满减优惠', NULL, NULL, NULL, '2024-01-15 00:00:00', '2024-03-15 23:59:59', 1, '满1000减200，满2000减500', '2025-10-06 16:06:16', '2025-10-06 16:06:16');
COMMIT;

-- ----------------------------
-- Table structure for product_statistics
-- ----------------------------
DROP TABLE IF EXISTS `product_statistics`;
CREATE TABLE `product_statistics` (
  `stat_id` bigint NOT NULL AUTO_INCREMENT COMMENT '统计ID',
  `merchant_id` bigint NOT NULL COMMENT '商家ID',
  `stat_date` date NOT NULL COMMENT '统计日期',
  `total_products` int DEFAULT '0' COMMENT '总产品数',
  `active_products` int DEFAULT '0' COMMENT '上架产品数',
  `inactive_products` int DEFAULT '0' COMMENT '下架产品数',
  `total_sales` int DEFAULT '0' COMMENT '总销量',
  `total_revenue` decimal(15,2) DEFAULT '0.00' COMMENT '总收入',
  `average_price` decimal(10,2) DEFAULT '0.00' COMMENT '平均价格',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`stat_id`),
  UNIQUE KEY `uk_merchant_date` (`merchant_id`,`stat_date`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_stat_date` (`stat_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品统计数据表';

-- ----------------------------
-- Records of product_statistics
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for promotion_product
-- ----------------------------
DROP TABLE IF EXISTS `promotion_product`;
CREATE TABLE `promotion_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `promotion_id` bigint NOT NULL COMMENT '活动ID',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_promotion_product` (`promotion_id`,`product_id`),
  KEY `idx_promotion_id` (`promotion_id`),
  KEY `idx_product_id` (`product_id`),
  CONSTRAINT `fk_promotion_product_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_promotion_product_promotion` FOREIGN KEY (`promotion_id`) REFERENCES `product_promotion` (`promotion_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='优惠活动产品关联表';

-- ----------------------------
-- Records of promotion_product
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qualification_file
-- ----------------------------
DROP TABLE IF EXISTS `qualification_file`;
CREATE TABLE `qualification_file` (
  `qual_file_id` bigint NOT NULL AUTO_INCREMENT COMMENT '资质文件唯一标识',
  `guide_id` bigint NOT NULL COMMENT '导游ID',
  `file_id` bigint NOT NULL COMMENT '文件ID',
  `qualification_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资质类型：guide_cert-导游证，health_cert-健康证明，id_card-身份证',
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名称',
  `file_url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件URL',
  `audit_status` tinyint NOT NULL DEFAULT '0' COMMENT '审核状态：0-待审核，1-已通过，2-已驳回',
  `audit_remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核备注',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`qual_file_id`),
  KEY `idx_guide_id` (`guide_id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_qualification_type` (`qualification_type`),
  KEY `idx_audit_status` (`audit_status`),
  CONSTRAINT `fk_qualification_file_file` FOREIGN KEY (`file_id`) REFERENCES `file_info` (`file_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_qualification_file_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资质证明文件表';

-- ----------------------------
-- Records of qualification_file
-- ----------------------------
BEGIN;
INSERT INTO `qualification_file` (`qual_file_id`, `guide_id`, `file_id`, `qualification_type`, `file_name`, `file_url`, `audit_status`, `audit_remark`, `audit_time`, `create_time`) VALUES (1, 20001, 2, 'guide_cert', '导游证.pdf', 'https://example.com/uploads/qualifications/2024/12/19/guide_cert_20001_20241219_001.pdf', 1, NULL, NULL, '2025-10-04 16:41:41');
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
-- Table structure for scenic_article
-- ----------------------------
DROP TABLE IF EXISTS `scenic_article`;
CREATE TABLE `scenic_article` (
  `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章唯一标识',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
  `cover` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '封面图片URL',
  `region` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属地区',
  `merchant_id` bigint NOT NULL COMMENT '发布服务商ID',
  `merchant_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务商名称',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `featured` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否精选：0-否，1-是',
  `excerpt` text COLLATE utf8mb4_unicode_ci COMMENT '文章摘要',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '文章内容（富文本）',
  `view_count` int NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞次数',
  `share_count` int NOT NULL DEFAULT '0' COMMENT '分享次数',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-下架，1-上架',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`article_id`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_region` (`region`),
  KEY `idx_publish_time` (`publish_time`),
  KEY `idx_featured` (`featured`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='景点文章表';

-- ----------------------------
-- Records of scenic_article
-- ----------------------------
BEGIN;
INSERT INTO `scenic_article` (`article_id`, `title`, `cover`, `region`, `merchant_id`, `merchant_name`, `publish_time`, `featured`, `excerpt`, `content`, `view_count`, `like_count`, `share_count`, `status`, `create_time`, `update_time`) VALUES (1, '在丽江遇见慢时光：古城·玉龙雪山·拉市海三日游', 'https://picsum.photos/id/1015/480/280', '西南', 1, '星辰文旅', '2024-11-01 10:00:00', 1, '丽江的每一砖一瓦都浸润着古老的故事，这里整理了三日游的最佳路线与避坑建议…', '<p>丽江古城，这座有着800多年历史的古城，以其独特的纳西族建筑风格和深厚的文化底蕴吸引着世界各地的游客。</p><p>在这里，你可以漫步在石板路上，感受古城的宁静与祥和；可以登上玉龙雪山，体验雪山的壮丽与神秘；可以在拉市海边，享受湖光山色的美丽。</p>', 1333, 89, 23, 1, '2025-10-04 15:42:23', '2025-10-07 15:52:30');
INSERT INTO `scenic_article` (`article_id`, `title`, `cover`, `region`, `merchant_id`, `merchant_name`, `publish_time`, `featured`, `excerpt`, `content`, `view_count`, `like_count`, `share_count`, `status`, `create_time`, `update_time`) VALUES (2, '鼓浪屿的正确打开方式：文艺地图与美食推荐', 'https://picsum.photos/id/1025/480/280', '华南', 2, '悠游国际', '2024-10-18 09:20:00', 0, '带你走进鼓浪屿的小巷，避开拥挤人潮，收好这份私藏清单…', '<p>鼓浪屿，这座被誉为\"海上花园\"的小岛，以其独特的建筑风格和浓厚的文艺气息而闻名。</p><p>在这里，你可以漫步在欧式建筑群中，感受异国风情；可以品尝地道的闽南美食，体验当地文化；可以在海边看日落，享受浪漫时光。</p>', 1048, 67, 18, 1, '2025-10-04 15:42:23', '2025-10-07 15:52:30');
INSERT INTO `scenic_article` (`article_id`, `title`, `cover`, `region`, `merchant_id`, `merchant_name`, `publish_time`, `featured`, `excerpt`, `content`, `view_count`, `like_count`, `share_count`, `status`, `create_time`, `update_time`) VALUES (3, '三亚海滩度假指南：亚龙湾·大东海·天涯海角', 'https://picsum.photos/id/1035/480/280', '华南', 3, '晴天旅社', '2024-10-15 14:30:00', 1, '三亚的海滩是度假的天堂，这里有最清澈的海水和最细腻的沙滩…', '<p>三亚，这座位于海南岛南端的城市，以其美丽的海滩和热带风情而闻名。</p><p>亚龙湾的海水清澈见底，沙滩细腻柔软；大东海是市区最近的海滩，交通便利；天涯海角则是浪漫的象征，是情侣们必去的景点。</p>', 1218, 95, 31, 1, '2025-10-04 15:42:23', '2025-10-07 15:52:30');
INSERT INTO `scenic_article` (`article_id`, `title`, `cover`, `region`, `merchant_id`, `merchant_name`, `publish_time`, `featured`, `excerpt`, `content`, `view_count`, `like_count`, `share_count`, `status`, `create_time`, `update_time`) VALUES (4, '成都美食地图：宽窄巷子·锦里·春熙路', 'https://picsum.photos/id/1045/480/280', '西南', 1, '星辰文旅', '2024-10-12 16:45:00', 0, '成都，这座美食之都，有着数不尽的美味等待你去发现…', '<p>成都，这座被誉为\"天府之国\"的城市，以其丰富的美食文化而闻名。</p><p>宽窄巷子保留了老成都的风貌，是体验传统文化的好去处；锦里是成都最著名的美食街，各种小吃应有尽有；春熙路则是现代成都的代表，购物娱乐一应俱全。</p>', 960, 72, 19, 1, '2025-10-04 15:42:23', '2025-10-07 15:52:30');
COMMIT;

-- ----------------------------
-- Table structure for service_exception
-- ----------------------------
DROP TABLE IF EXISTS `service_exception`;
CREATE TABLE `service_exception` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '异常ID',
  `service_id` bigint NOT NULL COMMENT '服务ID',
  `exception_type` varchar(20) NOT NULL COMMENT '异常类型：equipment-设备故障，delay-服务延迟，complaint-客户投诉，safety-安全问题，other-其他',
  `description` text NOT NULL COMMENT '异常描述',
  `priority` varchar(10) NOT NULL COMMENT '紧急程度：low-低，medium-中，high-高，urgent-紧急',
  `suggestion` text COMMENT '处理建议',
  `solution` text COMMENT '处理方案',
  `status` varchar(20) NOT NULL DEFAULT 'pending' COMMENT '异常状态：pending-待处理，resolved-已解决，escalated-已升级',
  `exception_time` datetime NOT NULL COMMENT '异常时间',
  `resolve_time` datetime DEFAULT NULL COMMENT '处理时间',
  `resolve_by` varchar(100) DEFAULT NULL COMMENT '处理人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `idx_service_id` (`service_id`),
  KEY `idx_exception_type` (`exception_type`),
  KEY `idx_priority` (`priority`),
  KEY `idx_status` (`status`),
  KEY `idx_exception_time` (`exception_time`),
  CONSTRAINT `fk_service_exception_service_id` FOREIGN KEY (`service_id`) REFERENCES `service_management` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务异常表';

-- ----------------------------
-- Records of service_exception
-- ----------------------------
BEGIN;
INSERT INTO `service_exception` (`id`, `service_id`, `exception_type`, `description`, `priority`, `suggestion`, `solution`, `status`, `exception_time`, `resolve_time`, `resolve_by`, `create_time`, `create_by`) VALUES (1, 3, 'delay', '由于交通拥堵，到达景点时间比预计晚了30分钟', 'medium', '已联系客户说明情况，调整后续行程', '已联系客户说明情况，调整后续行程', 'resolved', '2024-01-25 08:30:00', '2024-01-25 08:35:00', '张导游', '2025-10-07 21:18:10', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for service_management
-- ----------------------------
DROP TABLE IF EXISTS `service_management`;
CREATE TABLE `service_management` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '服务ID',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `customer_name` varchar(100) NOT NULL COMMENT '客户姓名',
  `customer_phone` varchar(20) NOT NULL COMMENT '客户电话',
  `customer_id_card` varchar(20) DEFAULT NULL COMMENT '客户身份证号',
  `emergency_contact` varchar(100) DEFAULT NULL COMMENT '紧急联系人',
  `service_type` varchar(20) NOT NULL COMMENT '服务类型：hotel-酒店服务，attraction-景区服务，route-路线服务',
  `service_name` varchar(200) NOT NULL COMMENT '服务名称',
  `service_image` varchar(500) DEFAULT NULL COMMENT '服务图片',
  `service_location` varchar(200) NOT NULL COMMENT '服务地点',
  `service_time` datetime NOT NULL COMMENT '服务时间',
  `estimated_duration` varchar(50) DEFAULT NULL COMMENT '预计时长',
  `status` varchar(20) NOT NULL DEFAULT 'pending' COMMENT '服务状态：pending-待服务，in_progress-服务中，completed-已完成，exception-异常',
  `staff_name` varchar(100) DEFAULT NULL COMMENT '服务人员',
  `special_requirements` text COMMENT '特殊要求',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_service_type` (`service_type`),
  KEY `idx_status` (`status`),
  KEY `idx_service_time` (`service_time`),
  KEY `idx_staff_name` (`staff_name`),
  KEY `idx_customer_name` (`customer_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务管理表';

-- ----------------------------
-- Records of service_management
-- ----------------------------
BEGIN;
INSERT INTO `service_management` (`id`, `order_no`, `customer_name`, `customer_phone`, `customer_id_card`, `emergency_contact`, `service_type`, `service_name`, `service_image`, `service_location`, `service_time`, `estimated_duration`, `status`, `staff_name`, `special_requirements`, `create_time`, `update_time`, `create_by`, `update_by`, `is_deleted`) VALUES (1, 'ORD202401150001', '张三', '138****1234', '110101199001011234', '李四', 'hotel', '豪华海景套房入住服务', '/src/assets/images/travel.jpg', '三亚海景酒店', '2024-01-20 14:00:00', '30分钟', 'pending', '王服务员', '需要无烟房，高层海景', '2025-10-07 21:18:10', '2025-10-07 21:18:10', 'admin', NULL, 0);
INSERT INTO `service_management` (`id`, `order_no`, `customer_name`, `customer_phone`, `customer_id_card`, `emergency_contact`, `service_type`, `service_name`, `service_image`, `service_location`, `service_time`, `estimated_duration`, `status`, `staff_name`, `special_requirements`, `create_time`, `update_time`, `create_by`, `update_by`, `is_deleted`) VALUES (2, 'ORD202401150002', '李四', '139****5678', '110101199002021234', '王五', 'attraction', '故宫门票核验服务', '/src/assets/images/travel2.jpg', '故宫博物院', '2024-01-18 09:00:00', '10分钟', 'completed', '赵检票员', '', '2025-10-07 21:18:10', '2025-10-07 21:18:10', 'admin', NULL, 0);
INSERT INTO `service_management` (`id`, `order_no`, `customer_name`, `customer_phone`, `customer_id_card`, `emergency_contact`, `service_type`, `service_name`, `service_image`, `service_location`, `service_time`, `estimated_duration`, `status`, `staff_name`, `special_requirements`, `create_time`, `update_time`, `create_by`, `update_by`, `is_deleted`) VALUES (3, 'ORD202401150003', '王五', '137****9012', '110101199003031234', '赵六', 'route', '三亚三日游导游服务', '/src/assets/images/travel3.jpg', '三亚市区', '2024-01-25 08:00:00', '3天', 'in_progress', '张导游', '需要英文导游', '2025-10-07 21:18:10', '2025-10-07 21:18:10', 'admin', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for service_record
-- ----------------------------
DROP TABLE IF EXISTS `service_record`;
CREATE TABLE `service_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `service_id` bigint NOT NULL COMMENT '服务ID',
  `record_type` varchar(20) NOT NULL COMMENT '记录类型：checkin-入住登记，service-服务执行，completion-服务完成，other-其他',
  `record_time` datetime NOT NULL COMMENT '记录时间',
  `staff_name` varchar(100) NOT NULL COMMENT '操作人员',
  `content` text NOT NULL COMMENT '操作内容',
  `notes` text COMMENT '备注',
  `images` text COMMENT '相关图片（JSON格式存储）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `idx_service_id` (`service_id`),
  KEY `idx_record_type` (`record_type`),
  KEY `idx_record_time` (`record_time`),
  KEY `idx_staff_name` (`staff_name`),
  CONSTRAINT `fk_service_record_service_id` FOREIGN KEY (`service_id`) REFERENCES `service_management` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务记录表';

-- ----------------------------
-- Records of service_record
-- ----------------------------
BEGIN;
INSERT INTO `service_record` (`id`, `service_id`, `record_type`, `record_time`, `staff_name`, `content`, `notes`, `images`, `create_time`, `create_by`) VALUES (1, 1, 'checkin', '2024-01-20 14:05:00', '王服务员', '客户到达酒店，开始办理入住手续', '客户对房间位置很满意', NULL, '2025-10-07 21:18:10', 'admin');
INSERT INTO `service_record` (`id`, `service_id`, `record_type`, `record_time`, `staff_name`, `content`, `notes`, `images`, `create_time`, `create_by`) VALUES (2, 2, 'service', '2024-01-18 09:00:00', '赵检票员', '核验门票，客户顺利入园', '客户对服务很满意', NULL, '2025-10-07 21:18:10', 'admin');
INSERT INTO `service_record` (`id`, `service_id`, `record_type`, `record_time`, `staff_name`, `content`, `notes`, `images`, `create_time`, `create_by`) VALUES (3, 2, 'completion', '2024-01-18 09:10:00', '赵检票员', '服务完成，客户已入园游览', '', NULL, '2025-10-07 21:18:10', 'admin');
INSERT INTO `service_record` (`id`, `service_id`, `record_type`, `record_time`, `staff_name`, `content`, `notes`, `images`, `create_time`, `create_by`) VALUES (4, 3, 'service', '2024-01-25 08:00:00', '张导游', '开始导游服务，接客户前往第一个景点', '客户对导游很满意', NULL, '2025-10-07 21:18:10', 'admin');
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
  `gender` varchar(16) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '性别：male/female/other',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `bio` varchar(1024) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '个人简介',
  `location` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '所在地(逗号分隔)',
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
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10001, 'adddmm1', 'e10adc3949ba59abbe56e057f20f883e', '系统管理员', '1380013801', 'admin@tour11.com', NULL, NULL, NULL, NULL, 2, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10002, 'finance', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '财务管理员', '13800138001', 'finance@tour.com', NULL, NULL, NULL, NULL, 3, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10003, 'tourist001', 'e10adc3949ba59abbe56e057f20f883e', '张三', '13900139001', 'zhangsan@example.com', '男', '2002-08-25', '我是一只小小鸟噢', '吉林省,四平市,梨树县', 0, 1, '2025-09-26 11:28:36', '2025-10-07 12:50:46');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10004, 'tourist02', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '李四', '13900139002', 'lisi@example.com', NULL, NULL, NULL, NULL, 1, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10005, 'merchant01', 'e10adc3949ba59abbe56e057f20f883e', '王五', '13900139003', 'hotel@example.com', NULL, NULL, NULL, NULL, 4, 1, '2025-09-26 11:28:36', '2025-09-27 13:10:32');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10006, 'merchant_scenic', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '山水景区', '13900139004', 'scenic@example.com', NULL, NULL, NULL, NULL, 4, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10007, 'merchant_travel', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '环球旅行社', '13900139005', 'travel@example.com', NULL, NULL, NULL, NULL, 4, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10008, 'guide01', 'e10adc3949ba59abbe56e057f20f883e', '王五', '13900139006', 'wangwu@example.com', NULL, NULL, NULL, NULL, 4, 1, '2025-09-26 11:28:36', '2025-10-05 21:49:40');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10009, 'guide02', '$2a$10$7JB720yubVSZvUI0rEqK/.Vq1tC1j890tZp3zftliQtj693k9tD4', '赵六', '13900139007', 'zhaoliu@example.com', NULL, NULL, NULL, NULL, 5, 1, '2025-09-26 11:28:36', '2025-09-26 11:28:36');
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `real_name`, `phone`, `email`, `gender`, `birthday`, `bio`, `location`, `user_role`, `status`, `create_time`, `update_time`) VALUES (10010, 'testuser', 'e10adc3949ba59abbe56e057f20f883e', '测试用户', '13800138000', 'test@example.com', NULL, NULL, NULL, NULL, 1, 1, '2025-09-26 20:14:38', '2025-09-26 20:14:38');
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
  `product_id` bigint NOT NULL COMMENT '产品id',
  `user_id` bigint NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `useful_count` int DEFAULT '0' COMMENT '有用数量',
  PRIMARY KEY (`eval_id`),
  KEY `fk_evaluation_order` (`order_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_eval_type` (`eval_type`),
  KEY `idx_target_id` (`target_id`),
  CONSTRAINT `fk_evaluation_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`),
  CONSTRAINT `fk_evaluation_product_id` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_evaluation_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=80014 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='评价表';

-- ----------------------------
-- Records of tour_evaluation
-- ----------------------------
BEGIN;
INSERT INTO `tour_evaluation` (`eval_id`, `order_id`, `eval_type`, `target_id`, `overall_score`, `service_score`, `environment_score`, `cost_eff_score`, `content`, `img_urls`, `eval_time`, `reply_content`, `reply_time`, `product_id`, `user_id`, `create_time`, `useful_count`) VALUES (80001, 60001, 1, 40003, 5, 5, 5, 4, '行程安排合理，酒店环境很好，非常满意的一次旅行', '/images/eval/eval1.jpg', '2025-09-26 11:28:36', '感谢您的好评，期待再次为您服务', '2024-07-20 10:30:00', 40003, 10003, '2025-09-28 17:17:53', 0);
INSERT INTO `tour_evaluation` (`eval_id`, `order_id`, `eval_type`, `target_id`, `overall_score`, `service_score`, `environment_score`, `cost_eff_score`, `content`, `img_urls`, `eval_time`, `reply_content`, `reply_time`, `product_id`, `user_id`, `create_time`, `useful_count`) VALUES (80002, 60001, 2, 20001, 5, 5, NULL, NULL, '王导游服务热情，讲解专业，推荐的餐厅也很美味', NULL, '2025-09-26 11:28:36', '感谢您的认可，很高兴为您服务', '2024-07-20 11:15:00', 40003, 10003, '2025-09-28 17:17:53', 0);
INSERT INTO `tour_evaluation` (`eval_id`, `order_id`, `eval_type`, `target_id`, `overall_score`, `service_score`, `environment_score`, `cost_eff_score`, `content`, `img_urls`, `eval_time`, `reply_content`, `reply_time`, `product_id`, `user_id`, `create_time`, `useful_count`) VALUES (80003, 60004, 1, 40002, 4, 4, 5, 4, '黄山风景壮丽，景区管理有序，就是人有点多', '/images/eval/eval2.jpg', '2025-09-26 11:28:36', NULL, NULL, 40001, 10004, '2025-09-28 17:17:53', 0);
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
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `product_id` bigint DEFAULT NULL COMMENT '产品ID',
  `order_status` tinyint DEFAULT NULL COMMENT '订单状态：0-待确认，1-已确认，2-已完成，3-已取消，4-退款中',
  `pay_status` tinyint DEFAULT NULL COMMENT '支付状态：0-待支付，1-已支付，2-已退款',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价格',
  `travellers` int DEFAULT NULL COMMENT '出行人数',
  `booking_date` date DEFAULT NULL COMMENT '预订使用日期',
  `special_needs` text COLLATE utf8mb3_bin COMMENT '特殊需求',
  `receiver_name` varchar(100) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货人电话',
  `receiver_address` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `pay_type` tinyint DEFAULT NULL COMMENT '支付方式：1-微信，2-支付宝',
  PRIMARY KEY (`itinerary_id`),
  KEY `fk_itinerary_order` (`order_id`),
  KEY `fk_itinerary_guide` (`guide_id`),
  KEY `idx_tour_itinerary_user_id` (`user_id`),
  KEY `idx_tour_itinerary_order_status` (`order_status`),
  KEY `idx_tour_itinerary_booking_date` (`booking_date`),
  KEY `idx_tour_itinerary_create_time` (`create_time`),
  CONSTRAINT `fk_itinerary_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`),
  CONSTRAINT `fk_itinerary_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70005 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='行程安排表';

-- ----------------------------
-- Records of tour_itinerary
-- ----------------------------
BEGIN;
INSERT INTO `tour_itinerary` (`itinerary_id`, `order_id`, `guide_id`, `itinerary_date`, `day_seq`, `spots`, `start_time`, `end_time`, `traffic`, `dining`, `accommodation`, `status`, `progress_note`, `img_urls`, `user_id`, `product_id`, `order_status`, `pay_status`, `total_price`, `travellers`, `booking_date`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_address`, `create_time`, `update_time`, `cancel_time`, `pay_type`) VALUES (70001, 60001, 20001, '2024-07-15', 1, '三亚凤凰机场、亚龙湾海滩', '10:00:00', '18:00:00', '旅游大巴', '含午餐、晚餐自理', '三亚海景度假酒店', 2, '游客已顺利抵达，入住酒店', '/images/trip/day1_1.jpg,/images/trip/day1_2.jpg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2025-10-04 13:50:51', '2025-10-04 13:50:51', NULL, NULL);
INSERT INTO `tour_itinerary` (`itinerary_id`, `order_id`, `guide_id`, `itinerary_date`, `day_seq`, `spots`, `start_time`, `end_time`, `traffic`, `dining`, `accommodation`, `status`, `progress_note`, `img_urls`, `user_id`, `product_id`, `order_status`, `pay_status`, `total_price`, `travellers`, `booking_date`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_address`, `create_time`, `update_time`, `cancel_time`, `pay_type`) VALUES (70002, 60001, 20001, '2024-07-16', 2, '蜈支洲岛', '08:30:00', '17:30:00', '旅游大巴+轮渡', '含早中晚餐', '三亚海景度假酒店', 2, '全天海岛游玩，游客满意', '/images/trip/day2_1.jpg', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2025-10-04 13:50:51', '2025-10-04 13:50:51', NULL, NULL);
INSERT INTO `tour_itinerary` (`itinerary_id`, `order_id`, `guide_id`, `itinerary_date`, `day_seq`, `spots`, `start_time`, `end_time`, `traffic`, `dining`, `accommodation`, `status`, `progress_note`, `img_urls`, `user_id`, `product_id`, `order_status`, `pay_status`, `total_price`, `travellers`, `booking_date`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_address`, `create_time`, `update_time`, `cancel_time`, `pay_type`) VALUES (70003, 60002, 20002, '2024-08-01', 1, '天安门广场、故宫', '08:00:00', '17:00:00', '旅游大巴', '含午餐', '北京饭店', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2025-10-04 13:50:51', '2025-10-04 13:50:51', NULL, NULL);
INSERT INTO `tour_itinerary` (`itinerary_id`, `order_id`, `guide_id`, `itinerary_date`, `day_seq`, `spots`, `start_time`, `end_time`, `traffic`, `dining`, `accommodation`, `status`, `progress_note`, `img_urls`, `user_id`, `product_id`, `order_status`, `pay_status`, `total_price`, `travellers`, `booking_date`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_address`, `create_time`, `update_time`, `cancel_time`, `pay_type`) VALUES (70004, 60016, NULL, '2025-10-03', 1, '', '09:00:00', '18:00:00', '', '', '', 0, '', '', 10003, 40001, 1, 0, 914.00, 1, '2025-10-03', NULL, '张三', '1345677785', '龙洞街道', '2025-10-03 22:02:04', '2025-10-04 14:29:32', NULL, 1);
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
  `total_price` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `pay_type` tinyint DEFAULT NULL COMMENT '支付方式：1-微信，2-支付宝',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态：0-待支付，1-已支付，2-已退款',
  `order_status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态：0-待确认，1-已确认，2-已完成，3-已取消，4-退款中',
  `booking_date` date NOT NULL COMMENT '预订使用日期',
  `person_count` int NOT NULL COMMENT '出行人数',
  `special_needs` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '特殊需求',
  `receiver_name` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货人电话',
  `receiver_province` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货省份',
  `receiver_city` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货城市',
  `receiver_district` varchar(50) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收货区县',
  `receiver_address` varchar(200) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '详细收货地址',
  `order_remark` varchar(500) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单备注',
  `coupon_id` bigint DEFAULT NULL COMMENT '使用的优惠券ID',
  `coupon_discount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠券折扣金额',
  `shipping_fee` decimal(10,2) DEFAULT '0.00' COMMENT '运费',
  `product_quantity` int DEFAULT '1' COMMENT '商品数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `refund_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `travellers` int NOT NULL COMMENT '旅行人数',
  `scan_time` datetime DEFAULT NULL COMMENT '扫码时间',
  `scan_status` tinyint DEFAULT '0' COMMENT '扫码状态：0-未扫码，1-已扫码',
  `wechat_trade_no` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '微信支付交易号',
  `wechat_trade_status` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '微信支付状态',
  `scan_confirm_time` datetime DEFAULT NULL COMMENT '扫码确认时间',
  PRIMARY KEY (`order_id`),
  KEY `fk_order_user` (`user_id`),
  KEY `fk_order_product` (`product_id`),
  KEY `fk_order_guide` (`guide_id`),
  KEY `idx_scan_status` (`scan_status`),
  KEY `idx_scan_time` (`scan_time`),
  KEY `idx_wechat_trade_no` (`wechat_trade_no`),
  CONSTRAINT `fk_order_guide` FOREIGN KEY (`guide_id`) REFERENCES `guide_extend` (`guide_id`),
  CONSTRAINT `fk_order_product` FOREIGN KEY (`product_id`) REFERENCES `tour_product` (`product_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60029 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='旅游订单表';

-- ----------------------------
-- Records of tour_order
-- ----------------------------
BEGIN;
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60001, 10003, 40003, 20001, 3699.00, 1, 1, 2, '2024-07-15', 1, '需要安排无烟房', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 1, '2025-09-26 11:28:36', '2024-06-10 09:30:00', NULL, NULL, 5, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60002, 10004, 40004, 20002, 5798.00, 2, 1, 1, '2024-08-01', 2, '希望安排资深导游', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 1, '2025-09-26 11:28:36', '2024-06-15 14:20:00', NULL, NULL, 0, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60004, 10004, 40001, NULL, 899.00, 1, 1, 3, '2024-07-20', 1, '需要高层房间', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0.00, 0.00, 1, '2025-09-26 11:28:36', '2024-06-12 16:45:00', '2024-06-18 09:20:00', NULL, 0, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60007, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:54:34', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60008, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:55:19', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60009, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:55:25', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60010, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:55:43', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60011, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:55:57', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60012, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:56:01', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60013, 10003, 40001, NULL, 914.00, 1, 0, 3, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:56:04', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60014, 10003, 40001, NULL, 914.00, 1, 1, 1, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:56:38', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60015, 10003, 40001, NULL, 914.00, 1, 0, 2, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 21:56:52', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60016, 10003, 40001, NULL, 914.00, 1, 0, 1, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 22:02:04', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60017, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-03', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-03 22:02:08', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60018, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 13:55:57', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60019, 10003, 40002, NULL, 205.00, 1, 0, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 14:37:35', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60020, 10003, 40002, NULL, 205.00, 1, 0, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 14:37:45', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60021, 10003, 40002, NULL, 205.00, 1, 0, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 14:50:37', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60022, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 15:09:36', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60023, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 15:16:32', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60024, 10003, 40001, NULL, 914.00, 1, 0, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 15:19:50', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60025, 10003, 40001, NULL, 914.00, 1, 1, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 15:25:10', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60026, 10003, 40002, NULL, 205.00, 1, 1, 2, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 15:26:58', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60027, 10003, 40001, NULL, 914.00, 1, 1, 0, '2025-10-04', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-04 15:32:41', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
INSERT INTO `tour_order` (`order_id`, `user_id`, `product_id`, `guide_id`, `total_price`, `pay_type`, `pay_status`, `order_status`, `booking_date`, `person_count`, `special_needs`, `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `order_remark`, `coupon_id`, `coupon_discount`, `shipping_fee`, `product_quantity`, `create_time`, `pay_time`, `cancel_time`, `refund_amount`, `travellers`, `scan_time`, `scan_status`, `wechat_trade_no`, `wechat_trade_status`, `scan_confirm_time`) VALUES (60028, 10003, 40007, NULL, 21.00, 1, 0, 0, '2025-10-06', 1, NULL, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', NULL, NULL, 0.00, 15.00, 1, '2025-10-06 21:34:59', NULL, NULL, NULL, 1, NULL, 0, NULL, NULL, NULL);
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
  `start_date` date DEFAULT '2024-01-01',
  `end_date` date DEFAULT '2024-12-31',
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
  `attractions` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '景点清单（逗号分隔）',
  `meal_standard` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '餐饮标准',
  `accommodation_standard` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '住宿标准',
  `validity_period` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '有效期（JSON格式）',
  `entry_time` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '入场时间',
  `room_type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '房间类型',
  `capacity` int DEFAULT NULL COMMENT '容量（人数）',
  `days` int DEFAULT NULL COMMENT '行程天数',
  `facilities` text CHARACTER SET utf8mb3 COLLATE utf8mb3_bin COMMENT '设施（JSON格式）',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_merchant` (`merchant_id`),
  KEY `fk_product_admin` (`audit_admin_id`),
  KEY `fk_product_category` (`category_id`),
  KEY `idx_product_attractions` (`attractions`(100)),
  KEY `idx_product_room_type` (`room_type`),
  KEY `idx_product_capacity` (`capacity`),
  KEY `idx_product_days` (`days`),
  CONSTRAINT `fk_product_admin` FOREIGN KEY (`audit_admin_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`category_id`),
  CONSTRAINT `fk_product_merchant` FOREIGN KEY (`merchant_id`) REFERENCES `merchant_extend` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40013 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='旅游产品表';

-- ----------------------------
-- Records of tour_product
-- ----------------------------
BEGIN;
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40001, 30001, '三亚海景大床房', 2, 899.00, 1099.00, 20, '2024-06-01', '2024-12-31', '豪华海景大床房，含双早，免费WiFi，游泳池使用权', '/images/hotel/sanya1.jpg,/images/hotel/sanya2.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-10-06 22:39:46', '超值，豪华，不错', 400, '退改无忧，安全可靠', NULL, '1111111', 140006, NULL, 'aaaaaaaaaaaaaaaa', '111111111', NULL, NULL, NULL, NULL, NULL, '标准间', 2, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40002, 30002, '黄山风景区门票', 3, 190.00, 190.00, 1000, '2024-06-01', '2024-12-31', '黄山风景区成人票，含景区交通', '/images/scenic/huangshan1.jpg,/images/scenic/huangshan2.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-10-06 22:39:46', NULL, 0, NULL, NULL, NULL, 140007, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '08:00-17:00', NULL, NULL, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40003, 30003, '三亚5日4晚自由行', 1, 3699.00, 4299.00, 50, '2024-06-01', '2024-10-31', '含往返机票+四星酒店+接送机，自由活动', '/images/tour/sanya3.jpg,/images/tour/sanya4.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-10-06 22:39:46', NULL, 0, NULL, NULL, NULL, 140004, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40004, 30003, '北京4日3晚跟团游', 1, 2899.00, 3299.00, 40, '2024-06-01', '2024-12-31', '含故宫、长城、颐和园等景点，导游全程陪同', '/images/tour/beijing1.jpg,/images/tour/beijing2.jpg', 10001, 1, 1, '2025-09-26 11:28:36', '2025-10-06 22:39:46', NULL, 0, NULL, NULL, NULL, 140005, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40005, 30003, '肇庆3日游', 1, 2300.00, 3400.00, 30, '2025-07-05', '2025-04-02', '1111111111111111111111111111111111111', '/images/tour/beijing1.jpg,/images/tour/beijing2.jpg', 10001, 1, 1, '2025-09-26 20:35:27', '2025-10-06 22:39:46', '很好，不错，喜欢', 0, 'abcd，bbbb', NULL, NULL, 140004, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40006, 30001, '测试更新产品', 2, 299.00, NULL, 20, '2024-01-01', '2024-12-31', '测试描述', NULL, NULL, 0, 1, '2025-10-06 19:56:02', '2025-10-06 22:39:46', '测试,更新', 0, '测试保障', NULL, '测试卖点', NULL, '测试供应商', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '标准间', 2, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40007, 30001, 'abcdef', 1, 6.00, NULL, 300, '2024-01-01', '2024-12-31', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', NULL, NULL, 0, 1, '2025-10-06 19:56:28', '2025-10-06 22:39:46', '非常好，很棒，很棒', 0, '爆炸，服务，保障', NULL, '很有卖点', NULL, 'xx供应商', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40008, 30001, '测试创建产品', 2, 199.00, NULL, 50, '2025-10-06', '2026-10-06', '测试创建描述', NULL, NULL, 0, 1, '2025-10-06 20:40:00', '2025-10-06 22:39:46', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '标准间', 2, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40009, 30001, 'hh', 1, 1.00, NULL, 1, '2025-10-11', '2025-11-06', 'fff', NULL, NULL, 0, 1, '2025-10-06 20:44:17', '2025-10-06 22:39:46', 'ff，nn', 0, 'ss，mm', NULL, '哈哈哈哈、', NULL, 'ttt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40010, 30001, 'hhh', 1, 2.00, NULL, 2, '2025-10-06', '2026-10-06', 'ffhhhh', NULL, NULL, 0, 1, '2025-10-06 21:17:00', '2025-10-06 22:39:46', 'fff', 0, 'jjj', NULL, 'ddd', NULL, 'jjj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40011, 30001, '测试产品图片功能', 2, 299.00, NULL, 10, '2025-10-25', '2025-11-28', '测试产品图片功能是否正常', NULL, NULL, 0, 1, '2025-10-06 21:24:56', '2025-10-06 22:39:46', 'hhh，hh', 0, 'fdd，jj', NULL, 'ff', NULL, 'h', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '标准间', 2, NULL, NULL);
INSERT INTO `tour_product` (`product_id`, `merchant_id`, `product_name`, `product_type`, `price`, `original_price`, `stock`, `start_date`, `end_date`, `description`, `img_urls`, `audit_admin_id`, `audit_status`, `product_status`, `create_time`, `update_time`, `product_tags`, `sold_count`, `service_guarantees`, `main_img_url`, `product_selling_points`, `category_id`, `supplier`, `features`, `features_imgs`, `attractions`, `meal_standard`, `accommodation_standard`, `validity_period`, `entry_time`, `room_type`, `capacity`, `days`, `facilities`) VALUES (40012, 30001, 'hh', 1, 1.00, NULL, 1, '2025-10-07', '2026-10-07', 'h', NULL, NULL, 0, 1, '2025-10-06 22:01:20', '2025-10-07 12:34:27', 'h，h', 0, 'h，j', NULL, 'yy', NULL, 'uu', NULL, NULL, 'hh', 'jj', 'dd', NULL, NULL, '', 2, 3, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址唯一标识',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `receiver_name` varchar(50) COLLATE utf8mb3_bin NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) COLLATE utf8mb3_bin NOT NULL COMMENT '收货人电话',
  `province` varchar(50) COLLATE utf8mb3_bin NOT NULL COMMENT '省份',
  `city` varchar(50) COLLATE utf8mb3_bin NOT NULL COMMENT '城市',
  `district` varchar(50) COLLATE utf8mb3_bin NOT NULL COMMENT '区县',
  `detail_address` varchar(200) COLLATE utf8mb3_bin NOT NULL COMMENT '详细地址',
  `is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`address_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='用户收货地址表';

-- ----------------------------
-- Records of user_address
-- ----------------------------
BEGIN;
INSERT INTO `user_address` (`address_id`, `user_id`, `receiver_name`, `receiver_phone`, `province`, `city`, `district`, `detail_address`, `is_default`, `create_time`, `update_time`) VALUES (10001, 10003, '张三', '1345677785', '广东', '广州', '天河', '龙洞街道', 1, '2025-10-03 15:11:44', '2025-10-03 15:11:44');
COMMIT;

-- ----------------------------
-- Table structure for user_avatar
-- ----------------------------
DROP TABLE IF EXISTS `user_avatar`;
CREATE TABLE `user_avatar` (
  `avatar_id` bigint NOT NULL AUTO_INCREMENT COMMENT '头像唯一标识',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `file_id` bigint NOT NULL COMMENT '文件ID',
  `avatar_url` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头像URL',
  `is_current` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否为当前头像',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`avatar_id`),
  UNIQUE KEY `uk_user_id_current` (`user_id`,`is_current`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_file_id` (`file_id`),
  CONSTRAINT `fk_user_avatar_file` FOREIGN KEY (`file_id`) REFERENCES `file_info` (`file_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_avatar_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户头像表';

-- ----------------------------
-- Records of user_avatar
-- ----------------------------
BEGIN;
INSERT INTO `user_avatar` (`avatar_id`, `user_id`, `file_id`, `avatar_url`, `is_current`, `create_time`) VALUES (1, 10001, 1, 'https://example.com/uploads/avatars/2024/12/19/avatar_10001_20241219_001.jpg', 1, '2025-10-04 16:41:41');
INSERT INTO `user_avatar` (`avatar_id`, `user_id`, `file_id`, `avatar_url`, `is_current`, `create_time`) VALUES (19, 10003, 23, '/uploads//avatars/2025/10/04/avatar_1759581670714_ba87d95b.png', 1, '2025-10-04 20:41:11');
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

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `coupon_id` bigint NOT NULL COMMENT '优惠券ID',
  `status` tinyint DEFAULT '0' COMMENT '使用状态：0-未使用，1-已使用，2-已过期',
  `get_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `order_id` bigint DEFAULT NULL COMMENT '使用的订单ID',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_coupon_id` (`coupon_id`),
  KEY `idx_order_id` (`order_id`),
  CONSTRAINT `fk_user_coupon_coupon` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`coupon_id`),
  CONSTRAINT `fk_user_coupon_order` FOREIGN KEY (`order_id`) REFERENCES `tour_order` (`order_id`),
  CONSTRAINT `fk_user_coupon_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='用户优惠券关联表';

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- View structure for v_order_scan_stats
-- ----------------------------
DROP VIEW IF EXISTS `v_order_scan_stats`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_order_scan_stats` AS select cast(`tour_order`.`create_time` as date) AS `order_date`,count(0) AS `total_orders`,sum((case when (`tour_order`.`scan_status` = 1) then 1 else 0 end)) AS `scanned_orders`,sum((case when (`tour_order`.`scan_status` = 0) then 1 else 0 end)) AS `not_scanned_orders`,round(((sum((case when (`tour_order`.`scan_status` = 1) then 1 else 0 end)) * 100.0) / count(0)),2) AS `scan_rate_percent` from `tour_order` where (`tour_order`.`create_time` >= (now() - interval 30 day)) group by cast(`tour_order`.`create_time` as date) order by `order_date` desc;

-- ----------------------------
-- Procedure structure for CheckOrderScanStatus
-- ----------------------------
DROP PROCEDURE IF EXISTS `CheckOrderScanStatus`;
delimiter ;;
CREATE PROCEDURE `CheckOrderScanStatus`(IN order_id BIGINT)
BEGIN
    DECLARE scan_status_val TINYINT DEFAULT 0;
    DECLARE scan_time_val DATETIME DEFAULT NULL;
    
    -- 查询订单扫码状态
    SELECT scan_status, scan_time 
    INTO scan_status_val, scan_time_val
    FROM tour_order 
    WHERE order_id = order_id;
    
    -- 返回扫码状态信息
    SELECT 
        order_id,
        scan_status_val as scan_status,
        scan_time_val as scan_time,
        CASE 
            WHEN scan_status_val = 1 THEN '已扫码'
            ELSE '未扫码'
        END as status_desc;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for UpdateEvaluationUsefulCount
-- ----------------------------
DROP PROCEDURE IF EXISTS `UpdateEvaluationUsefulCount`;
delimiter ;;
CREATE PROCEDURE `UpdateEvaluationUsefulCount`(IN p_eval_id BIGINT)
BEGIN
    UPDATE tour_evaluation 
    SET useful_count = (
        SELECT COUNT(*) 
        FROM evaluation_useful 
        WHERE eval_id = p_eval_id AND status = 1
    )
    WHERE eval_id = p_eval_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for UpdateOrderScanStatus
-- ----------------------------
DROP PROCEDURE IF EXISTS `UpdateOrderScanStatus`;
delimiter ;;
CREATE PROCEDURE `UpdateOrderScanStatus`(IN order_id BIGINT,
    IN scan_status_val TINYINT,
    IN scan_time_val DATETIME)
BEGIN
    DECLARE affected_rows INT DEFAULT 0;
    
    -- 更新订单扫码状态
    UPDATE tour_order 
    SET 
        scan_status = scan_status_val,
        scan_time = scan_time_val,
        scan_confirm_time = CASE 
            WHEN scan_status_val = 1 THEN NOW()
            ELSE scan_confirm_time
        END
    WHERE order_id = order_id;
    
    -- 获取影响的行数
    SET affected_rows = ROW_COUNT();
    
    -- 返回更新结果
    SELECT 
        affected_rows as updated_rows,
        CASE 
            WHEN affected_rows > 0 THEN '更新成功'
            ELSE '更新失败，订单不存在'
        END as result_message;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table evaluation_useful
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_evaluation_useful_after_insert`;
delimiter ;;
CREATE TRIGGER `tour_management_system`.`tr_evaluation_useful_after_insert` AFTER INSERT ON `evaluation_useful` FOR EACH ROW BEGIN
    CALL UpdateEvaluationUsefulCount(NEW.eval_id);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table evaluation_useful
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_evaluation_useful_after_update`;
delimiter ;;
CREATE TRIGGER `tour_management_system`.`tr_evaluation_useful_after_update` AFTER UPDATE ON `evaluation_useful` FOR EACH ROW BEGIN
    CALL UpdateEvaluationUsefulCount(NEW.eval_id);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table evaluation_useful
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_evaluation_useful_after_delete`;
delimiter ;;
CREATE TRIGGER `tour_management_system`.`tr_evaluation_useful_after_delete` AFTER DELETE ON `evaluation_useful` FOR EACH ROW BEGIN
    CALL UpdateEvaluationUsefulCount(OLD.eval_id);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tour_order
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_order_status_update`;
delimiter ;;
CREATE TRIGGER `tour_management_system`.`tr_order_status_update` AFTER UPDATE ON `tour_order` FOR EACH ROW BEGIN
    -- 当订单状态从其他状态更新为已确认(1)时
    IF NEW.order_status = 1 AND (OLD.order_status IS NULL OR OLD.order_status != 1) THEN
        -- 检查是否已存在行程记录
        IF NOT EXISTS (
            SELECT 1 FROM tour_itinerary WHERE order_id = NEW.order_id
        ) THEN
            -- 创建新的行程记录
            INSERT INTO tour_itinerary (
                order_id, user_id, product_id, guide_id, order_status, pay_status, pay_type,
                total_price, travellers, booking_date, special_needs, receiver_name, 
                receiver_phone, receiver_address, create_time, update_time, cancel_time,
                itinerary_date, day_seq, spots, start_time, end_time, traffic, dining, 
                accommodation, status, progress_note, img_urls
            ) VALUES (
                NEW.order_id, NEW.user_id, NEW.product_id, NEW.guide_id, NEW.order_status, 
                NEW.pay_status, NEW.pay_type, NEW.total_price, NEW.travellers, NEW.booking_date, 
                NEW.special_needs, NEW.receiver_name, NEW.receiver_phone, NEW.receiver_address, 
                NEW.create_time, NOW(), NEW.cancel_time, NEW.booking_date, 1, '', 
                '09:00:00', '18:00:00', '', '', '', 0, '', ''
            );
        END IF;
    END IF;
    
    -- 当订单状态更新为已完成(2)时，更新行程记录状态
    IF NEW.order_status = 2 AND OLD.order_status != 2 THEN
        UPDATE tour_itinerary 
        SET order_status = 2, update_time = NOW()
        WHERE order_id = NEW.order_id;
    END IF;
    
    -- 当订单状态更新为已取消(3)时，更新行程记录状态
    IF NEW.order_status = 3 AND OLD.order_status != 3 THEN
        UPDATE tour_itinerary 
        SET order_status = 3, update_time = NOW(), cancel_time = NEW.cancel_time
        WHERE order_id = NEW.order_id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table tour_order
-- ----------------------------
DROP TRIGGER IF EXISTS `tr_order_scan_status_update`;
delimiter ;;
CREATE TRIGGER `tour_management_system`.`tr_order_scan_status_update` AFTER UPDATE ON `tour_order` FOR EACH ROW BEGIN
    -- 当扫码状态从未扫码变为已扫码时，记录扫码时间
    IF OLD.scan_status = 0 AND NEW.scan_status = 1 THEN
        UPDATE tour_order 
        SET scan_time = NOW()
        WHERE order_id = NEW.order_id;
    END IF;
    
    -- 当支付状态变为已支付时，记录扫码确认时间
    IF OLD.pay_status = 0 AND NEW.pay_status = 1 AND NEW.scan_status = 1 THEN
        UPDATE tour_order 
        SET scan_confirm_time = NOW()
        WHERE order_id = NEW.order_id;
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
