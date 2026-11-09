package by.vb.diplomatripservice.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class GooglePlacesConstants {
	public static final String API_KEY_HEADER = "x-goog-api-key";
	public static final String FIELD_MASK_HEADER = "x-goog-fieldmask";
	public static final String DETAILS_FIELD_MASK = "id,displayName,location,addressComponents";
	public static final String PLACE_RESOURCE_PREFIX = "places/";
	public static final String LOCALITY_TYPE = "locality";
	public static final String COUNTRY_TYPE = "country";
}
