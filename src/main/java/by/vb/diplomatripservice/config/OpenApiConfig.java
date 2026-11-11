package by.vb.diplomatripservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI diplomaTripOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Diploma Trip Service API")
						.description("API for managing trips, categories, and location suggestions.")
						.version("v1")
						.contact(new Contact().name("Diploma Trip Service")))
				.servers(List.of(new Server().url("/api/trip")));
	}
}
