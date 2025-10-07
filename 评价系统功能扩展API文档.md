# 评价系统功能扩展API文档

## 概述
本文档描述了评价系统新增的回复功能、有用功能和优惠券功能的后端API接口。

## 基础配置
- **基础路径**: `/travel-portal/evaluation`
- **认证方式**: JWT Token
- **响应格式**: JSON
- **数据库**: MySQL (tour_management_system)

## 数据库表结构

### 1. 评价回复表 (evaluation_reply)
```sql
CREATE TABLE evaluation_reply (
    reply_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '回复ID',
    eval_id BIGINT NOT NULL COMMENT '评价ID',
    reply_content TEXT NOT NULL COMMENT '回复内容',
    reply_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
    replier_id BIGINT NOT NULL COMMENT '回复者ID',
    replier_name VARCHAR(100) COMMENT '回复者姓名',
    replier_role VARCHAR(20) COMMENT '回复者角色(merchant/admin)',
    status TINYINT DEFAULT 1 COMMENT '状态(1-正常,0-删除)',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);
```

### 2. 评价有用表 (evaluation_useful)
```sql
CREATE TABLE evaluation_useful (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    eval_id BIGINT NOT NULL COMMENT '评价ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    useful_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '标记时间',
    status TINYINT DEFAULT 1 COMMENT '状态(1-有用,0-取消)',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_eval_user (eval_id, user_id)
);
```

### 3. 评价优惠券表 (evaluation_coupon)
```sql
CREATE TABLE evaluation_coupon (
    coupon_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '优惠券ID',
    eval_id BIGINT NOT NULL COMMENT '评价ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    coupon_code VARCHAR(50) NOT NULL COMMENT '优惠券编码',
    amount DECIMAL(10,2) NOT NULL COMMENT '优惠券金额',
    status VARCHAR(20) DEFAULT 'active' COMMENT '状态(active-有效,used-已使用,expired-已过期)',
    claim_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
    expire_time DATETIME NOT NULL COMMENT '过期时间',
    used_time DATETIME NULL COMMENT '使用时间',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY uk_eval_user (eval_id, user_id),
    UNIQUE KEY uk_coupon_code (coupon_code)
);
```

## API接口列表

### 1. 回复评价功能

#### 1.1 添加/更新回复（新版本）
- **接口**: `POST /travel-portal/evaluation/reply/{evalId}/new`
- **描述**: 商家或管理员对评价进行回复
- **权限**: 商家(merchant)、管理员(admin)

**请求参数**:
```json
{
  "replyContent": "感谢您的评价，我们会继续努力提供更好的服务！"
}
```

**URL参数**:
- `evalId`: 评价ID
- `replierId`: 回复者ID
- `replierName`: 回复者姓名
- `replierRole`: 回复者角色

**响应格式**:
```json
{
  "code": 1,
  "msg": "操作成功",
  "data": {
    "replyId": 123,
    "evalId": 456,
    "replyContent": "感谢您的评价，我们会继续努力提供更好的服务！",
    "replyTime": "2024-01-15T10:30:00Z",
    "replierId": 789,
    "replierName": "商家客服",
    "replierRole": "merchant"
  }
}
```

#### 1.2 获取回复列表
- **接口**: `GET /travel-portal/evaluation/reply/{evalId}`
- **描述**: 获取指定评价的回复列表

**响应格式**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": [
    {
      "replyId": 123,
      "evalId": 456,
      "replyContent": "感谢您的评价...",
      "replyTime": "2024-01-15T10:30:00Z",
      "replierId": 789,
      "replierName": "商家客服",
      "replierRole": "merchant"
    }
  ]
}
```

#### 1.3 删除回复
- **接口**: `DELETE /travel-portal/evaluation/reply/{replyId}`
- **描述**: 删除指定的回复

**响应格式**:
```json
{
  "code": 1,
  "msg": "删除成功",
  "data": true
}
```

### 2. 有用功能

#### 2.1 切换有用状态
- **接口**: `POST /travel-portal/evaluation/useful/{evalId}`
- **描述**: 用户对评价进行有用/无用标记
- **权限**: 所有登录用户

**请求参数**:
- `userId`: 用户ID

**响应格式**:
```json
{
  "code": 1,
  "msg": "操作成功",
  "data": {
    "evalId": 456,
    "isUseful": true,
    "usefulCount": 15,
    "userId": 10003
  }
}
```

#### 2.2 获取有用状态
- **接口**: `GET /travel-portal/evaluation/useful/{evalId}`
- **描述**: 获取当前用户对指定评价的有用状态

**请求参数**:
- `userId`: 用户ID

**响应格式**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "evalId": 456,
    "isUseful": true,
    "usefulCount": 15,
    "userId": 10003
  }
}
```

