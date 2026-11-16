package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.dto.CategoryResponse;
import by.vb.diplomatripservice.entity.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
	List<CategoryResponse> getCategories();

	List<Category> getCategoriesByIds(Collection<Long> ids);
}
