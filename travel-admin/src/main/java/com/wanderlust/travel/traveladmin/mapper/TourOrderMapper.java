package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.TourOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 旅游订单表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@Mapper
public interface TourOrderMapper extends BaseMapper<TourOrder> {

    /**
     * 获取订单统计信息
     */
    @Select("SELECT " +
            "COUNT(CASE WHEN order_status = 0 THEN 1 END) as pending, " +
            "COUNT(CASE WHEN order_status = 1 THEN 1 END) as confirmed, " +
            "COUNT(CASE WHEN order_status = 2 THEN 1 END) as completed, " +
            "COUNT(CASE WHEN order_status = 4 THEN 1 END) as refunding, " +
            "COUNT(*) as total " +
            "FROM tour_order")
    Map<String, Object> getOrderStatistics();

    /**
     * 获取今日订单统计
     */
    @Select("SELECT " +
            "COUNT(*) as todayOrders, " +
            "COALESCE(SUM(total_price), 0) as todaySales " +
            "FROM tour_order " +
            "WHERE DATE(create_time) = CURDATE()")
    Map<String, Object> getTodayStatistics();

    /**
     * 获取产品类型统计
     */
    @Select("SELECT " +
            "tp.product_type, " +
            "COUNT(*) as orderCount, " +
            "COALESCE(SUM(to.total_price), 0) as totalSales " +
            "FROM tour_order to " +
            "LEFT JOIN tour_product tp ON to.product_id = tp.product_id " +
            "GROUP BY tp.product_type")
    List<Map<String, Object>> getProductTypeStatistics();

    /**
     * 获取销售趋势数据（最近30天）
     */
    @Select("SELECT " +
            "DATE(create_time) as date, " +
            "COUNT(*) as orderCount, " +
            "COALESCE(SUM(total_price), 0) as sales " +
            "FROM tour_order " +
            "WHERE create_time >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) " +
            "GROUP BY DATE(create_time) " +
            "ORDER BY date")
    List<Map<String, Object>> getSalesTrend();

    /**
     * 获取热销产品
     */
    @Select("SELECT " +
            "tp.product_name, " +
            "tp.product_type, " +
            "COUNT(*) as orderCount, " +
            "COALESCE(SUM(to.total_price), 0) as totalSales " +
            "FROM tour_order to " +
            "LEFT JOIN tour_product tp ON to.product_id = tp.product_id " +
            "GROUP BY tp.product_id, tp.product_name, tp.product_type " +
            "ORDER BY orderCount DESC " +
            "LIMIT 10")
    List<Map<String, Object>> getTopSellingProducts();

    /**
     * 批量更新订单状态
     */
    int batchUpdateOrderStatus(@Param("orderIds") List<Long> orderIds, 
                              @Param("status") Byte status, 
                              @Param("updateTime") LocalDateTime updateTime);

    /**
     * 获取订单详情（包含关联信息）
     */
    @Select("SELECT " +
            "to.*, " +
            "tp.product_name, " +
            "tp.product_type, " +
            "tp.product_image, " +
            "tp.product_description, " +
            "su.username as customer_name, " +
            "su.phone as customer_phone, " +
            "su.email as customer_email " +
            "FROM tour_order to " +
            "LEFT JOIN tour_product tp ON to.product_id = tp.product_id " +
            "LEFT JOIN sys_user su ON to.user_id = su.user_id " +
            "WHERE to.order_id = #{orderId}")
    Map<String, Object> getOrderDetail(@Param("orderId") Long orderId);
}
