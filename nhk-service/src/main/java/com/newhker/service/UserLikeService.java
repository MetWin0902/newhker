package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.entity.UserLike;
import com.newhker.exception.BusinessException;
import com.newhker.mapper.ArticleMapper;
import com.newhker.mapper.UserLikeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户点赞Service
 */
@Slf4j
@Service
public class UserLikeService extends ServiceImpl<UserLikeMapper, UserLike> {
    
    @Autowired
    private ArticleMapper articleMapper;
    
    /**
     * 点赞文章
     */
    @Transactional
    public boolean likeArticle(Long userId, Long articleId) {
        // 检查是否已点赞
        if (isLiked(userId, articleId)) {
            throw new BusinessException("已点赞该文章");
        }
        
        // 添加点赞记录
        UserLike like = new UserLike();
        like.setUserId(userId);
        like.setArticleId(articleId);
        boolean result = save(like);
        
        // 更新文章点赞数（使用 SQL 直接更新）
        if (result) {
            articleMapper.incrementLikeCount(articleId);
        }
        
        return result;
    }
    
    /**
     * 取消点赞
     */
    @Transactional
    public boolean cancelLike(Long userId, Long articleId) {
        LambdaQueryWrapper<UserLike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLike::getUserId, userId)
                .eq(UserLike::getArticleId, articleId);
        
        boolean result = remove(wrapper);
        
        // 更新文章点赞数（使用 SQL 直接更新）
        if (result) {
            articleMapper.decrementLikeCount(articleId);
        }
        
        return result;
    }
    
    /**
     * 检查是否已点赞
     */
    public boolean isLiked(Long userId, Long articleId) {
        LambdaQueryWrapper<UserLike> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserLike::getUserId, userId)
                .eq(UserLike::getArticleId, articleId);
        return count(wrapper) > 0;
    }
}
