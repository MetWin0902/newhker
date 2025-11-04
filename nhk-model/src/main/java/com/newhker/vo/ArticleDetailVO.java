package com.newhker.vo;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章详情VO
 */
@Data
public class ArticleDetailVO {
    
    private Long id;
    private String title;
    private String cover;
    private String content;
    private Integer type;
    private String videoUrl;
    private Long moduleId;
    private String moduleName;
    private Integer viewCount;
    private Integer likeCount;
    private Integer collectCount;
    private Integer shareCount;
    private LocalDateTime publishTime;
    private Boolean isLiked;
    private Boolean isCollected;
}
