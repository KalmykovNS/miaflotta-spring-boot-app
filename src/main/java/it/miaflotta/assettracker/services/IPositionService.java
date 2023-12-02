package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.PositionDTO;
import it.miaflotta.assettracker.models.dto.response.RouteResponse;

public interface IPositionService {
    PositionDTO findById(Long id);

    RouteResponse findRoutes(Long id);
}
