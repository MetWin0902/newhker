package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.service.BannerService;
import com.newhker.entity.Banner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序端 - Banner控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/banner")
@Tag(name = "小程序端-Banner", description = "Banner查看操作")
public class MiniBannerController {
    
    @Autowired
    private BannerService bannerService;
    
    /**
     * 获取Banner列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取Banner列表", description = "获取所有已发布的Banner")
    public Result<List<Banner>> getBannerList() {
        List<Banner> list = bannerService.getPublishedBanners();
        return Result.success(list);
    }
}
