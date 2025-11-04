package com.newhker.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 文章列表VO
 */
@Data
public class ArticleListVO {
    
    private Long id;
    private String title;
    private String cover;
    private Integer type;
    private Long moduleId;
    private String moduleName;
    private Integer viewCount;
    private Integer likeCount;
    private Integer collectCount;
    private LocalDateTime publishTime;
}
