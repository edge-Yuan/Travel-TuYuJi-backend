package com.wanderlust.travel.traveladmin.controller;

import com.wanderlust.travel.traveladmin.common.Result;
import com.wanderlust.travel.traveladmin.entity.ArticleTemplate;
import com.wanderlust.travel.traveladmin.service.ArticleTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 文章模板管理控制器
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Slf4j
@RestController
@RequestMapping("/article-template")
@RequiredArgsConstructor
@Validated
@Api(tags = "文章模板管理")
public class ArticleTemplateController {

    private final ArticleTemplateService articleTemplateService;

    /**
     * 获取所有启用的模板列表
     */
    @GetMapping("/list")
    @ApiOperation("获取所有启用的模板列表")
    public Result<List<ArticleTemplate>> getAllActiveTemplates() {
        log.info("获取所有启用的模板列表");
        
        try {
            List<ArticleTemplate> templates = articleTemplateService.getAllActiveTemplates();
            return Result.success(templates);
        } catch (Exception e) {
            log.error("获取模板列表失败", e);
            return Result.error("获取模板列表失败：" + e.getMessage());
        }
    }

    /**
     * 根据分类获取模板列表
     */
    @GetMapping("/category/{category}")
    @ApiOperation("根据分类获取模板列表")
    public Result<List<ArticleTemplate>> getTemplatesByCategory(@ApiParam("分类") @PathVariable String category) {
        log.info("根据分类获取模板列表，分类：{}", category);
        
        try {
            List<ArticleTemplate> templates = articleTemplateService.getTemplatesByCategory(category);
            return Result.success(templates);
        } catch (Exception e) {
            log.error("根据分类获取模板列表失败", e);
            return Result.error("获取模板列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取模板详情
     */
    @GetMapping("/{id}")
    @ApiOperation("获取模板详情")
    public Result<ArticleTemplate> getTemplateDetail(@ApiParam("模板ID") @PathVariable @NotNull Long id) {
        log.info("获取模板详情，模板ID：{}", id);
        
        try {
            ArticleTemplate template = articleTemplateService.getById(id);
            if (template == null) {
                return Result.error("模板不存在");
            }
            return Result.success(template);
        } catch (Exception e) {
            log.error("获取模板详情失败", e);
            return Result.error("获取模板详情失败：" + e.getMessage());
        }
    }

    /**
     * 创建模板
     */
    @PostMapping("/create")
    @ApiOperation("创建模板")
    public Result<Boolean> createTemplate(@Valid @RequestBody ArticleTemplate template) {
        log.info("创建模板请求：{}", template);
        
        try {
            boolean success = articleTemplateService.createTemplate(template);
            return success ? Result.success(true) : Result.error("创建模板失败");
        } catch (Exception e) {
            log.error("创建模板失败", e);
            return Result.error("创建模板失败：" + e.getMessage());
        }
    }

    /**
     * 更新模板
     */
    @PutMapping("/update")
    @ApiOperation("更新模板")
    public Result<Boolean> updateTemplate(@Valid @RequestBody ArticleTemplate template) {
        log.info("更新模板请求：{}", template);
        
        try {
            boolean success = articleTemplateService.updateTemplate(template);
            return success ? Result.success(true) : Result.error("更新模板失败");
        } catch (Exception e) {
            log.error("更新模板失败", e);
            return Result.error("更新模板失败：" + e.getMessage());
        }
    }

    /**
     * 删除模板
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除模板")
    public Result<Boolean> deleteTemplate(@ApiParam("模板ID") @PathVariable @NotNull Long id) {
        log.info("删除模板，模板ID：{}", id);
        
        try {
            boolean success = articleTemplateService.deleteTemplate(id);
            return success ? Result.success(true) : Result.error("删除模板失败");
        } catch (Exception e) {
            log.error("删除模板失败", e);
            return Result.error("删除模板失败：" + e.getMessage());
        }
    }

    /**
     * 启用/禁用模板
     */
    @PutMapping("/{id}/toggle")
    @ApiOperation("启用/禁用模板")
    public Result<Boolean> toggleTemplateStatus(
            @ApiParam("模板ID") @PathVariable @NotNull Long id,
            @ApiParam("是否启用") @RequestParam Boolean isActive) {
        
        log.info("切换模板状态，模板ID：{}，状态：{}", id, isActive);
        
        try {
            boolean success = articleTemplateService.toggleTemplateStatus(id, isActive);
            return success ? Result.success(true) : Result.error("切换模板状态失败");
        } catch (Exception e) {
            log.error("切换模板状态失败", e);
            return Result.error("切换模板状态失败：" + e.getMessage());
        }
    }
}
