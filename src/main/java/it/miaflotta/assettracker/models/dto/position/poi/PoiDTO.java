package it.miaflotta.assettracker.models.dto.position.poi;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoiDTO {
    @NotNull(message = "{input.validation.poi.name.notnull}")
    @Size(min = 1, max = 100, message = "{input.validation.poi.name.length}")
    private String name;

    private Long userId;

    private PoiCategoryDTO category;

    @NotNull(message = "{input.validation.poi.latlng.notnull}")
    private BigDecimal lat;

    @NotNull(message = "{input.validation.poi.latlng.notnull}")
    private BigDecimal lng;

    private String iconName;

    private String description;
}
