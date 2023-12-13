package it.miaflotta.assettracker.controllers.admin;

import it.miaflotta.assettracker.annotations.HandleBindingResult;
import it.miaflotta.assettracker.exteptions.InvalidInputException;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.CreateOrUpdateUserContactRequest;
import it.miaflotta.assettracker.models.dto.user.CreateOrUpdateUserRequest;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.services.IAdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final IAdminUserService service;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findUsers(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                                   @RequestParam(required = false, name = "searchBy", defaultValue = "") String searchBy,
                                                   Pageable pageable) {
        Page<UserDTO> page = service.findUsers(searchBy, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                            @PathVariable Long userId) {
        UserDTO user = service.findUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                       @RequestBody final @Valid CreateOrUpdateUserRequest request,
                                       @HandleBindingResult BindingResult bindingResult) throws NotFoundException, InvalidInputException {
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

    @PostMapping("/contact")
    public ResponseEntity<Long> createContact(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                              @RequestBody final @Valid CreateOrUpdateUserContactRequest request,
                                              @HandleBindingResult BindingResult bindingResult) throws NotFoundException, InvalidInputException {
        Long id = service.createContact(token, request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/contact/{contactId}")
    public ResponseEntity<Long> updateContact(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                              @PathVariable Long contactId,
                                              @RequestBody final CreateOrUpdateUserContactRequest request) throws NotFoundException {
        Long id = service.updateContact(token, contactId, request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/contact/{contactId}")
    public ResponseEntity<Long> deleteContact(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                              @PathVariable Long contactId) throws NotFoundException {
        Long id = service.deleteContact(token, contactId);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
