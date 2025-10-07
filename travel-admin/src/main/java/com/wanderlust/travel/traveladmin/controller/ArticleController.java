package com.wanderlust.travel.traveladmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.common.Result;
import com.wanderlust.travel.traveladmin.entity.Article;
import com.wanderlust.travel.traveladmin.service.ArticleService;
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
import java.util.Map;

/**
 * <p>
 * 文章管理控制器
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Slf4j
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@Validated
@Api(tags = "文章管理")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 分页查询文章列表
     */
    @GetMapping("/page")
    @ApiOperation("分页查询文章列表")
    public Result<IPage<Article>> getArticlePage(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("搜索关键词") @RequestParam(required = false) String keyword,
            @ApiParam("文章分类") @RequestParam(required = false) String category,
            @ApiParam("发布状态") @RequestParam(required = false) String status,
            @ApiParam("地区") @RequestParam(required = false) String region,
            @ApiParam("是否精选") @RequestParam(required = false) Boolean featured) {
        
        log.info("分页查询文章列表，页码：{}，每页大小：{}，关键词：{}，分类：{}，状态：{}，地区：{}，精选：{}", 
                current, size, keyword, category, status, region, featured);
        
        try {
            Page<Article> page = new Page<>(current, size);
            IPage<Article> articlePage = articleService.getArticlePage(page, keyword, category, status, region, featured);
            return Result.success(articlePage);
        } catch (Exception e) {
            log.error("分页查询文章列表失败", e);
            return Result.error("查询文章列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取文章详情
     */
    @GetMapping("/{id}")
    @ApiOperation("获取文章详情")
    public Result<Article> getArticleDetail(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.info("获取文章详情，文章ID：{}", id);
        
        try {
            Article article = articleService.getArticleDetail(id);
            if (article == null) {
                return Result.error("文章不存在");
            }
            return Result.success(article);
        } catch (Exception e) {
            log.error("获取文章详情失败", e);
            return Result.error("获取文章详情失败：" + e.getMessage());
        }
    }

    /**
     * 创建文章
     */
    @PostMapping("/create")
    @ApiOperation("创建文章")
    public Result<Boolean> createArticle(@Valid @RequestBody ArticleCreateRequest request) {
        log.info("创建文章请求：{}", request);
        
        try {
            boolean success = articleService.createArticle(request.getArticle(), request.getProductIds());
            return success ? Result.success(true) : Result.error("创建文章失败");
        } catch (Exception e) {
            log.error("创建文章失败", e);
            return Result.error("创建文章失败：" + e.getMessage());
        }
    }

    /**
     * 更新文章
     */
    @PutMapping("/update")
    @ApiOperation("更新文章")
    public Result<Boolean> updateArticle(@Valid @RequestBody ArticleUpdateRequest request) {
        log.info("更新文章请求：{}", request);
        
        try {
            boolean success = articleService.updateArticle(request.getArticle(), request.getProductIds());
            return success ? Result.success(true) : Result.error("更新文章失败");
        } catch (Exception e) {
            log.error("更新文章失败", e);
            return Result.error("更新文章失败：" + e.getMessage());
        }
    }

    /**
     * 删除文章
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除文章")
    public Result<Boolean> deleteArticle(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.info("删除文章，文章ID：{}", id);
        
        try {
            boolean success = articleService.deleteArticle(id);
            return success ? Result.success(true) : Result.error("删除文章失败");
        } catch (Exception e) {
            log.error("删除文章失败", e);
            return Result.error("删除文章失败：" + e.getMessage());
        }
    }

    /**
     * 批量删除文章
     */
    @DeleteMapping("/batch")
    @ApiOperation("批量删除文章")
    public Result<Boolean> batchDeleteArticles(@RequestBody List<Long> articleIds) {
        log.info("批量删除文章，文章ID列表：{}", articleIds);
        
        try {
            boolean success = articleService.batchDeleteArticles(articleIds);
            return success ? Result.success(true) : Result.error("批量删除文章失败");
        } catch (Exception e) {
            log.error("批量删除文章失败", e);
            return Result.error("批量删除文章失败：" + e.getMessage());
        }
    }

    /**
     * 批量更新文章状态
     */
    @PutMapping("/batch/status")
    @ApiOperation("批量更新文章状态")
    public Result<Boolean> batchUpdateStatus(@RequestBody BatchUpdateStatusRequest request) {
        log.info("批量更新文章状态请求：{}", request);
        
        try {
            boolean success = articleService.batchUpdateStatus(request.getArticleIds(), request.getStatus());
            return success ? Result.success(true) : Result.error("批量更新文章状态失败");
        } catch (Exception e) {
            log.error("批量更新文章状态失败", e);
            return Result.error("批量更新文章状态失败：" + e.getMessage());
        }
    }

    /**
     * 发布文章
     */
    @PutMapping("/{id}/publish")
    @ApiOperation("发布文章")
    public Result<Boolean> publishArticle(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.info("发布文章，文章ID：{}", id);
        
        try {
            boolean success = articleService.publishArticle(id);
            return success ? Result.success(true) : Result.error("发布文章失败");
        } catch (Exception e) {
            log.error("发布文章失败", e);
            return Result.error("发布文章失败：" + e.getMessage());
        }
    }

    /**
     * 下架文章
     */
    @PutMapping("/{id}/unpublish")
    @ApiOperation("下架文章")
    public Result<Boolean> unpublishArticle(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.info("下架文章，文章ID：{}", id);
        
        try {
            boolean success = articleService.unpublishArticle(id);
            return success ? Result.success(true) : Result.error("下架文章失败");
        } catch (Exception e) {
            log.error("下架文章失败", e);
            return Result.error("下架文章失败：" + e.getMessage());
        }
    }

    /**
     * 复制文章
     */
    @PostMapping("/{id}/copy")
    @ApiOperation("复制文章")
    public Result<Article> copyArticle(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.info("复制文章，文章ID：{}", id);
        
        try {
            Article copyArticle = articleService.copyArticle(id);
            if (copyArticle == null) {
                return Result.error("复制文章失败");
            }
            return Result.success(copyArticle);
        } catch (Exception e) {
            log.error("复制文章失败", e);
            return Result.error("复制文章失败：" + e.getMessage());
        }
    }

    /**
     * 获取文章统计数据
     */
    @GetMapping("/statistics")
    @ApiOperation("获取文章统计数据")
    public Result<Map<String, Object>> getArticleStatistics() {
        log.info("获取文章统计数据");
        
        try {
            Map<String, Object> statistics = articleService.getArticleStatistics();
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("获取文章统计数据失败", e);
            return Result.error("获取文章统计数据失败：" + e.getMessage());
        }
    }

    /**
     * 增加文章浏览次数
     */
    @PutMapping("/{id}/view")
    @ApiOperation("增加文章浏览次数")
    public Result<Boolean> incrementViewCount(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.debug("增加文章浏览次数，文章ID：{}", id);
        
        try {
            articleService.incrementViewCount(id);
            return Result.success(true);
        } catch (Exception e) {
            log.error("增加文章浏览次数失败", e);
            return Result.error("增加文章浏览次数失败：" + e.getMessage());
        }
    }

    /**
     * 增加文章点赞数
     */
    @PutMapping("/{id}/like")
    @ApiOperation("增加文章点赞数")
    public Result<Boolean> incrementLikeCount(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.debug("增加文章点赞数，文章ID：{}", id);
        
        try {
            articleService.incrementLikeCount(id);
            return Result.success(true);
        } catch (Exception e) {
            log.error("增加文章点赞数失败", e);
            return Result.error("增加文章点赞数失败：" + e.getMessage());
        }
    }

    /**
     * 增加文章评论数
     */
    @PutMapping("/{id}/comment")
    @ApiOperation("增加文章评论数")
    public Result<Boolean> incrementCommentCount(@ApiParam("文章ID") @PathVariable @NotNull Long id) {
        log.debug("增加文章评论数，文章ID：{}", id);
        
        try {
            articleService.incrementCommentCount(id);
            return Result.success(true);
        } catch (Exception e) {
            log.error("增加文章评论数失败", e);
            return Result.error("增加文章评论数失败：" + e.getMessage());
        }
    }

    /**
     * 获取热门文章列表
     */
    @GetMapping("/hot")
    @ApiOperation("获取热门文章列表")
    public Result<List<Article>> getHotArticles(@ApiParam("限制数量") @RequestParam(defaultValue = "10") Integer limit) {
        log.info("获取热门文章列表，限制数量：{}", limit);
        
        try {
            List<Article> articles = articleService.getHotArticles(limit);
            return Result.success(articles);
        } catch (Exception e) {
            log.error("获取热门文章列表失败", e);
            return Result.error("获取热门文章列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取最新文章列表
     */
    @GetMapping("/latest")
    @ApiOperation("获取最新文章列表")
    public Result<List<Article>> getLatestArticles(@ApiParam("限制数量") @RequestParam(defaultValue = "10") Integer limit) {
        log.info("获取最新文章列表，限制数量：{}", limit);
        
        try {
            List<Article> articles = articleService.getLatestArticles(limit);
            return Result.success(articles);
        } catch (Exception e) {
            log.error("获取最新文章列表失败", e);
            return Result.error("获取最新文章列表失败：" + e.getMessage());
        }
    }

    /**
     * 获取精选文章列表
     */
    @GetMapping("/featured")
    @ApiOperation("获取精选文章列表")
    public Result<List<Article>> getFeaturedArticles(@ApiParam("限制数量") @RequestParam(defaultValue = "10") Integer limit) {
        log.info("获取精选文章列表，限制数量：{}", limit);
        
        try {
            List<Article> articles = articleService.getFeaturedArticles(limit);
            return Result.success(articles);
        } catch (Exception e) {
            log.error("获取精选文章列表失败", e);
            return Result.error("获取精选文章列表失败：" + e.getMessage());
        }
    }

    /**
     * 搜索文章
     */
    @GetMapping("/search")
    @ApiOperation("搜索文章")
    public Result<List<Article>> searchArticles(
            @ApiParam("搜索关键词") @RequestParam(required = false) String keyword,
            @ApiParam("文章分类") @RequestParam(required = false) String category,
            @ApiParam("地区") @RequestParam(required = false) String region) {
        
        log.info("搜索文章，关键词：{}，分类：{}，地区：{}", keyword, category, region);
        
        try {
            List<Article> articles = articleService.searchArticles(keyword, category, region);
            return Result.success(articles);
        } catch (Exception e) {
            log.error("搜索文章失败", e);
            return Result.error("搜索文章失败：" + e.getMessage());
        }
    }

    /**
     * 文章创建请求
     */
    public static class ArticleCreateRequest {
        private Article article;
        private List<Long> productIds;

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }

        public List<Long> getProductIds() {
            return productIds;
        }

        public void setProductIds(List<Long> productIds) {
            this.productIds = productIds;
        }
    }

    /**
     * 文章更新请求
     */
    public static class ArticleUpdateRequest {
        private Article article;
        private List<Long> productIds;

        public Article getArticle() {
            return article;
        }

        public void setArticle(Article article) {
            this.article = article;
        }

        public List<Long> getProductIds() {
            return productIds;
        }

        public void setProductIds(List<Long> productIds) {
            this.productIds = productIds;
        }
    }

    /**
     * 批量更新状态请求
     */
    public static class BatchUpdateStatusRequest {
        private List<Long> articleIds;
        private String status;

        public List<Long> getArticleIds() {
            return articleIds;
        }

        public void setArticleIds(List<Long> articleIds) {
            this.articleIds = articleIds;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
