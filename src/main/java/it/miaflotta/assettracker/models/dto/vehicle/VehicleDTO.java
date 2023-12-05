package it.miaflotta.assettracker.models.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long id;

    private String name;

    private String plate;

    private DeviceDTO device;

    private VehicleCategoryDTO category;
}
