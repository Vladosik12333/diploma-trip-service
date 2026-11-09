package by.vb.diplomatripservice.repository;

import by.vb.diplomatripservice.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
	@EntityGraph(attributePaths = {"location", "categories"})
	Optional<Trip> findByIdAndUserId(Long id, Long userId);

	@EntityGraph(attributePaths = "location")
	Page<Trip> findAllByUserId(Long userId, Pageable pageable);

	@EntityGraph(attributePaths = "location")
	@Query(value = """
			select t from Trip t join t.location l
			where t.userId = :userId
			  and (lower(t.title) like :pattern escape '\\'
			    or lower(l.city) like :pattern escape '\\'
			    or lower(l.country) like :pattern escape '\\')
			""",
			countQuery = """
			select count(t) from Trip t join t.location l
			where t.userId = :userId
			  and (lower(t.title) like :pattern escape '\\'
			    or lower(l.city) like :pattern escape '\\'
			    or lower(l.country) like :pattern escape '\\')
			""")
	Page<Trip> searchUserTrips(@Param("userId") Long userId, @Param("pattern") String pattern, Pageable pageable);
}
