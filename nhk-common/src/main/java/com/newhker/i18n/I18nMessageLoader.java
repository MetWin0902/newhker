package com.newhker.i18n;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 多语言消息加载器
 * 从 properties 文件加载和管理多语言消息
 */
@Component
public class I18nMessageLoader {
    
    private static final Map<String, Properties> LANGUAGE_PROPERTIES = new HashMap<>();
    private static final Pattern PARAM_PATTERN = Pattern.compile("\\{(\\d+)\\}");
    
    static {
        loadLanguageMessages();
    }
    
    /**
     * 加载所有语言的消息文件
     */
    private static void loadLanguageMessages() {
        try {
            loadMessageFile("zh-CN", "/i18n/messages_zh_CN.properties");
            loadMessageFile("zh-TW", "/i18n/messages_zh_TW.properties");
            loadMessageFile("en-US", "/i18n/messages_en_US.properties");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load i18n message files", e);
        }
    }
    
    /**
     * 加载指定语言的消息文件
     */
    private static void loadMessageFile(String language, String filePath) throws IOException {
        Properties properties = new Properties();
        try (InputStreamReader reader = new InputStreamReader(
                I18nMessageLoader.class.getResourceAsStream(filePath), 
                StandardCharsets.UTF_8)) {
            properties.load(reader);
            LANGUAGE_PROPERTIES.put(language, properties);
        }
    }
    
    /**
     * 获取指定语言的消息
     * @param language 语言代码（zh-CN, zh-TW, en-US）
     * @param key 消息键
     * @param params 可变参数，用于替换消息中的 {0}, {1} 等占位符
     * @return 消息文本
     */
    public static String getMessage(String language, String key, Object... params) {
        LanguageEnum lang = LanguageEnum.fromCode(language);
        Properties properties = LANGUAGE_PROPERTIES.get(lang.getCode());
        
        if (properties == null) {
            properties = LANGUAGE_PROPERTIES.get(LanguageEnum.ZH_CN.getCode());
        }
        
        String message = properties.getProperty(key, key);
        
        if (params != null && params.length > 0) {
            message = formatMessage(message, params);
        }
        
        return message;
    }
    
    /**
     * 获取当前语言的消息
     * @param key 消息键
     * @param params 可变参数
     * @return 消息文本
     */
    public static String getMessage(String key, Object... params) {
        String language = I18nContext.getLanguage();
        return getMessage(language, key, params);
    }
    
    /**
     * 格式化消息，替换 {0}, {1} 等占位符
     */
    private static String formatMessage(String message, Object... params) {
        Matcher matcher = PARAM_PATTERN.matcher(message);
        StringBuffer sb = new StringBuffer();
        
        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            String replacement = index < params.length ? 
                    String.valueOf(params[index]) : matcher.group(0);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        
        matcher.appendTail(sb);
        return sb.toString();
    }
    
    /**
     * 获取指定语言的所有消息
     */
    public static Properties getMessages(String language) {
        LanguageEnum lang = LanguageEnum.fromCode(language);
        return LANGUAGE_PROPERTIES.getOrDefault(lang.getCode(), 
                LANGUAGE_PROPERTIES.get(LanguageEnum.ZH_CN.getCode()));
    }
}
