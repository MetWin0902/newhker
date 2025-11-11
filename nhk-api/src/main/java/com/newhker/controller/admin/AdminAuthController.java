package com.newhker.controller.admin;

import com.newhker.common.Result;
import com.newhker.dto.AdminLoginDTO;
import com.newhker.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 管理后台 - 管理员控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/auth")
@Tag(name = "管理后台-认证管理", description = "管理员登录认证")
public class AdminAuthController {
    
    @Autowired
    private AdminService adminService;
    
    /**
     * 管理员登录
     */
    @PostMapping("/login")
    @Operation(summary = "管理员登录", description = "管理员使用不名和密码登录")
    public Result<Map<String, Object>> login(
            @Valid @RequestBody
            @Parameter(description = "登录信息")
            AdminLoginDTO dto) {
        Map<String, Object> result = adminService.login(dto);
        return Result.success(result);
    }
}
