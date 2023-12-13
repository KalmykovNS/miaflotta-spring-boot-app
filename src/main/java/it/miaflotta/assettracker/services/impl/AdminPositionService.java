package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.position.PositionCountDTO;
import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.repositories.PositionRepository;
import it.miaflotta.assettracker.services.IAdminPositionService;
import it.miaflotta.assettracker.services.IVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminPositionService implements IAdminPositionService {
    private final IVehicleService vehicleService;
    private final PositionRepository repo;

    @Override
    public void handlePosition(String token, PositionDTO position) {

    }

    @Override
    public PositionCountDTO count(String token) {
        return null;
    }

    @Override
    public List<PositionCountDTO> findByUser(String token, Long userId) {
        return null;
    }

    @Override
    public void deleteByUser(String token, Long userId, LocalDate date) throws NotFoundException {
        log.info("user id: " + userId);
        List<Long> vehicleIds = vehicleService.findAllByUserId(userId);
        if (CollectionUtils.isEmpty(vehicleIds)) {
            return;
        }

        if (Objects.nonNull(date)) {
            log.info("date: " + date);
            repo.deleteByVehicleIdInAndDateTimeBefore(vehicleIds, LocalDateTime.of(date, LocalTime.of(23, 59, 59)));
            return;
        }

        log.info("delete all");
        repo.deleteByVehicleIdIn(vehicleIds);
    }
}
