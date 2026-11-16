package by.vb.diplomatripservice.service.impl;

import by.vb.diplomatripservice.dto.CategoryResponse;
import by.vb.diplomatripservice.entity.Category;
import by.vb.diplomatripservice.mapper.CategoryResponseMapper;
import by.vb.diplomatripservice.repository.CategoryRepository;
import by.vb.diplomatripservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryResponseMapper categoryResponseMapper;

	@Override
	public List<CategoryResponse> getCategories() {
		final List<CategoryResponse> categories = categoryRepository.findAll().stream().map(categoryResponseMapper::mapTo).toList();
		log.info("Loaded {} categories", categories.size());
		return categories;
	}

	@Override
	public List<Category> getCategoriesByIds(final Collection<Long> ids) {
		final List<Category> categories = categoryRepository.findAllByIdIn(ids);
		log.info("Resolved {} of {} requested categories", categories.size(), ids.size());
		return categories;
	}
}
