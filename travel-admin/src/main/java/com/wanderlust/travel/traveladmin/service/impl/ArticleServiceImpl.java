package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanderlust.travel.traveladmin.entity.Article;
import com.wanderlust.travel.traveladmin.entity.ArticlePackage;
import com.wanderlust.travel.traveladmin.mapper.ArticleMapper;
import com.wanderlust.travel.traveladmin.service.ArticlePackageService;
import com.wanderlust.travel.traveladmin.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章主表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticlePackageService articlePackageService;
    private final ObjectMapper objectMapper;

    @Override
    public IPage<Article> getArticlePage(Page<Article> page, String keyword, String category, 
                                        String status, String region, Boolean featured) {
        log.info("分页查询文章列表，页码：{}，每页大小：{}，关键词：{}，分类：{}，状态：{}，地区：{}，精选：{}", 
                page.getCurrent(), page.getSize(), keyword, category, status, region, featured);
        
        IPage<Article> articlePage = baseMapper.selectArticlePage(page, keyword, category, status, region, featured);
        
        // 处理标签和关联套餐
        articlePage.getRecords().forEach(this::processArticle);
        
        return articlePage;
    }

    @Override
    public Article getArticleDetail(Long articleId) {
        log.info("获取文章详情，文章ID：{}", articleId);
        
        Article article = getById(articleId);
        if (article != null) {
            processArticle(article);
        }
        
        return article;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createArticle(Article article, List<Long> productIds) {
        log.info("创建文章，标题：{}，作者：{}", article.getTitle(), article.getAuthor());
        
        try {
            // 处理标签
            processTags(article);
            
            // 设置默认值
            if (article.getViews() == null) article.setViews(0);
            if (article.getLikes() == null) article.setLikes(0);
            if (article.getComments() == null) article.setComments(0);
            if (article.getFeatured() == null) article.setFeatured(false);
            if (!StringUtils.hasText(article.getStatus())) article.setStatus("draft");
            
            // 保存文章
            boolean success = save(article);
            if (!success) {
                log.error("保存文章失败");
                return false;
            }
            
            // 保存关联套餐
            if (!CollectionUtils.isEmpty(productIds)) {
                success = articlePackageService.batchAddArticlePackages(article.getId(), productIds);
                if (!success) {
                    log.error("保存文章套餐关联失败");
                    throw new RuntimeException("保存文章套餐关联失败");
                }
            }
            
            log.info("创建文章成功，文章ID：{}", article.getId());
            return true;
            
        } catch (Exception e) {
            log.error("创建文章失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticle(Article article, List<Long> productIds) {
        log.info("更新文章，文章ID：{}，标题：{}", article.getId(), article.getTitle());
        
        try {
            // 处理标签
            processTags(article);
            
            // 更新文章
            boolean success = updateById(article);
            if (!success) {
                log.error("更新文章失败");
                return false;
            }
            
            // 更新关联套餐
            if (productIds != null) {
                // 删除原有关联
                articlePackageService.deleteByArticleId(article.getId());
                
                // 添加新关联
                if (!CollectionUtils.isEmpty(productIds)) {
                    success = articlePackageService.batchAddArticlePackages(article.getId(), productIds);
                    if (!success) {
                        log.error("更新文章套餐关联失败");
                        throw new RuntimeException("更新文章套餐关联失败");
                    }
                }
            }
            
            log.info("更新文章成功，文章ID：{}", article.getId());
            return true;
            
        } catch (Exception e) {
            log.error("更新文章失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteArticle(Long articleId) {
        log.info("删除文章，文章ID：{}", articleId);
        
        try {
            // 删除关联套餐
            articlePackageService.deleteByArticleId(articleId);
            
            // 删除文章
            boolean success = removeById(articleId);
            if (success) {
                log.info("删除文章成功，文章ID：{}", articleId);
            } else {
                log.error("删除文章失败，文章ID：{}", articleId);
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("删除文章失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteArticles(List<Long> articleIds) {
        log.info("批量删除文章，文章ID列表：{}", articleIds);
        
        try {
            // 删除关联套餐
            for (Long articleId : articleIds) {
                articlePackageService.deleteByArticleId(articleId);
            }
            
            // 批量删除文章
            int count = baseMapper.batchDelete(articleIds);
            boolean success = count > 0;
            
            if (success) {
                log.info("批量删除文章成功，删除数量：{}", count);
            } else {
                log.error("批量删除文章失败");
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("批量删除文章失败", e);
            throw e;
        }
    }

    @Override
    public boolean batchUpdateStatus(List<Long> articleIds, String status) {
        log.info("批量更新文章状态，文章ID列表：{}，状态：{}", articleIds, status);
        
        try {
            int count = baseMapper.batchUpdateStatus(articleIds, status);
            boolean success = count > 0;
            
            if (success) {
                log.info("批量更新文章状态成功，更新数量：{}", count);
            } else {
                log.error("批量更新文章状态失败");
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("批量更新文章状态失败", e);
            throw e;
        }
    }

    @Override
    public boolean publishArticle(Long articleId) {
        log.info("发布文章，文章ID：{}", articleId);
        
        Article article = getById(articleId);
        if (article == null) {
            log.error("文章不存在，文章ID：{}", articleId);
            return false;
        }
        
        article.setStatus("published");
        article.setPublishTime(LocalDateTime.now());
        
        boolean success = updateById(article);
        if (success) {
            log.info("发布文章成功，文章ID：{}", articleId);
        } else {
            log.error("发布文章失败，文章ID：{}", articleId);
        }
        
        return success;
    }

    @Override
    public boolean unpublishArticle(Long articleId) {
        log.info("下架文章，文章ID：{}", articleId);
        
        Article article = getById(articleId);
        if (article == null) {
            log.error("文章不存在，文章ID：{}", articleId);
            return false;
        }
        
        article.setStatus("draft");
        
        boolean success = updateById(article);
        if (success) {
            log.info("下架文章成功，文章ID：{}", articleId);
        } else {
            log.error("下架文章失败，文章ID：{}", articleId);
        }
        
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article copyArticle(Long articleId) {
        log.info("复制文章，文章ID：{}", articleId);
        
        Article originalArticle = getById(articleId);
        if (originalArticle == null) {
            log.error("原文章不存在，文章ID：{}", articleId);
            return null;
        }
        
        // 创建副本
        Article copyArticle = new Article();
        copyArticle.setTitle(originalArticle.getTitle() + " (副本)");
        copyArticle.setAuthor(originalArticle.getAuthor());
        copyArticle.setCategory(originalArticle.getCategory());
        copyArticle.setCoverImage(originalArticle.getCoverImage());
        copyArticle.setSummary(originalArticle.getSummary());
        copyArticle.setContent(originalArticle.getContent());
        copyArticle.setTags(originalArticle.getTags());
        copyArticle.setRegion(originalArticle.getRegion());
        copyArticle.setFeatured(false);
        copyArticle.setStatus("draft");
        copyArticle.setViews(0);
        copyArticle.setLikes(0);
        copyArticle.setComments(0);
        copyArticle.setSeoTitle(originalArticle.getSeoTitle());
        copyArticle.setSeoKeywords(originalArticle.getSeoKeywords());
        copyArticle.setSeoDescription(originalArticle.getSeoDescription());
        
        // 保存副本
        boolean success = save(copyArticle);
        if (!success) {
            log.error("保存文章副本失败");
            return null;
        }
        
        // 复制关联套餐
        List<ArticlePackage> originalPackages = articlePackageService.getPackagesByArticleId(articleId);
        if (!CollectionUtils.isEmpty(originalPackages)) {
            List<Long> productIds = new ArrayList<>();
            for (ArticlePackage pkg : originalPackages) {
                productIds.add(pkg.getProductId());
            }
            articlePackageService.batchAddArticlePackages(copyArticle.getId(), productIds);
        }
        
        log.info("复制文章成功，新文章ID：{}", copyArticle.getId());
        return copyArticle;
    }

    @Override
    public Map<String, Object> getArticleStatistics() {
        log.info("获取文章统计数据");
        
        Map<String, Object> statistics = baseMapper.selectArticleStatistics();
        log.info("文章统计数据：{}", statistics);
        
        return statistics;
    }

    @Override
    public void incrementViewCount(Long articleId) {
        log.debug("增加文章浏览次数，文章ID：{}", articleId);
        baseMapper.incrementViewCount(articleId);
    }

    @Override
    public void incrementLikeCount(Long articleId) {
        log.debug("增加文章点赞数，文章ID：{}", articleId);
        baseMapper.incrementLikeCount(articleId);
    }

    @Override
    public void incrementCommentCount(Long articleId) {
        log.debug("增加文章评论数，文章ID：{}", articleId);
        baseMapper.incrementCommentCount(articleId);
    }

    @Override
    public List<Article> getHotArticles(Integer limit) {
        log.info("获取热门文章列表，限制数量：{}", limit);
        
        List<Article> articles = baseMapper.selectHotArticles(limit);
        articles.forEach(this::processArticle);
        
        return articles;
    }

    @Override
    public List<Article> getLatestArticles(Integer limit) {
        log.info("获取最新文章列表，限制数量：{}", limit);
        
        List<Article> articles = baseMapper.selectLatestArticles(limit);
        articles.forEach(this::processArticle);
        
        return articles;
    }

    @Override
    public List<Article> getFeaturedArticles(Integer limit) {
        log.info("获取精选文章列表，限制数量：{}", limit);
        
        List<Article> articles = baseMapper.selectFeaturedArticles(limit);
        articles.forEach(this::processArticle);
        
        return articles;
    }

    @Override
    public List<Article> searchArticles(String keyword, String category, String region) {
        log.info("搜索文章，关键词：{}，分类：{}，地区：{}", keyword, category, region);
        
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, "published");
        
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper
                .like(Article::getTitle, keyword)
                .or()
                .like(Article::getSummary, keyword)
                .or()
                .like(Article::getContent, keyword)
            );
        }
        
        if (StringUtils.hasText(category)) {
            queryWrapper.eq(Article::getCategory, category);
        }
        
        if (StringUtils.hasText(region)) {
            queryWrapper.eq(Article::getRegion, region);
        }
        
        queryWrapper.orderByDesc(Article::getPublishTime);
        
        List<Article> articles = list(queryWrapper);
        articles.forEach(this::processArticle);
        
        return articles;
    }

    /**
     * 处理文章数据（标签和关联套餐）
     */
    private void processArticle(Article article) {
        // 处理标签
        if (StringUtils.hasText(article.getTags())) {
            try {
                List<String> tagList = objectMapper.readValue(article.getTags(), new TypeReference<List<String>>() {});
                article.setTagList(tagList);
            } catch (Exception e) {
                log.warn("解析文章标签失败，文章ID：{}，标签：{}", article.getId(), article.getTags());
                article.setTagList(new ArrayList<>());
            }
        } else {
            article.setTagList(new ArrayList<>());
        }
        
        // 获取关联套餐
        List<ArticlePackage> packages = articlePackageService.getPackagesByArticleId(article.getId());
        article.setRelatedPackages(packages);
    }

    /**
     * 处理标签数据
     */
    private void processTags(Article article) {
        if (!CollectionUtils.isEmpty(article.getTagList())) {
            try {
                String tagsJson = objectMapper.writeValueAsString(article.getTagList());
                article.setTags(tagsJson);
            } catch (Exception e) {
                log.warn("序列化文章标签失败，文章ID：{}，标签：{}", article.getId(), article.getTagList());
                article.setTags("[]");
            }
        } else {
            article.setTags("[]");
        }
    }
}
