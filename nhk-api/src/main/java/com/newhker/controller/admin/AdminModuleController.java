package com.newhker.controller.admin;

import com.newhker.common.Result;
import com.newhker.dto.ModuleDTO;
import com.newhker.entity.Module;
import com.newhker.service.ModuleService;
import com.newhker.vo.ModuleTreeVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理后台 - 模块管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/module")
public class AdminModuleController {
    
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
    
    /**
     * 获取所有模块列表
     */
    @GetMapping("/list")
    public Result<List<Module>> getModuleList() {
        List<Module> list = moduleService.list();
        return Result.success(list);
    }
    
    /**
     * 获取模块详情
     */
    @GetMapping("/{id}")
    public Result<Module> getModuleById(@PathVariable Long id) {
        Module module = moduleService.getById(id);
        return Result.success(module);
    }
    
    /**
     * 创建或更新模块
     */
    @PostMapping
    public Result<?> saveOrUpdate(@Valid @RequestBody ModuleDTO dto) {
        moduleService.saveOrUpdateModule(dto);
        return Result.success();
    }
    
    /**
     * 删除模块
     */
    @DeleteMapping("/{id}")
    public Result<?> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return Result.success();
    }
}
