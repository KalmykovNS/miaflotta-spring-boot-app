package it.miaflotta.assettracker.controllers.admin;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.position.PositionCountDTO;
import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.services.IAdminPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("api/v1/admin/positions")
@RequiredArgsConstructor
public class PositionController {
    private final IAdminPositionService service;

    @PostMapping("/handle")
    public void handlePosition(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                               @RequestBody final PositionDTO position) {
        service.handlePosition(token, position);
    }

    @GetMapping("/all")
    public ResponseEntity<PositionCountDTO> count(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
        PositionCountDTO count = service.count(token);
        return new ResponseEntity(count, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<PositionCountDTO>> findUserPositions(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                                    @RequestParam(name = "userId", required = false) Long userId) {
        List<PositionCountDTO> positions = service.findByUser(token, userId);
        return ResponseEntity.ok(positions);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserPositions(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                 @RequestParam(name = "userId", required = false) Long userId,
                                                 @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws NotFoundException {
        service.deleteByUser(token, userId, date);
        return ResponseEntity.ok().build();
    }
}