#### 2.3 获取有用用户列表
- **接口**: `GET /travel-portal/evaluation/useful/{evalId}/users`
- **描述**: 获取标记为有用的用户列表
- **参数**: 
  - `page`: 页码 (默认1)
  - `size`: 每页大小 (默认10)

**响应格式**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "total": 15,
    "page": 1,
    "size": 10,
    "users": [
      {
        "userId": 10003,
        "userName": "张***",
        "usefulTime": "2024-01-15T10:30:00Z"
      }
    ]
  }
}
```

### 3. 优惠券功能

#### 3.1 领取评价优惠券
- **接口**: `POST /travel-portal/evaluation/coupon/{evalId}`
- **描述**: 用户领取评价奖励的优惠券
- **权限**: 评价作者本人

**请求参数**:
- `userId`: 用户ID

**响应格式**:
```json
{
  "code": 1,
  "msg": "操作成功",
  "data": {
    "couponId": 789,
    "couponCode": "EVAL20240115001",
    "amount": 20.00,
    "expireTime": "2024-02-15T23:59:59Z",
    "status": "active"
  }
}
```

#### 3.2 检查优惠券领取状态
- **接口**: `GET /travel-portal/evaluation/coupon/{evalId}/status`
- **描述**: 检查用户是否已领取该评价的优惠券

**请求参数**:
- `userId`: 用户ID

**响应格式**:
```json
{
  "code": 1,
  "msg": "获取成功",
  "data": {
    "canClaim": false,
    "claimed": true,
    "couponId": 789,
    "claimTime": "2024-01-15T10:30:00Z"
  }
}
```

## 业务规则

### 1. 回复功能规则
- 只有商家(merchant)和管理员(admin)可以回复评价
- 每个评价只能有一个回复
- 回复内容限制5-500个字符
- 回复后不能修改，只能删除

### 2. 有用功能规则
- 每个用户对每个评价只能标记一次有用/无用
- 用户可以取消有用标记
- 有用数量实时统计

### 3. 优惠券功能规则
- 只有评价作者本人可以领取优惠券
- 每个评价只能领取一次优惠券
- 优惠券有效期30天
- 优惠券金额固定20元

## 错误码定义

| 错误码 | 说明 |
|--------|------|
| 40001 | 评价不存在 |
| 40002 | 无权限操作 |
| 40003 | 回复内容格式错误 |
| 40004 | 已存在回复 |
| 40005 | 优惠券已领取 |
| 40006 | 不是评价作者 |
| 40007 | 优惠券已过期 |

## 前端集成示例

### 1. 权限控制
```javascript
// 检查用户角色
const userRole = getUserRole() // 'tourist', 'merchant', 'guide', 'admin'
const canReply = ['merchant', 'admin'].includes(userRole)
```

### 2. API调用示例
```javascript
// 切换有用状态
async function toggleUseful(evalId, userId) {
    try {
        const response = await axios.post(`/travel-portal/evaluation/useful/${evalId}`, null, {
            params: { userId }
        });
        return response.data;
    } catch (error) {
        console.error('切换有用状态失败:', error);
        throw error;
    }
}

// 获取有用状态
async function getUsefulStatus(evalId, userId) {
    try {
        const response = await axios.get(`/travel-portal/evaluation/useful/${evalId}`, {
            params: { userId }
        });
        return response.data;
    } catch (error) {
        console.error('获取有用状态失败:', error);
        throw error;
    }
}

