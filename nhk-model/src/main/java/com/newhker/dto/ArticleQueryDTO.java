package com.newhker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文章查询DTO
 */
@Data
@Schema(description = "文章查询")
public class ArticleQueryDTO {
    
    @Schema(description = "文章标题", example = "新闻标题")
    private String title;
    
    @Schema(description = "模块ID", example = "1")
    private Long moduleId;
    
    @Schema(description = "文章类型", example = "1")
    private Integer type;
    
    @Schema(description = "审核状态", example = "1")
    private Integer auditStatus;
    
    @Schema(description = "发布状态", example = "1")
    private Integer publishStatus;
    
    @Schema(description = "页码", example = "1")
    private Integer pageNum = 1;
    
    @Schema(description = "页面大小", example = "10")
    private Integer pageSize = 10;
}
