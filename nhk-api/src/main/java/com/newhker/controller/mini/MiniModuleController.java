package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.service.ModuleService;
import com.newhker.vo.ModuleTreeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序端 - 模块控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/module")
@Tag(name = "小程序端-模块", description = "模块查看操作")
public class MiniModuleController {
    
    @Autowired
    private ModuleService moduleService;
    
    /**
     * 获取模块树形结构
     */
    @GetMapping("/tree")
    @Operation(summary = "获取模块树形结构", description = "获取整个模块树形结构")
    public Result<List<ModuleTreeVO>> getModuleTree() {
        List<ModuleTreeVO> tree = moduleService.getModuleTree();
        return Result.success(tree);
    }
}
