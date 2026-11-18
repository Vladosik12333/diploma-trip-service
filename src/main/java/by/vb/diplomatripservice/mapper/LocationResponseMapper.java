package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.LocationResponse;
import by.vb.diplomatripservice.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationResponseMapper implements Mapper<Location, LocationResponse> {
	@Override
	public LocationResponse mapTo(final Location location) {
		return new LocationResponse(location.getGeonameId(), location.getCity(), location.getCountry(),
				location.getLatitude(), location.getLongitude());
	}
}
