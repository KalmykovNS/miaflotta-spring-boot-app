package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.CreateOrUpdateUserRequest;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<UserDTO> findUsers(String searchBy, Pageable pageable);

    UserDTO findByToken(String token);

    UserDTO findById(Long userId) throws NotFoundException;

    User findEntityById(Long userId) throws NotFoundException;

    Long save(User user);

    void delete(Long id) throws NotFoundException;

    void enable(Long id, Boolean enable) throws NotFoundException;

    Long create(String token, UserDTO request) throws NotFoundException;

    Long update(String token,Long userId, UserDTO request) throws NotFoundException;
}
