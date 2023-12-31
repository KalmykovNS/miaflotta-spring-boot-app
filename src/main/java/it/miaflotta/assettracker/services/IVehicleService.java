package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.models.entities.Vehicle;

import java.util.List;

public interface IVehicleService {
    VehicleDTO findById(String token, Long id) throws NotFoundException;

    List<VehicleDTO> findAllByUser(String token);

    List<Long> findAllByUserId(Long userId) throws NotFoundException;

    VehicleDTO findByDeviceSerialNumber(String token, String serialNumber) throws NotFoundException;

}
