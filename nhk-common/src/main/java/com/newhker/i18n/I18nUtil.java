package com.newhker.i18n;

/**
 * 多语言工具类
 * 使用 properties 文件管理多语言消息
 */
public class I18nUtil {
    
    /**
     * 获取当前语言的消息
     * @param key 消息键
     * @return 消息文本
     */
    public static String getMessage(String key) {
        return I18nMessageLoader.getMessage(key);
    }
    
    /**
     * 获取当前语言的消息，支持可变参数
     * 占位符格式为 {0}, {1}, {2} 等
     * @param key 消息键
     * @param params 可变参数
     * @return 格式化后的消息文本
     */
    public static String getMessage(String key, Object... params) {
        return I18nMessageLoader.getMessage(key, params);
    }
    
    /**
     * 获取指定语言的消息
     * @param language 语言代码（zh-CN, zh-TW, en-US）
     * @param key 消息键
     * @return 消息文本
     */
    public static String getMessage(String language, String key) {
        return I18nMessageLoader.getMessage(language, key);
    }
    
    /**
     * 获取指定语言的消息，支持可变参数
     * @param language 语言代码（zh-CN, zh-TW, en-US）
     * @param key 消息键
     * @param params 可变参数
     * @return 格式化后的消息文本
     */
    public static String getMessage(String language, String key, Object... params) {
        return I18nMessageLoader.getMessage(language, key, params);
    }
}
