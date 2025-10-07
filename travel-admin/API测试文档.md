# 旅游产品管理系统后端API测试文档

## 概述
本文档提供了旅游产品管理系统后端API的测试指南，包括所有接口的测试用例和示例。

## 基础信息
- **服务地址**: http://localhost:8081/travel-admin
- **认证方式**: 通过请求头 `X-Merchant-Id` 传递商家ID
- **响应格式**: JSON

## 1. 产品管理API测试

### 1.1 获取所有产品
**请求**:
```http
GET /travel-admin/tourProduct/getAllProducts
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": [
    {
      "productId": 1,
      "productName": "北京三日游",
      "productType": 1,
      "price": 1299.00,
      "stock": 50,
      "soldCount": 23,
      "productStatus": 1,
      "createTime": "2024-01-15T10:30:00",
      "updateTime": "2024-01-20T15:45:00",
      "mainImgUrl": "/images/products/beijing_tour.jpg",
      "description": "包含故宫、天安门、长城等经典景点",
      "productTags": "经典,文化,历史",
      "serviceGuarantees": "7天无理由退款,24小时客服",
      "productSellingPoints": "专业导游讲解,含门票住宿",
      "supplier": "北京旅行社",
      "merchantId": 1001,
      "merchantName": "北京旅行社"
    }
  ]
}
```

### 1.2 创建产品
**请求**:
```http
POST /travel-admin/tourProduct/create
Headers:
  X-Merchant-Id: 1001
  Content-Type: application/json

Body:
{
  "productType": 1,
  "productName": "上海两日游",
  "price": 899.00,
  "stock": 30,
  "description": "包含外滩、东方明珠、豫园等经典景点",
  "productTags": "经典,现代,都市",
  "serviceGuarantees": "7天无理由退款,24小时客服",
  "productSellingPoints": "专业导游讲解,含门票住宿",
  "supplier": "上海旅行社"
}
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "产品创建成功",
  "data": {
    "productId": 123,
    "productName": "上海两日游",
    "createTime": "2024-01-15T10:30:00"
  }
}
```

### 1.3 更新产品
**请求**:
```http
PUT /travel-admin/tourProduct/update/123
Headers:
  X-Merchant-Id: 1001
  Content-Type: application/json

Body:
{
  "productType": 1,
  "productName": "上海两日游（更新）",
  "price": 999.00,
  "stock": 25,
  "description": "包含外滩、东方明珠、豫园等经典景点，更新版本",
  "productTags": "经典,现代,都市,更新",
  "serviceGuarantees": "7天无理由退款,24小时客服",
  "productSellingPoints": "专业导游讲解,含门票住宿",
  "supplier": "上海旅行社"
}
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "产品更新成功",
  "data": {
    "productId": 123,
    "updateTime": "2024-01-20T15:45:00"
  }
}
```

### 1.4 删除产品
**请求**:
```http
DELETE /travel-admin/tourProduct/delete/123
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "产品删除成功",
  "data": null
}
```

### 1.5 批量更新产品状态
**请求**:
```http
PUT /travel-admin/tourProduct/batchUpdateStatus
Headers:
  X-Merchant-Id: 1001
  Content-Type: application/json

Body:
{
  "productIds": [1, 2, 3],
  "status": 1
}
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "批量更新成功",
  "data": {
    "successCount": 3,
    "failedCount": 0
  }
}
```

### 1.6 获取产品详情
**请求**:
```http
GET /travel-admin/tourProduct/detail/1
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "productId": 1,
    "productName": "北京三日游",
    "productType": 1,
    "price": 1299.00,
    "stock": 50,
    "soldCount": 23,
    "productStatus": 1,
    "createTime": "2024-01-15T10:30:00",
    "updateTime": "2024-01-20T15:45:00",
    "mainImgUrl": "/images/products/beijing_tour.jpg",
    "description": "包含故宫、天安门、长城等经典景点",
    "productTags": "经典,文化,历史",
    "serviceGuarantees": "7天无理由退款,24小时客服",
    "productSellingPoints": "专业导游讲解,含门票住宿",
    "supplier": "北京旅行社",
    "merchantId": 1001,
    "merchantName": "北京旅行社"
  }
}
```

### 1.7 复制产品
**请求**:
```http
POST /travel-admin/tourProduct/copy/1
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "产品复制成功",
  "data": {
    "newProductId": 124,
    "productName": "北京三日游 (副本)"
  }
}
```

