package com.newhker.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newhker.dto.ModuleDTO;
import com.newhker.entity.Module;
import com.newhker.mapper.ModuleMapper;
import com.newhker.vo.AdminModuleVO;
import com.newhker.vo.ModuleTreeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模块Service
 */
@Slf4j
@Service
public class ModuleService extends ServiceImpl<ModuleMapper, Module> {
    
    /**
     * 获取所有模块列表（管理端）
     */
    public List<AdminModuleVO> getModuleListForAdmin() {
        // ... existing code ...
        List<Module> allModules = list();
        
        return allModules.stream().map(module -> {
            AdminModuleVO vo = new AdminModuleVO();
            BeanUtils.copyProperties(module, vo);
            return vo;
        }).collect(Collectors.toList());
    }
    
    /**
     * 获取模块详情（管理端）
     */
    public AdminModuleVO getModuleByIdForAdmin(Long moduleId) {
        Module module = getById(moduleId);
        if (module == null) {
            throw new com.newhker.exception.BusinessException("模块不存在");
        }
        
        AdminModuleVO vo = new AdminModuleVO();
        BeanUtils.copyProperties(module, vo);
        return vo;
    }
    
    /**
     * 获取模块树形结构
     */
    public List<ModuleTreeVO> getModuleTree() {
        // 查询所有启用的模块
        LambdaQueryWrapper<Module> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Module::getStatus, 0)
                .orderByAsc(Module::getSortOrder);
        
        List<Module> allModules = list(wrapper);
        
        // 构建树形结构
        return buildTree(allModules, 0L);
    }
    
    /**
     * 递归构建树形结构
     */
    private List<ModuleTreeVO> buildTree(List<Module> allModules, Long parentId) {
        List<ModuleTreeVO> tree = new ArrayList<>();
        
        for (Module module : allModules) {
            if (module.getParentId().equals(parentId)) {
                ModuleTreeVO vo = new ModuleTreeVO();
                BeanUtils.copyProperties(module, vo);
                
                // 递归获取子模块
                List<ModuleTreeVO> children = buildTree(allModules, module.getId());
                if (!children.isEmpty()) {
                    vo.setChildren(children);
                }
                
                tree.add(vo);
            }
        }
        
        return tree;
    }
    
    /**
     * 保存或更新模块
     */
    public boolean saveOrUpdateModule(ModuleDTO dto) {
        Module module = new Module();
        BeanUtils.copyProperties(dto, module);
        
        if (module.getId() == null) {
            module.setStatus(0);
        }
        
        return saveOrUpdate(module);
    }
    
    /**
     * 删除模块（检查是否有子模块和文章）
     */
    public boolean deleteModule(Long moduleId) {
        // 检查是否有子模块
        LambdaQueryWrapper<Module> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Module::getParentId, moduleId);
        long count = count(wrapper);
        
        if (count > 0) {
            throw new com.newhker.exception.BusinessException("该模块下存在子模块，无法删除");
        }
        
        return removeById(moduleId);
    }
}
