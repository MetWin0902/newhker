package com.newhker.dto;

import lombok.Data;

/**
 * 文章查询DTO
 */
@Data
public class ArticleQueryDTO {
    
    private String title;
    private Long moduleId;
    private Integer type;
    private Integer auditStatus;
    private Integer publishStatus;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
