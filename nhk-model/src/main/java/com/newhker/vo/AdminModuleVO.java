package com.newhker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 管理后台模块返回VO
 */
@Data
@Schema(description = "模块信息")
public class AdminModuleVO {
    
    @Schema(description = "模块ID", example = "1")
    private Long id;
    
    @Schema(description = "父级模块ID", example = "0")
    private Long parentId;
    
    @Schema(description = "模块名称", example = "热门资讯")
    private String name;
    
    @Schema(description = "模块图标", example = "icon_news")
    private String icon;
    
    @Schema(description = "模块层级 1-L1 2-L2 3-L3", example = "1")
    private Integer level;
    
    @Schema(description = "排序", example = "1")
    private Integer sortOrder;
    
    @Schema(description = "状态 0-启用 1-禁用", example = "0")
    private Integer status;
    
    @Schema(description = "创建时间", example = "2024-01-01T12:00:00")
    private LocalDateTime createTime;
    
    @Schema(description = "更新时间", example = "2024-01-01T12:00:00")
    private LocalDateTime updateTime;
}
