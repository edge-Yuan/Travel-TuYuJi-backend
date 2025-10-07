package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.ServiceRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务记录Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface ServiceRecordMapper extends BaseMapper<ServiceRecord> {

    /**
     * 根据服务ID查询记录列表
     */
    List<ServiceRecord> selectByServiceId(@Param("serviceId") Long serviceId);

    /**
     * 根据记录类型查询记录列表
     */
    List<ServiceRecord> selectByRecordType(@Param("serviceId") Long serviceId, @Param("recordType") String recordType);
}
