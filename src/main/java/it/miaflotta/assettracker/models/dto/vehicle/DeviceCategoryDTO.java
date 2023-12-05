package it.miaflotta.assettracker.models.dto.vehicle;

import it.miaflotta.assettracker.enumerations.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCategoryDTO {
    private String name;
    private DeviceType type;
}
