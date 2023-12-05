package it.miaflotta.assettracker.models.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCategoryDTO {
    private String name;

    private String description;

    private Integer weightIndex;
}
