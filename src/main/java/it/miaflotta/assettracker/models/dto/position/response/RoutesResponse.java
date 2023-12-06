package it.miaflotta.assettracker.models.dto.position.response;

import it.miaflotta.assettracker.models.dto.position.route.RouteDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteStatisticDTO;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutesResponse {
    private VehicleDTO vehicle;
    private LocalDate date;
    private List<RouteDTO> routes;
    private RouteStatisticDTO statistic;
}
