package com.newhker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 文章实体类
 */
@Data
@TableName("tb_article")
public class Article {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 文章标题
     */
    private String title;
    
    /**
     * 文章封面
     */
    private String cover;
    
    /**
     * 文章内容
     */
    private String content;
    
    /**
     * 文章类型：1-图文，2-视频
     */
    private Integer type;
    
    /**
     * 视频URL（type=2时有效）
     */
    private String videoUrl;
    
    /**
     * 所属模块ID
     */
    private Long moduleId;
    
    /**
     * 浏览次数
     */
    private Integer viewCount;
    
    /**
     * 点赞次数
     */
    private Integer likeCount;
    
    /**
     * 收藏次数
     */
    private Integer collectCount;
    
    /**
     * 分享次数
     */
    private Integer shareCount;
    
    /**
     * 审核状态：0-待审核，1-审核通过，2-审核拒绝
     */
    private Integer auditStatus;
    
    /**
     * 审核人ID
     */
    private Long auditUserId;
    
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    
    /**
     * 审核意见
     */
    private String auditRemark;
    
    /**
     * 发布状态：0-草稿，1-已发布，2-已下架
     */
    private Integer publishStatus;
    
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    
    /**
     * 创建人ID
     */
    private Long createUserId;
    
    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
