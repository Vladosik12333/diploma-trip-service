package by.vb.diplomatripservice.service;

import by.vb.diplomatripservice.dto.CategoryResponse;
import by.vb.diplomatripservice.entity.Category;
import by.vb.diplomatripservice.repository.CategoryRepository;
import by.vb.diplomatripservice.mapper.CategoryResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryResponseMapper categoryResponseMapper;

	public List<CategoryResponse> getCategories() {
		List<CategoryResponse> categories = categoryRepository.findAll().stream().map(categoryResponseMapper::mapTo).toList();
		log.info("Loaded {} categories", categories.size());
		return categories;
	}

	public List<Category> getCategoriesByIds(Collection<Long> ids) {
		List<Category> categories = categoryRepository.findAllByIdIn(ids);
		log.info("Resolved {} of {} requested categories", categories.size(), ids.size());
		return categories;
	}
}
