package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;

import java.util.List;

public interface IVehicleService {
    VehicleDTO findById(String token, Long id) throws NotFoundException;

    List<VehicleDTO> findAllByUser(String token);
}
