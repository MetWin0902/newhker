package com.newhker.service;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.dto.AdminLoginDTO;
import com.newhker.entity.Admin;
import com.newhker.exception.BusinessException;
import com.newhker.mapper.AdminMapper;
import com.newhker.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员Service
 */
@Slf4j
@Service
public class AdminService extends ServiceImpl<AdminMapper, Admin> {
    
    @Autowired
    private JwtUtils jwtUtils;
    
    /**
     * 管理员登录
     */
    public Map<String, Object> login(AdminLoginDTO dto) {
        // 查询管理员
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername, dto.getUsername());
        Admin admin = getOne(wrapper);
        
        if (admin == null) {
            throw new BusinessException("用户名或密码错误");
        }
        
        // 验证密码
        if (!BCrypt.checkpw(dto.getPassword(), admin.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        
        // 检查状态
        if (admin.getStatus() == 1) {
            throw new BusinessException("账号已被禁用");
        }
        
        // 生成token
        String token = jwtUtils.generateAdminToken(admin.getId(), admin.getUsername());
        
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("adminInfo", admin);
        
        return result;
    }
    
    /**
     * 创建管理员（密码加密）
     */
    public boolean createAdmin(Admin admin) {
        // 检查用户名是否存在
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername, admin.getUsername());
        if (count(wrapper) > 0) {
            throw new BusinessException("用户名已存在");
        }
        
        // 密码加密
        admin.setPassword(BCrypt.hashpw(admin.getPassword()));
        return save(admin);
    }
}
