package it.miaflotta.assettracker.models.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    private String name;

    private String serialNumber;

    private String status;

    private Long offModeInSec;

    private DeviceCategoryDTO category;
}
