package com.intercom.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.intercom.spring.rest.filter.TidFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

    @Bean
    public FilterRegistrationBean<TidFilter> tidFilterRegistration() {
        FilterRegistrationBean<TidFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TidFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1); // Ensure it's early in the chain
        return registrationBean;
    }
}