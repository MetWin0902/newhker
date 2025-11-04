package com.newhker.controller.admin;

import com.newhker.common.Result;
import com.newhker.dto.AdminLoginDTO;
import com.newhker.service.AdminService;
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
public class AdminAuthController {
    
    @Autowired
    private AdminService adminService;
    
    /**
     * 管理员登录
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Valid @RequestBody AdminLoginDTO dto) {
        Map<String, Object> result = adminService.login(dto);
        return Result.success(result);
    }
}
