package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.mapper.VehicleMapper;
import it.miaflotta.assettracker.models.dto.UserDTO;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.models.entities.Vehicle;
import it.miaflotta.assettracker.repositories.VehicleRepository;
import it.miaflotta.assettracker.services.IUserService;
import it.miaflotta.assettracker.services.IVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {
    private final IUserService userService;
    private final VehicleRepository repo;

    @Override
    public VehicleDTO findById(String token, Long id) throws NotFoundException {
        UserDTO user = userService.findByToken(token);
        if (user.getVehicles().isEmpty() || !user.getVehicles().contains(id)) {
            throw new NotFoundException();
        }
        Vehicle vehicle = repo.findById(id).orElseThrow(NotFoundException::new);
        return VehicleMapper.map(vehicle);
    }

    @Override
    public List<VehicleDTO> findAllByUser(String token) {
        UserDTO user = userService.findByToken(token);
        if (user.getVehicles().isEmpty()) {
            return new LinkedList<>();
        }
        List<Vehicle> vehicles = repo.findByIdIn(user.getVehicles());
        return VehicleMapper.map(vehicles);
    }

    @Override
    public VehicleDTO findByDeviceSerialNumber(String token, String serialNumber) throws NotFoundException {
        throw new NotFoundException();
    }
}
