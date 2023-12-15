package it.miaflotta.assettracker.controllers;

import it.miaflotta.assettracker.annotations.HandleBindingResult;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.position.poi.CreateOrUpdatePoiRequest;
import it.miaflotta.assettracker.models.dto.position.poi.PoiDTO;
import it.miaflotta.assettracker.services.IPoiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/positions/poi")
@RequiredArgsConstructor
public class PoiController {
    private final IPoiService service;

    @GetMapping
    public ResponseEntity<List<PoiDTO>> findAll(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
        List<PoiDTO> poiList = service.findAll(token);
        return ResponseEntity.ok(poiList);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody final @Valid CreateOrUpdatePoiRequest request,
                                       @HandleBindingResult BindingResult bindingResult) throws NotFoundException {
        Long id = service.create(token, request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @PathVariable(name = "id") Long poiId, @RequestBody final CreateOrUpdatePoiRequest request) throws NotFoundException {
        Long id = service.update(token, poiId, request);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @PathVariable(name = "id") Long poiId) throws NotFoundException {
        service.delete(token, poiId);
        return ResponseEntity.ok().build();
    }
}
