package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;

public interface IAdminVehicleService {
    Long create(String token, VehicleDTO request);

    Long update(String token, VehicleDTO request);

    void delete(String token, Long id);
}
