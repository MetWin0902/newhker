package com.newhker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户反馈DTO
 */
@Data
@Schema(description = "用户反馈信息")
public class FeedbackDTO {
    
    @Schema(description = "反馈内容", example = "应用有问题")
    private String content;
    
    @Schema(description = "反馈图片（多张逗号分隔）", example = "http://example.com/1.jpg,http://example.com/2.jpg")
    private String images;
    
    @Schema(description = "联系方式", example = "13800138000")
    private String contact;
}
