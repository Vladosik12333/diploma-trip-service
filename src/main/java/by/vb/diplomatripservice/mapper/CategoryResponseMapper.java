package by.vb.diplomatripservice.mapper;

import by.vb.diplomatripservice.dto.CategoryResponse;
import by.vb.diplomatripservice.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper implements Mapper<Category, CategoryResponse> {
	@Override
	public CategoryResponse mapTo(Category category) {
		return new CategoryResponse(category.getId(), category.getCode(),
				category.getName(), category.getDescription());
	}

}
