package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.models.dto.vehicle.CreateOrUpdateVehicleRequest;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdminVehicleService {

    Page<VehicleDTO> find(String searchBy, Pageable pageable);
    VehicleDTO find(Long id) throws NotFoundException;

    Long create(String token, CreateOrUpdateVehicleRequest request);

    Long update(String token, Long vehicleId, CreateOrUpdateVehicleRequest request);

    void delete(String token, Long id);
}
