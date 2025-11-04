package com.newhker.vo;

import lombok.Data;
import java.util.List;

/**
 * 模块树形VO
 */
@Data
public class ModuleTreeVO {
    
    private Long id;
    private Long parentId;
    private String name;
    private String icon;
    private Integer level;
    private Integer sortOrder;
    private List<ModuleTreeVO> children;
}
