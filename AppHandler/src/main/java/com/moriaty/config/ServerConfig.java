package com.moriaty.config;

import com.moriaty.filter.JWTProcessingFilter;
import com.moriaty.filter.MoCharacterEncodingFilter;
import com.moriaty.filter.OriginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2019/8/29 15:51
 * @Description TODO
 *   注册 filter listen
 */
@Configuration
public class ServerConfig {
    // 注册 OriginFilter
    @Bean
    public FilterRegistrationBean<OriginFilter> crossFilter() {
        FilterRegistrationBean<OriginFilter> registrationBean = new FilterRegistrationBean<OriginFilter>();
        registrationBean.setFilter(new OriginFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
    // 注册 MoCharacterEncodingFilter
    @Bean
    public FilterRegistrationBean<MoCharacterEncodingFilter> encodingFilter() {
        FilterRegistrationBean<MoCharacterEncodingFilter> registrationBean = new FilterRegistrationBean<MoCharacterEncodingFilter>();
        registrationBean.setFilter(new MoCharacterEncodingFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
    // 注册 JWTProcessingFilter
    @Bean
    public FilterRegistrationBean<JWTProcessingFilter> jwtFilter() {
        FilterRegistrationBean<JWTProcessingFilter> registrationBean = new FilterRegistrationBean<JWTProcessingFilter>();
        registrationBean.setFilter(new JWTProcessingFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }
}
