package com.newhker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户反馈实体类
 */
@Data
@TableName("tb_feedback")
public class Feedback {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 反馈内容
     */
    private String content;
    
    /**
     * 反馈图片（多张图片用逗号分隔）
     */
    private String images;
    
    /**
     * 联系方式
     */
    private String contact;
    
    /**
     * 处理状态：0-待处理，1-已处理
     */
    private Integer status;
    
    /**
     * 处理人ID
     */
    private Long handleUserId;
    
    /**
     * 处理时间
     */
    private LocalDateTime handleTime;
    
    /**
     * 处理备注
     */
    private String handleRemark;
    
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
