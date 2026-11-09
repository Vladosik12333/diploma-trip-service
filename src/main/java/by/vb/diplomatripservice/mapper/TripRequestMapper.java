package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.CreateTripRequest;
import by.vb.diplomatripservice.entity.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripRequestMapper implements Mapper<CreateTripRequest, Trip> {
	@Override
	public Trip mapTo(CreateTripRequest dto) {
		Trip trip = new Trip();
		trip.setUserId(dto.userId());
		trip.setTitle(dto.title().trim());
		trip.setStartDate(dto.startDate());
		trip.setEndDate(dto.endDate());
		trip.setBudgetAmount(dto.budgetAmount());
		return trip;
	}

}
