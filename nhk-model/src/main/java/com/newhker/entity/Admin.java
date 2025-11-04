package com.newhker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 管理员实体类
 */
@Data
@TableName("tb_admin")
public class Admin {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 管理员账号
     */
    private String username;
    
    /**
     * 管理员密码
     */
    private String password;
    
    /**
     * 管理员昵称
     */
    private String nickname;
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 角色：1-超级管理员，2-普通管理员
     */
    private Integer role;
    
    /**
     * 状态：0-正常，1-禁用
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
