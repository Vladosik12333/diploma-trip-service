package by.vb.diplomatripservice.repository;

import by.vb.diplomatripservice.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
