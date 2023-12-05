package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.mapper.UserMapper;
import it.miaflotta.assettracker.models.dto.UserDTO;
import it.miaflotta.assettracker.models.entities.User;
import it.miaflotta.assettracker.repositories.UserRepository;
import it.miaflotta.assettracker.services.IKeyCloakService;
import it.miaflotta.assettracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IKeyCloakService keyCloakService;
    private final UserRepository repo;

    @Override
    public Page<UserDTO> findUsers(String searchBy, Pageable pageable) {
        return null;
    }

    @Override
    public UserDTO findByToken(String token) {
//        UserRepresentation ur = keyCloakService.findByToken(token);
        String uuid = null;
        User user = repo.findByUuid(uuid);
        return UserMapper.map(user);
    }

    @Override
    public UserDTO findById(Long id) throws NotFoundException {
        User user = findEntityById(id);
        return UserMapper.map(user);
    }

    private User findEntityById(Long id) throws NotFoundException {
       return repo.findById(id).orElseThrow(() ->new NotFoundException());
    }
}
