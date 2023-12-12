package it.miaflotta.assettracker.models.dto.position;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PoiDTO {
    private String name;

    private Long userId;

    private Long poiCategoryId;

    private BigDecimal lat;

    private BigDecimal lng;

    private String iconName;

    private String description;
}
