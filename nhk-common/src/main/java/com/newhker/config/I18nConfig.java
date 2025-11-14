package com.newhker.config;

import com.newhker.i18n.I18nInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 多语言配置
 */
@Configuration
public class I18nConfig implements WebMvcConfigurer {
    
    @Autowired
    private I18nInterceptor i18nInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(i18nInterceptor)
                .addPathPatterns("/mini/**");
    }
}
