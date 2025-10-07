package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.entity.EvaluationUseful;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.wanderlust.travel.travelportal.entity.TourEvaluation;
import com.wanderlust.travel.travelportal.mapper.EvaluationUsefulMapper;
import com.wanderlust.travel.travelportal.service.IEvaluationUsefulService;
import com.wanderlust.travel.travelportal.service.ISysUserService;
import com.wanderlust.travel.travelportal.service.ITourEvaluationService;
import com.wanderlust.travel.travelportal.vo.EvaluationUsefulVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评价有用服务实现类
 */
@Service
@Slf4j
public class EvaluationUsefulServiceImpl extends ServiceImpl<EvaluationUsefulMapper, EvaluationUseful> implements IEvaluationUsefulService {

    @Autowired
    private ITourEvaluationService tourEvaluationService;

    @Autowired
    private ISysUserService sysUserService;

    @Override
    @Transactional
    public EvaluationUsefulVO toggleUseful(Long evalId, Long userId) {
        try {
            log.info("切换有用状态: evalId={}, userId={}", evalId, userId);

            // 检查评价是否存在
            TourEvaluation evaluation = tourEvaluationService.getById(evalId);
            if (evaluation == null) {
                throw new RuntimeException("评价不存在");
            }

            // 查询现有记录
            LambdaQueryWrapper<EvaluationUseful> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EvaluationUseful::getEvalId, evalId)
                       .eq(EvaluationUseful::getUserId, userId);
            EvaluationUseful existing = getOne(queryWrapper);

            boolean isUseful;
            if (existing != null) {
                // 切换状态
                isUseful = existing.getStatus() == 0;
                existing.setStatus((byte) (isUseful ? 1 : 0));
                existing.setUsefulTime(LocalDateTime.now());
                existing.setUpdatedTime(LocalDateTime.now());
                updateById(existing);
                log.info("切换有用状态: {} -> {}", !isUseful, isUseful);
            } else {
                // 创建新记录
                existing = new EvaluationUseful();
                existing.setEvalId(evalId);
                existing.setUserId(userId);
                existing.setStatus((byte) 1);
                existing.setUsefulTime(LocalDateTime.now());
                existing.setCreatedTime(LocalDateTime.now());
                existing.setUpdatedTime(LocalDateTime.now());
                save(existing);
                isUseful = true;
                log.info("创建有用记录: evalId={}, userId={}", evalId, userId);
            }

            // 统计有用数量
            int usefulCount = baseMapper.countByEvalId(evalId);

            // 构建返回结果
            EvaluationUsefulVO vo = new EvaluationUsefulVO();
            vo.setEvalId(evalId);
            vo.setIsUseful(isUseful);
            vo.setUsefulCount(usefulCount);
            vo.setUserId(userId);

            return vo;

        } catch (Exception e) {
            log.error("切换有用状态失败", e);
            throw new RuntimeException("操作失败: " + e.getMessage());
        }
    }

    @Override
    public EvaluationUsefulVO getUsefulStatus(Long evalId, Long userId) {
        try {
            log.info("获取有用状态: evalId={}, userId={}", evalId, userId);

            // 查询用户的有用记录
            LambdaQueryWrapper<EvaluationUseful> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EvaluationUseful::getEvalId, evalId)
                       .eq(EvaluationUseful::getUserId, userId);
            EvaluationUseful useful = getOne(queryWrapper);

            // 统计有用数量
            int usefulCount = baseMapper.countByEvalId(evalId);

            // 构建返回结果
            EvaluationUsefulVO vo = new EvaluationUsefulVO();
            vo.setEvalId(evalId);
            vo.setIsUseful(useful != null && useful.getStatus() == 1);
            vo.setUsefulCount(usefulCount);
            vo.setUserId(userId);

            return vo;

        } catch (Exception e) {
            log.error("获取有用状态失败", e);
            return new EvaluationUsefulVO();
        }
    }

    @Override
    public List<Object> getUsefulUsers(Long evalId, Integer page, Integer size) {
        try {
            log.info("获取有用用户列表: evalId={}, page={}, size={}", evalId, page, size);

            Page<EvaluationUseful> pageParam = new Page<>(page, size);
            Page<EvaluationUseful> usefulPage = baseMapper.selectUsefulUsersByEvalId(pageParam, evalId);

            List<EvaluationUseful> usefulList = usefulPage.getRecords();
            if (usefulList.isEmpty()) {
                return List.of();
            }

            // 获取用户信息
            List<Long> userIds = usefulList.stream()
                    .map(EvaluationUseful::getUserId)
                    .distinct()
                    .toList();

            List<SysUser> users = sysUserService.listByIds(userIds);
            Map<Long, SysUser> userMap = users.stream()
                    .collect(java.util.stream.Collectors.toMap(SysUser::getUserId, user -> user));

            // 构建返回结果
            List<Object> result = new ArrayList<>();
            for (EvaluationUseful useful : usefulList) {
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("userId", useful.getUserId());
                userInfo.put("usefulTime", useful.getUsefulTime());

                SysUser user = userMap.get(useful.getUserId());
                if (user != null) {
                    // 脱敏用户名
                    String realName = user.getRealName();
                    if (realName != null && realName.length() > 1) {
                        if (realName.length() == 2) {
                            userInfo.put("userName", realName.charAt(0) + "***");
                        } else {
                            userInfo.put("userName", realName.charAt(0) + "***" + realName.charAt(realName.length() - 1));
                        }
                    } else {
                        userInfo.put("userName", "***");
                    }
                } else {
                    userInfo.put("userName", "匿名用户");
                }

                result.add(userInfo);
            }

            return result;

        } catch (Exception e) {
            log.error("获取有用用户列表失败", e);
            return List.of();
        }
    }
}
