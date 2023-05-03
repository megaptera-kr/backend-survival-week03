package kr.megaptera.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS")
						.allowedOrigins("http://localhost:8000");
			}
		};
	}

	@Configuration
	public class RequestLoggingFilterConfig {

		@Bean
		public CommonsRequestLoggingFilter logFilter() {
			CommonsRequestLoggingFilter filter
					= new CommonsRequestLoggingFilter();
			filter.setIncludeQueryString(true);
			filter.setIncludePayload(true);
			filter.setMaxPayloadLength(10000);
			filter.setIncludeHeaders(false);
			filter.setAfterMessagePrefix("REQUEST DATA: ");
			return filter;
		}
	}
}
