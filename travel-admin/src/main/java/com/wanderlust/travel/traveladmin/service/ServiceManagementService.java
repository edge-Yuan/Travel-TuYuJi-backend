package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.dto.ServiceExceptionDTO;
import com.wanderlust.travel.traveladmin.dto.ServiceRecordDTO;
import com.wanderlust.travel.traveladmin.dto.ServiceSearchDTO;
import com.wanderlust.travel.traveladmin.entity.ServiceManagement;
import com.wanderlust.travel.traveladmin.entity.ServiceException;
import com.wanderlust.travel.traveladmin.entity.ServiceRecord;
import com.wanderlust.travel.traveladmin.vo.ServiceStatisticsVO;
import com.wanderlust.travel.traveladmin.vo.ServiceVO;

import java.util.List;

/**
 * 服务管理业务接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
public interface ServiceManagementService {

    /**
     * 分页查询服务列表
     */
    IPage<ServiceVO> getServicePage(ServiceSearchDTO searchDTO);

    /**
     * 根据ID获取服务详情
     */
    ServiceVO getServiceById(Long serviceId);

    /**
     * 根据订单号获取服务详情
     */
    ServiceVO getServiceByOrderNo(String orderNo);

    /**
     * 获取今日服务统计
     */
    ServiceStatisticsVO getTodayStatistics();

    /**
     * 开始服务
     */
    boolean startService(Long serviceId, String staffName);

    /**
     * 完成服务
     */
    boolean completeService(Long serviceId, String staffName);

    /**
     * 批量更新服务状态
     */
    boolean batchUpdateServiceStatus(List<Long> serviceIds, String status, String updateBy);

    /**
     * 添加服务记录
     */
    boolean addServiceRecord(ServiceRecordDTO recordDTO, String createBy);

    /**
     * 获取服务记录列表
     */
    List<ServiceRecord> getServiceRecords(Long serviceId);

    /**
     * 添加服务异常
     */
    boolean addServiceException(ServiceExceptionDTO exceptionDTO, String createBy);

    /**
     * 解决异常
     */
    boolean resolveException(Long exceptionId, String solution, String resolveBy);

    /**
     * 升级异常
     */
    boolean escalateException(Long exceptionId, String resolveBy);

    /**
     * 获取服务异常列表
     */
    List<ServiceException> getServiceExceptions(Long serviceId);

    /**
     * 获取服务人员列表
     */
    List<String> getStaffList();

    /**
     * 导出服务数据
     */
    List<ServiceVO> exportServices(ServiceSearchDTO searchDTO);
}
