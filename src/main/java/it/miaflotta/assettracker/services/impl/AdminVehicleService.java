package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.models.dto.vehicle.CreateOrUpdateVehicleRequest;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.services.IAdminVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminVehicleService implements IAdminVehicleService {

    @Override
    public Page<VehicleDTO> find(String searchBy, Pageable pageable) {
        return null;
    }

    @Override
    public VehicleDTO find(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public Long create(String token, CreateOrUpdateVehicleRequest request) {
        return null;
    }

    @Override
    public Long update(String token, Long vehicleId, CreateOrUpdateVehicleRequest request) {
        return null;
    }

    @Override
    public void delete(String token, Long id) {

    }
}
