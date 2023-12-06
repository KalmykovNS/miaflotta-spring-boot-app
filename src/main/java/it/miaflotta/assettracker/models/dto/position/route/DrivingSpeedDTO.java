package it.miaflotta.assettracker.models.dto.position.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrivingSpeedDTO {
    private Integer max;
    private Integer min;
    private Integer avg;
}
