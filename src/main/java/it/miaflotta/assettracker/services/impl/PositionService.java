package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.dto.PositionDTO;
import it.miaflotta.assettracker.models.dto.response.RouteResponse;
import it.miaflotta.assettracker.models.entities.Position;
import it.miaflotta.assettracker.repositories.PositionRepository;
import it.miaflotta.assettracker.services.IPositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionService implements IPositionService {
    private final PositionRepository repo;
    @Override
    public PositionDTO findById(Long id) {
        Optional<Position> position = repo.findById(id);
        PositionDTO dto = new PositionDTO();
        if (position.isPresent()) {
            dto.setId(position.get().getId());
            dto.setLat(position.get().getLat());
            dto.setLng(position.get().getLng());
            dto.setAngle(position.get().getAngle());
            dto.setSpeed(position.get().getSpeed());
            dto.setStatus(position.get().getStatus());
            dto.setCreatedAt(position.get().getCreatedAt());
            return dto;
        }
        return null;
    }

    @Override
    public RouteResponse findRoutes(Long id) {
        return null;
    }
}