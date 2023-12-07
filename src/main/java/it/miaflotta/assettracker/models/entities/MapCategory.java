package it.miaflotta.assettracker.models.entities;

import it.miaflotta.assettracker.enumerations.MapType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "map_category")
public class MapCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "map_name")
    private String name;

    @Column(name = "map_type")
    @Enumerated(EnumType.STRING)
    private MapType type;

    @Column
    private String apiKey;
}
