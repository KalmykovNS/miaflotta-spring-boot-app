package it.miaflotta.assettracker.models.dto.position.poi;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrUpdatePoiRequest extends PoiDTO {
    @NotNull(message = "{input.validation.poi.category.notnull}")
    private Long poiCategoryId;
}
