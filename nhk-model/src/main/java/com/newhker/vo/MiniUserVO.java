package com.newhker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Mini用户简要信息VO
 */
@Data
@Schema(description = "用户基本信息")
public class MiniUserVO {
    
    @Schema(description = "用户ID", example = "1")
    private Long id;
    
    @Schema(description = "用户昵称", example = "张三")
    private String nickname;
    
    @Schema(description = "用户头像", example = "http://example.com/avatar.jpg")
    private String avatar;
    
    @Schema(description = "性别 0-未知 1-男 2-女", example = "1")
    private Integer gender;
}
