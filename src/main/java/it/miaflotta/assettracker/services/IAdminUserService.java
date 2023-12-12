package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.request.CreateUserRequest;

public interface IAdminUserService {
    Long create(String token, CreateUserRequest request) throws NotFoundException;

    Long update(String token, Long userId, CreateUserRequest request) throws NotFoundException;

    void delete(String token, Long id);
}
