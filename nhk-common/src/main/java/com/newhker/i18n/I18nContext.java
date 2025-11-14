package com.newhker.i18n;

/**
 * 多语言上下文线程变量
 */
public class I18nContext {
    
    private static final ThreadLocal<String> LANGUAGE_HOLDER = ThreadLocal.withInitial(() -> "zh-CN");
    
    /**
     * 设置当前线程的语言
     */
    public static void setLanguage(String language) {
        LANGUAGE_HOLDER.set(language);
    }
    
    /**
     * 获取当前线程的语言
     */
    public static String getLanguage() {
        return LANGUAGE_HOLDER.get();
    }
    
    /**
     * 清理线程变量
     */
    public static void clear() {
        LANGUAGE_HOLDER.remove();
    }
}
