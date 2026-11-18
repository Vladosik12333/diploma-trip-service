package by.vb.diplomatripservice.client.geonames;

import by.vb.diplomatripservice.dto.geonames.GeoNamesCityResponse;
import by.vb.diplomatripservice.dto.geonames.GeoNamesSearchResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface GeoNamesClient {
	@GetExchange("/searchJSON?featureClass=P&cities=cities15000&orderby=population&lang=en")
	GeoNamesSearchResponse searchCities(@RequestParam("name_startsWith") String nameStartsWith,
			@RequestParam int maxRows, @RequestParam String username);

	@GetExchange("/getJSON?lang=en")
	GeoNamesCityResponse getCity(@RequestParam long geonameId, @RequestParam String username);
}
