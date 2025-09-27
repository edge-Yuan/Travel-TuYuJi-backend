<template>
    <el-row>
        <el-col :span="24" v-if="singlePackage">
            <el-card class="box-card" v-loading.fullscreen.lock="fullscreenLoading">
                <div class="grid-content bg-purple-dark">
                    <div class="title-row">
                        <div class="title">{{ singlePackage.productName }}</div>
                        <div class="button-buy">
                            <el-button type="primary" round @click="openFullScreenAndBuy">立即购买</el-button>
                        </div>
                    </div>
                    <div class="discriptions">
                        {{ singlePackage.description }}
                        <br />
                        <div class="span-container" v-for="(tag, index) in splitProductTags" :key="index">
                            <span class="span1">{{ tag }}</span>
                        </div>
                        <div class="price-row">
                            <div class="price">
                                <span class="price-symbol">￥</span>
                                <span class="price-text">{{ formatPrice(singlePackage.price) }}</span>
                            </div>
                            <div class="salesDesc">
                                已售出{{ singlePackage.soldCount }}件
                            </div>
                        </div>
                    </div>
                </div>
                <el-row :gutter="20" class="image-row">
                    <el-col :span="6" v-for="(img, index) in productImages" :key="index">
                        <div class="image-container">
                            <img :src="img" :alt="`景点图片${index + 1}`" />
                        </div>
                    </el-col>
                </el-row>
                <div class="sales-details">
                    <span class="service-guarantee">服务保障:</span>
                    <span class="service-guarantee-content" v-for="(guarantees, index) in splitserviceGuarantees"
                        :key="index">
                        <i class="el-icon-circle-check"></i>
                        <span class="service-guarantee-content-text">{{ guarantees }}</span>
                    </span>
                </div>
                <div class="sales-details">
                    <span class="service-guarantee">供应商:</span>
                    <span class="service-guarantee-content">
                        <span class="service-guarantee-content-text">{{ singlePackage.supplier }}</span>
                    </span>
                </div>
                <div class="sales-details product-features">
                    <span class="service-guarantee">产品卖点:</span>
                    <div class="service-guarantee-content product-features-content">
                        <p class="service-guarantee-content-text product-features-text">★ 💎〖多重选择〗
                            {{ singlePackage.productSellingPoints }}
                        </p>
                    </div>
                </div>
            </el-card>
            <el-card class="box-card1" style="margin-top: 20px;">
                <div class="order-details">
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="产品特色" name="first">
                            <div>
                                <el-row :gutter="20">
                                    <el-col :span="16">
                                        <div class="grid-content bg-purple">
                                            <p>{{ singlePackage.description }}</p>
                                        </div>
                                    </el-col>
                                    <el-col :span="8">
                                        <div class="grid-content bg-purple">
                                            <img :src="singlePackage.mainImgUrl || '@/assets/logo/logo1.jpg'" alt="" class="picDesc">
                                        </div>
                                    </el-col>
                                </el-row>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="每日行程" name="second">
                            <div class="block">
                                <el-timeline v-if="dailyItineraries.length > 0">
                                    <el-timeline-item 
                                        v-for="(itinerary, index) in dailyItineraries" 
                                        :key="itinerary.itineraryId"
                                        :timestamp="`第${itinerary.daySeq}天`" 
                                        placement="top">
                                        <el-card>
                                            <el-tag class="tag">{{ getTimeTag(index) }}</el-tag>
                                            <h4>{{ itinerary.title }}</h4>
                                            <p>{{ itinerary.description }}</p>
                                            <div v-if="itinerary.meals" class="itinerary-detail">
                                                <strong>餐饮安排：</strong>{{ itinerary.meals }}
                                            </div>
                                            <div v-if="itinerary.traffic" class="itinerary-detail">
                                                <strong>交通方式：</strong>{{ itinerary.traffic }}
                                            </div>
                                            <div v-if="itinerary.accommodation" class="itinerary-detail">
                                                <strong>住宿安排：</strong>{{ itinerary.accommodation }}
                                            </div>
                                        </el-card>
                                    </el-timeline-item>
                                </el-timeline>
                                <div v-else class="empty-state">
                                    <el-empty description="暂无行程安排"></el-empty>
                                </div>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="费用说明" name="third">
                            <h3 class="fee-title">费用说明</h3>
                            <div v-if="costExplanation">
                                <div class="cost-section">
                                    <h4>费用包含</h4>
                                    <div class="cost-content" v-html="formatCostContent(costExplanation.includeItems)"></div>
                                </div>
                                <div class="cost-section">
                                    <h4>费用不含</h4>
                                    <div class="cost-content" v-html="formatCostContent(costExplanation.excludeItems)"></div>
                                </div>
                                <div class="cost-section">
                                    <h4>退改政策</h4>
                                    <div class="cost-content" v-html="formatCostContent(costExplanation.refundPolicy)"></div>
                                </div>
                            </div>
                            <div v-else class="empty-state">
                                <el-empty description="暂无费用说明"></el-empty>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="预订须知" name="fourth">
                            <div class="booking-notice-container" v-if="bookingNotice">
                                <h2 class="title">预订须知</h2>
                                <div class="notice-content">
                                    <div class="notice-section">
                                        <h3 class="section-title">预订条件</h3>
                                        <div class="notice-content-text" v-html="formatNoticeContent(bookingNotice.bookingConditions)"></div>
                                    </div>
                                    <div class="notice-section">
                                        <h3 class="section-title">有效期</h3>
                                        <div class="notice-content-text">{{ bookingNotice.validityPeriod }}</div>
                                    </div>
                                    <div class="notice-section" v-if="bookingNotice.notes">
                                        <h3 class="section-title">其他注意事项</h3>
                                        <div class="notice-content-text" v-html="formatNoticeContent(bookingNotice.notes)"></div>
                                    </div>
                                </div>
                            </div>
                            <div v-else class="empty-state">
                                <el-empty description="暂无预订须知"></el-empty>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="点评" name="fifth">
                            <div class="tourist-review">
                                <!-- 左侧标签 -->
                                <div class="review-tag">
                                    <div class="tag-content">游客评价</div>
                                </div>
                                <!-- 右侧内容 -->
                                <div class="review-content">
                                    <!-- 整体满意度 -->
                                    <div class="overall-satisfaction">
                                        <div class="satisfaction-percentage">
                                            <span class="percentage">{{ evaluationSummary.satisfactionRate }}%</span>
                                            <span class="review-count">基于{{ evaluationSummary.totalReviews }}条游客评价</span>
                                        </div>
                                        <!-- 满意度分布 -->
                                        <div class="satisfaction-distribution">
                                            <div class="distribution-item" v-for="item in evaluationSummary.distributionItems"
                                                :key="item.type">
                                                <span class="item-label">{{ item.label }} ({{ item.count }})</span>
                                                <el-progress :percentage="item.percentage"
                                                    :color="getProgressColor(item.type)"
                                                    :show-text="false"></el-progress>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 分项评分 -->
                                    <div class="item-scores">
                                        <div class="score-item" v-for="item in evaluationSummary.scoreItems" :key="item.type">
                                            <span class="item-type">{{ item.type }}</span>
                                            <span class="item-score">{{ item.score }}/5</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="reviews-container">
                                <div class="reviews-header">
                                    <h3 class="reviews-title">
                                        <i class="el-icon-star-on"></i>
                                        用户评价
                                    </h3>
                                    <div class="reviews-summary">
                                        <span class="summary-text">共{{ evaluationSummary.totalReviews }}条评价</span>
                                    </div>
                                </div>
                                
                                <div class="reviews-list">
                                    <div class="review-item" v-for="(review, index) in productEvaluations" :key="review.evalId">
                                        <div class="review-header">
                                            <div class="user-profile">
                                                <div class="avatar-container">
                                                    <img :src="review.userAvatar" alt="用户头像" class="user-avatar">
                                                    <div class="avatar-badge">
                                                        <i class="el-icon-check"></i>
                                                    </div>
                                                </div>
                                                <div class="user-details">
                                                    <div class="user-name">{{ review.userName }}</div>
                                                    <div class="user-type-badge">{{ review.userType }}</div>
                                                </div>
                                            </div>
                                            <div class="review-meta">
                                                <div class="review-date">{{ formatDate(review.evalTime) }}</div>
                                                <div class="review-source">{{ review.source }}</div>
                                            </div>
                                        </div>
                                        
                                        <div class="review-ratings">
                                            <div class="rating-item">
                                                <span class="rating-label">总体评价</span>
                                                <el-rate v-model="review.overallScore" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
                                            </div>
                                            <div class="rating-item" v-if="review.serviceScore">
                                                <span class="rating-label">导游服务</span>
                                                <el-rate v-model="review.serviceScore" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
                                            </div>
                                            <div class="rating-item" v-if="review.environmentScore">
                                                <span class="rating-label">行程安排</span>
                                                <el-rate v-model="review.environmentScore" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
                                            </div>
                                            <div class="rating-item" v-if="review.costEffScore">
                                                <span class="rating-label">餐饮住宿</span>
                                                <el-rate v-model="review.costEffScore" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
                                            </div>
                                        </div>
                                        
                                        <div class="review-text" v-if="review.content">
                                            <div class="review-summary">
                                                <i class="el-icon-quote-left quote-icon"></i>
                                                <span class="summary-content">{{ review.content }}</span>
                                            </div>
                                        </div>
                                        
                                        <div class="review-gallery" v-if="review.imgUrls">
                                            <div class="gallery-header">
                                                <span class="gallery-title">旅行照片</span>
                                                <span class="gallery-count">{{ getImageCount(review.imgUrls) }}张</span>
                                            </div>
                                            <el-carousel :interval="4000" type="card" height="180px" indicator-position="outside">
                                                <el-carousel-item v-for="(img, imgIndex) in getImageList(review.imgUrls)" :key="imgIndex">
                                                    <div class="gallery-item">
                                                        <img :src="img" alt="旅行图片" class="gallery-img">
                                                        <div class="gallery-overlay">
                                                            <i class="el-icon-zoom-in"></i>
                                                        </div>
                                                    </div>
                                                </el-carousel-item>
                                            </el-carousel>
                                        </div>
                                        
                                        <div class="review-footer">
                                            <div class="review-actions">
                                                <el-button type="text" class="action-btn">
                                                    <i class="el-icon-thumb"></i>
                                                    有用 ({{ review.usefulCount || 0 }})
                                                </el-button>
                                                <el-button type="text" class="action-btn" v-if="review.replyContent">
                                                    <i class="el-icon-chat-dot-round"></i>
                                                    回复
                                                </el-button>
                                            </div>
                                            <div class="coupon-reward">
                                                <el-button type="primary" class="coupon-btn">
                                                    <i class="el-icon-present"></i>
                                                    点评赠送抵用券 ¥20
                                                </el-button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div v-if="productEvaluations.length === 0" class="empty-state">
                                    <el-empty description="暂无用户评价"></el-empty>
                                </div>
                            </div>
                        </el-tab-pane>
                    </el-tabs>
                </div>
            </el-card>
        </el-col>
    </el-row>
