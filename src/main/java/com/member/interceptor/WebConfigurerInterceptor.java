package com.member.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 *
 * @author f
 * @date 2018-04-22
 */
@Configuration
public class WebConfigurerInterceptor extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new SSOInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
