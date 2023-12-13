package it.miaflotta.assettracker.controllers.admin;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.vehicle.CreateOrUpdateVehicleRequest;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.services.IAdminVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/vehicles")
@RequiredArgsConstructor
public class AdminVehicleController {
    private final IAdminVehicleService service;

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> findUsers(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                      @RequestParam(required = false, name = "searchBy", defaultValue = "") String searchBy,
                                                      Pageable pageable) {
        Page<VehicleDTO> page = service.find(searchBy, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDTO> findUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                               @PathVariable Long vehicleId) throws NotFoundException {
        VehicleDTO vehicle = service.find(vehicleId);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody final CreateOrUpdateVehicleRequest request) {
        Long id = service.create(token, request);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Long> update(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @PathVariable Long vehicleId,
                                       @RequestBody final CreateOrUpdateVehicleRequest request) {
        Long id = service.update(token, vehicleId, request);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                    @PathVariable Long id) {
        service.delete(token, id);
        return ResponseEntity.ok().build();
    }
}
