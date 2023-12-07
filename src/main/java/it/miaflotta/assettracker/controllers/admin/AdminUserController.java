package it.miaflotta.assettracker.controllers.admin;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.request.CreateOrUpdateUserRequest;
import it.miaflotta.assettracker.services.IAdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final IAdminUserService service;

    @PostMapping
    public ResponseEntity<Long> create(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody final CreateOrUpdateUserRequest request) throws NotFoundException {
        Long id = service.create(token, request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Long> update(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @PathVariable Long userId,
                                       @RequestBody final CreateOrUpdateUserRequest request) throws NotFoundException {
        Long id = service.update(token, userId, request);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                    @PathVariable Long id) {
        service.delete(token, id);
        return ResponseEntity.ok().build();
    }
}
