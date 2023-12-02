package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.VehicleDTO;

import java.util.List;

public interface IVehicleService {
    VehicleDTO findById(Long id);

    List<VehicleDTO> findAllByUser(String token);
}
