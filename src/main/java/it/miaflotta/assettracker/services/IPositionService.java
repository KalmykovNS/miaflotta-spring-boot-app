package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteCalendarResponse;
import it.miaflotta.assettracker.models.dto.position.route.RouteResponse;
import it.miaflotta.assettracker.models.entities.position.Position;

import java.time.LocalDate;
import java.util.List;

public interface IPositionService {
    PositionDTO findById(Long id) throws NotFoundException;

    Position findEntityById(Long id) throws NotFoundException;

    PositionDTO findLastByVehicleId(Long vehicleId);

    List<PositionDTO> findLastPositions(String token, List<Long> vehicleIds);

    RouteResponse findRoutes(String token, Long vehicleId, LocalDate date) throws NotFoundException;

    List<RouteCalendarResponse> findRoutesCalendars(Long id);
}
