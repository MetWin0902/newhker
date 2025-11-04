package com.newhker.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.entity.User;
import com.newhker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理后台 - 用户管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 分页查询用户列表
     */
    @GetMapping("/page")
    public Result<PageResult<User>> getUserPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword) {
        
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> result = userService.page(page);
        
        return Result.success(new PageResult<>(result.getTotal(), result.getRecords()));
    }
    
    /**
     * 拉黑用户
     */
    @PostMapping("/block/{id}")
    public Result<?> blockUser(@PathVariable Long id) {
        userService.blockUser(id);
        return Result.success();
    }
    
    /**
     * 解除拉黑
     */
    @PostMapping("/unblock/{id}")
    public Result<?> unblockUser(@PathVariable Long id) {
        userService.unblockUser(id);
        return Result.success();
    }
}
