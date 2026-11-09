package by.vb.diplomatripservice.config;

import by.vb.diplomatripservice.constant.GooglePlacesConstants;
import by.vb.diplomatripservice.exception.ApiException;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.rpc.FixedHeaderProvider;
import com.google.maps.places.v1.PlacesClient;
import com.google.maps.places.v1.PlacesSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

@Configuration
public class PlacesApiConfig {
	@Value("${google.api.key}")
	private String googleApiKey;

	@Bean
	public PlacesClient googleApiKey() {
		return createClient();
	}

	private PlacesClient createClient() {
		try {
			Map<String, String> headers = Map.of(GooglePlacesConstants.API_KEY_HEADER, googleApiKey);
			FixedHeaderProvider headersProvider = FixedHeaderProvider.create(headers);

			PlacesSettings placesSettings =
					PlacesSettings.newBuilder()
							.setHeaderProvider(headersProvider)
							.setCredentialsProvider(NoCredentialsProvider.create())
							.build();

			return PlacesClient.create(placesSettings);
		} catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error creating Google Places client", e);
		}
	}
}
