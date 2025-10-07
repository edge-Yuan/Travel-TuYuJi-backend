# 用户统计API接口文档

## 概述
本文档描述了为个人中心页面用户统计信息新增的三个API接口，用于获取用户的行程数量、订单数量和评价数量。

## API接口列表

### 1. 获取用户行程数量

**接口地址：** `GET /travel-portal/tourItinerary/travelRoute/user/{userId}/count`

**功能描述：** 获取指定用户的所有行程数量

**请求参数：**
- `userId` (路径参数): 用户ID，类型为Long

**响应示例：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": 5
}
```

**实现位置：** `TourItineraryController.getUserItineraryCount()`

---

### 2. 获取用户订单数量

**接口地址：** `GET /travel-portal/tourOrder/order/user/{userId}/count`

**功能描述：** 获取指定用户的所有订单数量

**请求参数：**
- `userId` (路径参数): 用户ID，类型为Long

**响应示例：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": 12
}
```

**实现位置：** `TourOrderController.getUserOrderCount()`

---

### 3. 获取用户评价数量

**接口地址：** `GET /travel-portal/evaluation/user/{userId}/count`

**功能描述：** 获取指定用户的所有评价数量

**请求参数：**
- `userId` (路径参数): 用户ID，类型为Long

**响应示例：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": 8
}
```

**实现位置：** `UnifiedEvaluationController.getUserEvaluationCount()`

---

## 技术实现细节

### 数据查询逻辑

1. **行程数量查询：**
   - 直接查询 `tour_itinerary` 表中 `user_id` 字段等于指定用户ID的记录数量
   - 使用 MyBatis-Plus 的 `lambdaQuery()` 方法进行条件查询

2. **订单数量查询：**
   - 直接查询 `tour_order` 表中 `user_id` 字段等于指定用户ID的记录数量
   - 使用 MyBatis-Plus 的 `lambdaQuery()` 方法进行条件查询

3. **评价数量查询：**
   - 直接查询 `tour_evaluation` 表中 `user_id` 字段等于指定用户ID的记录数量
   - 使用 `TourEvaluationMapper.selectCount()` 方法配合 `LambdaQueryWrapper` 进行条件查询

### 错误处理

所有接口都包含完整的异常处理机制：
- 记录详细的日志信息（请求参数、执行结果、异常信息）
- 返回统一的错误响应格式
- 确保接口的健壮性和可维护性

### 日志记录

每个接口都会记录以下日志：
- 请求开始：记录用户ID
- 执行结果：记录查询到的数量
- 异常情况：记录详细的错误信息

## 前端调用示例

```javascript
// 获取用户统计数据
async loadUserStats() {
  const userId = this.userInfo.userId;
  
  try {
    const [itineraryRes, orderRes, evaluationRes] = await Promise.allSettled([
      this.$http.get(`/travel-portal/tourItinerary/travelRoute/user/${userId}/count`),
      this.$http.get(`/travel-portal/tourOrder/order/user/${userId}/count`),
      this.$http.get(`/travel-portal/evaluation/user/${userId}/count`)
    ]);
    
    // 处理行程数量
    if (itineraryRes.status === 'fulfilled') {
      this.stats.itineraryCount = itineraryRes.value.data.data || 0;
    }
    
    // 处理订单数量
    if (orderRes.status === 'fulfilled') {
      this.stats.orderCount = orderRes.value.data.data || 0;
    }
    
    // 处理评价数量
    if (evaluationRes.status === 'fulfilled') {
      this.stats.evaluationCount = evaluationRes.value.data.data || 0;
    }
    
  } catch (error) {
    console.error('获取用户统计数据失败:', error);
  }
}
```

## 注意事项

1. **权限控制：** 建议在实际部署时添加用户身份验证，确保用户只能查询自己的统计数据
2. **性能优化：** 对于大量数据的场景，可以考虑添加缓存机制
3. **数据一致性：** 确保数据库中的用户ID字段存在且正确关联
4. **接口版本：** 建议在接口路径中添加版本号，便于后续升级维护

## 更新日期
2025年1月27日

## 维护人员
wanderlust
