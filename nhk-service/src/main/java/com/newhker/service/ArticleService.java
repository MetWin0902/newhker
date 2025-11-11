package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.common.PageResult;
import com.newhker.dto.ArticleDTO;
import com.newhker.dto.ArticleQueryDTO;
import com.newhker.entity.Article;
import com.newhker.entity.Module;
import com.newhker.entity.UserCollect;
import com.newhker.entity.UserLike;
import com.newhker.exception.BusinessException;
import com.newhker.mapper.ArticleMapper;
import com.newhker.vo.AdminArticleVO;
import com.newhker.vo.ArticleDetailVO;
import com.newhker.vo.ArticleListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 文章Service
 */
@Slf4j
@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> {
    
    @Autowired
    private ModuleService moduleService;
    
    @Autowired
    private UserCollectService userCollectService;
    
    @Autowired
    private UserLikeService userLikeService;
    
    @Autowired
    private UserBrowseService userBrowseService;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 分页查询文章列表（管理端）
     */
    public PageResult<AdminArticleVO> getArticlePageForAdmin(ArticleQueryDTO query) {
        Page<Article> page = new Page<>(query.getPageNum(), query.getPageSize());
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        
        if (query.getTitle() != null && !query.getTitle().isEmpty()) {
            wrapper.like(Article::getTitle, query.getTitle());
        }
        if (query.getModuleId() != null) {
            wrapper.eq(Article::getModuleId, query.getModuleId());
        }
        if (query.getType() != null) {
            wrapper.eq(Article::getType, query.getType());
        }
        if (query.getAuditStatus() != null) {
            wrapper.eq(Article::getAuditStatus, query.getAuditStatus());
        }
        if (query.getPublishStatus() != null) {
            wrapper.eq(Article::getPublishStatus, query.getPublishStatus());
        }
        
        wrapper.orderByDesc(Article::getCreateTime);
        
        IPage<Article> result = page(page, wrapper);
        
        List<AdminArticleVO> voList = result.getRecords().stream().map(article -> {
            AdminArticleVO vo = new AdminArticleVO();
            BeanUtils.copyProperties(article, vo);
            return vo;
        }).collect(Collectors.toList());
        
        return new PageResult<>(result.getTotal(), voList);
    }
    
    /**
     * 分页查询文章列表（小程序端）
     */
    public PageResult<ArticleListVO> getArticlePageForMini(Long moduleId, Integer pageNum, Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getAuditStatus, 1)  // 审核通过
                .eq(Article::getPublishStatus, 1);  // 已发布
        
        if (moduleId != null) {
            wrapper.eq(Article::getModuleId, moduleId);
        }
        
        wrapper.orderByDesc(Article::getPublishTime);
        
        IPage<Article> result = page(page, wrapper);
        
        List<ArticleListVO> voList = result.getRecords().stream().map(article -> {
            ArticleListVO vo = new ArticleListVO();
            BeanUtils.copyProperties(article, vo);
            
            // 获取模块名称
            Module module = moduleService.getById(article.getModuleId());
            if (module != null) {
                vo.setModuleName(module.getName());
            }
            
            return vo;
        }).collect(Collectors.toList());
        
        return new PageResult<>(result.getTotal(), voList);
    }
    
    /**
     * 获取文章详情（管理端）
     */
    public AdminArticleVO getArticleByIdForAdmin(Long articleId) {
        Article article = getById(articleId);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        
        AdminArticleVO vo = new AdminArticleVO();
        BeanUtils.copyProperties(article, vo);
        return vo;
    }
    
    /**
     * 获取文章详情（小程序端）
     */
    public ArticleDetailVO getArticleDetail(Long articleId, Long userId) {
        Article article = getById(articleId);
        if (article == null || article.getPublishStatus() != 1) {
            throw new BusinessException("文章不存在或已下架");
        }
        
        ArticleDetailVO vo = new ArticleDetailVO();
        BeanUtils.copyProperties(article, vo);
        
        // 获取模块名称
        Module module = moduleService.getById(article.getModuleId());
        if (module != null) {
            vo.setModuleName(module.getName());
        }
        
        // 获取用户点赞和收藏状态
        if (userId != null) {
            vo.setIsLiked(userLikeService.isLiked(userId, articleId));
            vo.setIsCollected(userCollectService.isCollected(userId, articleId));
            
            // 记录浏览历史
            userBrowseService.addBrowseRecord(userId, articleId);
        }
        
        // 增加浏览次数
        incrementViewCount(articleId);
        
        return vo;
    }
    
    /**
     * 创建或更新文章
     */
    public boolean saveOrUpdateArticle(ArticleDTO dto, Long createUserId) {
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        
        if (dto.getId() == null) {
            // 新建
            article.setCreateUserId(createUserId);
            article.setAuditStatus(0);  // 待审核
            article.setPublishStatus(0);  // 草稿
            article.setViewCount(0);
            article.setLikeCount(0);
            article.setCollectCount(0);
            article.setShareCount(0);
        }
        
        return saveOrUpdate(article);
    }
    
    /**
     * 审核文章
     */
    public boolean auditArticle(Long articleId, Integer auditStatus, String auditRemark, Long auditUserId) {
        Article article = getById(articleId);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        
        article.setAuditStatus(auditStatus);
        article.setAuditRemark(auditRemark);
        article.setAuditUserId(auditUserId);
        article.setAuditTime(LocalDateTime.now());
        
        return updateById(article);
    }
    
    /**
     * 发布文章
     */
    public boolean publishArticle(Long articleId) {
        Article article = getById(articleId);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        
        if (article.getAuditStatus() != 1) {
            throw new BusinessException("文章未通过审核，无法发布");
        }
        
        article.setPublishStatus(1);
        article.setPublishTime(LocalDateTime.now());
        
        return updateById(article);
    }
    
    /**
     * 下架文章
     */
    public boolean offlineArticle(Long articleId) {
        Article article = getById(articleId);
        if (article == null) {
            throw new BusinessException("文章不存在");
        }
        
        article.setPublishStatus(2);
        return updateById(article);
    }
    
    /**
     * 增加浏览次数（使用Redis缓存）
     */
    private void incrementViewCount(Long articleId) {
        String key = "article:view:" + articleId;
        redisTemplate.opsForValue().increment(key);
        
        // 每100次同步到数据库
        Long count = (Long) redisTemplate.opsForValue().get(key);
        if (count != null && count % 100 == 0) {
            Article article = getById(articleId);
            article.setViewCount(article.getViewCount() + 100);
            updateById(article);
            redisTemplate.delete(key);
        }
    }
}
