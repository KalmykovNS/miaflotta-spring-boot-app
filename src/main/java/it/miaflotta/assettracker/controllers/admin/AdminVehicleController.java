package it.miaflotta.assettracker.controllers.admin;

import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.services.IAdminVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/vehicles")
@RequiredArgsConstructor
public class AdminVehicleController {
    private final IAdminVehicleService service;

    @PostMapping
    public void create(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                       @RequestBody final VehicleDTO request) {
        service.create(token, request);
    }

    @PutMapping("/{id}")
    public void update(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                       @PathVariable Long id,
                       @RequestBody final VehicleDTO request) {
        service.update(token, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                       @PathVariable Long id) {
        service.delete(token, id);
    }
}
