package infostaff.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("Authorization", "Cache-Control", "Content-Type", "Accept", "X-Requested-With",
						"Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin")
				.allowedHeaders("Access-Control-Expose-Headers", "Authorization", "Cache-Control", "Content-Type",
						"Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin");

		//registry.addMapping("/**");
	}
}