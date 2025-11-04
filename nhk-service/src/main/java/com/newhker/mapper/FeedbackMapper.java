package com.newhker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newhker.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户反馈Mapper
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
}
