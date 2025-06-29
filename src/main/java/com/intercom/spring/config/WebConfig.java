package com.intercom.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.intercom.spring.rest.filter.TidFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

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