</template>

<script>
import request from '@/utils/request';

export default {
    name: 'ProductDetail',
    data() {
        return {
            activeName: 'second',
            fullscreenLoading: false,
            singlePackage: null,
            dailyItineraries: [],
            costExplanation: null,
            bookingNotice: null,
            evaluationSummary: {
                satisfactionRate: 0,
                totalReviews: 0,
                distributionItems: [],
                scoreItems: []
            },
            productEvaluations: [],
            // 默认图片
            defaultImages: [
                '@/assets/logo/test.png',
                '@/assets/logo/test.png',
                '@/assets/logo/test.png',
                '@/assets/logo/test.png'
            ]
        };
    },
    created() {
        this.loadSinglePackageData();
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        },
        
        openFullScreenAndBuy() {
            this.fullscreenLoading = true;
            setTimeout(() => {
                this.$router.push('/purchasePages');
                this.fullscreenLoading = false;
            }, 800);
        },

        // 根据满意度类型获取进度条颜色
        getProgressColor(type) {
            if (type === 'satisfied') {
                return '#f56c6c';
            } else if (type === 'moderate') {
                return '#e6a23c';
            } else {
                return '#67c23a';
            }
        },

        // 获取时间标签
        getTimeTag(index) {
            const tags = ['早上', '中午', '下午', '晚上'];
            return tags[index % tags.length];
        },

        // 格式化费用内容
        formatCostContent(content) {
            if (!content) return '';
            return content.replace(/\n/g, '<br>');
        },

        // 格式化通知内容
        formatNoticeContent(content) {
            if (!content) return '';
            return content.replace(/\n/g, '<br>');
        },

        // 格式化日期
        formatDate(dateString) {
            if (!dateString) return '';
            const date = new Date(dateString);
            return date.toLocaleDateString('zh-CN');
        },

        // 获取图片数量
        getImageCount(imgUrls) {
            if (!imgUrls) return 0;
            return imgUrls.split(',').length;
        },

        // 获取图片列表
        getImageList(imgUrls) {
            if (!imgUrls) return [];
            return imgUrls.split(',').map(url => url.trim());
        },

        // 加载产品详情
        async loadSinglePackageData() {
            const productId = this.$route.query.productId;
            if (!productId) {
                this.$message.error('无效的套餐ID！');
                return;
            }
            this.fullscreenLoading = true;
            try {
                const response = await request({
                    url: `/travel-portal/tourProduct/getProductDetail/${productId}`,
                    method: 'get',
                });
                
                if (response && response.data) {
                    this.singlePackage = response.data;
                } else {
                    this.singlePackage = response;
                }
                
                // 加载产品评价汇总信息
                await this.loadProductEvaluationSummary(productId);
                
                // 加载产品评价列表
                await this.loadProductEvaluations(productId);
                
                // 加载产品每日行程
                await this.loadProductDailyItineraries(productId);
                
                // 加载产品费用说明
                await this.loadProductCostExplanation(productId);
                
                // 加载产品预订须知
                await this.loadProductBookingNotice(productId);
                
                this.$message.success('套餐详情加载成功！');
            } catch (error) {
                this.$message.error(error.message || '套餐详情加载失败！');
                console.error('套餐详情加载失败:', error);
            } finally {
                this.fullscreenLoading = false;
            }
        },

        // 加载产品评价汇总信息
        async loadProductEvaluationSummary(productId) {
            try {
                const response = await request({
                    url: `/travel-portal/productEvaluation/summary/${productId}`,
                    method: 'get',
                });
                
                if (response && response.data) {
                    this.evaluationSummary = response.data;
                } else {
                    this.evaluationSummary = response;
                }
            } catch (error) {
                console.error('加载产品评价汇总失败:', error);
                // 设置默认值
                this.evaluationSummary = {
                    satisfactionRate: 96.5,
                    totalReviews: 620,
                    distributionItems: [
                        { type: 'satisfied', label: '很满意', count: 530, percentage: 85.5 },
                        { type: 'moderate', label: '还不错', count: 60, percentage: 9.7 },
                        { type: 'dissatisfied', label: '不满意', count: 30, percentage: 4.8 },
                    ],
                    scoreItems: [
                        { type: '导游服务', score: 4.7 },
                        { type: '行程规划', score: 4.6 },
                        { type: '住宿体验', score: 4.5 },
                        { type: '交通出行', score: 4.9 },
                    ]
                };
            }
        },

        // 加载产品评价列表
        async loadProductEvaluations(productId) {
            try {
                const response = await request({
                    url: `/travel-portal/productEvaluation/list/${productId}`,
                    method: 'get',
                    params: {
                        page: 1,
                        size: 10
                    }
                });
                
                if (response && response.data) {
                    this.productEvaluations = response.data;
                } else {
                    this.productEvaluations = response || [];
                }
            } catch (error) {
                console.error('加载产品评价列表失败:', error);
                this.productEvaluations = [];
            }
        },

        // 加载产品每日行程
        async loadProductDailyItineraries(productId) {
            try {
                const response = await request({
                    url: `/travel-portal/productDailyItinerary/list/${productId}`,
                    method: 'get',
                });
                
                if (response && response.data) {
                    this.dailyItineraries = response.data;
                } else {
                    this.dailyItineraries = response || [];
                }
            } catch (error) {
                console.error('加载产品每日行程失败:', error);
                this.dailyItineraries = [];
            }
        },

        // 加载产品费用说明
        async loadProductCostExplanation(productId) {
            try {
                const response = await request({
                    url: `/travel-portal/productCostExplanation/get/${productId}`,
                    method: 'get',
                });
                
                if (response && response.data) {
                    this.costExplanation = response.data;
                } else {
                    this.costExplanation = response;
                }
            } catch (error) {
                console.error('加载产品费用说明失败:', error);
                this.costExplanation = null;
            }
        },

        // 加载产品预订须知
        async loadProductBookingNotice(productId) {
            try {
                const response = await request({
                    url: `/travel-portal/productBookingNotice/get/${productId}`,
                    method: 'get',
                });
                
                if (response && response.data) {
                    this.bookingNotice = response.data;
                } else {
                    this.bookingNotice = response;
                }
            } catch (error) {
                console.error('加载产品预订须知失败:', error);
                this.bookingNotice = null;
            }
        },

        formatPrice(price) {
            if (!price) return '0.00';
            return Number(price).toFixed(2);
        }
    },
    computed: {
        splitProductTags() {
            if (!this.singlePackage || !this.singlePackage.productTags) return [];
            return this.singlePackage.productTags.split('，').map(tag => tag.trim());
        },
        splitserviceGuarantees() {
            if (!this.singlePackage || !this.singlePackage.serviceGuarantees) return [];
            return this.singlePackage.serviceGuarantees.split('，').map(tag => tag.trim());
        },
        productImages() {
            if (!this.singlePackage || !this.singlePackage.imgUrls) return this.defaultImages;
            const images = this.singlePackage.imgUrls.split(',').map(url => url.trim());
            // 确保至少有4张图片
            while (images.length < 4) {
                images.push(this.defaultImages[images.length % this.defaultImages.length]);
            }
            return images.slice(0, 4);
        }
    }
};
</script>

