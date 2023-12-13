package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.CreateOrUpdateUserContactRequest;
import it.miaflotta.assettracker.models.dto.user.CreateOrUpdateUserRequest;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAdminUserService {
    Long create(String token, CreateOrUpdateUserRequest request) throws NotFoundException;

    Long update(String token, Long userId, CreateOrUpdateUserRequest request) throws NotFoundException;

    void delete(String token, Long id);

    Long createContact(String token, CreateOrUpdateUserContactRequest request);

    Long updateContact(String token, Long contactId, CreateOrUpdateUserContactRequest request);

    Long deleteContact(String token, Long contactId);

    Page<UserDTO> findUsers(String searchBy, Pageable pageable);

    UserDTO findUser(Long userId);
}
