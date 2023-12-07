package it.miaflotta.assettracker.models.dto;

import it.miaflotta.assettracker.enumerations.MapType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapCategoryDTO {
    private String name;
    private MapType type;
    private String apiKey;
}
