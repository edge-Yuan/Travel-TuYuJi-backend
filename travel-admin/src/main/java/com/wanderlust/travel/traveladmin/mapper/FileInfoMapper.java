package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件信息Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {
}