### 1.8 产品搜索
**请求**:
```http
GET /travel-admin/tourProduct/search?keyword=北京&type=1&status=1&page=1&size=10&sortBy=price&sortOrder=desc
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "搜索成功",
  "data": {
    "total": 150,
    "page": 1,
    "size": 10,
    "products": [
      {
        "productId": 1,
        "productName": "北京三日游",
        "productType": 1,
        "price": 1299.00,
        "stock": 50,
        "soldCount": 23,
        "productStatus": 1,
        "mainImgUrl": "/images/products/beijing_tour.jpg",
        "createTime": "2024-01-15T10:30:00"
      }
    ]
  }
}
```

## 2. 优惠活动管理API测试

### 2.1 创建优惠活动
**请求**:
```http
POST /travel-admin/promotion/create
Headers:
  X-Merchant-Id: 1001
  Content-Type: application/json

Body:
{
  "type": "discount",
  "name": "春节特惠",
  "discount": 20.0,
  "startTime": "2024-01-20T00:00:00",
  "endTime": "2024-02-20T23:59:59",
  "productIds": [1, 2, 3],
  "description": "春节期间所有产品8折优惠"
}
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "优惠活动创建成功",
  "data": {
    "promotionId": 1,
    "name": "春节特惠",
    "createTime": "2024-01-15T10:30:00"
  }
}
```

### 2.2 获取优惠活动列表
**请求**:
```http
GET /travel-admin/promotion/list?page=1&size=10&status=1
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": [
    {
      "promotionId": 1,
      "type": "discount",
      "name": "春节特惠",
      "discount": 20.0,
      "startTime": "2024-01-20T00:00:00",
      "endTime": "2024-02-20T23:59:59",
      "status": 1,
      "productCount": 3,
      "createTime": "2024-01-15T10:30:00"
    }
  ],
  "total": 1,
  "page": 1,
  "size": 10
}
```

### 2.3 获取优惠活动详情
**请求**:
```http
GET /travel-admin/promotion/detail/1
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "promotionId": 1,
    "merchantId": 1001,
    "type": "discount",
    "name": "春节特惠",
    "discount": 20.0,
    "startTime": "2024-01-20T00:00:00",
    "endTime": "2024-02-20T23:59:59",
    "status": 1,
    "description": "春节期间所有产品8折优惠",
    "createTime": "2024-01-15T10:30:00",
    "updateTime": "2024-01-15T10:30:00",
    "productCount": 3,
    "products": [
      {
        "productId": 1,
        "productName": "北京三日游",
        "price": 1299.00,
        "mainImgUrl": "/images/products/beijing_tour.jpg"
      }
    ]
  }
}
```

### 2.4 更新优惠活动
**请求**:
```http
PUT /travel-admin/promotion/update/1
Headers:
  X-Merchant-Id: 1001
  Content-Type: application/json

Body:
{
  "type": "discount",
  "name": "春节特惠（更新）",
  "discount": 25.0,
  "startTime": "2024-01-20T00:00:00",
  "endTime": "2024-02-20T23:59:59",
  "productIds": [1, 2, 3, 4],
  "description": "春节期间所有产品75折优惠"
}
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "优惠活动更新成功",
  "data": {
    "promotionId": 1,
    "updateTime": "2024-01-20T15:45:00"
  }
}
```

### 2.5 删除优惠活动
**请求**:
```http
DELETE /travel-admin/promotion/delete/1
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "优惠活动删除成功",
  "data": null
}
```

### 2.6 更新优惠活动状态
**请求**:
```http
PUT /travel-admin/promotion/status/1?status=0
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "状态更新成功",
  "data": {
    "promotionId": 1,
    "status": 0
  }
}
```

## 3. 统计数据API测试

### 3.1 获取产品统计数据
**请求**:
```http
GET /travel-admin/statistics/products
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "totalProducts": 150,
    "activeProducts": 120,
    "inactiveProducts": 30,
    "totalSales": 2500,
    "totalRevenue": 3250000.00,
    "averagePrice": 1300.00,
    "topSellingProducts": [
      {
        "productId": 1,
        "productName": "北京三日游",
        "salesCount": 500,
        "revenue": 649500.00
      }
    ],
    "salesTrend": [
      {
        "date": "2024-01-01",
        "sales": 25,
        "revenue": 32475.00
      }
    ]
  }
}
```

