package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.dto.EvaluationReplyDTO;
import com.wanderlust.travel.travelportal.entity.EvaluationReply;
import com.wanderlust.travel.travelportal.entity.TourEvaluation;
import com.wanderlust.travel.travelportal.mapper.EvaluationReplyMapper;
import com.wanderlust.travel.travelportal.service.IEvaluationReplyService;
import com.wanderlust.travel.travelportal.service.ITourEvaluationService;
import com.wanderlust.travel.travelportal.vo.EvaluationReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评价回复服务实现类
 */
@Service
@Slf4j
public class EvaluationReplyServiceImpl extends ServiceImpl<EvaluationReplyMapper, EvaluationReply> implements IEvaluationReplyService {

    @Autowired
    private ITourEvaluationService tourEvaluationService;

    @Override
    @Transactional
    public EvaluationReplyVO addOrUpdateReply(Long evalId, EvaluationReplyDTO replyDTO, Long replierId, String replierName, String replierRole) {
        try {
            log.info("添加/更新回复: evalId={}, replierId={}, replierRole={}", evalId, replierId, replierRole);

            // 检查评价是否存在
            TourEvaluation evaluation = tourEvaluationService.getById(evalId);
            if (evaluation == null) {
                throw new RuntimeException("评价不存在");
            }

            // 检查是否已有回复
            LambdaQueryWrapper<EvaluationReply> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EvaluationReply::getEvalId, evalId)
                       .eq(EvaluationReply::getStatus, 1);
            EvaluationReply existingReply = getOne(queryWrapper);

            EvaluationReply reply;
            if (existingReply != null) {
                // 更新现有回复
                existingReply.setReplyContent(replyDTO.getReplyContent());
                existingReply.setReplyTime(LocalDateTime.now());
                existingReply.setReplierId(replierId);
                existingReply.setReplierName(replierName);
                existingReply.setReplierRole(replierRole);
                existingReply.setUpdatedTime(LocalDateTime.now());
                updateById(existingReply);
                reply = existingReply;
                log.info("更新回复成功: replyId={}", reply.getReplyId());
            } else {
                // 创建新回复
                reply = new EvaluationReply();
                reply.setEvalId(evalId);
                reply.setReplyContent(replyDTO.getReplyContent());
                reply.setReplyTime(LocalDateTime.now());
                reply.setReplierId(replierId);
                reply.setReplierName(replierName);
                reply.setReplierRole(replierRole);
                reply.setStatus((byte) 1);
                reply.setCreatedTime(LocalDateTime.now());
                reply.setUpdatedTime(LocalDateTime.now());
                save(reply);
                log.info("创建回复成功: replyId={}", reply.getReplyId());
            }

            // 转换为VO
            EvaluationReplyVO vo = new EvaluationReplyVO();
            BeanUtils.copyProperties(reply, vo);
            return vo;

        } catch (Exception e) {
            log.error("添加/更新回复失败", e);
            throw new RuntimeException("操作失败: " + e.getMessage());
        }
    }

    @Override
    public List<EvaluationReplyVO> getReplyList(Long evalId) {
        try {
            log.info("获取回复列表: evalId={}", evalId);

            LambdaQueryWrapper<EvaluationReply> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EvaluationReply::getEvalId, evalId)
                       .eq(EvaluationReply::getStatus, 1)
                       .orderByDesc(EvaluationReply::getReplyTime);

            List<EvaluationReply> replies = list(queryWrapper);

            return replies.stream().map(reply -> {
                EvaluationReplyVO vo = new EvaluationReplyVO();
                BeanUtils.copyProperties(reply, vo);
                return vo;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            log.error("获取回复列表失败", e);
            return List.of();
        }
    }

    @Override
    @Transactional
    public boolean deleteReply(Long replyId) {
        try {
            log.info("删除回复: replyId={}", replyId);

            EvaluationReply reply = getById(replyId);
            if (reply == null) {
                return false;
            }

            reply.setStatus((byte) 0);
            reply.setUpdatedTime(LocalDateTime.now());
            return updateById(reply);

        } catch (Exception e) {
            log.error("删除回复失败", e);
            return false;
        }
    }
}
