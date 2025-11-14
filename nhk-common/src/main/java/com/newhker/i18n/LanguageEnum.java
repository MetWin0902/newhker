package com.newhker.i18n;

import lombok.Getter;

/**
 * 支持的语言类型
 */
@Getter
public enum LanguageEnum {
    ZH_CN("zh-CN", "简体中文"),
    ZH_TW("zh-TW", "繁体中文"),
    EN_US("en-US", "英文");
    
    private final String code;
    private final String name;
    
    LanguageEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public static LanguageEnum fromCode(String code) {
        for (LanguageEnum lang : LanguageEnum.values()) {
            if (lang.code.equals(code)) {
                return lang;
            }
        }
        return ZH_CN;
    }
}
