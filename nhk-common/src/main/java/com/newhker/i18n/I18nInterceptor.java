package com.newhker.i18n;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.http.HttpRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 多语言拦截器
 * 从请求头中获取语言信息并设置到上下文
 */
@Component
public class I18nInterceptor implements HandlerInterceptor {
    
    private static final String LANGUAGE_HEADER = "X-Language";
    private static final String DEFAULT_LANGUAGE = "zh-CN";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String language = request.getHeader(LANGUAGE_HEADER);
        
        if (language == null || language.isEmpty()) {
            language = DEFAULT_LANGUAGE;
        }
        
        // 验证语言是否有效
        LanguageEnum lang = LanguageEnum.fromCode(language);
        I18nContext.setLanguage(lang.getCode());
        
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        I18nContext.clear();
    }
}
