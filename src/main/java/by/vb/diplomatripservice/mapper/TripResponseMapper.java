package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.TripResponse;
import by.vb.diplomatripservice.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripResponseMapper implements Mapper<Trip, TripResponse> {
	private final LocationResponseMapper locationResponseMapper;
	private final CategoryResponseMapper categoryResponseMapper;

	@Override
	public TripResponse mapTo(final Trip entity) {
		return new TripResponse(entity.getId(), entity.getTitle(), entity.getStartDate(), entity.getEndDate(),
				entity.getBudgetAmount(), entity.getCurrency(), entity.getTravelMode(), entity.getTravelersCount(),
				locationResponseMapper.mapTo(entity.getLocation()),
				entity.getCategories().stream().map(categoryResponseMapper::mapTo).toList(), entity.getCreatedAt(), entity.getUpdatedAt());
	}
}
