package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.dto.ServiceSearchDTO;
import com.wanderlust.travel.traveladmin.entity.ServiceManagement;
import com.wanderlust.travel.traveladmin.vo.ServiceStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服务管理Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface ServiceMapper extends BaseMapper<ServiceManagement> {

    /**
     * 分页查询服务列表
     */
    IPage<ServiceManagement> selectServicePage(Page<ServiceManagement> page, @Param("searchDTO") ServiceSearchDTO searchDTO);

    /**
     * 获取今日服务统计
     */
    ServiceStatisticsVO selectTodayStatistics(@Param("today") LocalDateTime today);

    /**
     * 根据订单号查询服务
     */
    ServiceManagement selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 根据服务类型统计数量
     */
    Integer countByServiceType(@Param("serviceType") String serviceType, @Param("today") LocalDateTime today);

    /**
     * 根据状态统计数量
     */
    Integer countByStatus(@Param("status") String status, @Param("today") LocalDateTime today);

    /**
     * 获取服务人员列表
     */
    List<String> selectStaffList();

    /**
     * 批量更新服务状态
     */
    int batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") String status, @Param("updateBy") String updateBy);
}
