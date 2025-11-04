package com.newhker.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 文章DTO
 */
@Data
public class ArticleDTO {
    
    private Long id;
    
    @NotBlank(message = "文章标题不能为空")
    private String title;
    
    private String cover;
    
    @NotBlank(message = "文章内容不能为空")
    private String content;
    
    @NotNull(message = "文章类型不能为空")
    private Integer type;
    
    private String videoUrl;
    
    @NotNull(message = "所属模块不能为空")
    private Long moduleId;
}
