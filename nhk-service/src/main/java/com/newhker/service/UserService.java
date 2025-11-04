package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.entity.User;
import com.newhker.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    
    /**
     * 根据openid获取用户
     */
    public User getUserByOpenid(String openid) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenid, openid);
        return getOne(wrapper);
    }
    
    /**
     * 拉黑用户
     */
    public boolean blockUser(Long userId) {
        User user = getById(userId);
        if (user != null) {
            user.setStatus(1);
            return updateById(user);
        }
        return false;
    }
    
    /**
     * 解除拉黑
     */
    public boolean unblockUser(Long userId) {
        User user = getById(userId);
        if (user != null) {
            user.setStatus(0);
            return updateById(user);
        }
        return false;
    }
}
