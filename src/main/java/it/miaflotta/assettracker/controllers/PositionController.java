package it.miaflotta.assettracker.controllers;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteCalendarResponse;
import it.miaflotta.assettracker.services.IPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.miaflotta.assettracker.models.dto.position.route.RouteResponse;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/vehicle/{id}/last")
    public ResponseEntity<PositionDTO> findLast(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                @PathVariable Long id) {
        PositionDTO position = service.findLast(id);
        return ResponseEntity.ok(position);
    }

    @GetMapping("/vehicle/{id}/routes")
    public ResponseEntity<RouteResponse> findRoutes(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                    @PathVariable Long id,
                                                    @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        RouteResponse response = service.findRoutes(id, date);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicle/{id}/routes/calendars")
    public ResponseEntity<List<RouteCalendarResponse>> findRoutesCalendars(@PathVariable Long id) {
        List<RouteCalendarResponse> calendars = service.findRoutesCalendars(id);
        return new ResponseEntity<>(calendars, HttpStatus.OK);
    }
}
