package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wanderlust.travel.travelportal.entity.GuideExtend;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.wanderlust.travel.travelportal.mapper.GuideExtendMapper;
import com.wanderlust.travel.travelportal.service.IGuideExtendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 导游扩展信息表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@Service
public class GuideExtendServiceImpl extends ServiceImpl<GuideExtendMapper, GuideExtend> implements IGuideExtendService {
    @Autowired
    private GuideExtendMapper guideExtendMapper;
    public GuideExtend findGuideExtendByGuideId(Long guideId) {
        return guideExtendMapper.selectById(guideId);
    }

    // 批量查询导游扩展信息
    public List<GuideExtend> batchFindGuideExtendByGuideIds(List<Long> guideIds) {
        if (CollectionUtils.isEmpty(guideIds)) {
            return Collections.emptyList();
        }

        return baseMapper.selectList(
                new QueryWrapper<GuideExtend>()
                        .in("guide_id", guideIds)
        );
    }

}
