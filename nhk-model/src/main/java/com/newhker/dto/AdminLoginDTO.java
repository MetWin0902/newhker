package com.newhker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * 管理员登录DTO
 */
@Data
@Schema(description = "管理员登录信息")
public class AdminLoginDTO {
    
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "管理员用户名", example = "admin")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Schema(description = "管理员密码", example = "123456")
    private String password;
}
