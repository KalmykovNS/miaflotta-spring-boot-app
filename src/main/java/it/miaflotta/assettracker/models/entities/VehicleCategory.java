package it.miaflotta.assettracker.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle_category")
public class VehicleCategory extends BaseEntity {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer weightIndex;
}
