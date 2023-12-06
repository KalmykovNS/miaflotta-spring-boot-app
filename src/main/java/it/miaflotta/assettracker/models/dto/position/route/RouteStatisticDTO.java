package it.miaflotta.assettracker.models.dto.position.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteStatisticDTO {
    private DrivingSpeedDTO speed;
    private DrivingTimeDTO drivingTime;
    private DrivingDistanceDTO distance;
}
