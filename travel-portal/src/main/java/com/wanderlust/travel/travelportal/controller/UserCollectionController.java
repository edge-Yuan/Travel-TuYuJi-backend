package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.UserCollection;
import com.wanderlust.travel.travelportal.service.IUserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户收藏表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/travel-portal/userCollection")
public class UserCollectionController {

	@Autowired
	private IUserCollectionService userCollectionService;

	@GetMapping("/list")
	public Result<List<UserCollection>> list(@RequestParam Long userId){
		List<UserCollection> list = userCollectionService.lambdaQuery()
			.eq(UserCollection::getUserId, userId)
			.list();
		return Result.success(list);
	}

	@PostMapping("/add")
	public Result<Boolean> add(@RequestBody UserCollection uc){
		boolean ok = userCollectionService.save(uc);
		return ok ? Result.success(true) : Result.error("添加失败");
	}

	@DeleteMapping("/remove")
	public Result<Boolean> remove(@RequestParam Long userId, @RequestParam Long productId){
		boolean ok = userCollectionService.lambdaUpdate()
			.eq(UserCollection::getUserId, userId)
			.eq(UserCollection::getProductId, productId)
			.remove();
		return ok ? Result.success(true) : Result.error("取消失败");
	}

	@GetMapping("/check")
	public Result<Boolean> checkCollection(@RequestParam Long userId, @RequestParam Long productId){
		UserCollection collection = userCollectionService.lambdaQuery()
			.eq(UserCollection::getUserId, userId)
			.eq(UserCollection::getProductId, productId)
			.one();
		return Result.success(collection != null);
	}

	@GetMapping("/count/{userId}")
	public Result<Long> getCollectionCount(@PathVariable Long userId){
		long count = userCollectionService.lambdaQuery()
			.eq(UserCollection::getUserId, userId)
			.count();
		return Result.success(count);
	}

	@DeleteMapping("/batchRemove")
	public Result<Boolean> batchRemove(@RequestParam Long userId, @RequestParam List<Long> productIds){
		boolean ok = userCollectionService.lambdaUpdate()
			.eq(UserCollection::getUserId, userId)
			.in(UserCollection::getProductId, productIds)
			.remove();
		return ok ? Result.success(true) : Result.error("批量取消失败");
	}
}
