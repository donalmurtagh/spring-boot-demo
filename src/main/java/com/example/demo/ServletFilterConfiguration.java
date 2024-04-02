package com.example.demo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletFilterConfiguration {

    @Bean
    public FilterRegistrationBean<ThrowErrorFilter> filter() {
        FilterRegistrationBean<ThrowErrorFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ThrowErrorFilter());
        registrationBean.addUrlPatterns("/users/errorInFilter");
        registrationBean.setOrder(2);

        return registrationBean;
    }
}
