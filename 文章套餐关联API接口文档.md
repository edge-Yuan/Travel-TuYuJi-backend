# 文章套餐关联API接口文档

## 概述
本文档描述了文章套餐关联管理的完整API接口，包括CRUD操作、批量操作、排序管理等功能。

## 基础信息
- **基础路径：** `/travel-portal/article-package`
- **API版本：** v1.0
- **支持格式：** JSON
- **认证方式：** 根据系统配置

## 接口列表

### 1. 添加文章套餐关联
- **接口路径：** `POST /travel-portal/article-package/add`
- **功能描述：** 为文章添加套餐关联
- **请求参数：**
  ```json
  {
    "articleId": 1,           // 文章ID（必填）
    "productId": 2,           // 产品ID（必填）
    "packageName": "豪华套餐", // 套餐名称（可选）
    "packageImage": "image.jpg", // 套餐图片（可选）
    "packagePrice": 999.00,   // 套餐价格（可选）
    "sortOrder": 1            // 排序权重（可选）
  }
  ```
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": true
  }
  ```

### 2. 更新文章套餐关联
- **接口路径：** `PUT /travel-portal/article-package/update`
- **功能描述：** 更新文章套餐关联信息
- **请求参数：**
  ```json
  {
    "relId": 1,               // 关联ID（必填）
    "articleId": 1,           // 文章ID（可选）
    "productId": 2,           // 产品ID（可选）
    "packageName": "升级套餐", // 套餐名称（可选）
    "packageImage": "new.jpg", // 套餐图片（可选）
    "packagePrice": 1299.00,  // 套餐价格（可选）
    "sortOrder": 2            // 排序权重（可选）
  }
  ```
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": true
  }
  ```

### 3. 删除文章套餐关联
- **接口路径：** `DELETE /travel-portal/article-package/{relId}`
- **功能描述：** 删除指定的文章套餐关联
- **路径参数：**
  - `relId`: 关联ID
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": true
  }
  ```

### 4. 根据文章ID获取套餐关联列表
- **接口路径：** `GET /travel-portal/article-package/article/{articleId}`
- **功能描述：** 获取指定文章的所有套餐关联
- **路径参数：**
  - `articleId`: 文章ID
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": [
      {
        "relId": 1,
        "articleId": 1,
        "productId": 2,
        "packageName": "豪华套餐",
        "packageImage": "image.jpg",
        "packagePrice": 999.00,
        "sortOrder": 1,
        "productName": "北京三日游",
        "mainImgUrl": "product.jpg",
        "price": 899.00,
        "createTime": "2024-12-19T10:00:00"
      }
    ]
  }
  ```

### 5. 根据关联ID获取套餐关联详情
- **接口路径：** `GET /travel-portal/article-package/{relId}`
- **功能描述：** 获取指定关联的详细信息
- **路径参数：**
  - `relId`: 关联ID
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": {
      "relId": 1,
      "articleId": 1,
      "productId": 2,
      "packageName": "豪华套餐",
      "packageImage": "image.jpg",
      "packagePrice": 999.00,
      "sortOrder": 1,
      "productName": "北京三日游",
      "mainImgUrl": "product.jpg",
      "price": 899.00,
      "createTime": "2024-12-19T10:00:00"
    }
  }
  ```

### 6. 批量操作文章套餐关联
- **接口路径：** `POST /travel-portal/article-package/batch`
- **功能描述：** 批量添加、更新或删除文章套餐关联
- **请求参数：**
  ```json
  {
    "articleId": 1,           // 文章ID（必填）
    "operationType": "ADD",   // 操作类型：ADD/UPDATE/DELETE
    "packages": [             // 套餐列表（必填）
      {
        "relId": 1,           // 关联ID（UPDATE/DELETE时必填）
        "productId": 2,       // 产品ID（ADD时必填）
        "packageName": "豪华套餐",
        "packageImage": "image.jpg",
        "packagePrice": 999.00,
        "sortOrder": 1
      }
    ]
  }
  ```
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": true
  }
  ```

### 7. 检查文章套餐关联是否存在
- **接口路径：** `GET /travel-portal/article-package/check`
- **功能描述：** 检查指定的文章和产品是否已有关联
- **请求参数：**
  - `articleId`: 文章ID
  - `productId`: 产品ID
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": true
  }
  ```

### 8. 更新套餐关联排序
- **接口路径：** `PUT /travel-portal/article-package/{relId}/sort`
- **功能描述：** 更新套餐关联的排序权重
- **路径参数：**
  - `relId`: 关联ID
- **请求参数：**
  - `sortOrder`: 排序权重
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": true
  }
  ```

### 9. 获取所有文章套餐关联列表
- **接口路径：** `GET /travel-portal/article-package/list`
- **功能描述：** 获取所有文章套餐关联列表
- **响应示例：**
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": [
      {
        "relId": 1,
        "articleId": 1,
        "productId": 2,
        "packageName": "豪华套餐",
        "packageImage": "image.jpg",
        "packagePrice": 999.00,
        "sortOrder": 1,
        "createTime": "2024-12-19T10:00:00"
      }
    ]
  }
  ```

## 错误码说明

| 错误码 | 说明 |
|--------|------|
| 200 | 操作成功 |
| 400 | 请求参数错误 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 数据模型

### ArticlePackageDTO
```json
{
  "relId": "Long",           // 关联唯一标识
  "articleId": "Long",       // 文章ID
  "productId": "Long",       // 产品ID
  "packageName": "String",   // 套餐名称
  "packageImage": "String",  // 套餐图片
  "packagePrice": "BigDecimal", // 套餐价格
  "sortOrder": "Integer"     // 排序权重
}
```

### ArticlePackageVO
```json
{
  "relId": "Long",           // 关联唯一标识
  "articleId": "Long",       // 文章ID
  "productId": "Long",       // 产品ID
  "packageName": "String",   // 套餐名称
  "packageImage": "String",  // 套餐图片
  "packagePrice": "BigDecimal", // 套餐价格
  "sortOrder": "Integer",    // 排序权重
  "productName": "String",   // 产品名称
  "mainImgUrl": "String",    // 产品主图
  "price": "BigDecimal",     // 产品价格
  "createTime": "LocalDateTime" // 创建时间
}
```

## 使用示例

### 前端调用示例（JavaScript）
```javascript
// 添加文章套餐关联
const addPackage = async (articleId, productId) => {
  const response = await fetch('/travel-portal/article-package/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      articleId: articleId,
      productId: productId,
      packageName: '豪华套餐',
      packagePrice: 999.00,
      sortOrder: 1
    })
  });
  return response.json();
};

// 获取文章套餐关联列表
const getArticlePackages = async (articleId) => {
  const response = await fetch(`/travel-portal/article-package/article/${articleId}`);
  return response.json();
};

// 批量操作
const batchOperate = async (articleId, packages, operationType) => {
  const response = await fetch('/travel-portal/article-package/batch', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      articleId: articleId,
      operationType: operationType,
      packages: packages
    })
  });
  return response.json();
};
```

## 注意事项

1. **数据验证：** 所有必填字段都会进行验证，确保数据完整性
2. **重复检查：** 添加关联时会检查是否已存在相同的文章-产品关联
3. **事务处理：** 批量操作使用事务处理，确保数据一致性
4. **排序管理：** 支持通过sortOrder字段控制套餐显示顺序
5. **关联查询：** 获取套餐信息时会自动关联产品表，返回完整的产品信息
6. **错误处理：** 所有操作都有完善的错误处理和日志记录

## 更新日志

- **v1.0 (2024-12-19):** 初始版本，包含完整的CRUD操作和批量管理功能
