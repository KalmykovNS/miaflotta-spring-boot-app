package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.dto.UserDTO;
import it.miaflotta.assettracker.repositories.PoiRepository;
import it.miaflotta.assettracker.repositories.UserRepository;
import it.miaflotta.assettracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository repo;

    @Override
    public Page<UserDTO> findUsers(String searchBy, Pageable pageable) {
        return null;
    }

    @Override
    public UserDTO findByToken(String token) {
        return null;
    }

    @Override
    public UserDTO findById(Long userId) {
        return null;
    }
}
