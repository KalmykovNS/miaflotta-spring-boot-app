package it.miaflotta.assettracker.models.dto.position.route;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    Integer indexFrom;
    Integer indexTo;
    LocalTime dateFrom;
    LocalTime dateTo;
    RouteAddressDTO address;
    PositionDTO positionFrom;
    PositionDTO positionTo;
    List<PositionDTO> positions;
    RouteStatisticDTO statistic;
}
