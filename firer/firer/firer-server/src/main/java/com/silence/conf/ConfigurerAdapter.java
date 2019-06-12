package com.silence.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by silence on 2018/1/15.
 */
@Configuration
public class ConfigurerAdapter extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("*//**")
                .excludePathPatterns("/assets*//**","/user/login","/user/register");*/
    }
}
