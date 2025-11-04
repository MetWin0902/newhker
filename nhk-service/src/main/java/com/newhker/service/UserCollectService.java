package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.entity.UserCollect;
import com.newhker.exception.BusinessException;
import com.newhker.mapper.ArticleMapper;
import com.newhker.mapper.UserCollectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户收藏Service
 */
@Slf4j
@Service
public class UserCollectService extends ServiceImpl<UserCollectMapper, UserCollect> {
    
    @Autowired
    private ArticleMapper articleMapper;
    
    /**
     * 收藏文章
     */
    @Transactional
    public boolean collectArticle(Long userId, Long articleId) {
        // 检查是否已收藏
        if (isCollected(userId, articleId)) {
            throw new BusinessException("已收藏该文章");
        }
        
        // 添加收藏记录
        UserCollect collect = new UserCollect();
        collect.setUserId(userId);
        collect.setArticleId(articleId);
        boolean result = save(collect);
        
        // 更新文章收藏数（使用 SQL 直接更新）
        if (result) {
            articleMapper.incrementCollectCount(articleId);
        }
        
        return result;
    }
    
    /**
     * 取消收藏
     */
    @Transactional
    public boolean cancelCollect(Long userId, Long articleId) {
        LambdaQueryWrapper<UserCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCollect::getUserId, userId)
                .eq(UserCollect::getArticleId, articleId);
        
        boolean result = remove(wrapper);
        
        // 更新文章收藏数（使用 SQL 直接更新）
        if (result) {
            articleMapper.decrementCollectCount(articleId);
        }
        
        return result;
    }
    
    /**
     * 检查是否已收藏
     */
    public boolean isCollected(Long userId, Long articleId) {
        LambdaQueryWrapper<UserCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCollect::getUserId, userId)
                .eq(UserCollect::getArticleId, articleId);
        return count(wrapper) > 0;
    }
}
