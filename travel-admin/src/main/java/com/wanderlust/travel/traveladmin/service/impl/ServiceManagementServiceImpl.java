package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanderlust.travel.traveladmin.dto.ServiceExceptionDTO;
import com.wanderlust.travel.traveladmin.dto.ServiceRecordDTO;
import com.wanderlust.travel.traveladmin.dto.ServiceSearchDTO;
import com.wanderlust.travel.traveladmin.entity.ServiceManagement;
import com.wanderlust.travel.traveladmin.entity.ServiceException;
import com.wanderlust.travel.traveladmin.entity.ServiceRecord;
import com.wanderlust.travel.traveladmin.exception.BusinessException;
import com.wanderlust.travel.traveladmin.mapper.ServiceExceptionMapper;
import com.wanderlust.travel.traveladmin.mapper.ServiceMapper;
import com.wanderlust.travel.traveladmin.mapper.ServiceRecordMapper;
import com.wanderlust.travel.traveladmin.service.ServiceManagementService;
import com.wanderlust.travel.traveladmin.vo.ServiceExceptionVO;
import com.wanderlust.travel.traveladmin.vo.ServiceRecordVO;
import com.wanderlust.travel.traveladmin.vo.ServiceStatisticsVO;
import com.wanderlust.travel.traveladmin.vo.ServiceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务管理业务实现类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Service
@Slf4j
public class ServiceManagementServiceImpl implements ServiceManagementService {

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ServiceRecordMapper serviceRecordMapper;

    @Autowired
    private ServiceExceptionMapper serviceExceptionMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public IPage<ServiceVO> getServicePage(ServiceSearchDTO searchDTO) {
        Page<ServiceManagement> page = new Page<>(searchDTO.getPageNum(), searchDTO.getPageSize());
        IPage<ServiceManagement> servicePage = serviceMapper.selectServicePage(page, searchDTO);
        
        // 转换为VO
        IPage<ServiceVO> voPage = new Page<>();
        BeanUtils.copyProperties(servicePage, voPage);
        
        List<ServiceVO> voList = servicePage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        
        voPage.setRecords(voList);
        return voPage;
    }

    @Override
    public ServiceVO getServiceById(Long serviceId) {
        ServiceManagement service = serviceMapper.selectById(serviceId);
        if (service == null) {
            throw new BusinessException("服务不存在");
        }
        return convertToVO(service);
    }

    @Override
    public ServiceVO getServiceByOrderNo(String orderNo) {
        ServiceManagement service = serviceMapper.selectByOrderNo(orderNo);
        if (service == null) {
            throw new BusinessException("服务不存在");
        }
        return convertToVO(service);
    }

