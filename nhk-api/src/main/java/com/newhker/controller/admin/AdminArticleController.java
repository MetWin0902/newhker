package com.newhker.controller.admin;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.dto.ArticleDTO;
import com.newhker.dto.ArticleQueryDTO;
import com.newhker.entity.Article;
import com.newhker.service.ArticleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理后台 - 文章管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 分页查询文章列表
     */
    @GetMapping("/page")
    public Result<PageResult<Article>> getArticlePage(ArticleQueryDTO query) {
        PageResult<Article> result = articleService.getArticlePageForAdmin(query);
        return Result.success(result);
    }
    
    /**
     * 获取文章详情
     */
    @GetMapping("/{id}")
    public Result<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getById(id);
        return Result.success(article);
    }
    
    /**
     * 创建或更新文章
     */
    @PostMapping
    public Result<?> saveOrUpdate(
            @Valid @RequestBody ArticleDTO dto,
            @RequestHeader("adminId") Long adminId) {
        
        articleService.saveOrUpdateArticle(dto, adminId);
        return Result.success();
    }
    
    /**
     * 审核文章
     */
    @PostMapping("/audit")
    public Result<?> auditArticle(
            @RequestParam Long articleId,
            @RequestParam Integer auditStatus,
            @RequestParam(required = false) String auditRemark,
            @RequestHeader("adminId") Long adminId) {
        
        articleService.auditArticle(articleId, auditStatus, auditRemark, adminId);
        return Result.success();
    }
    
    /**
     * 发布文章
     */
    @PostMapping("/publish/{id}")
    public Result<?> publishArticle(@PathVariable Long id) {
        articleService.publishArticle(id);
        return Result.success();
    }
    
    /**
     * 下架文章
     */
    @PostMapping("/offline/{id}")
    public Result<?> offlineArticle(@PathVariable Long id) {
        articleService.offlineArticle(id);
        return Result.success();
    }
    
    /**
     * 删除文章
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteArticle(@PathVariable Long id) {
        articleService.removeById(id);
        return Result.success();
    }
}
