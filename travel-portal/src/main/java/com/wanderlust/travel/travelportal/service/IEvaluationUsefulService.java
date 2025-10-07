package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.entity.EvaluationUseful;
import com.wanderlust.travel.travelportal.vo.EvaluationUsefulVO;

import java.util.List;

/**
 * 评价有用服务接口
 */
public interface IEvaluationUsefulService extends IService<EvaluationUseful> {

    /**
     * 切换有用状态
     */
    EvaluationUsefulVO toggleUseful(Long evalId, Long userId);

    /**
     * 获取有用状态
     */
    EvaluationUsefulVO getUsefulStatus(Long evalId, Long userId);

    /**
     * 获取有用用户列表
     */
    List<Object> getUsefulUsers(Long evalId, Integer page, Integer size);
}
