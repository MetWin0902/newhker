package com.newhker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Mini反馈返回VO
 */
@Data
@Schema(description = "反馈信息")
public class MiniFeedbackVO {
    
    @Schema(description = "反馈ID", example = "1")
    private Long id;
    
    @Schema(description = "用户ID", example = "1")
    private Long userId;
    
    @Schema(description = "反馈内容", example = "应用有问题")
    private String content;
    
    @Schema(description = "反馈图片", example = "http://example.com/1.jpg,http://example.com/2.jpg")
    private String images;
    
    @Schema(description = "联系方式", example = "13800138000")
    private String contact;
    
    @Schema(description = "处理状态 0-待处理 1-已处理", example = "0")
    private Integer status;
    
    @Schema(description = "处理人ID", example = "1")
    private Long handleUserId;
    
    @Schema(description = "处理时间", example = "2024-01-01T12:00:00")
    private LocalDateTime handleTime;
    
    @Schema(description = "处理备注", example = "已修复")
    private String handleRemark;
    
    @Schema(description = "创建时间", example = "2024-01-01T12:00:00")
    private LocalDateTime createTime;
}
