package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.entity.ProductImportExportLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 产品导入导出日志Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface ProductImportExportLogMapper extends BaseMapper<ProductImportExportLog> {

    /**
     * 分页查询导入导出日志
     * 
     * @param page 分页参数
     * @param merchantId 商家ID
     * @param operationType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 日志列表
     */
    IPage<ProductImportExportLog> selectLogPage(Page<ProductImportExportLog> page,
                                               @Param("merchantId") Long merchantId,
                                               @Param("operationType") String operationType,
                                               @Param("startTime") LocalDateTime startTime,
                                               @Param("endTime") LocalDateTime endTime);
}