<style scoped>
/* 基础样式 */
.box-card {
    margin-bottom: 20px;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}

.bg-purple-dark {
    background: #f8f9fa;
    padding: 20px;
}

.title-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
}

.title {
    font-size: 24px;
    font-weight: bold;
    color: #303133;
}

.button-buy {
    margin-left: 20px;
}

.discriptions {
    color: #606266;
    line-height: 1.6;
}

.span-container {
    display: inline-block;
    margin: 5px 5px 5px 0;
}

.span1 {
    background: #e6f7ff;
    color: #1890ff;
    padding: 4px 8px;
    border-radius: 4px;
    font-size: 12px;
}

.price-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 15px;
}

.price {
    display: flex;
    align-items: baseline;
}

.price-symbol {
    color: #e6a23c;
    font-size: 16px;
    font-weight: bold;
}

.price-text {
    color: #e6a23c;
    font-size: 24px;
    font-weight: bold;
}

.salesDesc {
    color: #909399;
    font-size: 14px;
}

.image-row {
    margin: 20px 0;
}

.image-container {
    height: 150px;
    overflow: hidden;
    border-radius: 8px;
}

.image-container img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.sales-details {
    margin: 15px 0;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
}

.service-guarantee {
    font-weight: bold;
    color: #303133;
    margin-right: 10px;
}

