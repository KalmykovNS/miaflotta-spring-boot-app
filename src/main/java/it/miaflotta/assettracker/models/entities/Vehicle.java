package it.miaflotta.assettracker.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {
    @Column
    private String name;

    @Column
    private String plate;

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @OneToOne
    @JoinColumn(name = "vehicle_category_id")
    private VehicleCategory category;
}
