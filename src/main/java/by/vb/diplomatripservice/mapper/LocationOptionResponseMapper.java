package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.LocationOptionResponse;
import by.vb.diplomatripservice.dto.geonames.GeoNamesCityResponse;
import org.springframework.stereotype.Component;

@Component
public class LocationOptionResponseMapper implements Mapper<GeoNamesCityResponse, LocationOptionResponse> {
	@Override
	public LocationOptionResponse mapTo(final GeoNamesCityResponse city) {
		return new LocationOptionResponse(city.geonameId(), city.name(), city.countryName());
	}
}
