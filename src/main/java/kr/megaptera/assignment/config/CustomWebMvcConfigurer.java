package kr.megaptera.assignment.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/posts/**")
                .allowedOrigins("http://localhost:8000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        registry.addMapping("/comments/**")
                .allowedOrigins("http://localhost:8000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
