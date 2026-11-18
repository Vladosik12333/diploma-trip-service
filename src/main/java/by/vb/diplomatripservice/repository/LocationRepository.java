package by.vb.diplomatripservice.repository;

import by.vb.diplomatripservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
	Optional<Location> findByGeonameId(Long geonameId);
}
