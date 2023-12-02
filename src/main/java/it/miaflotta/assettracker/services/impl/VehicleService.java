package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.dto.VehicleDTO;
import it.miaflotta.assettracker.repositories.VehicleRepository;
import it.miaflotta.assettracker.services.IVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {
    private final VehicleRepository repo;

    @Override
    public VehicleDTO findById(Long id) {
        return null;
    }

    @Override
    public List<VehicleDTO> findAllByUser(String token) {
        return null;
    }
}
