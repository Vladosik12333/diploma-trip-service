package by.vb.diplomatripservice.service.impl;

import by.vb.diplomatripservice.client.geonames.GeoNamesClient;
import by.vb.diplomatripservice.constant.GeoNamesConstants;
import by.vb.diplomatripservice.dto.LocationOptionResponse;
import by.vb.diplomatripservice.dto.geonames.GeoNamesCityResponse;
import by.vb.diplomatripservice.dto.geonames.GeoNamesCountryInfoResponse;
import by.vb.diplomatripservice.dto.geonames.GeoNamesCountryResponse;
import by.vb.diplomatripservice.dto.geonames.GeoNamesSearchResponse;
import by.vb.diplomatripservice.entity.Location;
import by.vb.diplomatripservice.exception.ApiException;
import by.vb.diplomatripservice.mapper.LocationOptionResponseMapper;
import by.vb.diplomatripservice.repository.LocationRepository;
import by.vb.diplomatripservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService {
	private final GeoNamesClient geoNamesClient;
	private final LocationRepository locationRepository;
	private final LocationOptionResponseMapper locationOptionResponseMapper;

	@Value("${geonames.username}")
	private String geoNamesUsername;

	@Override
	public List<LocationOptionResponse> autocomplete(final String userInput) {
		final GeoNamesSearchResponse response;

		try {
			response = geoNamesClient.searchCities(userInput, GeoNamesConstants.AUTOCOMPLETE_MAX_RESULTS,
					geoNamesUsername);
		} catch (final RestClientException e) {
			log.error("GeoNames city search failed. userInput={}", userInput, e);
			throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE, "Location service is unavailable", e);
		}

		return Stream.ofNullable(response.geonames())
				.flatMap(List::stream)
				.map(locationOptionResponseMapper::mapTo)
				.toList();
	}

	@Override
	@Transactional
	public Location getOrCreateLocation(final Long geonameId) {
		return locationRepository.findByGeonameId(geonameId)
				.orElseGet(() -> createLocation(geonameId));
	}

	private Location createLocation(final Long geonameId) {
		final GeoNamesCityResponse city;

		try {
			city = geoNamesClient.getCity(geonameId, geoNamesUsername);
		} catch (final RestClientException e) {
			log.error("GeoNames city lookup failed. geonameId={}", geonameId, e);
			throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE, "Location service is unavailable", e);
		}

		if (city.status() != null && city.status().value() == GeoNamesConstants.NOT_FOUND_STATUS) {
			log.warn("GeoNames city not found. geonameId={}", geonameId);
			throw new ApiException(HttpStatus.BAD_REQUEST, "City not found");
		}

		final String currency = getCurrency(city.countryCode());

		final Location location = new Location();

		location.setGeonameId(city.geonameId());
		location.setCity(city.name());
		location.setCountry(city.countryName());
		location.setLatitude(city.lat());
		location.setLongitude(city.lng());
		location.setCurrency(currency);

		final Location savedLocation = locationRepository.save(location);

		log.info("Created location from GeoNames data. Location={}", savedLocation);

		return savedLocation;
	}

	private String getCurrency(final String countryCode) {
		final GeoNamesCountryInfoResponse countryInfo;

		try {
			countryInfo = geoNamesClient.getCountryInfo(countryCode, geoNamesUsername);
		} catch (final RestClientException e) {
			log.error("GeoNames country lookup failed. countryCode={}", countryCode, e);
			throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE, "Location service is unavailable", e);
		}

		return Stream.ofNullable(countryInfo.geonames())
				.flatMap(List::stream)
				.map(GeoNamesCountryResponse::currencyCode)
				.filter(StringUtils::isNotBlank)
				.findFirst()
				.orElseThrow(() -> {
					log.error("GeoNames returned no currency. countryCode={}, status={}", countryCode,
							countryInfo.status());
					return new ApiException(HttpStatus.SERVICE_UNAVAILABLE, "Location service is unavailable");
				});
	}
}
