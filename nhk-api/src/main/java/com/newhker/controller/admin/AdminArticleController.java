package com.newhker.controller.admin;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.dto.ArticleDTO;
import com.newhker.dto.ArticleQueryDTO;
import com.newhker.vo.AdminArticleVO;
import com.newhker.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "管理后台-文章管理", description = "文章CRUD及审核发布操作")
public class AdminArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 分页查询文章列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询文章列表", description = "管理员分页查询所有文章")
    public Result<PageResult<AdminArticleVO>> getArticlePage(ArticleQueryDTO query) {
        PageResult<AdminArticleVO> result = articleService.getArticlePageForAdmin(query);
        return Result.success(result);
    }
    
    /**
     * 获取文章详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取文章详情", description = "根据文章ID获取文章详细信息")
    public Result<AdminArticleVO> getArticleById(
            @PathVariable
            @Parameter(description = "文章ID")
            Long id) {
        AdminArticleVO article = articleService.getArticleByIdForAdmin(id);
        return Result.success(article);
    }
    
    /**
     * 创建或更新文章
     */
    @PostMapping
    @Operation(summary = "创建或更新文章", description = "创建新文章或更新现有文章")
    public Result<?> saveOrUpdate(
            @Valid @RequestBody
            @Parameter(description = "文章信息")
            ArticleDTO dto,
            @RequestHeader("adminId")
            @Parameter(description = "管理员ID")
            Long adminId) {
        
        articleService.saveOrUpdateArticle(dto, adminId);
        return Result.success();
    }
    
    /**
     * 审核文章
     */
    @PostMapping("/audit")
    @Operation(summary = "审核文章", description = "审核文章是否通过")
    public Result<?> auditArticle(
            @RequestParam
            @Parameter(description = "文章ID")
            Long articleId,
            @RequestParam
            @Parameter(description = "审核状态 1:通过 2:拒绝")
            Integer auditStatus,
            @RequestParam(required = false)
            @Parameter(description = "审核备注")
            String auditRemark,
            @RequestHeader("adminId")
            @Parameter(description = "管理员ID")
            Long adminId) {
        
        articleService.auditArticle(articleId, auditStatus, auditRemark, adminId);
        return Result.success();
    }
    
    /**
     * 发布文章
     */
    @PostMapping("/publish/{id}")
    @Operation(summary = "发布文章", description = "将文章发布到线上")
    public Result<?> publishArticle(
            @PathVariable
            @Parameter(description = "文章ID")
            Long id) {
        articleService.publishArticle(id);
        return Result.success();
    }
    
    /**
     * 下架文章
     */
    @PostMapping("/offline/{id}")
    @Operation(summary = "下架文章", description = "将文章从线上下架")
    public Result<?> offlineArticle(
            @PathVariable
            @Parameter(description = "文章ID")
            Long id) {
        articleService.offlineArticle(id);
        return Result.success();
    }
    
    /**
     * 删除文章
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除文章", description = "永久删除指定的文章")
    public Result<?> deleteArticle(
            @PathVariable
            @Parameter(description = "文章ID")
            Long id) {
        articleService.removeById(id);
        return Result.success();
    }
}
