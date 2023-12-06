package it.miaflotta.assettracker.controllers.admin;

import it.miaflotta.assettracker.models.dto.UserDTO;
import it.miaflotta.assettracker.services.IAdminUserService;
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
@RequestMapping("api/v1/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final IAdminUserService service;

    @PostMapping
    public void create(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                       @RequestBody final UserDTO request) {
        Long id = service.create(token, request);
    }

    @PutMapping("/{userId}")
    public void update(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                       @PathVariable Long userId,
                       @RequestBody final UserDTO request) {
        Long id = service.update(token, userId, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                       @PathVariable Long id) {
        service.delete(token, id);
    }
}
