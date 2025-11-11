package com.newhker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * BannerDTO
 */
@Data
@Schema(description = "Banner信息")
public class BannerDTO {
    
    @Schema(description = "Banner ID", example = "1")
    private Long id;
    
    @NotBlank(message = "Banner标题不能为空")
    @Schema(description = "Banner标题", example = "新闻资讯")
    private String title;
    
    @NotBlank(message = "Banner图片不能为空")
    @Schema(description = "Banner图片URL", example = "http://example.com/banner.jpg")
    private String imageUrl;
    
    @NotNull(message = "跳转类型不能为空")
    @Schema(description = "跳转类型 1-文章详情 2-外链", example = "1")
    private Integer jumpType;
    
    @NotBlank(message = "跳转目标不能为空")
    @Schema(description = "跳转目标（文章ID或URL）", example = "123")
    private String jumpTarget;
    
    @Schema(description = "排序", example = "1")
    private Integer sortOrder;
}
