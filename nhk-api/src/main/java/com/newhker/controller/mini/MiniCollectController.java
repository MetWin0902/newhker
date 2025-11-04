package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.service.UserCollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序端 - 用户收藏控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/collect")
public class MiniCollectController {
    
    @Autowired
    private UserCollectService userCollectService;
    
    /**
     * 收藏文章
     */
    @PostMapping("/add")
    public Result<?> collectArticle(
            @RequestParam Long articleId,
            @RequestHeader("userId") Long userId) {
        
        userCollectService.collectArticle(userId, articleId);
        return Result.success();
    }
    
    /**
     * 取消收藏
     */
    @PostMapping("/cancel")
    public Result<?> cancelCollect(
            @RequestParam Long articleId,
            @RequestHeader("userId") Long userId) {
        
        userCollectService.cancelCollect(userId, articleId);
        return Result.success();
    }
    
    /**
     * 检查是否已收藏
     */
    @GetMapping("/check")
    public Result<Boolean> checkCollect(
            @RequestParam Long articleId,
            @RequestHeader("userId") Long userId) {
        
        boolean isCollected = userCollectService.isCollected(userId, articleId);
        return Result.success(isCollected);
    }
}
