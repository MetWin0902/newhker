package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.i18n.I18nUtil;
import com.newhker.utils.MinioUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 小程序端 - 文件上传控制器
 */
@Slf4j
@RestController
@RequestMapping("/mini/upload")
@Tag(name = "小程序端-文件上传", description = "文件上传操作")
public class MiniUploadController {
    
    @Autowired
    private MinioUtils minioUtils;
    
    /**
     * 上传文件
     */
    @PostMapping
    @Operation(summary = "上传文件", description = "上传文件到MinIO对象存储")
    public Result<String> uploadFile(
            @RequestParam("file")
            @Parameter(description = "上传的文件")
            MultipartFile file) {
        try {
            String url = minioUtils.uploadFile(file);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error(I18nUtil.getMessage("i18n.upload.failed"));
        }
    }
}
