package com.thcode.feedreader.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * This is AppWebConfig, it sets Mvc configuration for CORS
 * CORS is an issue, because our client is hosted on its own.
 * 
 * @author taha-sk
 *
 */
@Configuration
@EnableWebMvc
public class AppWebConfig implements WebMvcConfigurer {
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
        .allowedOrigins("http://localhost:4200")
        .maxAge(3600);
    }

}
