package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanderlust.travel.traveladmin.entity.ArticleTemplate;
import com.wanderlust.travel.traveladmin.mapper.ArticleTemplateMapper;
import com.wanderlust.travel.traveladmin.service.ArticleTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章模板表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleTemplateServiceImpl extends ServiceImpl<ArticleTemplateMapper, ArticleTemplate> implements ArticleTemplateService {

    private final ObjectMapper objectMapper;

    @Override
    public List<ArticleTemplate> getTemplatesByCategory(String category) {
        log.info("根据分类获取模板列表，分类：{}", category);
        
        List<ArticleTemplate> templates = baseMapper.selectByCategory(category);
        templates.forEach(this::processTemplate);
        
        return templates;
    }

    @Override
    public List<ArticleTemplate> getAllActiveTemplates() {
        log.info("获取所有启用的模板列表");
        
        List<ArticleTemplate> templates = baseMapper.selectActiveTemplates();
        templates.forEach(this::processTemplate);
        
        return templates;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createTemplate(ArticleTemplate template) {
        log.info("创建模板，名称：{}，分类：{}", template.getName(), template.getCategory());
        
        try {
            // 处理标签
            processTemplateTags(template);
            
            // 设置默认值
            if (template.getIsActive() == null) {
                template.setIsActive(true);
            }
            
            boolean success = save(template);
            if (success) {
                log.info("创建模板成功，模板ID：{}", template.getId());
            } else {
                log.error("创建模板失败");
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("创建模板失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTemplate(ArticleTemplate template) {
        log.info("更新模板，模板ID：{}，名称：{}", template.getId(), template.getName());
        
        try {
            // 处理标签
            processTemplateTags(template);
            
            boolean success = updateById(template);
            if (success) {
                log.info("更新模板成功，模板ID：{}", template.getId());
            } else {
                log.error("更新模板失败，模板ID：{}", template.getId());
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("更新模板失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTemplate(Long templateId) {
        log.info("删除模板，模板ID：{}", templateId);
        
        try {
            boolean success = removeById(templateId);
            if (success) {
                log.info("删除模板成功，模板ID：{}", templateId);
            } else {
                log.error("删除模板失败，模板ID：{}", templateId);
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("删除模板失败", e);
            throw e;
        }
    }

    @Override
    public boolean toggleTemplateStatus(Long templateId, Boolean isActive) {
        log.info("切换模板状态，模板ID：{}，状态：{}", templateId, isActive);
        
        try {
            ArticleTemplate template = new ArticleTemplate();
            template.setId(templateId);
            template.setIsActive(isActive);
            
            boolean success = updateById(template);
            if (success) {
                log.info("切换模板状态成功，模板ID：{}，状态：{}", templateId, isActive);
            } else {
                log.error("切换模板状态失败，模板ID：{}", templateId);
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("切换模板状态失败", e);
            throw e;
        }
    }

    /**
     * 处理模板数据（标签）
     */
    private void processTemplate(ArticleTemplate template) {
        // 处理标签
        if (StringUtils.hasText(template.getTags())) {
            try {
                List<String> tagList = objectMapper.readValue(template.getTags(), new TypeReference<List<String>>() {});
                template.setTagList(tagList);
            } catch (Exception e) {
                log.warn("解析模板标签失败，模板ID：{}，标签：{}", template.getId(), template.getTags());
                template.setTagList(new ArrayList<>());
            }
        } else {
            template.setTagList(new ArrayList<>());
        }
    }

    /**
     * 处理模板标签数据
     */
    private void processTemplateTags(ArticleTemplate template) {
        if (!CollectionUtils.isEmpty(template.getTagList())) {
            try {
                String tagsJson = objectMapper.writeValueAsString(template.getTagList());
                template.setTags(tagsJson);
            } catch (Exception e) {
                log.warn("序列化模板标签失败，模板ID：{}，标签：{}", template.getId(), template.getTagList());
                template.setTags("[]");
            }
        } else {
            template.setTags("[]");
        }
    }
}
