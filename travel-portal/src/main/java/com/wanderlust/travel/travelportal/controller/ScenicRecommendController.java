package com.wanderlust.travel.travelportal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.travelportal.entity.ScenicArticle;
import com.wanderlust.travel.travelportal.entity.HotDestination;
import com.wanderlust.travel.travelportal.service.IScenicRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 景点推荐控制器
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@RestController
@RequestMapping({"/travel-portal/scenic-recommend", "/api/scenic-recommend"})
@Api(tags = "景点推荐管理")
public class ScenicRecommendController {

    @Autowired
    private IScenicRecommendService scenicRecommendService;

    /**
     * 分页查询景点文章列表
     */
    @GetMapping("/articles")
    @ApiOperation("分页查询景点文章列表")
    public Map<String, Object> getArticlePage(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页大小") @RequestParam(defaultValue = "6") Integer size,
            @ApiParam("搜索关键词") @RequestParam(required = false) String keyword,
            @ApiParam("地区筛选") @RequestParam(required = false) String region,
            @ApiParam("排序方式") @RequestParam(defaultValue = "comprehensive") String sortBy) {
        
        Page<ScenicArticle> page = new Page<>(current, size);
        IPage<ScenicArticle> articlePage = scenicRecommendService.getArticlePage(page, keyword, region, sortBy);
        
        Map<String, Object> result = new HashMap<>();
        result.put("articles", articlePage.getRecords());
        result.put("total", articlePage.getTotal());
        result.put("current", articlePage.getCurrent());
        result.put("size", articlePage.getSize());
        result.put("pages", articlePage.getPages());
        
        return result;
    }

    /**
     * 获取文章详情
     */
    @GetMapping("/articles/{articleId}")
    @ApiOperation("获取文章详情")
    public Map<String, Object> getArticleDetail(@ApiParam("文章ID") @PathVariable Long articleId) {
        ScenicArticle article = scenicRecommendService.getArticleDetail(articleId);
        List<Map<String, Object>> packages = scenicRecommendService.getArticlePackages(articleId);
        
        // 增加浏览次数
        scenicRecommendService.incrementArticleViewCount(articleId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("article", article);
        result.put("packages", packages);
        
        return result;
    }

    /**
     * 获取热门目的地列表
     */
    @GetMapping("/hot-destinations")
    @ApiOperation("获取热门目的地列表")
    public List<HotDestination> getHotDestinations(
            @ApiParam("限制数量") @RequestParam(defaultValue = "6") Integer limit) {
        return scenicRecommendService.getHotDestinations(limit);
    }

    /**
     * 获取热门服务商列表
     */
    @GetMapping("/hot-merchants")
    @ApiOperation("获取热门服务商列表")
    public List<Map<String, Object>> getHotMerchants(
            @ApiParam("限制数量") @RequestParam(defaultValue = "3") Integer limit) {
        return scenicRecommendService.getHotMerchants(limit);
    }

    /**
     * 获取地区列表
     */
    @GetMapping("/regions")
    @ApiOperation("获取地区列表")
    public List<String> getRegions() {
        return List.of("华北", "华东", "华南", "西南", "西北", "东北");
    }

    /**
     * 增加目的地浏览次数
     */
    @PostMapping("/destinations/{destId}/view")
    @ApiOperation("增加目的地浏览次数")
    public Map<String, Object> incrementDestinationView(@ApiParam("目的地ID") @PathVariable Long destId) {
        scenicRecommendService.incrementDestinationViewCount(destId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "浏览次数已更新");
        
        return result;
    }
}
