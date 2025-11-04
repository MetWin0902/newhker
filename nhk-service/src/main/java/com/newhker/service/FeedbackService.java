package com.newhker.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.dto.FeedbackDTO;
import com.newhker.entity.Feedback;
import com.newhker.mapper.FeedbackMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户反馈Service
 */
@Slf4j
@Service
public class FeedbackService extends ServiceImpl<FeedbackMapper, Feedback> {
    
    /**
     * 提交反馈
     */
    public boolean submitFeedback(FeedbackDTO dto, Long userId) {
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(dto, feedback);
        feedback.setUserId(userId);
        feedback.setStatus(0);  // 待处理
        
        return save(feedback);
    }
}
