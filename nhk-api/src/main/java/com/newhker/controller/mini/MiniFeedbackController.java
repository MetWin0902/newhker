package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.dto.FeedbackDTO;
import com.newhker.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序端 - 用户反馈控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/feedback")
public class MiniFeedbackController {
    
    @Autowired
    private FeedbackService feedbackService;
    
    /**
     * 提交反馈
     */
    @PostMapping("/submit")
    public Result<?> submitFeedback(
            @Valid @RequestBody FeedbackDTO dto,
            @RequestHeader("userId") Long userId) {
        
        feedbackService.submitFeedback(dto, userId);
        return Result.success();
    }
}
