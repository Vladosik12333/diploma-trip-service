package by.vb.diplomatripservice.controller;

import by.vb.diplomatripservice.dto.BasicResponse;
import by.vb.diplomatripservice.dto.CategoryResponse;
import by.vb.diplomatripservice.service.CategoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Categories", description = "Trip category lookup")
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	@Operation(summary = "List categories", description = "Returns all categories available for trips.")
	@ApiResponse(responseCode = "200", description = "Categories returned")
	public BasicResponse<List<CategoryResponse>> getCategories() {
		log.info("Received request to list categories");
		return new BasicResponse<>(categoryService.getCategories(), StringUtils.EMPTY, true);
	}
}
