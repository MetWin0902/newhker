package com.newhker.controller.admin;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.dto.BannerDTO;
import com.newhker.entity.Banner;
import com.newhker.service.BannerService;
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
public class AdminBannerController {
    
    @Autowired
    private BannerService bannerService;
    
    /**
     * 分页查询Banner列表
     */
    @GetMapping("/page")
    public Result<PageResult<Banner>> getBannerPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        
        PageResult<Banner> result = bannerService.getBannerPage(pageNum, pageSize);
        return Result.success(result);
    }
    
    /**
     * 获取Banner详情
     */
    @GetMapping("/{id}")
    public Result<Banner> getBannerById(@PathVariable Long id) {
        Banner banner = bannerService.getById(id);
        return Result.success(banner);
    }
    
    /**
     * 创建或更新Banner
     */
    @PostMapping
    public Result<?> saveOrUpdate(
            @Valid @RequestBody BannerDTO dto,
            @RequestHeader("adminId") Long adminId) {
        
        bannerService.saveOrUpdateBanner(dto, adminId);
        return Result.success();
    }
    
    /**
     * 审核Banner
     */
    @PostMapping("/audit")
    public Result<?> auditBanner(
            @RequestParam Long bannerId,
            @RequestParam Integer auditStatus,
            @RequestHeader("adminId") Long adminId) {
        
        bannerService.auditBanner(bannerId, auditStatus, adminId);
        return Result.success();
    }
    
    /**
     * 发布Banner
     */
    @PostMapping("/publish/{id}")
    public Result<?> publishBanner(@PathVariable Long id) {
        bannerService.publishBanner(id);
        return Result.success();
    }
    
    /**
     * 下架Banner
     */
    @PostMapping("/offline/{id}")
    public Result<?> offlineBanner(@PathVariable Long id) {
        bannerService.offlineBanner(id);
        return Result.success();
    }
    
    /**
     * 删除Banner
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteBanner(@PathVariable Long id) {
        bannerService.removeById(id);
        return Result.success();
    }
}