.service-guarantee-content {
    display: flex;
    align-items: center;
    margin-right: 20px;
    margin-bottom: 5px;
}

.service-guarantee-content i {
    color: #67c23a;
    margin-right: 5px;
}

.service-guarantee-content-text {
    color: #606266;
    font-size: 14px;
}

.product-features {
    flex-direction: column;
    align-items: flex-start;
}

.product-features-content {
    margin-top: 10px;
}

.product-features-text {
    color: #606266;
    line-height: 1.6;
}

/* 标签页样式 */
.order-details {
    padding: 20px;
}

.picDesc {
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 8px;
}

/* 每日行程样式 */
.block {
    padding: 20px 0;
}

.tag {
    margin-bottom: 10px;
}

.itinerary-detail {
    margin: 8px 0;
    font-size: 14px;
    color: #606266;
}

/* 费用说明样式 */
.fee-title {
    color: #303133;
    margin-bottom: 20px;
}

.cost-section {
    margin-bottom: 25px;
}

.cost-section h4 {
    color: #303133;
    margin-bottom: 10px;
    font-size: 16px;
}

.cost-content {
    color: #606266;
    line-height: 1.6;
    padding: 15px;
    background: #f8f9fa;
    border-radius: 6px;
}

/* 预订须知样式 */
.booking-notice-container {
    padding: 20px 0;
}

