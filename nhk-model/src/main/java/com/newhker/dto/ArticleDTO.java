package com.newhker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 文章DTO
 */
@Data
@Schema(description = "文章信息")
public class ArticleDTO {
    
    @Schema(description = "文章ID", example = "1")
    private Long id;
    
    @NotBlank(message = "文章标题不能为空")
    @Schema(description = "文章标题", example = "新文章标题")
    private String title;
    
    @Schema(description = "文章封面", example = "http://example.com/cover.jpg")
    private String cover;
    
    @NotBlank(message = "文章内容不能为空")
    @Schema(description = "文章内容", example = "新文章内容...")
    private String content;
    
    @NotNull(message = "文章类型不能为空")
    @Schema(description = "文章类型 1-图文 2-视频", example = "1")
    private Integer type;
    
    @Schema(description = "\u89c6\u9891URL\uff08type=2\u65f6\u6709\u6548\uff09", example = "http://example.com/video.mp4")
    private String videoUrl;
    
    @NotNull(message = "所属模块不能为空")
    @Schema(description = "所属模块ID", example = "1")
    private Long moduleId;
}
