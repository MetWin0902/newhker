package com.newhker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 模块分类实体类
 */
@Data
@TableName("tb_module")
public class Module {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 父级模块ID，0表示顶级模块
     */
    private Long parentId;
    
    /**
     * 模块名称
     */
    private String name;
    
    /**
     * 模块图标
     */
    private String icon;
    
    /**
     * 模块层级：1-L1，2-L2，3-L3
     */
    private Integer level;
    
    /**
     * 排序
     */
    private Integer sortOrder;
    
    /**
     * 状态：0-启用，1-禁用
     */
    private Integer status;
    
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
