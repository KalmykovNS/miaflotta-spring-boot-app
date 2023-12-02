package it.miaflotta.assettracker.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "device")
public class Device extends BaseEntity {
    @Column
    private String name;

    @Column
    private String serialNumber;

    @Column
    private String status;

    @Column
    private Long offModeInSec;

    @OneToOne
    @JoinColumn(name = "device_category_id")
    private DeviceCategory category;
}
