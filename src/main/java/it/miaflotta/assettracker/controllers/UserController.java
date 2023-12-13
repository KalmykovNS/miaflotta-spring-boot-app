package it.miaflotta.assettracker.controllers;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService service;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findUsers(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                   @RequestParam(required = false, name = "searchBy", defaultValue = "") String searchBy,
                                                   Pageable pageable) {
        Page<UserDTO> page = service.findUsers(searchBy, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> findByToken(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
        UserDTO user = service.findByToken(token);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                            @PathVariable Long userId) throws NotFoundException {
        UserDTO user = service.findById(userId);
        return ResponseEntity.ok(user);
    }
}
