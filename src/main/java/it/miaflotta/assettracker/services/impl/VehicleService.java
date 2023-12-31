package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.mapper.VehicleMapper;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.models.entities.BaseEntity;
import it.miaflotta.assettracker.models.entities.Vehicle;
import it.miaflotta.assettracker.repositories.VehicleRepository;
import it.miaflotta.assettracker.services.IUserService;
import it.miaflotta.assettracker.services.IVehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {
    private final MessageSource messageSource;
    private final IUserService userService;
    private final VehicleRepository repo;

    @Override
    public VehicleDTO findById(String token, Long id) throws NotFoundException {
        UserDTO user = userService.findByToken(token);
        if (user.getVehicles().isEmpty() || !user.getVehicles().contains(id)) {
            throw new NotFoundException(messageSource.getMessage("exception.vehicle.notfound", null, Locale.ITALIAN));
        }
        Vehicle vehicle = repo.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("exception.vehicle.notfound", null, Locale.ITALIAN)));
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
    public List<Long> findAllByUserId(Long userId) throws NotFoundException {
        UserDTO user = userService.findById(userId);
        if (user.getVehicles().isEmpty()) {
            return new LinkedList<>();
        }

        return repo.findByIdIn(user.getVehicles()).stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO findByDeviceSerialNumber(String token, String serialNumber) throws NotFoundException {
        Vehicle vehicle = repo.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("exception.vehicle.notfound", null, Locale.ITALIAN)));
        return VehicleMapper.map(vehicle);
    }


}
