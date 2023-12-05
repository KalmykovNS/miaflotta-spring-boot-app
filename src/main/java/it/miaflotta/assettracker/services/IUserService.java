package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<UserDTO> findUsers(String searchBy, Pageable pageable);

    UserDTO findByToken(String token);

    UserDTO findById(Long userId) throws NotFoundException;
}
