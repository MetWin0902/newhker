package com.newhker.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * BannerDTO
 */
@Data
public class BannerDTO {
    
    private Long id;
    
    @NotBlank(message = "Banner标题不能为空")
    private String title;
    
    @NotBlank(message = "Banner图片不能为空")
    private String imageUrl;
    
    @NotNull(message = "跳转类型不能为空")
    private Integer jumpType;
    
    @NotBlank(message = "跳转目标不能为空")
    private String jumpTarget;
    
    private Integer sortOrder;
}
