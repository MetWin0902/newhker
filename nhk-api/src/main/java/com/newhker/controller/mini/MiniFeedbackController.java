package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.dto.FeedbackDTO;
import com.newhker.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "小程序端-反馈", description = "用户反馈操作")
public class MiniFeedbackController {
    
    @Autowired
    private FeedbackService feedbackService;
    
    /**
     * 提交反馈
     */
    @PostMapping("/submit")
    @Operation(summary = "提交反馈", description = "用户提交反馈信息")
    public Result<?> submitFeedback(
            @Valid @RequestBody
            @Parameter(description = "反馈信息")
            FeedbackDTO dto,
            @RequestHeader("userId")
            @Parameter(description = "用户ID")
            Long userId) {
        
        feedbackService.submitFeedback(dto, userId);
        return Result.success();
    }
}
