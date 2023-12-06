package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.dto.UserDTO;
import it.miaflotta.assettracker.services.IAdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService implements IAdminUserService {
    @Override
    public Long create(String token, UserDTO request) {
        return null;
    }

    @Override
    public Long update(String token, Long userId, UserDTO request) {
        return null;
    }

    @Override
    public void delete(String token, Long id) {

    }
}
