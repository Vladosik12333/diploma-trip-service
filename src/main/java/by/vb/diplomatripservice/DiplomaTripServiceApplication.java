package by.vb.diplomatripservice;

import by.vb.diplomatripservice.client.geonames.GeoNamesClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(group = "geonames", types = GeoNamesClient.class)
public class DiplomaTripServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DiplomaTripServiceApplication.class, args);
	}

}
