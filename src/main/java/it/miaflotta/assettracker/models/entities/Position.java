package it.miaflotta.assettracker.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "position")
public class Position extends BaseEntity {
    @Column
    private BigDecimal lat;

    @Column
    private BigDecimal lng;

    @Column
    private Long speed;

    @Column
    private Long angle;

    @Column
    private String status;

    @Column
    private String address;
}
