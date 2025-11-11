package com.newhker.controller.admin;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.vo.AdminUserVO;
import com.newhker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理后台 - 用户管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
@Tag(name = "管理后台-用户管理", description = "用户管理操作")
public class AdminUserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 分页查询用户列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户列表", description = "管理员分页查询所有用户")
    public Result<PageResult<AdminUserVO>> getUserPage(
            @RequestParam(defaultValue = "1")
            @Parameter(description = "页码")
            Integer pageNum,
            @RequestParam(defaultValue = "10")
            @Parameter(description = "每页记录数")
            Integer pageSize,
            @RequestParam(required = false)
            @Parameter(description = "搜索关键词")
            String keyword) {
        
        PageResult<AdminUserVO> result = userService.getUserPageForAdmin(pageNum, pageSize, keyword);
        return Result.success(result);
    }
    
    /**
     * 拉黑用户
     */
    @PostMapping("/block/{id}")
    @Operation(summary = "拉黑用户", description = "将指定用户拉黑")
    public Result<?> blockUser(
            @PathVariable
            @Parameter(description = "用户ID")
            Long id) {
        userService.blockUser(id);
        return Result.success();
    }
    
    /**
     * 解除拉黑
     */
    @PostMapping("/unblock/{id}")
    @Operation(summary = "解除拉黑", description = "解除指定用户的拉黑状态")
    public Result<?> unblockUser(
            @PathVariable
            @Parameter(description = "用户ID")
            Long id) {
        userService.unblockUser(id);
        return Result.success();
    }
}
