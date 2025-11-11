package com.newhker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 管理后台文章返回VO
 */
@Data
@Schema(description = "文章信息")
public class AdminArticleVO {
    
    @Schema(description = "文章ID", example = "1")
    private Long id;
    
    @Schema(description = "文章标题", example = "新闻标题")
    private String title;
    
    @Schema(description = "文章封面", example = "http://example.com/cover.jpg")
    private String cover;
    
    @Schema(description = "文章内容")
    private String content;
    
    @Schema(description = "文章类型 1-图文 2-视频", example = "1")
    private Integer type;
    
    @Schema(description = "视频URL", example = "http://example.com/video.mp4")
    private String videoUrl;
    
    @Schema(description = "所属模块ID", example = "1")
    private Long moduleId;
    
    @Schema(description = "浏览次数", example = "100")
    private Integer viewCount;
    
    @Schema(description = "点赞次数", example = "50")
    private Integer likeCount;
    
    @Schema(description = "收藏次数", example = "20")
    private Integer collectCount;
    
    @Schema(description = "分享次数", example = "10")
    private Integer shareCount;
    
    @Schema(description = "审核状态 0-待审核 1-通过 2-拒绝", example = "0")
    private Integer auditStatus;
    
    @Schema(description = "审核人ID", example = "1")
    private Long auditUserId;
    
    @Schema(description = "审核时间", example = "2024-01-01T12:00:00")
    private LocalDateTime auditTime;
    
    @Schema(description = "审核意见")
    private String auditRemark;
    
    @Schema(description = "发布状态 0-草稿 1-已发布 2-已下架", example = "0")
    private Integer publishStatus;
    
    @Schema(description = "发布时间", example = "2024-01-01T12:00:00")
    private LocalDateTime publishTime;
    
    @Schema(description = "创建人ID", example = "1")
    private Long createUserId;
    
    @Schema(description = "创建时间", example = "2024-01-01T12:00:00")
    private LocalDateTime createTime;
    
    @Schema(description = "更新时间", example = "2024-01-01T12:00:00")
    private LocalDateTime updateTime;
}
