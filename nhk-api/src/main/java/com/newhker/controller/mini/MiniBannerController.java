package com.newhker.controller.mini;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.service.BannerService;
import com.newhker.entity.Banner;
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
public class MiniBannerController {
    
    @Autowired
    private BannerService bannerService;
    
    /**
     * 获取Banner列表
     */
    @GetMapping("/list")
    public Result<List<Banner>> getBannerList() {
        List<Banner> list = bannerService.getPublishedBanners();
        return Result.success(list);
    }
}
