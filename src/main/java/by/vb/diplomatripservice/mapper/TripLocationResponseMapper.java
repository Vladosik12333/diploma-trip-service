package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.TripLocationResponse;
import by.vb.diplomatripservice.entity.TripLocation;
import org.springframework.stereotype.Component;

@Component
public class TripLocationResponseMapper implements Mapper<TripLocation, TripLocationResponse> {
	@Override
	public TripLocationResponse mapTo(TripLocation location) {
		return new TripLocationResponse(location.getGooglePlaceId(), location.getCity(),
				location.getCountry(), location.getLatitude(), location.getLongitude());
	}
}
