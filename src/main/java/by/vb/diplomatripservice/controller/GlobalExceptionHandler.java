package by.vb.diplomatripservice.controller;

import by.vb.diplomatripservice.dto.BasicResponse;
import by.vb.diplomatripservice.exception.ApiException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<BasicResponse<Void>> handleApiException(
			ApiException exception
	) {
		log.warn("Request failed with status {}: {}", exception.getStatus(), exception.getMessage());

		return error(exception.getStatus(), exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ApiResponse(responseCode = "400", description = "Request validation failed")
	public ResponseEntity<BasicResponse<Void>> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception
	) {
		log.warn("Request validation failed: {}", exception.getBindingResult().getFieldErrors());

		String message = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.distinct()
				.collect(Collectors.joining("; "));

		return error(HttpStatus.BAD_REQUEST,
				message.isBlank() ? "Request validation failed" : message);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ApiResponse(responseCode = "400", description = "Request body is missing or malformed")
	public ResponseEntity<BasicResponse<Void>> handleUnreadableMessage(
			HttpMessageNotReadableException exception
	) {
		log.warn("Malformed request body", exception);

		return error(HttpStatus.BAD_REQUEST, "Request body is missing or malformed");
	}

	@ExceptionHandler(Exception.class)
	@ApiResponse(responseCode = "500", description = "Unexpected server error")
	public ResponseEntity<BasicResponse<Void>> handleUnexpectedException(Exception exception) {
		log.error("Unhandled application exception", exception);

		return error(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
	}

	private ResponseEntity<BasicResponse<Void>> error(HttpStatus status, String message) {
		return ResponseEntity.status(status).body(new BasicResponse<>(null, message, false));
	}
}