// 领取优惠券
async function claimCoupon(evalId, userId) {
    try {
        const response = await axios.post(`/travel-portal/evaluation/coupon/${evalId}`, null, {
            params: { userId }
        });
        return response.data;
    } catch (error) {
        console.error('领取优惠券失败:', error);
        throw error;
    }
}

// 添加回复
async function addReply(evalId, replyContent, replierId, replierName, replierRole) {
    try {
        const response = await axios.post(`/travel-portal/evaluation/reply/${evalId}/new`, {
            replyContent
        }, {
            params: { replierId, replierName, replierRole }
        });
        return response.data;
    } catch (error) {
        console.error('添加回复失败:', error);
        throw error;
    }
}
```

### 3. Vue组件集成示例
```vue
<template>
  <div class="evaluation-item">
    <!-- 评价内容 -->
    <div class="evaluation-content">{{ evaluation.content }}</div>
    
    <!-- 有用功能 -->
    <div class="useful-section">
      <button 
        @click="toggleUseful" 
        :class="{ active: isUseful }"
        :disabled="usefulLoading"
      >
        👍 有用 ({{ usefulCount }})
      </button>
    </div>
    
    <!-- 回复功能 -->
    <div class="reply-section" v-if="canReply">
      <button @click="showReplyForm = true">回复</button>
      <div v-if="showReplyForm" class="reply-form">
        <textarea v-model="replyContent" placeholder="请输入回复内容"></textarea>
        <button @click="submitReply">提交回复</button>
      </div>
    </div>
    
    <!-- 优惠券功能 -->
    <div class="coupon-section" v-if="canClaimCoupon">
      <button @click="claimCoupon" :disabled="couponClaimed">
        {{ couponClaimed ? '已领取' : '领取优惠券' }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      evaluation: {},
      isUseful: false,
      usefulCount: 0,
      usefulLoading: false,
      canReply: false,
      showReplyForm: false,
      replyContent: '',
      canClaimCoupon: false,
      couponClaimed: false
    }
  },
  methods: {
    async toggleUseful() {
      this.usefulLoading = true;
      try {
        const response = await this.toggleUsefulAPI(this.evaluation.evalId, this.userId);
        this.isUseful = response.data.isUseful;
        this.usefulCount = response.data.usefulCount;
      } catch (error) {
        this.$message.error('操作失败');
      } finally {
        this.usefulLoading = false;
      }
    },
    
    async submitReply() {
      try {
        await this.addReplyAPI(
          this.evaluation.evalId,
          this.replyContent,
          this.userId,
          this.userName,
          this.userRole
        );
        this.$message.success('回复成功');
        this.showReplyForm = false;
        this.replyContent = '';
      } catch (error) {
        this.$message.error('回复失败');
      }
    },
    
    async claimCoupon() {
      try {
        const response = await this.claimCouponAPI(this.evaluation.evalId, this.userId);
        this.$message.success('优惠券领取成功！');
        this.couponClaimed = true;
      } catch (error) {
        this.$message.error('领取失败');
      }
    }
  }
}
</script>
```

## 部署说明

### 1. 数据库初始化
```bash
# 执行数据库脚本
mysql -u root -p tour_management_system < database/quick_fix_evaluation_tables.sql
```

### 2. 应用重启
```bash
# 重启Spring Boot应用以加载新的Mapper配置
./mvnw spring-boot:run
```

### 3. 验证部署
```bash
# 测试API接口
curl -X POST "http://localhost:8081/travelManagementSystem/travel-portal/evaluation/useful/80013?userId=10003"
```

## 注意事项

1. **权限验证**: 确保前端正确传递用户角色信息
2. **数据一致性**: 有用数量通过触发器自动更新
3. **性能优化**: 大量数据时考虑分页和缓存
4. **错误处理**: 前端需要处理各种错误情况
5. **安全性**: 验证用户身份和权限

## 更新日志

- **2024-01-15**: 初始版本，实现回复、有用、优惠券功能
- **2024-01-15**: 修复数据库表结构问题
- **2024-01-15**: 添加MyBatis XML映射文件

