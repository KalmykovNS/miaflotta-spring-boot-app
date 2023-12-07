package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.user.request.CreateOrUpdateUserRequest;

public interface IAdminUserService {
    Long create(String token, CreateOrUpdateUserRequest request) throws NotFoundException;

    Long update(String token, Long userId, CreateOrUpdateUserRequest request) throws NotFoundException;

    void delete(String token, Long id);
}
