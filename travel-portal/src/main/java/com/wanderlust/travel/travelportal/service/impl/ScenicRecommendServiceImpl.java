package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.travelportal.entity.ScenicArticle;
import com.wanderlust.travel.travelportal.entity.HotDestination;
import com.wanderlust.travel.travelportal.mapper.ScenicArticleMapper;
import com.wanderlust.travel.travelportal.mapper.ArticlePackageMapper;
import com.wanderlust.travel.travelportal.mapper.HotDestinationMapper;
import com.wanderlust.travel.travelportal.service.IScenicRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 景点推荐服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Service
public class ScenicRecommendServiceImpl implements IScenicRecommendService {

    @Autowired
    private ScenicArticleMapper scenicArticleMapper;

    @Autowired
    private ArticlePackageMapper articlePackageMapper;

    @Autowired
    private HotDestinationMapper hotDestinationMapper;

    @Override
    public IPage<ScenicArticle> getArticlePage(Page<ScenicArticle> page, String keyword, String region, String sortBy) {
        return scenicArticleMapper.selectArticlePage(page, keyword, region, sortBy);
    }

    @Override
    public ScenicArticle getArticleDetail(Long articleId) {
        return scenicArticleMapper.selectById(articleId);
    }

    @Override
    public List<Map<String, Object>> getArticlePackages(Long articleId) {
        return articlePackageMapper.selectPackagesByArticleId(articleId);
    }

    @Override
    public List<HotDestination> getHotDestinations(Integer limit) {
        return hotDestinationMapper.selectHotDestinations(limit);
    }

    @Override
    public List<Map<String, Object>> getHotMerchants(Integer limit) {
        return scenicArticleMapper.selectHotMerchants(limit);
    }

    @Override
    public void incrementArticleViewCount(Long articleId) {
        scenicArticleMapper.incrementViewCount(articleId);
    }

    @Override
    public void incrementDestinationViewCount(Long destId) {
        hotDestinationMapper.incrementViewCount(destId);
    }
}
