package com.wanderlust.travel.traveladmin.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 景点推荐API代理控制器
 * 将请求转发到travel-portal应用
 */
@RestController
@RequestMapping("/travel-portal/scenic-recommend")
public class ScenicRecommendProxyController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String PORTAL_BASE_URL = "http://localhost:8086/travelManagementSystem/travel-portal/scenic-recommend";

    /**
     * 分页查询景点文章列表
     */
    @GetMapping("/articles")
    public ResponseEntity<Map<String, Object>> getArticlePage(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "6") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String region,
            @RequestParam(defaultValue = "comprehensive") String sortBy) {
        
        String url = PORTAL_BASE_URL + "/articles?current=" + current + "&size=" + size;
        if (keyword != null) url += "&keyword=" + keyword;
        if (region != null) url += "&region=" + region;
        url += "&sortBy=" + sortBy;
        
        return restTemplate.getForEntity(url, (Class<Map<String, Object>>) (Class<?>) Map.class);
    }

    /**
     * 获取文章详情
     */
    @GetMapping("/articles/{articleId}")
    public ResponseEntity<Map<String, Object>> getArticleDetail(@PathVariable Long articleId) {
        String url = PORTAL_BASE_URL + "/articles/" + articleId;
        return restTemplate.getForEntity(url, (Class<Map<String, Object>>) (Class<?>) Map.class);
    }

    /**
     * 获取热门目的地列表
     */
    @GetMapping("/hot-destinations")
    public ResponseEntity<Object> getHotDestinations(
            @RequestParam(defaultValue = "6") Integer limit) {
        String url = PORTAL_BASE_URL + "/hot-destinations?limit=" + limit;
        return restTemplate.getForEntity(url, Object.class);
    }

    /**
     * 获取热门服务商列表
     */
    @GetMapping("/hot-merchants")
    public ResponseEntity<Object> getHotMerchants(
            @RequestParam(defaultValue = "3") Integer limit) {
        String url = PORTAL_BASE_URL + "/hot-merchants?limit=" + limit;
        return restTemplate.getForEntity(url, Object.class);
    }

    /**
     * 获取地区列表
     */
    @GetMapping("/regions")
    public ResponseEntity<Object> getRegions() {
        String url = PORTAL_BASE_URL + "/regions";
        return restTemplate.getForEntity(url, Object.class);
    }

    /**
     * 增加目的地浏览次数
     */
    @PostMapping("/destinations/{destId}/view")
    public ResponseEntity<Map<String, Object>> incrementDestinationView(@PathVariable Long destId) {
        String url = PORTAL_BASE_URL + "/destinations/" + destId + "/view";
        return restTemplate.postForEntity(url, null, (Class<Map<String, Object>>) (Class<?>) Map.class);
    }
}
