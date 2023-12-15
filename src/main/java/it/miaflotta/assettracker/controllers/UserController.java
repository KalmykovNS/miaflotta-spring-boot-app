package it.miaflotta.assettracker.controllers;

import it.miaflotta.assettracker.annotations.HandleBindingResult;
import it.miaflotta.assettracker.exteptions.InvalidInputException;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.CreateOrUpdateGuestRequest;
import it.miaflotta.assettracker.models.dto.user.CreateOrUpdateUserRequest;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping("api/v1/users")
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

    @PostMapping("/guest")
    public ResponseEntity<Long> create(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody final @Valid UserDTO request,
                                       @HandleBindingResult BindingResult bindingResult) throws NotFoundException, InvalidInputException {
        Long id = service.create(token, request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/guest/{guestId}")
    public ResponseEntity<Long> update(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @PathVariable Long userId,
                                       @RequestBody final @Valid UserDTO request) throws NotFoundException, InvalidInputException {
        Long id = service.update(token, userId, request);
        return ResponseEntity.ok(id);
    }
}
