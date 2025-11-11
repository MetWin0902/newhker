package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.service.UserCollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序端 - 用户收藏控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/collect")
@Tag(name = "小程序端-收藏", description = "收藏文章操作")
public class MiniCollectController {
    
    @Autowired
    private UserCollectService userCollectService;
    
    /**
     * 收藏文章
     */
    @PostMapping("/add")
    @Operation(summary = "收藏文章", description = "收藏指定的文章")
    public Result<?> collectArticle(
            @RequestParam
            @Parameter(description = "文章ID")
            Long articleId,
            @RequestHeader("userId")
            @Parameter(description = "用户ID")
            Long userId) {
        
        userCollectService.collectArticle(userId, articleId);
        return Result.success();
    }
    
    /**
     * 取消收藏
     */
    @PostMapping("/cancel")
    @Operation(summary = "取消收藏", description = "取消收藏的文章")
    public Result<?> cancelCollect(
            @RequestParam
            @Parameter(description = "文章ID")
            Long articleId,
            @RequestHeader("userId")
            @Parameter(description = "用户ID")
            Long userId) {
        
        userCollectService.cancelCollect(userId, articleId);
        return Result.success();
    }
    
    /**
     * 检查是否已收藏
     */
    @GetMapping("/check")
    @Operation(summary = "检查是否已收藏", description = "检查是否收藏了指定文章")
    public Result<Boolean> checkCollect(
            @RequestParam
            @Parameter(description = "文章ID")
            Long articleId,
            @RequestHeader("userId")
            @Parameter(description = "用户ID")
            Long userId) {
        
        boolean isCollected = userCollectService.isCollected(userId, articleId);
        return Result.success(isCollected);
    }
}
