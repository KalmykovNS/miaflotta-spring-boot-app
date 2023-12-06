package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.services.IAdminVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminVehicleService implements IAdminVehicleService {
    @Override
    public Long create(String token, VehicleDTO request) {
        return null;
    }

    @Override
    public Long update(String token, VehicleDTO request) {
        return null;
    }

    @Override
    public void delete(String token, Long id) {

    }
}
