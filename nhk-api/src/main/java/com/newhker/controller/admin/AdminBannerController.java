package com.newhker.controller.admin;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.dto.BannerDTO;
import com.newhker.vo.AdminBannerVO;
import com.newhker.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理后台 - Banner管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/banner")
@Tag(name = "管理后台-Banner管理", description = "Banner CRUD及审核发布操作")
public class AdminBannerController {
    
    @Autowired
    private BannerService bannerService;
    
    /**
     * 分页查询Banner列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询Banner列表", description = "管理员分页查询所有Banner")
    public Result<PageResult<AdminBannerVO>> getBannerPage(
            @RequestParam(defaultValue = "1")
            @Parameter(description = "页码")
            Integer pageNum,
            @RequestParam(defaultValue = "10")
            @Parameter(description = "每页记录数")
            Integer pageSize) {
        
        PageResult<AdminBannerVO> result = bannerService.getBannerPage(pageNum, pageSize);
        return Result.success(result);
    }
    
    /**
     * 获取Banner详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取Banner详情", description = "根据Banner ID获取Banner详细信息")
    public Result<AdminBannerVO> getBannerById(
            @PathVariable
            @Parameter(description = "Banner ID")
            Long id) {
        AdminBannerVO banner = bannerService.getBannerByIdForAdmin(id);
        return Result.success(banner);
    }
    
    /**
     * 创建或更新Banner
     */
    @PostMapping
    @Operation(summary = "创建或更新Banner", description = "创建新Banner或更新现有Banner")
    public Result<?> saveOrUpdate(
            @Valid @RequestBody
            @Parameter(description = "Banner信息")
            BannerDTO dto,
            @RequestHeader("adminId")
            @Parameter(description = "管理员ID")
            Long adminId) {
        
        bannerService.saveOrUpdateBanner(dto, adminId);
        return Result.success();
    }
    
    /**
     * 审核Banner
     */
    @PostMapping("/audit")
    @Operation(summary = "审核Banner", description = "审核Banner是否通过")
    public Result<?> auditBanner(
            @RequestParam
            @Parameter(description = "Banner ID")
            Long bannerId,
            @RequestParam
            @Parameter(description = "审核状态 1:通过 2:拒绝")
            Integer auditStatus,
            @RequestHeader("adminId")
            @Parameter(description = "管理员ID")
            Long adminId) {
        
        bannerService.auditBanner(bannerId, auditStatus, adminId);
        return Result.success();
    }
    
    /**
     * 发布Banner
     */
    @PostMapping("/publish/{id}")
    @Operation(summary = "发布Banner", description = "将Banner发布到线上")
    public Result<?> publishBanner(
            @PathVariable
            @Parameter(description = "Banner ID")
            Long id) {
        bannerService.publishBanner(id);
        return Result.success();
    }
    
    /**
     * 下架Banner
     */
    @PostMapping("/offline/{id}")
    @Operation(summary = "下架Banner", description = "将Banner从线上下架")
    public Result<?> offlineBanner(
            @PathVariable
            @Parameter(description = "Banner ID")
            Long id) {
        bannerService.offlineBanner(id);
        return Result.success();
    }
    
    /**
     * 删除Banner
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除Banner", description = "永久删除指定Banner")
    public Result<?> deleteBanner(
            @PathVariable
            @Parameter(description = "Banner ID")
            Long id) {
        bannerService.removeById(id);
        return Result.success();
    }
}
