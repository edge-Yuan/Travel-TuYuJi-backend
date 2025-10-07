package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.dto.ArticlePackageDTO;
import com.wanderlust.travel.travelportal.dto.BatchArticlePackageDTO;
import com.wanderlust.travel.travelportal.service.IArticlePackageService;
import com.wanderlust.travel.travelportal.vo.ArticlePackageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 文章套餐关联控制器
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@RestController
@RequestMapping("/travel-portal/article-package")
@Api(tags = "文章套餐关联管理")
@Slf4j
public class ArticlePackageController {

    @Autowired
    private IArticlePackageService articlePackageService;

    /**
     * 添加文章套餐关联
     */
    @PostMapping("/add")
    @ApiOperation("添加文章套餐关联")
    public Result<Boolean> addArticlePackage(@Valid @RequestBody ArticlePackageDTO articlePackageDTO) {
        log.info("添加文章套餐关联请求: {}", articlePackageDTO);
        boolean success = articlePackageService.addArticlePackage(articlePackageDTO);
        return success ? Result.success(true) : Result.error("添加文章套餐关联失败");
    }

    /**
     * 更新文章套餐关联
     */
    @PutMapping("/update")
    @ApiOperation("更新文章套餐关联")
    public Result<Boolean> updateArticlePackage(@Valid @RequestBody ArticlePackageDTO articlePackageDTO) {
        log.info("更新文章套餐关联请求: {}", articlePackageDTO);
        boolean success = articlePackageService.updateArticlePackage(articlePackageDTO);
        return success ? Result.success(true) : Result.error("更新文章套餐关联失败");
    }

    /**
     * 删除文章套餐关联
     */
    @DeleteMapping("/{relId}")
    @ApiOperation("删除文章套餐关联")
    public Result<Boolean> deleteArticlePackage(@ApiParam("关联ID") @PathVariable Long relId) {
        log.info("删除文章套餐关联请求: relId={}", relId);
        boolean success = articlePackageService.deleteArticlePackage(relId);
        return success ? Result.success(true) : Result.error("删除文章套餐关联失败");
    }

    /**
     * 根据文章ID获取套餐关联列表
     */
    @GetMapping("/article/{articleId}")
    @ApiOperation("根据文章ID获取套餐关联列表")
    public Result<List<ArticlePackageVO>> getArticlePackages(@ApiParam("文章ID") @PathVariable Long articleId) {
        log.info("获取文章套餐关联列表请求: articleId={}", articleId);
        List<ArticlePackageVO> packages = articlePackageService.getArticlePackages(articleId);
        return Result.success(packages);
    }

    /**
     * 根据关联ID获取套餐关联详情
     */
    @GetMapping("/{relId}")
    @ApiOperation("根据关联ID获取套餐关联详情")
    public Result<ArticlePackageVO> getArticlePackageById(@ApiParam("关联ID") @PathVariable Long relId) {
        log.info("获取文章套餐关联详情请求: relId={}", relId);
        ArticlePackageVO packageVO = articlePackageService.getArticlePackageById(relId);
        return packageVO != null ? Result.success(packageVO) : Result.error("文章套餐关联不存在");
    }

    /**
     * 批量操作文章套餐关联
     */
    @PostMapping("/batch")
    @ApiOperation("批量操作文章套餐关联")
    public Result<Boolean> batchOperateArticlePackages(@Valid @RequestBody BatchArticlePackageDTO batchDTO) {
        log.info("批量操作文章套餐关联请求: {}", batchDTO);
        boolean success = articlePackageService.batchOperateArticlePackages(batchDTO);
        return success ? Result.success(true) : Result.error("批量操作文章套餐关联失败");
    }

    /**
     * 检查文章套餐关联是否存在
     */
    @GetMapping("/check")
    @ApiOperation("检查文章套餐关联是否存在")
    public Result<Boolean> checkArticlePackageExists(
            @ApiParam("文章ID") @RequestParam Long articleId,
            @ApiParam("产品ID") @RequestParam Long productId) {
        log.info("检查文章套餐关联是否存在: articleId={}, productId={}", articleId, productId);
        boolean exists = articlePackageService.checkArticlePackageExists(articleId, productId);
        return Result.success(exists);
    }

    /**
     * 更新套餐关联排序
     */
    @PutMapping("/{relId}/sort")
    @ApiOperation("更新套餐关联排序")
    public Result<Boolean> updatePackageSortOrder(
            @ApiParam("关联ID") @PathVariable Long relId,
            @ApiParam("排序权重") @RequestParam Integer sortOrder) {
        log.info("更新套餐关联排序请求: relId={}, sortOrder={}", relId, sortOrder);
        boolean success = articlePackageService.updatePackageSortOrder(relId, sortOrder);
        return success ? Result.success(true) : Result.error("更新套餐关联排序失败");
    }

    /**
     * 获取所有文章套餐关联列表
     */
    @GetMapping("/list")
    @ApiOperation("获取所有文章套餐关联列表")
    public Result<List<ArticlePackageVO>> getAllArticlePackages() {
        log.info("获取所有文章套餐关联列表请求");
        List<com.wanderlust.travel.travelportal.entity.ArticlePackage> packages = articlePackageService.list();
        List<ArticlePackageVO> packageVOs = new java.util.ArrayList<>();
        
        for (com.wanderlust.travel.travelportal.entity.ArticlePackage pkg : packages) {
            ArticlePackageVO packageVO = new ArticlePackageVO();
            org.springframework.beans.BeanUtils.copyProperties(pkg, packageVO);
            packageVOs.add(packageVO);
        }
        
        return Result.success(packageVOs);
    }
}
