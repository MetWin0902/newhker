package com.newhker.controller.mini;

import com.newhker.common.PageResult;
import com.newhker.common.Result;
import com.newhker.service.ModuleService;
import com.newhker.vo.ModuleTreeVO;
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
public class MiniModuleController {
    
    @Autowired
    private ModuleService moduleService;
    
    /**
     * 获取模块树形结构
     */
    @GetMapping("/tree")
    public Result<List<ModuleTreeVO>> getModuleTree() {
        List<ModuleTreeVO> tree = moduleService.getModuleTree();
        return Result.success(tree);
    }
}
