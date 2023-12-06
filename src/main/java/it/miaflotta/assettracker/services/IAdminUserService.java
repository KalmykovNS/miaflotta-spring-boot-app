package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.UserDTO;

public interface IAdminUserService {
    Long create(String token, UserDTO request);

    Long update(String token, Long userId, UserDTO request);

    void delete(String token, Long id);
}
