package it.miaflotta.assettracker.controllers;

import it.miaflotta.assettracker.models.dto.VehicleDTO;
import it.miaflotta.assettracker.services.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/vehicles")
public class VehicleController {
    private final IVehicleService service;

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> retrievePaymentBaseInfo(@PathVariable Long id) {
        VehicleDTO vehicle = service.findById(id);
        return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/user")
    public ResponseEntity<List<VehicleDTO>> findAllByUser(String token) {
        List<VehicleDTO> vehicles = service.findAllByUser(token);
        return ResponseEntity.ok(vehicles);
    }


}
