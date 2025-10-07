package com.wanderlust.travel.traveladmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanderlust.travel.traveladmin.common.PageResult;
import com.wanderlust.travel.traveladmin.common.Result;
import com.wanderlust.travel.traveladmin.dto.ServiceExceptionDTO;
import com.wanderlust.travel.traveladmin.dto.ServiceRecordDTO;
import com.wanderlust.travel.traveladmin.dto.ServiceSearchDTO;
import com.wanderlust.travel.traveladmin.entity.ServiceException;
import com.wanderlust.travel.traveladmin.entity.ServiceRecord;
import com.wanderlust.travel.traveladmin.service.ServiceManagementService;
import com.wanderlust.travel.traveladmin.vo.ServiceStatisticsVO;
import com.wanderlust.travel.traveladmin.vo.ServiceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务管理控制器
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/service")
@Api(tags = "服务管理")
@Slf4j
public class ServiceManagementController {

    @Autowired
    private ServiceManagementService serviceManagementService;

    /**
     * 分页查询服务列表
     */
    @PostMapping("/page")
    @ApiOperation("分页查询服务列表")
    public Result<PageResult<ServiceVO>> getServicePage(@RequestBody ServiceSearchDTO searchDTO) {
        log.info("分页查询服务列表请求: {}", searchDTO);
        try {
            IPage<ServiceVO> page = serviceManagementService.getServicePage(searchDTO);
            PageResult<ServiceVO> pageResult = new PageResult<>();
            pageResult.setRecords(page.getRecords());
            pageResult.setTotal(page.getTotal());
            pageResult.setPage(page.getCurrent());
            pageResult.setSize(page.getSize());
            pageResult.setPages(page.getPages());
            return Result.success(pageResult);
        } catch (Exception e) {
            log.error("分页查询服务列表失败", e);
            return Result.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 根据ID获取服务详情
     */
    @GetMapping("/{serviceId}")
    @ApiOperation("获取服务详情")
    public Result<ServiceVO> getServiceById(@ApiParam("服务ID") @PathVariable Long serviceId) {
        log.info("获取服务详情请求: serviceId={}", serviceId);
        try {
            ServiceVO service = serviceManagementService.getServiceById(serviceId);
            return Result.success(service);
        } catch (Exception e) {
            log.error("获取服务详情失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 根据订单号获取服务详情
     */
    @GetMapping("/order/{orderNo}")
    @ApiOperation("根据订单号获取服务详情")
    public Result<ServiceVO> getServiceByOrderNo(@ApiParam("订单号") @PathVariable String orderNo) {
        log.info("根据订单号获取服务详情请求: orderNo={}", orderNo);
        try {
            ServiceVO service = serviceManagementService.getServiceByOrderNo(orderNo);
            return Result.success(service);
        } catch (Exception e) {
            log.error("根据订单号获取服务详情失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 获取今日服务统计
     */
    @GetMapping("/statistics/today")
    @ApiOperation("获取今日服务统计")
    public Result<Map<String, Object>> getTodayStatistics() {
        log.info("获取今日服务统计请求");
        try {
            // 暂时返回模拟数据
            Map<String, Object> statistics = new HashMap<>();
            statistics.put("hotelCheckins", 15);
            statistics.put("ticketEntries", 89);
            statistics.put("routeServices", 12);
            statistics.put("exceptions", 3);
            statistics.put("pendingServices", 5);
            statistics.put("inProgressServices", 8);
            statistics.put("completedServices", 25);
            statistics.put("exceptionServices", 2);
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("获取今日服务统计失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 开始服务
     */
    @PutMapping("/{serviceId}/start")
    @ApiOperation("开始服务")
    public Result<Boolean> startService(
            @ApiParam("服务ID") @PathVariable Long serviceId,
            @ApiParam("服务人员") @RequestParam String staffName) {
        log.info("开始服务请求: serviceId={}, staffName={}", serviceId, staffName);
        try {
            boolean success = serviceManagementService.startService(serviceId, staffName);
            return success ? Result.success(true) : Result.error("开始服务失败");
        } catch (Exception e) {
            log.error("开始服务失败", e);
            return Result.error("开始服务失败: " + e.getMessage());
        }
    }

    /**
     * 完成服务
     */
    @PutMapping("/{serviceId}/complete")
    @ApiOperation("完成服务")
    public Result<Boolean> completeService(
            @ApiParam("服务ID") @PathVariable Long serviceId,
            @ApiParam("服务人员") @RequestParam String staffName) {
        log.info("完成服务请求: serviceId={}, staffName={}", serviceId, staffName);
        try {
            boolean success = serviceManagementService.completeService(serviceId, staffName);
            return success ? Result.success(true) : Result.error("完成服务失败");
        } catch (Exception e) {
            log.error("完成服务失败", e);
            return Result.error("完成服务失败: " + e.getMessage());
        }
    }

    /**
     * 批量更新服务状态
     */
    @PutMapping("/batch/status")
    @ApiOperation("批量更新服务状态")
    public Result<Boolean> batchUpdateServiceStatus(
            @ApiParam("服务ID列表") @RequestBody List<Long> serviceIds,
            @ApiParam("状态") @RequestParam String status,
            @ApiParam("更新人") @RequestParam String updateBy) {
        log.info("批量更新服务状态请求: serviceIds={}, status={}, updateBy={}", serviceIds, status, updateBy);
        try {
            boolean success = serviceManagementService.batchUpdateServiceStatus(serviceIds, status, updateBy);
            return success ? Result.success(true) : Result.error("批量更新状态失败");
        } catch (Exception e) {
            log.error("批量更新服务状态失败", e);
            return Result.error("批量更新状态失败: " + e.getMessage());
        }
    }

    /**
     * 添加服务记录
     */
    @PostMapping("/record")
    @ApiOperation("添加服务记录")
    public Result<Boolean> addServiceRecord(
            @RequestBody ServiceRecordDTO recordDTO,
            @ApiParam("创建人") @RequestParam String createBy) {
        log.info("添加服务记录请求: {}", recordDTO);
        try {
            boolean success = serviceManagementService.addServiceRecord(recordDTO, createBy);
            return success ? Result.success(true) : Result.error("添加服务记录失败");
        } catch (Exception e) {
            log.error("添加服务记录失败", e);
            return Result.error("添加服务记录失败: " + e.getMessage());
        }
    }

    /**
     * 获取服务记录列表
     */
    @GetMapping("/{serviceId}/records")
    @ApiOperation("获取服务记录列表")
    public Result<List<ServiceRecord>> getServiceRecords(@ApiParam("服务ID") @PathVariable Long serviceId) {
        log.info("获取服务记录列表请求: serviceId={}", serviceId);
        try {
            List<ServiceRecord> records = serviceManagementService.getServiceRecords(serviceId);
            return Result.success(records);
        } catch (Exception e) {
            log.error("获取服务记录列表失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 添加服务异常
     */
    @PostMapping("/exception")
    @ApiOperation("添加服务异常")
    public Result<Boolean> addServiceException(
            @RequestBody ServiceExceptionDTO exceptionDTO,
            @ApiParam("创建人") @RequestParam String createBy) {
        log.info("添加服务异常请求: {}", exceptionDTO);
        try {
            boolean success = serviceManagementService.addServiceException(exceptionDTO, createBy);
            return success ? Result.success(true) : Result.error("添加服务异常失败");
        } catch (Exception e) {
            log.error("添加服务异常失败", e);
            return Result.error("添加服务异常失败: " + e.getMessage());
        }
    }

    /**
     * 解决异常
     */
    @PutMapping("/exception/{exceptionId}/resolve")
    @ApiOperation("解决异常")
    public Result<Boolean> resolveException(
            @ApiParam("异常ID") @PathVariable Long exceptionId,
            @ApiParam("处理方案") @RequestParam String solution,
            @ApiParam("处理人") @RequestParam String resolveBy) {
        log.info("解决异常请求: exceptionId={}, solution={}, resolveBy={}", exceptionId, solution, resolveBy);
        try {
            boolean success = serviceManagementService.resolveException(exceptionId, solution, resolveBy);
            return success ? Result.success(true) : Result.error("解决异常失败");
        } catch (Exception e) {
            log.error("解决异常失败", e);
            return Result.error("解决异常失败: " + e.getMessage());
        }
    }

    /**
     * 升级异常
     */
    @PutMapping("/exception/{exceptionId}/escalate")
    @ApiOperation("升级异常")
    public Result<Boolean> escalateException(
            @ApiParam("异常ID") @PathVariable Long exceptionId,
            @ApiParam("处理人") @RequestParam String resolveBy) {
        log.info("升级异常请求: exceptionId={}, resolveBy={}", exceptionId, resolveBy);
        try {
            boolean success = serviceManagementService.escalateException(exceptionId, resolveBy);
            return success ? Result.success(true) : Result.error("升级异常失败");
        } catch (Exception e) {
            log.error("升级异常失败", e);
            return Result.error("升级异常失败: " + e.getMessage());
        }
    }

    /**
     * 获取服务异常列表
     */
    @GetMapping("/{serviceId}/exceptions")
    @ApiOperation("获取服务异常列表")
    public Result<List<ServiceException>> getServiceExceptions(@ApiParam("服务ID") @PathVariable Long serviceId) {
        log.info("获取服务异常列表请求: serviceId={}", serviceId);
        try {
            List<ServiceException> exceptions = serviceManagementService.getServiceExceptions(serviceId);
            return Result.success(exceptions);
        } catch (Exception e) {
            log.error("获取服务异常列表失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 获取服务人员列表
     */
    @GetMapping("/staff/list")
    @ApiOperation("获取服务人员列表")
    public Result<List<String>> getStaffList() {
        log.info("获取服务人员列表请求");
        try {
            List<String> staffList = serviceManagementService.getStaffList();
            return Result.success(staffList);
        } catch (Exception e) {
            log.error("获取服务人员列表失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 导出服务数据
     */
    @PostMapping("/export")
    @ApiOperation("导出服务数据")
    public Result<List<ServiceVO>> exportServices(@RequestBody ServiceSearchDTO searchDTO) {
        log.info("导出服务数据请求: {}", searchDTO);
        try {
            List<ServiceVO> services = serviceManagementService.exportServices(searchDTO);
            return Result.success(services);
        } catch (Exception e) {
            log.error("导出服务数据失败", e);
            return Result.error("导出失败: " + e.getMessage());
        }
    }
}
