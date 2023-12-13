package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteCalendarResponse;
import it.miaflotta.assettracker.models.dto.position.route.RouteResponse;

import java.time.LocalDate;
import java.util.List;

public interface IPositionService {
    PositionDTO findById(Long id);

    PositionDTO findLast(Long id);

    RouteResponse findRoutes(Long id, LocalDate date);

    void handlePosition(String token, PositionDTO position);

    List<RouteCalendarResponse> findRoutesCalendars(Long id);
}
