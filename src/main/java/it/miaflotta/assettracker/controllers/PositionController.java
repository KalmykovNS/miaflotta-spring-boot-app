package it.miaflotta.assettracker.controllers;

import it.miaflotta.assettracker.models.dto.PositionDTO;
import it.miaflotta.assettracker.models.dto.response.RouteResponse;
import it.miaflotta.assettracker.services.IPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("v1/positions")
@RequiredArgsConstructor
public class PositionController {
    private final IPositionService service;

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> findById(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                @PathVariable Long id) {
        PositionDTO position = service.findById(id);
        return ResponseEntity.ok(position);
    }

    @GetMapping("/routes/vehicle/{id}")
    public ResponseEntity<RouteResponse> findRoutes(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                    @PathVariable Long id) {
        RouteResponse response = service.findRoutes(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Only for test scope
     * */
    @PostMapping
    public void handlePosition(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                               @RequestBody final PositionDTO position) {
        service.handlePosition(token, position);
    }
}