### 3.2 获取产品类型统计
**请求**:
```http
GET /travel-admin/statistics/productTypes
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": [
    {
      "productType": 1,
      "typeName": "旅行路线",
      "count": 80,
      "sales": 1500,
      "revenue": 1950000.00
    },
    {
      "productType": 2,
      "typeName": "酒店客房",
      "count": 50,
      "sales": 800,
      "revenue": 720000.00
    },
    {
      "productType": 3,
      "typeName": "景区门票",
      "count": 20,
      "sales": 200,
      "revenue": 12000.00
    }
  ]
}
```

### 3.3 获取热销产品列表
**请求**:
```http
GET /travel-admin/statistics/topSelling?limit=10
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": [
    {
      "productId": 1,
      "productName": "北京三日游",
      "salesCount": 500,
      "revenue": 649500.00
    },
    {
      "productId": 2,
      "productName": "上海两日游",
      "salesCount": 300,
      "revenue": 269700.00
    }
  ]
}
```

### 3.4 获取销售趋势数据
**请求**:
```http
GET /travel-admin/statistics/salesTrend?startDate=2024-01-01&endDate=2024-01-31
Headers:
  X-Merchant-Id: 1001
```

**预期响应**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": [
    {
      "date": "2024-01-01",
      "sales": 25,
      "revenue": 32475.00
    },
    {
      "date": "2024-01-02",
      "sales": 30,
      "revenue": 38970.00
    }
  ]
}
```

## 4. 错误响应示例

### 4.1 参数校验错误
**请求**:
```http
POST /travel-admin/tourProduct/create
Headers:
  X-Merchant-Id: 1001
  Content-Type: application/json

Body:
{
  "productName": "",
  "price": -100
}
```

**预期响应**:
```json
{
  "code": 40001,
  "msg": "产品名称不能为空",
  "data": null
}
```

### 4.2 权限错误
**请求**:
```http
DELETE /travel-admin/tourProduct/delete/1
Headers:
  X-Merchant-Id: 9999
```

**预期响应**:
```json
{
  "code": 0,
  "msg": "删除失败：产品不存在或无权限操作",
  "data": null
}
```

### 4.3 系统错误
**请求**:
```http
GET /travel-admin/tourProduct/detail/999999
```

**预期响应**:
```json
{
  "code": 0,
  "msg": "获取失败：产品不存在",
  "data": null
}
```

## 5. 测试工具推荐

### 5.1 Postman
1. 导入API测试集合
2. 设置环境变量：`baseUrl = http://localhost:8081/travel-admin`
3. 设置全局请求头：`X-Merchant-Id: 1001`

### 5.2 curl命令
```bash
# 获取所有产品
curl -X GET "http://localhost:8081/travel-admin/tourProduct/getAllProducts" \
  -H "X-Merchant-Id: 1001"

# 创建产品
curl -X POST "http://localhost:8081/travel-admin/tourProduct/create" \
  -H "X-Merchant-Id: 1001" \
  -H "Content-Type: application/json" \
  -d '{
    "productType": 1,
    "productName": "测试产品",
    "price": 999.00,
    "stock": 10,
    "description": "测试产品描述"
  }'
```

## 6. 注意事项

1. **商家ID**: 所有请求都需要在请求头中传递有效的商家ID
2. **数据格式**: 请求和响应都使用JSON格式
3. **时间格式**: 时间字段使用ISO 8601格式（如：2024-01-15T10:30:00）
4. **分页参数**: 分页查询默认page=1，size=10
5. **错误处理**: 所有错误都会返回统一的错误格式
6. **权限控制**: 商家只能操作自己的产品数据

## 7. 部署和运行

1. **启动数据库**: 确保MySQL数据库运行在localhost:3306
2. **执行SQL脚本**: 运行`add_product_promotion_tables.sql`创建新表
3. **启动应用**: 运行`TravelAdminApplication.main()`方法
4. **验证启动**: 访问http://localhost:8081/travel-admin确认服务正常

## 8. 性能测试建议

1. **并发测试**: 使用JMeter或类似工具进行并发请求测试
2. **数据量测试**: 测试大量数据下的查询性能
3. **响应时间**: 监控API响应时间，确保在可接受范围内
4. **内存使用**: 监控应用内存使用情况
5. **数据库性能**: 监控数据库查询性能

这个测试文档涵盖了所有主要的API接口，可以作为开发和测试的参考指南。
