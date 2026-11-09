package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.constant.TripConstants;
import by.vb.diplomatripservice.dto.TripResponse;
import by.vb.diplomatripservice.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripResponseMapper implements Mapper<Trip, TripResponse> {
	private final TripLocationResponseMapper tripLocationResponseMapper;
	private final CategoryResponseMapper categoryResponseMapper;

	@Override
	public TripResponse mapTo(Trip entity) {
		return new TripResponse(entity.getId(), entity.getTitle(), entity.getStartDate(), entity.getEndDate(),
				entity.getBudgetAmount(), TripConstants.DEFAULT_CURRENCY, tripLocationResponseMapper.mapTo(entity.getLocation()),
				entity.getCategories().stream().map(categoryResponseMapper::mapTo).toList(), entity.getCreatedAt(), entity.getUpdatedAt());
	}
}
