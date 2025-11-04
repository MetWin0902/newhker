package com.newhker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newhker.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * 文章Mapper
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    
    /**
     * 增加点赞数
     */
    @Update("UPDATE tb_article SET like_count = like_count + 1 WHERE id = #{articleId}")
    int incrementLikeCount(Long articleId);
    
    /**
     * 减少点赞数
     */
    @Update("UPDATE tb_article SET like_count = CASE WHEN like_count > 0 THEN like_count - 1 ELSE 0 END WHERE id = #{articleId}")
    int decrementLikeCount(Long articleId);
    
    /**
     * 增加收藏数
     */
    @Update("UPDATE tb_article SET collect_count = collect_count + 1 WHERE id = #{articleId}")
    int incrementCollectCount(Long articleId);
    
    /**
     * 减少收藏数
     */
    @Update("UPDATE tb_article SET collect_count = CASE WHEN collect_count > 0 THEN collect_count - 1 ELSE 0 END WHERE id = #{articleId}")
    int decrementCollectCount(Long articleId);
}
