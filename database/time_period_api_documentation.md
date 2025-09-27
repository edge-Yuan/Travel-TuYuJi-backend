# 产品每日行程时间段功能API文档

## 概述
为产品每日行程添加了时间段字段，支持更精确的行程时间管理。

## 数据库变更

### 新增字段
- 表名：`product_daily_itinerary`
- 字段名：`time_period`
- 类型：`varchar(20)`
- 允许值：`早上`、`中午`、`下午`、`晚上`
- 默认值：`NULL`

### 执行SQL脚本
```sql
-- 执行数据库迁移脚本
source database/add_time_period_field.sql
```

## API接口

### 1. 获取有效时间段列表
- **URL**: `GET /travel-portal/productDailyItinerary/timePeriods`
- **描述**: 获取所有有效的时间段选项
- **响应示例**:
```json
{
  "code": 200,
  "message": "success",
  "data": ["早上", "中午", "下午", "晚上"]
}
```

### 2. 添加产品每日行程（已更新）
- **URL**: `POST /travel-portal/productDailyItinerary/add`
- **请求体**:
```json
{
  "productId": 40001,
  "daySeq": 1,
  "title": "抵达三亚-海滩漫步",
  "description": "抵达三亚，前往酒店办理入住，下午在海滩漫步",
  "meals": "含晚餐",
  "traffic": "旅游大巴",
  "accommodation": "三亚海景度假酒店",
  "timePeriod": "下午"
}
```

### 3. 更新产品每日行程（已更新）
- **URL**: `PUT /travel-portal/productDailyItinerary/update/{itineraryId}`
- **请求体**: 同添加接口

### 4. 获取产品每日行程列表（已更新）
- **URL**: `GET /travel-portal/productDailyItinerary/list/{productId}`
- **响应示例**:
```json
{
  "code": 200,
  "message": "success",
  "data": [
    {
      "itineraryId": 1,
      "productId": 40001,
      "daySeq": 1,
      "title": "抵达三亚-海滩漫步",
      "description": "抵达三亚，前往酒店办理入住，下午在海滩漫步",
      "meals": "含晚餐",
      "traffic": "旅游大巴",
      "accommodation": "三亚海景度假酒店",
      "timePeriod": "下午"
    }
  ]
}
```

## 数据验证

### 时间段验证规则
- 只允许以下四个值：`早上`、`中午`、`下午`、`晚上`
- 使用自定义验证注解 `@ValidTimePeriod`
- 验证失败时返回错误信息：`"时间段只能是：早上、中午、下午、晚上"`

### 验证示例
```java
// 有效的时间段
"早上" ✅
"中午" ✅  
"下午" ✅
"晚上" ✅

// 无效的时间段
"凌晨" ❌
"深夜" ❌
"" ❌
null ❌ (由@NotNull处理)
```

## 工具类

### TimePeriodUtils
提供时间段相关的工具方法：

```java
// 获取所有有效时间段
List<String> periods = TimePeriodUtils.getAllValidTimePeriods();

// 根据索引获取时间段
String period = TimePeriodUtils.getTimePeriodByIndex(0); // "早上"

// 根据天数序号获取时间段
String period = TimePeriodUtils.getTimePeriodByDaySeq(1); // "早上"

// 验证时间段是否有效
boolean valid = TimePeriodUtils.isValidTimePeriod("早上"); // true
```

## 前端集成

### 时间段选择器
前端可以使用以下方式获取时间段选项：

```javascript
// 获取有效时间段列表
async getTimePeriods() {
  const response = await request.get('/travel-portal/productDailyItinerary/timePeriods');
  return response.data; // ["早上", "中午", "下午", "晚上"]
}
```

### 表单验证
前端表单验证规则：
- 时间段为必选项
- 只能选择预定义的四个时间段之一

## 测试

### 运行测试
```bash
mvn test -Dtest=TimePeriodValidatorTest
```

### 测试覆盖
- 有效时间段验证
- 无效时间段验证
- null值处理
- 工具类功能测试

## 注意事项

1. **数据库迁移**: 执行SQL脚本前请备份数据库
2. **向后兼容**: 现有数据的时间段字段为NULL，不影响现有功能
3. **默认值**: 建议为现有数据设置默认时间段
4. **扩展性**: 如需添加新的时间段，需要同时更新验证器和工具类
