package it.miaflotta.assettracker.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "poi")
public class Poi extends BaseEntity {
    @Column
    private String name;

    @Column
    private Long userId;

    @OneToOne
    @JoinColumn(name = "poi_category_id")
    private PoiCategory category;

    @Column
    private BigDecimal lat;

    @Column
    private BigDecimal lng;

    @Column
    private String iconName;

    @Column
    private String description;
}
