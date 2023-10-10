package kr.megaptera.assignment;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 요청 경로에 대해서
                .allowedOrigins("http://localhost:8000") // 허용할 오리진 설정
                .allowedMethods("*") // 허용할 HTTP 메서드 설정. 예: GET, POST, PUT, DELETE 등
                .maxAge(3600); // preflight 요청의 결과를 1시간 동안 캐싱
    }
}
