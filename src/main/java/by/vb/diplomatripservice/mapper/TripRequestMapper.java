package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.constant.TripConstants;
import by.vb.diplomatripservice.dto.CreateTripRequest;
import by.vb.diplomatripservice.entity.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripRequestMapper implements Mapper<CreateTripRequest, Trip> {
	@Override
	public Trip mapTo(final CreateTripRequest dto) {
		final Trip trip = new Trip();
		trip.setUserId(dto.userId());
		trip.setTitle(dto.title().trim());
		trip.setStartDate(dto.startDate());
		trip.setEndDate(dto.endDate());
		trip.setBudgetAmount(dto.budgetAmount());
		trip.setCurrency(TripConstants.DEFAULT_CURRENCY);
		trip.setTravelMode(dto.travelMode());
		trip.setTravelersCount(dto.travelersCount());
		return trip;
	}

}
