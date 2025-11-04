package com.newhker.controller.mini;

import com.newhker.common.Result;
import com.newhker.utils.MinioUtils;
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
public class MiniUploadController {
    
    @Autowired
    private MinioUtils minioUtils;
    
    /**
     * 上传文件
     */
    @PostMapping
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String url = minioUtils.uploadFile(file);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败");
        }
    }
}
