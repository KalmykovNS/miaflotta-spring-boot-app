package it.miaflotta.assettracker.controllers;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.services.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/vehicles")
public class VehicleController {
    private final IVehicleService service;

    /**
     * Returns a vehicle by id
     *
     * @param token is a bearer auth user's token
     * @param id    is a unique vehicle's identifier
     * @return VehicleDTO vehicles of a given user by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                               @PathVariable Long id) throws NotFoundException {
        VehicleDTO vehicle = service.findById(token, id);
        return ResponseEntity.ok(vehicle);
    }

    /**
     * Returns a user's vehicle by user token
     *
     * @param token is a bearer auth user's token
     * @return List<VehicleDTO> list of vehicles of a given user
     */
    @GetMapping("/user")
    public ResponseEntity<List<VehicleDTO>> findAllByUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
        List<VehicleDTO> vehicles = service.findAllByUser(token);
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Method for retrieving a vihicle dto object using its device's serial number
     * ones user is authenticated by token
     * @param token is a bearer auth user's token
     * @param serialNumber is a device's serial number
     * @return VehicleDTO list of vehicles of a given user
     */
    @GetMapping("/device/serial-number/{serialNumber}")
    public ResponseEntity<VehicleDTO> findVehicle(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token, @PathVariable String serialNumber) throws NotFoundException {
        VehicleDTO vehicle = service.findByDeviceSerialNumber(token, serialNumber);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }
}
