package aesir.api.restaurant.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Data
@Table(name = "diningTables")
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int tablesNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placesId")
    private Place places;


}
