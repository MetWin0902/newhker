package com.newhker.controller.admin;

import com.newhker.common.Result;
import com.newhker.dto.ModuleDTO;
import com.newhker.vo.AdminModuleVO;
import com.newhker.service.ModuleService;
import com.newhker.vo.ModuleTreeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "管理后台-模块管理", description = "模块CRUD及模块树操作")
public class AdminModuleController {
    
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
    
    /**
     * 获取所有模块列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取所有模块列表", description = "获取整个模块列表")
    public Result<List<AdminModuleVO>> getModuleList() {
        List<AdminModuleVO> list = moduleService.getModuleListForAdmin();
        return Result.success(list);
    }
    
    /**
     * 获取模块详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取模块详情", description = "根据模块ID获取模块详细信息")
    public Result<AdminModuleVO> getModuleById(
            @PathVariable
            @Parameter(description = "模块ID")
            Long id) {
        AdminModuleVO module = moduleService.getModuleByIdForAdmin(id);
        return Result.success(module);
    }
    
    /**
     * 创建或更新模块
     */
    @PostMapping
    @Operation(summary = "创建或更新模块", description = "创建新模块或更新现有模块")
    public Result<?> saveOrUpdate(
            @Valid @RequestBody
            @Parameter(description = "模块信息")
            ModuleDTO dto) {
        moduleService.saveOrUpdateModule(dto);
        return Result.success();
    }
    
    /**
     * 删除模块
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除模块", description = "永久删除指定模块")
    public Result<?> deleteModule(
            @PathVariable
            @Parameter(description = "模块ID")
            Long id) {
        moduleService.deleteModule(id);
        return Result.success();
    }
}
