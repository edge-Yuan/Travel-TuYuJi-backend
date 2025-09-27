package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.entity.GuideExtend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.entity.SysUser;
import org.apache.catalina.User;

import java.util.List;

/**
 * <p>
 * 导游扩展信息表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
public interface IGuideExtendService extends IService<GuideExtend> {
    GuideExtend findGuideExtendByGuideId(Long guideId);
    List<GuideExtend> batchFindGuideExtendByGuideIds(List<Long> guideIds);

}
