package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.common.PageResult;
import com.newhker.entity.User;
import com.newhker.mapper.UserMapper;
import com.newhker.vo.AdminUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户Service
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    
    /**
     * 分页查询用户列表（管理端）
     */
    public PageResult<AdminUserVO> getUserPageForAdmin(Integer pageNum, Integer pageSize, String keyword) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.like(User::getNickname, keyword)
                    .or().like(User::getPhone, keyword);
        }
        
        wrapper.orderByDesc(User::getCreateTime);
        
        IPage<User> result = page(page, wrapper);
        
        List<AdminUserVO> voList = result.getRecords().stream().map(user -> {
            AdminUserVO vo = new AdminUserVO();
            BeanUtils.copyProperties(user, vo);
            return vo;
        }).collect(Collectors.toList());
        
        return new PageResult<>(result.getTotal(), voList);
    }
    
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
