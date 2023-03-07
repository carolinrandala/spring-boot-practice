package com.sda.study.springbootpractice.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for CORS
 */
@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // ** refers to all the mappings
                .allowedMethods("GET", "POST", "PUT");
    }
}
