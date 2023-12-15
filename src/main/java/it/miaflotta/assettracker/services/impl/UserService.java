package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.annotations.MethodExecutionTime;
import it.miaflotta.assettracker.enumerations.MapType;
import it.miaflotta.assettracker.enumerations.UserRole;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.mapper.UserMapper;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.models.entities.MapCategory;
import it.miaflotta.assettracker.models.entities.User;
import it.miaflotta.assettracker.repositories.UserRepository;
import it.miaflotta.assettracker.services.IKeyCloakService;
import it.miaflotta.assettracker.services.IMapService;
import it.miaflotta.assettracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final MessageSource messageSource;
    private final IKeyCloakService keyCloakService;
    private final IMapService mapService;
    private final UserRepository repo;

    @Override
    @MethodExecutionTime
    public Page<UserDTO> findUsers(String searchBy, Pageable pageable) {
        List<UserDTO> list = new ArrayList<>();
        Page<User> entities = repo.findAllByAttributePageable(searchBy, pageable);
        return new PageImpl<>(list, pageable, entities.getTotalElements());
    }

    @Override
    @MethodExecutionTime
    public UserDTO findByToken(String token) {
//        UserRepresentation ur = keyCloakService.findByToken(token);
        String uuid = null;
        User user = repo.findByUuid(uuid);
        return UserMapper.map(user);
    }

    @Override
    @MethodExecutionTime
    public UserDTO findById(Long id) throws NotFoundException {
        User user = findEntityById(id);
        return UserMapper.map(user);
    }

    @Override
    @MethodExecutionTime
    public User findEntityById(Long id) throws NotFoundException {
        log.info("Search user by id: " + id);
        final String errorMessage = messageSource.getMessage("exception.user.notfound", null, Locale.ITALIAN);
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(errorMessage));
    }

    @Override
    @Transactional
    @MethodExecutionTime
    public Long save(User user) {
        User entity = repo.save(user);
        keyCloakService.save(user);
        return entity.getId();
    }

    @Override
    @Transactional
    @MethodExecutionTime
    public void delete(Long id) throws NotFoundException {
        User user = findEntityById(id);
        repo.delete(user);
        keyCloakService.delete(user);
    }

    @Override
    @MethodExecutionTime
    public void enable(Long id, Boolean enable) throws NotFoundException {
        log.info("Enable user by id: " + id + " enable: " + enable);
        User user = findEntityById(id);
        keyCloakService.enable(user, enable);
    }

    @Override
    @MethodExecutionTime
    public Long create(String token, UserDTO request) throws NotFoundException {
        MapCategory map = mapService.findByType(MapType.OSM);
        User user = UserMapper.map(request.getName(), request.getSurname(), UserRole.GUEST, null, map);
        return save(user);
    }

    @Override
    @MethodExecutionTime
    public Long update(String token, Long userId, UserDTO request) throws NotFoundException {
        User user = findEntityById(userId);
        UserMapper.map(user, request.getName(), request.getSurname(), request.getRole(), null, user.getMap());
        if (Objects.nonNull(request.getEnabled())) {
            enable(user.getId(), request.getEnabled());
        }
        return save(user);
    }
}
