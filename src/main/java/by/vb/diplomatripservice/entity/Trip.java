package by.vb.diplomatripservice.entity;

import by.vb.diplomatripservice.entity.enums.TravelMode;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"location", "categories"})
@ToString(exclude = {"location", "categories"})
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@Column(name = "budget_amount", precision = 12, scale = 2, nullable = false)
	private BigDecimal budgetAmount;

	@Enumerated(EnumType.STRING)
	@Column(name = "travel_mode", length = 20, nullable = false)
	private TravelMode travelMode;

	@Column(name = "travelers_count", nullable = false)
	private Integer travelersCount;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;

	@ManyToMany
	@JoinTable(name = "trip_categories",
			joinColumns = @JoinColumn(name = "trip_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new LinkedHashSet<>();
}
