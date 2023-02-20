package kr.megaptera.assignment;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class AssignmentApplication {

    public static void main(String[] args) {

        SpringApplication.run(AssignmentApplication.class, args);


    }

//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(CorsRegistry registry) {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//                        .allowedOrigins("*")
//                        .allowCredentials(true)
//                        .maxAge(1200);
//            }
//        };
//    }

}
