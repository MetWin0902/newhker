package com.newhker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Banner实体类
 */
@Data
@TableName("tb_banner")
public class Banner {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * Banner标题
     */
    private String title;
    
    /**
     * Banner图片URL
     */
    private String imageUrl;
    
    /**
     * 跳转类型：1-文章详情，2-外链
     */
    private Integer jumpType;
    
    /**
     * 跳转目标（文章ID或外链URL）
     */
    private String jumpTarget;
    
    /**
     * 排序
     */
    private Integer sortOrder;
    
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
     * 发布状态：0-草稿，1-已发布，2-已下架
     */
    private Integer publishStatus;
    
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    
    /**
     * 下架时间
     */
    private LocalDateTime offlineTime;
    
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
