package by.vb.diplomatripservice.entity;

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

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "geoname_id", nullable = false, unique = true)
	private Long geonameId;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(precision = 9, scale = 6, nullable = false)
	private BigDecimal latitude;

	@Column(precision = 9, scale = 6, nullable = false)
	private BigDecimal longitude;

	@Column(name = "currency", length = 3, nullable = false)
	private String currency;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