.booking-notice-container .title {
    color: #303133;
    margin-bottom: 20px;
    font-size: 20px;
}

.notice-section {
    margin-bottom: 25px;
}

.section-title {
    color: #303133;
    margin-bottom: 10px;
    font-size: 16px;
}

.notice-content-text {
    color: #606266;
    line-height: 1.6;
    padding: 15px;
    background: #f8f9fa;
    border-radius: 6px;
}

/* 评价样式 */
.tourist-review {
    display: flex;
    margin-bottom: 30px;
    padding: 20px;
    background: #f8f9fa;
    border-radius: 8px;
}

.review-tag {
    width: 80px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.tag-content {
    writing-mode: vertical-rl;
    text-orientation: mixed;
    font-size: 16px;
    font-weight: bold;
    color: #303133;
}

.review-content {
    flex: 1;
    margin-left: 20px;
}

.overall-satisfaction {
    margin-bottom: 20px;
}

.satisfaction-percentage {
    text-align: center;
    margin-bottom: 15px;
}

.percentage {
    font-size: 36px;
    font-weight: bold;
    color: #e6a23c;
}

.review-count {
    display: block;
    color: #909399;
    font-size: 14px;
    margin-top: 5px;
}

.satisfaction-distribution {
    margin-bottom: 20px;
}

.distribution-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}

