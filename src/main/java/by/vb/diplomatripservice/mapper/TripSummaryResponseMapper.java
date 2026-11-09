package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.TripSummaryResponse;
import by.vb.diplomatripservice.entity.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripSummaryResponseMapper implements Mapper<Trip, TripSummaryResponse> {
	@Override
	public TripSummaryResponse mapTo(Trip entity) {
		return new TripSummaryResponse(entity.getId(), entity.getTitle(), entity.getStartDate(),
				entity.getLocation().getCity(), entity.getLocation().getCountry());
	}
}
