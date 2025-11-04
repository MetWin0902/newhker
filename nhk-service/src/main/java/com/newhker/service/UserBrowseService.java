package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.entity.UserBrowse;
import com.newhker.mapper.UserBrowseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户浏览记录Service
 */
@Slf4j
@Service
public class UserBrowseService extends ServiceImpl<UserBrowseMapper, UserBrowse> {
    
    /**
     * 添加浏览记录
     */
    public void addBrowseRecord(Long userId, Long articleId) {
        // 检查是否已存在浏览记录
        LambdaQueryWrapper<UserBrowse> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserBrowse::getUserId, userId)
                .eq(UserBrowse::getArticleId, articleId);
        
        UserBrowse browse = getOne(wrapper);
        
        if (browse != null) {
            // 更新浏览时间
            browse.setUpdateTime(LocalDateTime.now());
            updateById(browse);
        } else {
            // 新增浏览记录
            browse = new UserBrowse();
            browse.setUserId(userId);
            browse.setArticleId(articleId);
            save(browse);
        }
    }
    
    /**
     * 清理7天前的浏览记录
     */
    public void cleanOldRecords() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        LambdaQueryWrapper<UserBrowse> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(UserBrowse::getUpdateTime, sevenDaysAgo);
        remove(wrapper);
    }
}