.item-label {
    width: 100px;
    font-size: 14px;
    color: #606266;
}

.item-scores {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}

.score-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    min-width: 80px;
}

.item-type {
    font-size: 12px;
    color: #909399;
    margin-bottom: 5px;
}

.item-score {
    font-size: 16px;
    font-weight: bold;
    color: #e6a23c;
}

/* 评价列表样式 */
.reviews-container {
    margin-top: 30px;
}

.reviews-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 10px;
    border-bottom: 1px solid #ebeef5;
}

.reviews-title {
    display: flex;
    align-items: center;
    color: #303133;
    margin: 0;
}

.reviews-title i {
    margin-right: 8px;
    color: #e6a23c;
}

.summary-text {
    color: #909399;
    font-size: 14px;
}

.review-item {
    border: 1px solid #ebeef5;
    border-radius: 8px;
    padding: 20px;
    margin-bottom: 20px;
    background: #fff;
}

.review-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
}

.user-profile {
    display: flex;
    align-items: center;
}

.avatar-container {
    position: relative;
    margin-right: 12px;
}

.user-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    object-fit: cover;
}

.avatar-badge {
    position: absolute;
    bottom: -2px;
    right: -2px;
    width: 16px;
    height: 16px;
    background: #67c23a;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 10px;
}

.user-details {
    display: flex;
    flex-direction: column;
}

.user-name {
    font-weight: bold;
    color: #303133;
    font-size: 14px;
}

.user-type-badge {
    background: #e6f7ff;
    color: #1890ff;
    padding: 2px 6px;
    border-radius: 4px;
    font-size: 12px;
    margin-top: 2px;
}

.review-meta {
    text-align: right;
}

.review-date {
    color: #909399;
    font-size: 12px;
}

.review-source {
    color: #c0c4cc;
    font-size: 12px;
    margin-top: 2px;
}

.review-ratings {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    margin-bottom: 15px;
}

.rating-item {
    display: flex;
    align-items: center;
    gap: 8px;
}

.rating-label {
    font-size: 14px;
    color: #606266;
    min-width: 60px;
}

.review-text {
    margin-bottom: 15px;
}

.review-summary {
    display: flex;
    align-items: flex-start;
    gap: 10px;
}

.quote-icon {
    color: #e6a23c;
    font-size: 16px;
    margin-top: 2px;
}

.summary-content {
    color: #606266;
    line-height: 1.6;
    flex: 1;
}

.review-gallery {
    margin-bottom: 15px;
}

.gallery-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.gallery-title {
    font-weight: bold;
    color: #303133;
    font-size: 14px;
}

.gallery-count {
    color: #909399;
    font-size: 12px;
}

.gallery-item {
    position: relative;
    height: 180px;
    border-radius: 8px;
    overflow: hidden;
}

.gallery-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.gallery-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.3);
    display: flex;
    align-items: center;
    justify-content: center;
    opacity: 0;
    transition: opacity 0.3s;
}

.gallery-item:hover .gallery-overlay {
    opacity: 1;
}

.gallery-overlay i {
    color: white;
    font-size: 24px;
}

.review-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-top: 15px;
    border-top: 1px solid #f0f0f0;
}

.review-actions {
    display: flex;
    gap: 15px;
}

.action-btn {
    color: #909399;
    font-size: 14px;
}

.action-btn:hover {
    color: #409eff;
}

.coupon-btn {
    font-size: 12px;
    padding: 6px 12px;
}

/* 空状态样式 */
.empty-state {
    text-align: center;
    padding: 40px 0;
}

/* 响应式设计 */
@media (max-width: 768px) {
    .title-row {
        flex-direction: column;
        align-items: flex-start;
    }
    
    .button-buy {
        margin-left: 0;
        margin-top: 10px;
    }
    
    .tourist-review {
        flex-direction: column;
    }
    
    .review-tag {
        width: 100%;
        margin-bottom: 15px;
    }
    
    .tag-content {
        writing-mode: horizontal-tb;
        text-orientation: initial;
    }
    
    .review-content {
        margin-left: 0;
    }
    
    .review-header {
        flex-direction: column;
        align-items: flex-start;
    }
    
    .review-meta {
        text-align: left;
        margin-top: 10px;
    }
    
    .review-footer {
        flex-direction: column;
        gap: 10px;
        align-items: flex-start;
    }
}
</style>
