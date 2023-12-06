package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.position.response.RoutesResponse;

public interface IPositionService {
    PositionDTO findById(Long id);

    RoutesResponse findRoutes(Long id);

    void handlePosition(String token, PositionDTO position);
}
