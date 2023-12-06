package it.miaflotta.assettracker.models.dto.vehicle;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.vehicle.device.DeviceDTO;
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

    private PositionDTO lastPosition;
}
