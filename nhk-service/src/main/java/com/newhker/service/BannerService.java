package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.common.PageResult;
import com.newhker.dto.BannerDTO;
import com.newhker.entity.Banner;
import com.newhker.vo.AdminBannerVO;
import com.newhker.exception.BusinessException;
import com.newhker.i18n.I18nUtil;
import com.newhker.mapper.BannerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Banner Service
 */
@Slf4j
@Service
public class BannerService extends ServiceImpl<BannerMapper, Banner> {
    
    /**
     * 获取已发布的Banner列表（小程序端）
     */
    public List<Banner> getPublishedBanners() {
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getAuditStatus, 1)  // 审核通过
                .eq(Banner::getPublishStatus, 1)  // 已发布
                .orderByAsc(Banner::getSortOrder)
                .orderByDesc(Banner::getPublishTime);
        
        return list(wrapper);
    }
    
    /**
     * 分页查询Banner列表（管理端）
     */
    public PageResult<AdminBannerVO> getBannerPage(Integer pageNum, Integer pageSize) {
        Page<Banner> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Banner::getSortOrder)
                .orderByDesc(Banner::getCreateTime);
        
        IPage<Banner> result = page(page, wrapper);
        
        List<AdminBannerVO> voList = result.getRecords().stream().map(banner -> {
            AdminBannerVO vo = new AdminBannerVO();
            BeanUtils.copyProperties(banner, vo);
            return vo;
        }).collect(Collectors.toList());
        
        return new PageResult<>(result.getTotal(), voList);
    }
    
    /**
     * 获取Banner详情（管理端）
     */
    public AdminBannerVO getBannerByIdForAdmin(Long bannerId) {
        Banner banner = getById(bannerId);
        if (banner == null) {
            throw new BusinessException(I18nUtil.getMessage("i18n.banner.notFound"));
        }
        
        AdminBannerVO vo = new AdminBannerVO();
        BeanUtils.copyProperties(banner, vo);
        return vo;
    }
    
    /**
     * 保存或更新Banner
     */
    public boolean saveOrUpdateBanner(BannerDTO dto, Long createUserId) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(dto, banner);
        
        if (dto.getId() == null) {
            // 新建
            banner.setCreateUserId(createUserId);
            banner.setAuditStatus(0);  // 待审核
            banner.setPublishStatus(0);  // 草稿
        }
        
        return saveOrUpdate(banner);
    }
    
    /**
     * 审核Banner
     */
    public boolean auditBanner(Long bannerId, Integer auditStatus, Long auditUserId) {
        Banner banner = getById(bannerId);
        if (banner == null) {
            throw new BusinessException(I18nUtil.getMessage("i18n.banner.notFound"));
        }
        
        banner.setAuditStatus(auditStatus);
        banner.setAuditUserId(auditUserId);
        banner.setAuditTime(LocalDateTime.now());
        
        return updateById(banner);
    }
    
    /**
     * 发布Banner
     */
    public boolean publishBanner(Long bannerId) {
        Banner banner = getById(bannerId);
        if (banner == null) {
            throw new BusinessException(I18nUtil.getMessage("i18n.banner.notFound"));
        }
        
        if (banner.getAuditStatus() != 1) {
            throw new BusinessException(I18nUtil.getMessage("i18n.banner.notApproved"));
        }
        
        banner.setPublishStatus(1);
        banner.setPublishTime(LocalDateTime.now());
        
        return updateById(banner);
    }
    
    /**
     * 下架Banner
     */
    public boolean offlineBanner(Long bannerId) {
        Banner banner = getById(bannerId);
        if (banner == null) {
            throw new BusinessException(I18nUtil.getMessage("i18n.banner.notFound"));
        }
        
        banner.setPublishStatus(2);
        banner.setOfflineTime(LocalDateTime.now());
        
        return updateById(banner);
    }
}
