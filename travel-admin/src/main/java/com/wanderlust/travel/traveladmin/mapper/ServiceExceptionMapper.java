package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.ServiceException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务异常Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface ServiceExceptionMapper extends BaseMapper<ServiceException> {

    /**
     * 根据服务ID查询异常列表
     */
    List<ServiceException> selectByServiceId(@Param("serviceId") Long serviceId);

    /**
     * 根据异常状态查询异常列表
     */
    List<ServiceException> selectByStatus(@Param("status") String status);

    /**
     * 根据紧急程度查询异常列表
     */
    List<ServiceException> selectByPriority(@Param("priority") String priority);
}
