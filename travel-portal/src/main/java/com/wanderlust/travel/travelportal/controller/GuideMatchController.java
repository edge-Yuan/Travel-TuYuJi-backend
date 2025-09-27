package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.GuideMatch;
import com.wanderlust.travel.travelportal.service.IGuideMatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 导游匹配需求表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/guideMatch")
@Slf4j
public class GuideMatchController {
	@Autowired
	private IGuideMatchService guideMatchService;

	@GetMapping("/list")
	public Result<List<GuideMatch>> listAll() {
		return Result.success(guideMatchService.list());
	}

	@GetMapping("/{id}")
	public Result<GuideMatch> getById(@PathVariable Long id) {
		GuideMatch gm = guideMatchService.getById(id);
		return gm == null ? Result.error("记录不存在") : Result.success(gm);
	}

	@PostMapping("/create")
	public Result<Boolean> create(@RequestBody GuideMatch guideMatch) {
		log.info("创建导游匹配需求: {}", guideMatch);
		return guideMatchService.save(guideMatch) ? Result.success(true) : Result.error("创建失败");
	}

	@PutMapping("/update")
	public Result<Boolean> update(@RequestBody GuideMatch guideMatch) {
		log.info("更新导游匹配需求: {}", guideMatch);
		return guideMatchService.updateById(guideMatch) ? Result.success(true) : Result.error("更新失败");
	}

	@DeleteMapping("/{id}")
	public Result<Boolean> delete(@PathVariable Long id) {
		return guideMatchService.removeById(id) ? Result.success(true) : Result.error("删除失败");
	}
}
