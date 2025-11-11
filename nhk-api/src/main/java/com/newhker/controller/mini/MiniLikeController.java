package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.service.UserLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序端 - 用户点赞控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/like")
@Tag(name = "小程序端-点赞", description = "点赞文章操作")
public class MiniLikeController {
    
    @Autowired
    private UserLikeService userLikeService;
    
    /**
     * 点赞文章
     */
    @PostMapping("/add")
    @Operation(summary = "点赞文章", description = "点赞指定文章")
    public Result<?> likeArticle(
            @RequestParam
            @Parameter(description = "文章ID")
            Long articleId,
            @RequestHeader("userId")
            @Parameter(description = "用户ID")
            Long userId) {
        
        userLikeService.likeArticle(userId, articleId);
        return Result.success();
    }
    
    /**
     * 取消点赞
     */
    @PostMapping("/cancel")
    @Operation(summary = "取消点赞", description = "取消点赞的文章")
    public Result<?> cancelLike(
            @RequestParam
            @Parameter(description = "文章ID")
            Long articleId,
            @RequestHeader("userId")
            @Parameter(description = "用户ID")
            Long userId) {
        
        userLikeService.cancelLike(userId, articleId);
        return Result.success();
    }
    
    /**
     * 检查是否已点赞
     */
    @GetMapping("/check")
    @Operation(summary = "检查是否已点赞", description = "检查是否点赞了指定文章")
    public Result<Boolean> checkLike(
            @RequestParam
            @Parameter(description = "文章ID")
            Long articleId,
            @RequestHeader("userId")
            @Parameter(description = "用户ID")
            Long userId) {
        
        boolean isLiked = userLikeService.isLiked(userId, articleId);
        return Result.success(isLiked);
    }
}
