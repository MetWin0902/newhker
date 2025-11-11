package com.newhker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 管理后台用户返回VO
 */
@Data
@Schema(description = "用户信息")
public class AdminUserVO {
    
    @Schema(description = "用户ID", example = "1")
    private Long id;
    
    @Schema(description = "微信openid", example = "oXxxx")
    private String openid;
    
    @Schema(description = "用户昵称", example = "张三")
    private String nickname;
    
    @Schema(description = "用户头像", example = "http://example.com/avatar.jpg")
    private String avatar;
    
    @Schema(description = "手机号", example = "13800138000")
    private String phone;
    
    @Schema(description = "性别 0-未知 1-男 2-女", example = "1")
    private Integer gender;
    
    @Schema(description = "用户状态 0-正常 1-拉黑", example = "0")
    private Integer status;
    
    @Schema(description = "创建时间", example = "2024-01-01T12:00:00")
    private LocalDateTime createTime;
    
    @Schema(description = "更新时间", example = "2024-01-01T12:00:00")
    private LocalDateTime updateTime;
}
