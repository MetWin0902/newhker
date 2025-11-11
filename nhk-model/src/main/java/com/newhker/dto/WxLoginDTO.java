package com.newhker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 微信小程序登录DTO
 */
@Data
@Schema(description = "微信登录信息")
public class WxLoginDTO {
    
    @Schema(description = "微信授权code", example = "051xxx")
    private String code;
    
    @Schema(description = "加密的用户数据", example = "xxx")
    private String encryptedData;
    
    @Schema(description = "加密算法的初始向量", example = "xxx")
    private String iv;
}
