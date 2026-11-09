package by.vb.diplomatripservice.repository;

import by.vb.diplomatripservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findAllByIdIn(Collection<Long> ids);
}
