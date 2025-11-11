package com.newhker.controller.mini;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.service.ArticleService;
import com.newhker.vo.ArticleDetailVO;
import com.newhker.vo.ArticleListVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序端 - 文章控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/article")
@Tag(name = "小程序端-文章", description = "文章查看操作")
public class MiniArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 分页获取文章列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页获取文章列表", description = "分页查询已发布的文章")
    public Result<PageResult<ArticleListVO>> getArticlePage(
            @RequestParam(required = false)
            @Parameter(description = "模块ID")
            Long moduleId,
            @RequestParam(defaultValue = "1")
            @Parameter(description = "页码")
            Integer pageNum,
            @RequestParam(defaultValue = "10")
            @Parameter(description = "每页记录数")
            Integer pageSize,
            @RequestHeader(value = "userId", required = false)
            @Parameter(description = "用户ID")
            Long userId) {
        
        PageResult<ArticleListVO> result = articleService.getArticlePageForMini(moduleId, pageNum, pageSize);
        return Result.success(result);
    }
    
    /**
     * 获取文章详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取文章详情", description = "获取文章的完整详细信息")
    public Result<ArticleDetailVO> getArticleDetail(
            @PathVariable
            @Parameter(description = "文章ID")
            Long id,
            @RequestHeader(value = "userId", required = false)
            @Parameter(description = "用户ID")
            Long userId) {
        
        ArticleDetailVO detail = articleService.getArticleDetail(id, userId);
        return Result.success(detail);
    }
}
