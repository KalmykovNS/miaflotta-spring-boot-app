package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.position.PositionCountDTO;
import it.miaflotta.assettracker.models.dto.position.PositionDTO;

import java.time.LocalDate;
import java.util.List;

public interface IAdminPositionService {
    void handlePosition(String token, PositionDTO position);

    PositionCountDTO count(String token);

    List<PositionCountDTO> findByUser(String token, Long userId);

    void deleteByUser(String token, Long userId, LocalDate date) throws NotFoundException;
}
