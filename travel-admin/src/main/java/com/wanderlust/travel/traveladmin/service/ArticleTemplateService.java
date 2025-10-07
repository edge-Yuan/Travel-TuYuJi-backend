package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.traveladmin.entity.ArticleTemplate;

import java.util.List;

/**
 * <p>
 * 文章模板表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface ArticleTemplateService extends IService<ArticleTemplate> {

    /**
     * 根据分类获取启用的模板列表
     */
    List<ArticleTemplate> getTemplatesByCategory(String category);

    /**
     * 获取所有启用的模板列表
     */
    List<ArticleTemplate> getAllActiveTemplates();

    /**
     * 创建模板
     */
    boolean createTemplate(ArticleTemplate template);

    /**
     * 更新模板
     */
    boolean updateTemplate(ArticleTemplate template);

    /**
     * 删除模板
     */
    boolean deleteTemplate(Long templateId);

    /**
     * 启用/禁用模板
     */
    boolean toggleTemplateStatus(Long templateId, Boolean isActive);
}
