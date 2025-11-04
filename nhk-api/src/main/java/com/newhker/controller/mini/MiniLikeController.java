package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.service.UserLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序端 - 用户点赞控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/like")
public class MiniLikeController {
    
    @Autowired
    private UserLikeService userLikeService;
    
    /**
     * 点赞文章
     */
    @PostMapping("/add")
    public Result<?> likeArticle(
            @RequestParam Long articleId,
            @RequestHeader("userId") Long userId) {
        
        userLikeService.likeArticle(userId, articleId);
        return Result.success();
    }
    
    /**
     * 取消点赞
     */
    @PostMapping("/cancel")
    public Result<?> cancelLike(
            @RequestParam Long articleId,
            @RequestHeader("userId") Long userId) {
        
        userLikeService.cancelLike(userId, articleId);
        return Result.success();
    }
    
    /**
     * 检查是否已点赞
     */
    @GetMapping("/check")
    public Result<Boolean> checkLike(
            @RequestParam Long articleId,
            @RequestHeader("userId") Long userId) {
        
        boolean isLiked = userLikeService.isLiked(userId, articleId);
        return Result.success(isLiked);
    }
}
