package it.miaflotta.assettracker.controllers.admin;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.services.IPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RequiredArgsConstructor
public class PositionController {
    private final IPositionService service;


    @PostMapping
    public void handlePosition(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                               @RequestBody final PositionDTO position) {
        service.handlePosition(token, position);
    }
}
