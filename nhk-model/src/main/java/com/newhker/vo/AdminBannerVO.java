package com.newhker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 管理后台Banner返回VO
 */
@Data
@Schema(description = "Banner信息")
public class AdminBannerVO {
    
    @Schema(description = "Banner ID", example = "1")
    private Long id;
    
    @Schema(description = "Banner标题", example = "新闻资讯")
    private String title;
    
    @Schema(description = "Banner图片URL", example = "http://example.com/banner.jpg")
    private String imageUrl;
    
    @Schema(description = "跳转类型 1-文章详情 2-外链", example = "1")
    private Integer jumpType;
    
    @Schema(description = "跳转目标", example = "123")
    private String jumpTarget;
    
    @Schema(description = "排序", example = "1")
    private Integer sortOrder;
    
    @Schema(description = "审核状态 0-待审核 1-通过 2-拒绝", example = "0")
    private Integer auditStatus;
    
    @Schema(description = "审核人ID", example = "1")
    private Long auditUserId;
    
    @Schema(description = "审核时间", example = "2024-01-01T12:00:00")
    private LocalDateTime auditTime;
    
    @Schema(description = "发布状态 0-草稿 1-已发布 2-已下架", example = "0")
    private Integer publishStatus;
    
    @Schema(description = "发布时间", example = "2024-01-01T12:00:00")
    private LocalDateTime publishTime;
    
    @Schema(description = "下架时间", example = "2024-01-01T12:00:00")
    private LocalDateTime offlineTime;
    
    @Schema(description = "创建人ID", example = "1")
    private Long createUserId;
    
    @Schema(description = "创建时间", example = "2024-01-01T12:00:00")
    private LocalDateTime createTime;
    
    @Schema(description = "更新时间", example = "2024-01-01T12:00:00")
    private LocalDateTime updateTime;
}
