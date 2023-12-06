package it.miaflotta.assettracker.models.dto.position.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrivingTimeDTO {
    private LocalTime active;
    private LocalTime inactive;
    private LocalTime pause;
}
