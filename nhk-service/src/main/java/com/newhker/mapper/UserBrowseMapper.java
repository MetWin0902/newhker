package com.newhker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newhker.entity.UserBrowse;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户浏览记录Mapper
 */
@Mapper
public interface UserBrowseMapper extends BaseMapper<UserBrowse> {
}
