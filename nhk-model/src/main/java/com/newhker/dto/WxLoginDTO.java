package com.newhker.dto;

import lombok.Data;

/**
 * 微信小程序登录DTO
 */
@Data
public class WxLoginDTO {
    
    private String code;
    private String encryptedData;
    private String iv;
}
