package it.miaflotta.assettracker.models.entities;

import it.miaflotta.assettracker.enumerations.PositionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")
public class Position extends BaseEntity {
    @Column
    private BigDecimal lat;

    @Column
    private BigDecimal lng;

    @Column
    private Integer speed;

    @Column
    private Integer angle;

    @Column
    private LocalDateTime dateTime;

    @Column
    private Long deviceId;

    @Column
    private Long vehicleId;

    @Column
    @Enumerated(EnumType.STRING)
    private PositionStatus status;

    @Column
    private String address;
}
