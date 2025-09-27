<template>
    <div class="itinerary-management">
        <!-- 导航菜单 -->
        <el-menu :default-active="activeTab" class="el-menu-demo sticky-menu" mode="horizontal" @select="handleTabChange">
            <el-menu-item index="all">所有行程</el-menu-item>
            <el-menu-item index="upcoming">待出行</el-menu-item>
            <el-menu-item index="completed">已完成</el-menu-item>
            <el-menu-item index="cancelled">已取消</el-menu-item>
        </el-menu>

        <!-- 行程列表 -->
        <div class="itinerary-list">
            <el-card v-for="itinerary in itineraries" :key="itinerary.orderId" class="itinerary-card">
                <div class="itinerary-header">
                    <div class="itinerary-title">
                        <h3>{{ itinerary.title }}</h3>
                        <el-tag :type="getStatusTagType(itinerary.orderStatus)">{{ getStatusText(itinerary.orderStatus)
                        }}</el-tag>
                    </div>

                    <div class="itinerary-actions">
                        <el-button type="text" @click="viewItineraryDetails(itinerary.orderId)" class="detail-btn">
                            查看详情
                        </el-button>

                        <template v-if="itinerary.orderStatus === 1 && canModifyItinerary(itinerary)">
                            <el-button type="text" @click="startModifyItinerary(itinerary.orderId)" class="modify-btn">
                                修改行程
                            </el-button>
                        </template>

                        <template v-if="itinerary.orderStatus === 1 && canCancelItinerary(itinerary)">
                            <el-button type="text" @click="openCancelDialog(itinerary.orderId)" class="cancel-btn">
                                取消行程
                            </el-button>
                        </template>
                    </div>
                </div>

                <div class="itinerary-basic-info">
                    <el-row :gutter="20">
                        <el-col :span="4">
                            <div class="info-item">
                                <span class="info-label">出行日期</span>
                                <span class="info-value">{{ formatDate(itinerary.bookingDate) }} - {{
                                    formatDate(itinerary.bookingDate)
                                }}</span>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div class="info-item">
                                <span class="info-label">天数</span>
                                <span class="info-value">{{ calculateDays(itinerary) }}天</span>
                            </div>
                        </el-col>
                        <el-col :span="4">
                            <div class="info-item">
                                <span class="info-label">人数</span>
                                <span class="info-value">{{ itinerary.personCount }}人</span>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div class="info-item">
                                <span class="info-label">导游</span>
                                <span class="info-value">
                                    <el-avatar :src="itinerary.guide?.avatar || '/default-avatar.png'" size="small"
                                        class="guide-avatar"></el-avatar>
                                    {{ itinerary.guide?.name || '未分配' }}
                                </span>
                            </div>
                        </el-col>
                        <el-col :span="6">
                            <div class="info-item">
                                <span class="info-label">总价</span>
                                <span class="info-value price">{{ itinerary.orderAmount | currency }}</span>
                            </div>
                        </el-col>
                    </el-row>
                </div>
            </el-card>

            <!-- 空状态 -->
            <el-empty v-if="itineraries.length === 0 && !loading" description="暂无行程记录" class="empty-state">
                <el-button type="primary" @click="createNewItinerary">创建新行程</el-button>
            </el-empty>

            <!-- 加载状态 -->
            <div v-if="loading" class="loading-container">
                <el-skeleton :rows="3" animated />
            </div>

            <!-- 分页 -->
            <el-pagination v-if="itineraries.length > 0" class="pagination" @size-change="handleSizeChange"
                @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[5, 10, 20]"
                :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                :total="total"></el-pagination>
        </div>

        <!-- 行程详情弹窗 -->
        <el-dialog title="行程详情" :visible.sync="showDetailDialog" width="80%" :before-close="handleDetailClose">
            <div v-if="currentItinerary" class="itinerary-detail">
                <div class="detail-header">
                    <h2>{{ currentItinerary.title }}</h2>
                    <el-tag :type="getStatusTagType(currentItinerary.orderStatus)">{{ getStatusText(currentItinerary.orderStatus)
                    }}</el-tag>
                </div>

                <el-row :gutter="30" class="detail-section">
                    <el-col :span="8">
                        <h3 class="section-title">基本信息</h3>
                        <el-descriptions column="1" border>
                            <el-descriptions-item label="订单编号">{{ currentItinerary.orderNumber }}</el-descriptions-item>
                            <el-descriptions-item label="创建时间">{{ formatDateTime(currentItinerary.createTime)
                            }}</el-descriptions-item>
                            <el-descriptions-item label="出行日期">{{ formatDate(currentItinerary.bookingDate) }}</el-descriptions-item>
                            <el-descriptions-item label="行程天数">{{ calculateDays(currentItinerary)
                            }}天</el-descriptions-item>
                            <el-descriptions-item label="出行人数">{{ currentItinerary.personCount }}人</el-descriptions-item>
                            <el-descriptions-item label="总费用">{{ currentItinerary.orderAmount | currency
                            }}</el-descriptions-item>
                            <el-descriptions-item label="支付状态">{{ getPaymentStatusText(currentItinerary.payStatus)
                            }}</el-descriptions-item>
                        </el-descriptions>
                    </el-col>

                    <el-col :span="8">
                        <h3 class="section-title">导游信息</h3>
                        <div class="guide-info-card" v-if="currentItinerary.guideInfo">
                            <el-avatar :src="currentItinerary.guideInfo.avatar || '/default-avatar.png'" size="large"
                                class="guide-avatar-large"></el-avatar>
                            <div class="guide-personal-info">
                                <h4>{{ currentItinerary.guideInfo.name }}</h4>
                                <div class="guide-rating">
                                    <el-rate v-model="currentItinerary.guideInfo.rating" disabled :max="5"
                                        :precision="0.5"></el-rate>
                                    <span class="rating-value">{{ currentItinerary.guideInfo.rating }}</span>
                                </div>
                                <p>{{ getServiceTypeText(currentItinerary.guideInfo.serviceType) }}</p>
                            </div>

                            <el-descriptions column="1" border class="guide-contact">
                                <el-descriptions-item label="联系电话">{{ currentItinerary.guideInfo.phone
                                }}</el-descriptions-item>
                                <el-descriptions-item label="微信">{{ currentItinerary.guideInfo.wechat || '未提供'
                                }}</el-descriptions-item>
                                <el-descriptions-item label="服务语言">{{ currentItinerary.guideInfo.languages?.join('、') || '未提供'
                                }}</el-descriptions-item>
                            </el-descriptions>
                        </div>
                        <div v-else class="no-guide">
                            <p>暂未分配导游</p>
                        </div>
                    </el-col>

                    <el-col :span="8">
                        <h3 class="section-title">行程状态</h3>
                        <el-timeline>
                            <el-timeline-item v-for="(event, index) in currentItinerary.timeline" :key="index"
                                :timestamp="formatDateTime(event.time)" :type="event.type" :color="event.color">
                                {{ event.content }}
                            </el-timeline-item>
                        </el-timeline>
                    </el-col>
                </el-row>

                <div class="detail-section mt-30" v-if="currentItinerary.dailySchedule && currentItinerary.dailySchedule.length > 0">
                    <h3 class="section-title">每日行程安排</h3>
                    <el-collapse v-model="activeDay">
                        <el-collapse-item v-for="(day, index) in currentItinerary.dailySchedule" :key="index"
                            :title="`第${index + 1}天 (${formatDate(day.date)})`" :name="index">
                            <el-timeline>
                                <el-timeline-item v-for="(activity, actIndex) in day.activities" :key="actIndex"
                                    :timestamp="activity.time" type="primary">
                                    <div class="activity-content">
                                        <h4>{{ activity.name }}</h4>
                                        <p>{{ activity.description }}</p>
                                        <template v-if="activity.location">
                                            <el-tag type="info" effect="plain">{{ activity.location }}</el-tag>
                                        </template>
                                    </div>
                                </el-timeline-item>
                            </el-timeline>
                        </el-collapse-item>
                    </el-collapse>
                </div>

                <div class="detail-section mt-30" v-if="currentItinerary.notes">
                    <h3 class="section-title">重要提示</h3>
                    <el-card class="notes-card">
                        <p>{{ currentItinerary.notes }}</p>
                    </el-card>
                </div>
            </div>
        </el-dialog>

        <!-- 修改行程弹窗 -->
        <el-dialog title="修改行程" :visible.sync="showModifyDialog" width="60%">
            <el-form :model="modifiedItinerary" :rules="modifyRules" ref="modifyForm" label-width="120px">
                <el-form-item label="行程标题" prop="title">
                    <el-input v-model="modifiedItinerary.title"></el-input>
                </el-form-item>

                <el-form-item label="开始日期" prop="startDate">
                    <el-date-picker v-model="modifiedItinerary.startDate" type="date" placeholder="选择开始日期"
                        :picker-options="startDateOptions"></el-date-picker>
                </el-form-item>

                <el-form-item label="结束日期" prop="endDate">
                    <el-date-picker v-model="modifiedItinerary.endDate" type="date" placeholder="选择结束日期"
                        :picker-options="endDateOptions"></el-date-picker>
                </el-form-item>

                <el-form-item label="出行人数" prop="travelers">
                    <el-input-number v-model="modifiedItinerary.travelers" :min="1" :max="50"
                        label="人数"></el-input-number>
                </el-form-item>

                <el-form-item label="行程备注" prop="notes">
                    <el-input v-model="modifiedItinerary.notes" type="textarea" :rows="4"
                        placeholder="请输入行程备注或特殊需求"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-alert title="提示：行程修改需要导游确认，请耐心等待回复" type="info" show-icon></el-alert>
                </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="showModifyDialog = false">取消</el-button>
                <el-button type="primary" @click="submitItineraryModification" :loading="modifyLoading">提交修改</el-button>
            </div>
        </el-dialog>

        <!-- 取消行程弹窗 -->
        <el-dialog title="取消行程" :visible.sync="showCancelDialog" width="50%">
            <div v-if="currentItinerary" class="cancel-dialog-content">
                <p class="warning-text">
                    <i class="el-icon-warning"></i>
                    您确定要取消 "{{ currentItinerary.title }}" 吗？
                </p>

                <el-form :model="cancelForm" :rules="cancelRules" ref="cancelForm" label-width="100px">
                    <el-form-item label="取消原因" prop="reason">
                        <el-select v-model="cancelForm.reason" placeholder="请选择取消原因">
                            <el-option label="行程冲突" value="schedule_conflict"></el-option>
                            <el-option label="价格原因" value="price"></el-option>
                            <el-option label="找到更合适的行程" value="better_option"></el-option>
                            <el-option label="个人原因" value="personal"></el-option>
                            <el-option label="其他原因" value="other"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="详细说明" prop="description">
                        <el-input v-model="cancelForm.description" type="textarea" :rows="3"
                            placeholder="请输入详细说明（选填）"></el-input>
                    </el-form-item>
                </el-form>

                <el-card class="refund-info">
                    <h4>退款信息</h4>
                    <el-descriptions column="1">
                        <el-descriptions-item label="距离出发时间">
                            <span class="days-left">{{ refundInfo?.daysBeforeDeparture || 0 }}天</span>
                        </el-descriptions-item>
                        <el-descriptions-item label="退款比例">
                            <span class="refund-percentage">{{ refundInfo?.refundPercentage || 0 }}%</span>
                        </el-descriptions-item>
                        <el-descriptions-item label="可退金额">
                            <span class="refund-amount">{{ refundInfo?.refundAmount | currency }}</span>
                        </el-descriptions-item>
                        <el-descriptions-item label="退款方式">
                            原路返回（预计{{ refundInfo?.refundDays || 0 }}个工作日到账）
                        </el-descriptions-item>
                    </el-descriptions>
                </el-card>

                <el-alert :title="refundInfo?.refundPolicy || '取消政策说明'" type="info" show-icon
                    class="policy-alert"></el-alert>
            </div>

            <div slot="footer" class="dialog-footer">
                <el-button @click="showCancelDialog = false">再想想</el-button>
                <el-button type="danger" @click="confirmCancelItinerary" :loading="cancelLoading">确认取消</el-button>
            </div>
        </el-dialog>

        <!-- 操作结果提示 -->
        <el-dialog title="操作成功" :visible.sync="showSuccessDialog" width="40%" :show-close="false">
            <div class="success-content">
                <i class="el-icon-success"></i>
                <p>{{ successMessage }}</p>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="showSuccessDialog = false">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'routesManage',
    filters: {
        currency(value) {
            if (!value) return '¥0.00';
            return `¥${value.toFixed(2)}`;
        }
    },
    data() {
        return {
            // 用户ID（实际应用中从登录状态获取）
            currentUserId: 1,
            
            // 标签页状态
            activeTab: 'all',

            // 分页控制
            currentPage: 1,
            pageSize: 10,
            total: 0,

            // 加载状态
            loading: false,
            modifyLoading: false,
            cancelLoading: false,

            // 弹窗控制
            showDetailDialog: false,
            showModifyDialog: false,
            showCancelDialog: false,
            showSuccessDialog: false,
            successMessage: '',

            // 当前操作的订单ID
            currentOrderId: null,

            // 当前行程数据
            currentItinerary: null,

            // 行程列表数据
            itineraries: [],

            // 修改行程表单数据
            modifiedItinerary: {
                title: '',
                startDate: '',
                endDate: '',
                travelers: 1,
                notes: ''
            },

            // 取消行程表单数据
            cancelForm: {
                reason: '',
                description: ''
            },

            // 退款信息
            refundInfo: null,

            // 表单验证规则
            modifyRules: {
                title: [
                    { required: true, message: '请输入行程标题', trigger: 'blur' },
                    { max: 50, message: '行程标题不能超过50个字符', trigger: 'blur' }
                ],
                startDate: [
                    { required: true, message: '请选择开始日期', trigger: 'change' }
                ],
                endDate: [
                    { required: true, message: '请选择结束日期', trigger: 'change' }
                ],
                travelers: [
                    { required: true, message: '请输入出行人数', trigger: 'blur' }
                ]
            },

            cancelRules: {
                reason: [
                    { required: true, message: '请选择取消原因', trigger: 'change' }
                ],
                description: [
                    { max: 200, message: '详细说明不能超过200个字符', trigger: 'blur' }
                ]
            },

            // 日期选择器选项
            startDateOptions: {
                disabledDate: (time) => {
                    return time.getTime() < Date.now() - 8.64e7;
                }
            },

            // 折叠面板状态
            activeDay: 0
        };
    },
    computed: {
        // 结束日期选择限制
        endDateOptions() {
            return {
                disabledDate: (time) => {
                    if (!this.modifiedItinerary.startDate) {
                        return time.getTime() < Date.now() - 8.64e7;
                    }
                    return time.getTime() < new Date(this.modifiedItinerary.startDate).getTime() - 8.64e7;
                }
            };
        }
    },
    mounted() {
        this.loadItineraries();
    },
    methods: {
        // 加载行程列表
        async loadItineraries() {
            this.loading = true;
            try {
                const response = await this.$http.get('/travel-portal/itinerary/list', {
                    params: {
                        userId: this.currentUserId,
                        status: this.activeTab,
                        page: this.currentPage,
                        size: this.pageSize
                    }
                });
                
                if (response.data.code === 1) {
                    this.itineraries = response.data.data.records || [];
                    this.total = response.data.data.total || 0;
                } else {
                    this.$message.error(response.data.msg || '获取行程列表失败');
                }
            } catch (error) {
                console.error('获取行程列表失败:', error);
                this.$message.error('获取行程列表失败');
            } finally {
                this.loading = false;
            }
        },

        // 切换标签页
        handleTabChange(tab) {
            this.activeTab = tab;
            this.currentPage = 1;
            this.loadItineraries();
        },

        // 分页处理
        handleSizeChange(val) {
            this.pageSize = val;
            this.currentPage = 1;
            this.loadItineraries();
        },
        
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadItineraries();
        },

        // 查看行程详情
        async viewItineraryDetails(orderId) {
            try {
                const response = await this.$http.get(`/travel-portal/itinerary/detail/${orderId}`, {
                    params: { userId: this.currentUserId }
                });
                
                if (response.data.code === 1) {
                    this.currentItinerary = response.data.data;
                    this.showDetailDialog = true;
                } else {
                    this.$message.error(response.data.msg || '获取行程详情失败');
                }
            } catch (error) {
                console.error('获取行程详情失败:', error);
                this.$message.error('获取行程详情失败');
            }
        },

        // 关闭详情弹窗
        handleDetailClose() {
            this.showDetailDialog = false;
            this.currentItinerary = null;
            this.currentOrderId = null;
        },

        // 开始修改行程
        async startModifyItinerary(orderId) {
            this.currentOrderId = orderId;
            
            // 先获取行程详情
            try {
                const response = await this.$http.get(`/travel-portal/itinerary/detail/${orderId}`, {
                    params: { userId: this.currentUserId }
                });
                
                if (response.data.code === 1) {
                    const itinerary = response.data.data;
                    this.modifiedItinerary = {
                        title: itinerary.title,
                        startDate: new Date(itinerary.bookingDate),
                        endDate: new Date(itinerary.bookingDate), // 这里可能需要根据实际业务调整
                        travelers: itinerary.personCount,
                        notes: itinerary.notes || ''
                    };
                    this.showModifyDialog = true;
                } else {
                    this.$message.error(response.data.msg || '获取行程信息失败');
                }
            } catch (error) {
                console.error('获取行程信息失败:', error);
                this.$message.error('获取行程信息失败');
            }
        },

        // 提交行程修改
        async submitItineraryModification() {
            this.$refs.modifyForm.validate(async (valid) => {
                if (valid) {
                    // 检查日期是否有效
                    if (new Date(this.modifiedItinerary.startDate) > new Date(this.modifiedItinerary.endDate)) {
                        this.$message.error('结束日期不能早于开始日期');
                        return;
                    }

                    this.modifyLoading = true;
                    try {
                        const modifyData = {
                            orderId: this.currentOrderId,
                            title: this.modifiedItinerary.title,
                            startDate: this.formatDateForAPI(this.modifiedItinerary.startDate),
                            endDate: this.formatDateForAPI(this.modifiedItinerary.endDate),
                            travelers: this.modifiedItinerary.travelers,
                            notes: this.modifiedItinerary.notes,
                            modifyReason: '用户申请修改行程'
                        };
                        
                        const response = await this.$http.post('/travel-portal/itinerary/modify', modifyData, {
                            params: { userId: this.currentUserId }
                        });
                        
                        if (response.data.code === 1) {
                            this.showModifyDialog = false;
                            this.successMessage = response.data.msg || '行程修改请求已提交';
                            this.showSuccessDialog = true;
                            this.loadItineraries(); // 重新加载列表
                        } else {
                            this.$message.error(response.data.msg || '修改行程失败');
                        }
                    } catch (error) {
                        console.error('修改行程失败:', error);
                        this.$message.error('修改行程失败');
                    } finally {
                        this.modifyLoading = false;
                    }
                }
            });
        },

        // 显示取消行程弹窗
        async openCancelDialog(orderId) {
            this.currentOrderId = orderId;
            this.cancelForm = {
                reason: '',
                description: ''
            };
            
            // 获取退款信息
            try {
                const response = await this.$http.get(`/travel-portal/itinerary/refund-info/${orderId}`, {
                    params: { userId: this.currentUserId }
                });
                
                if (response.data.code === 1) {
                    this.refundInfo = response.data.data;
                }
            } catch (error) {
                console.error('获取退款信息失败:', error);
            }
            
            this.showCancelDialog = true;
        },

        // 确认取消行程
        async confirmCancelItinerary() {
            this.$refs.cancelForm.validate(async (valid) => {
                if (valid) {
                    this.cancelLoading = true;
                    try {
                        const cancelData = {
                            orderId: this.currentOrderId,
                            reason: this.cancelForm.reason,
                            description: this.cancelForm.description
                        };
                        
                        const response = await this.$http.post('/travel-portal/itinerary/cancel', cancelData, {
                            params: { userId: this.currentUserId }
                        });
                        
                        if (response.data.code === 1) {
                            this.showCancelDialog = false;
                            this.successMessage = response.data.msg || '行程已取消';
                            this.showSuccessDialog = true;
                            this.loadItineraries(); // 重新加载列表
                        } else {
                            this.$message.error(response.data.msg || '取消行程失败');
                        }
                    } catch (error) {
                        console.error('取消行程失败:', error);
                        this.$message.error('取消行程失败');
                    } finally {
                        this.cancelLoading = false;
                    }
                }
            });
        },

        // 检查是否可以修改行程
        canModifyItinerary(itinerary) {
            // 只有已确认且未过期的行程可以修改
            if (itinerary.orderStatus !== 1) return false;

            const startDate = new Date(itinerary.bookingDate);
            const today = new Date();
            // 出发前1天以上可以修改
            return startDate - today > 24 * 60 * 60 * 1000;
        },

        // 检查是否可以取消行程
        canCancelItinerary(itinerary) {
            // 只有已确认且未过期的行程可以取消
            if (itinerary.orderStatus !== 1) return false;

            const startDate = new Date(itinerary.bookingDate);
            const today = new Date();
            // 出发当天及之前可以取消（但可能不退款）
            return startDate >= today;
        },

        // 获取行程状态文本
        getStatusText(status) {
            const statusMap = {
                0: '待确认',
                1: '已确认',
                2: '已完成',
                3: '已取消',
                4: '退款中'
            };
            return statusMap[status] || '未知状态';
        },

        // 获取行程状态标签类型
        getStatusTagType(status) {
            const typeMap = {
                0: 'warning',
                1: 'success',
                2: 'primary',
                3: 'danger',
                4: 'info'
            };
            return typeMap[status] || 'default';
        },

        // 获取支付状态文本
        getPaymentStatusText(status) {
            const statusMap = {
                0: '未支付',
                1: '已支付',
                2: '已退款'
            };
            return statusMap[status] || '未知状态';
        },

        // 获取服务类型文本
        getServiceTypeText(type) {
            const typeMap = {
                'full-time': '全程陪同',
                'local': '当地向导',
                'translator': '翻译导游',
                'custom': '定制旅游'
            };
            return typeMap[type] || type;
        },

        // 格式化日期
        formatDate(date) {
            if (!date) return '';
            const d = new Date(date);
            return `${d.getFullYear()}-${this.padZero(d.getMonth() + 1)}-${this.padZero(d.getDate())}`;
        },

        // 格式化日期时间
        formatDateTime(date) {
            if (!date) return '';
            const d = new Date(date);
            return `${d.getFullYear()}-${this.padZero(d.getMonth() + 1)}-${this.padZero(d.getDate())} ${this.padZero(d.getHours())}:${this.padZero(d.getMinutes())}`;
        },

        // 格式化日期为API需要的格式
        formatDateForAPI(date) {
            if (!date) return '';
            const d = new Date(date);
            return `${d.getFullYear()}-${this.padZero(d.getMonth() + 1)}-${this.padZero(d.getDate())}`;
        },

        // 数字补零
        padZero(num) {
            return num < 10 ? `0${num}` : num;
        },

        // 计算行程天数
        calculateDays(itinerary) {
            if (!itinerary || !itinerary.bookingDate) return 0;
            // 这里简化处理，实际可能需要根据产品信息计算
            return 1;
        },

        // 创建新行程（实际应用中会跳转到创建页面）
        createNewItinerary() {
            this.$message.info('跳转到创建新行程页面');
            // 实际应用中会使用路由跳转
            // this.$router.push('/create-itinerary');
        }
    }
}
</script>

