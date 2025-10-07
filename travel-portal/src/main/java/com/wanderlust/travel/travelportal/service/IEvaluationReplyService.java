package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.dto.EvaluationReplyDTO;
import com.wanderlust.travel.travelportal.entity.EvaluationReply;
import com.wanderlust.travel.travelportal.vo.EvaluationReplyVO;

import java.util.List;

/**
 * 评价回复服务接口
 */
public interface IEvaluationReplyService extends IService<EvaluationReply> {

    /**
     * 添加/更新回复
     */
    EvaluationReplyVO addOrUpdateReply(Long evalId, EvaluationReplyDTO replyDTO, Long replierId, String replierName, String replierRole);

    /**
     * 获取回复列表
     */
    List<EvaluationReplyVO> getReplyList(Long evalId);

    /**
     * 删除回复
     */
    boolean deleteReply(Long replyId);
}
