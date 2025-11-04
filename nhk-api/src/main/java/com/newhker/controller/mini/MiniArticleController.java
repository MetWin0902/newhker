package com.newhker.controller.mini;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.service.ArticleService;
import com.newhker.vo.ArticleDetailVO;
import com.newhker.vo.ArticleListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序端 - 文章控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/article")
public class MiniArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 分页获取文章列表
     */
    @GetMapping("/page")
    public Result<PageResult<ArticleListVO>> getArticlePage(
            @RequestParam(required = false) Long moduleId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "userId", required = false) Long userId) {
        
        PageResult<ArticleListVO> result = articleService.getArticlePageForMini(moduleId, pageNum, pageSize);
        return Result.success(result);
    }
    
    /**
     * 获取文章详情
     */
    @GetMapping("/{id}")
    public Result<ArticleDetailVO> getArticleDetail(
            @PathVariable Long id,
            @RequestHeader(value = "userId", required = false) Long userId) {
        
        ArticleDetailVO detail = articleService.getArticleDetail(id, userId);
        return Result.success(detail);
    }
}