<style scoped>
.itinerary-management {
    padding: 20px;
}

.sticky-menu {
    position: sticky;
    top: 0;
    z-index: 100;
    background: white;
    border-bottom: 1px solid #e4e7ed;
    margin-bottom: 20px;
}

.itinerary-list {
    min-height: 400px;
}

.itinerary-card {
    margin-bottom: 20px;
    transition: all 0.3s;
}

.itinerary-card:hover {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.itinerary-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.itinerary-title {
    display: flex;
    align-items: center;
    gap: 15px;
}

.itinerary-title h3 {
    margin: 0;
    color: #303133;
}

.itinerary-actions {
    display: flex;
    gap: 10px;
}

.itinerary-basic-info {
    background: #f8f9fa;
    padding: 15px;
    border-radius: 6px;
}

.info-item {
    display: flex;
    flex-direction: column;
    gap: 5px;
}

.info-label {
    font-size: 12px;
    color: #909399;
}

.info-value {
    font-size: 14px;
    color: #303133;
    font-weight: 500;
}

.info-value.price {
    color: #e6a23c;
    font-weight: bold;
}

.guide-avatar {
    margin-right: 8px;
}

.empty-state {
    margin: 50px 0;
}

.loading-container {
    margin: 20px 0;
}

.pagination {
    margin-top: 30px;
    text-align: center;
}

.detail-section {
    margin-bottom: 30px;
}

.section-title {
    color: #303133;
    margin-bottom: 15px;
    font-size: 16px;
    font-weight: 600;
}

.guide-info-card {
    text-align: center;
}

.guide-avatar-large {
    margin-bottom: 15px;
}

.guide-personal-info h4 {
    margin: 10px 0 5px 0;
    color: #303133;
}

.guide-rating {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    margin: 10px 0;
}

.rating-value {
    color: #e6a23c;
    font-weight: bold;
}

.guide-contact {
    margin-top: 15px;
}

.no-guide {
    text-align: center;
    color: #909399;
    padding: 20px;
}

.activity-content h4 {
    margin: 0 0 8px 0;
    color: #303133;
}

.activity-content p {
    margin: 0 0 8px 0;
    color: #606266;
    line-height: 1.5;
}

.notes-card {
    background: #f0f9ff;
    border: 1px solid #b3d8ff;
}

.cancel-dialog-content .warning-text {
    color: #e6a23c;
    font-size: 16px;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    gap: 8px;
}

.refund-info {
    margin: 20px 0;
}

.refund-info h4 {
    margin: 0 0 15px 0;
    color: #303133;
}

.days-left, .refund-percentage, .refund-amount {
    font-weight: bold;
    color: #e6a23c;
}

.policy-alert {
    margin-top: 20px;
}

.success-content {
    text-align: center;
    padding: 20px;
}

.success-content i {
    font-size: 48px;
    color: #67c23a;
    margin-bottom: 15px;
}

.success-content p {
    font-size: 16px;
    color: #303133;
    margin: 0;
}

.mt-30 {
    margin-top: 30px;
}
</style>
