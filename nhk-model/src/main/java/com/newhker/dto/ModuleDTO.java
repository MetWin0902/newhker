package com.newhker.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 模块DTO
 */
@Data
public class ModuleDTO {
    
    private Long id;
    
    @NotNull(message = "父级模块ID不能为空")
    private Long parentId;
    
    @NotBlank(message = "模块名称不能为空")
    private String name;
    
    private String icon;
    
    @NotNull(message = "模块层级不能为空")
    private Integer level;
    
    private Integer sortOrder;
}
