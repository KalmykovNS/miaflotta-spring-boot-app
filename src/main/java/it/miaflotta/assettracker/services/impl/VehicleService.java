package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.dto.VehicleDTO;
import it.miaflotta.assettracker.services.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements IVehicleService {
    @Override
    public VehicleDTO findById(Long id) {
        return null;
    }

    @Override
    public List<VehicleDTO> findAllByUser(String token) {
        return null;
    }
}