    @Override
    public ServiceStatisticsVO getTodayStatistics() {
        return serviceMapper.selectTodayStatistics(LocalDateTime.now());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean startService(Long serviceId, String staffName) {
        ServiceManagement service = serviceMapper.selectById(serviceId);
        if (service == null) {
            throw new BusinessException("服务不存在");
        }
        
        if (!"pending".equals(service.getStatus())) {
            throw new BusinessException("只有待服务状态的服务才能开始");
        }
        
        // 更新服务状态
        service.setStatus("in_progress");
        service.setStaffName(staffName);
        service.setUpdateTime(LocalDateTime.now());
        service.setUpdateBy(staffName);
        
        int result = serviceMapper.updateById(service);
        
        // 添加服务记录
        ServiceRecord record = new ServiceRecord();
        record.setServiceId(serviceId);
        record.setRecordType("service");
        record.setRecordTime(LocalDateTime.now());
        record.setStaffName(staffName);
        record.setContent("开始服务");
        record.setCreateTime(LocalDateTime.now());
        record.setCreateBy(staffName);
        
        serviceRecordMapper.insert(record);
        
        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean completeService(Long serviceId, String staffName) {
        ServiceManagement service = serviceMapper.selectById(serviceId);
        if (service == null) {
            throw new BusinessException("服务不存在");
        }
        
        if (!"in_progress".equals(service.getStatus())) {
            throw new BusinessException("只有服务中状态的服务才能完成");
        }
        
        // 更新服务状态
        service.setStatus("completed");
        service.setUpdateTime(LocalDateTime.now());
        service.setUpdateBy(staffName);
        
        int result = serviceMapper.updateById(service);
        
        // 添加服务记录
        ServiceRecord record = new ServiceRecord();
        record.setServiceId(serviceId);
        record.setRecordType("completion");
        record.setRecordTime(LocalDateTime.now());
        record.setStaffName(staffName);
        record.setContent("服务完成");
        record.setCreateTime(LocalDateTime.now());
        record.setCreateBy(staffName);
        
        serviceRecordMapper.insert(record);
        
        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchUpdateServiceStatus(List<Long> serviceIds, String status, String updateBy) {
        if (CollectionUtils.isEmpty(serviceIds)) {
            throw new BusinessException("服务ID列表不能为空");
        }
        
        return serviceMapper.batchUpdateStatus(serviceIds, status, updateBy) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addServiceRecord(ServiceRecordDTO recordDTO, String createBy) {
        ServiceRecord record = new ServiceRecord();
        BeanUtils.copyProperties(recordDTO, record);
        
        record.setRecordTime(LocalDateTime.now());
        record.setCreateTime(LocalDateTime.now());
        record.setCreateBy(createBy);
        
        // 处理图片列表
        if (!CollectionUtils.isEmpty(recordDTO.getImages())) {
            try {
                record.setImages(objectMapper.writeValueAsString(recordDTO.getImages()));
            } catch (Exception e) {
                log.error("序列化图片列表失败", e);
                throw new BusinessException("图片数据格式错误");
            }
        }
        
        return serviceRecordMapper.insert(record) > 0;
    }

    @Override
    public List<ServiceRecord> getServiceRecords(Long serviceId) {
        return serviceRecordMapper.selectByServiceId(serviceId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addServiceException(ServiceExceptionDTO exceptionDTO, String createBy) {
        ServiceException exception = new ServiceException();
        BeanUtils.copyProperties(exceptionDTO, exception);
        
        exception.setStatus("pending");
        exception.setExceptionTime(LocalDateTime.now());
        exception.setCreateTime(LocalDateTime.now());
        exception.setCreateBy(createBy);
        
        int result = serviceExceptionMapper.insert(exception);
        
        // 更新服务状态为异常
        if (result > 0) {
            ServiceManagement service = serviceMapper.selectById(exceptionDTO.getServiceId());
            if (service != null) {
                service.setStatus("exception");
                service.setUpdateTime(LocalDateTime.now());
                service.setUpdateBy(createBy);
                serviceMapper.updateById(service);
            }
        }
        
        return result > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resolveException(Long exceptionId, String solution, String resolveBy) {
        ServiceException exception = serviceExceptionMapper.selectById(exceptionId);
        if (exception == null) {
            throw new BusinessException("异常记录不存在");
        }
        
        exception.setStatus("resolved");
        exception.setSolution(solution);
        exception.setResolveTime(LocalDateTime.now());
        exception.setResolveBy(resolveBy);
        
        return serviceExceptionMapper.updateById(exception) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean escalateException(Long exceptionId, String resolveBy) {
        ServiceException exception = serviceExceptionMapper.selectById(exceptionId);
        if (exception == null) {
            throw new BusinessException("异常记录不存在");
        }
        
        exception.setStatus("escalated");
        exception.setResolveTime(LocalDateTime.now());
        exception.setResolveBy(resolveBy);
        
        return serviceExceptionMapper.updateById(exception) > 0;
    }

    @Override
    public List<ServiceException> getServiceExceptions(Long serviceId) {
        return serviceExceptionMapper.selectByServiceId(serviceId);
    }

    @Override
    public List<String> getStaffList() {
        return serviceMapper.selectStaffList();
    }

    @Override
    public List<ServiceVO> exportServices(ServiceSearchDTO searchDTO) {
        // 设置大分页获取所有数据
        searchDTO.setPageNum(1);
        searchDTO.setPageSize(10000);
        
        IPage<ServiceVO> page = getServicePage(searchDTO);
        return page.getRecords();
    }

    /**
     * 转换为VO对象
     */
    private ServiceVO convertToVO(ServiceManagement service) {
        ServiceVO vo = new ServiceVO();
        BeanUtils.copyProperties(service, vo);
        
        // 获取服务记录
        List<ServiceRecord> records = getServiceRecords(service.getId());
        List<ServiceRecordVO> recordVOs = records.stream()
                .map(this::convertRecordToVO)
                .collect(Collectors.toList());
        vo.setRecords(recordVOs);
        
        // 获取异常记录
        List<ServiceException> exceptions = getServiceExceptions(service.getId());
        List<ServiceExceptionVO> exceptionVOs = exceptions.stream()
                .map(this::convertExceptionToVO)
                .collect(Collectors.toList());
        vo.setExceptions(exceptionVOs);
        
        return vo;
    }

    /**
     * 转换记录为VO
     */
    private ServiceRecordVO convertRecordToVO(ServiceRecord record) {
        ServiceRecordVO vo = new ServiceRecordVO();
        BeanUtils.copyProperties(record, vo);
        
        // 处理图片列表
        if (StringUtils.hasText(record.getImages())) {
            try {
                List<String> images = objectMapper.readValue(record.getImages(), new TypeReference<List<String>>() {});
                vo.setImages(images);
            } catch (Exception e) {
                log.error("反序列化图片列表失败", e);
                vo.setImages(new ArrayList<>());
            }
        } else {
            vo.setImages(new ArrayList<>());
        }
        
        return vo;
    }

    /**
     * 转换异常为VO
     */
    private ServiceExceptionVO convertExceptionToVO(ServiceException exception) {
        ServiceExceptionVO vo = new ServiceExceptionVO();
        BeanUtils.copyProperties(exception, vo);
        return vo;
    }
}
