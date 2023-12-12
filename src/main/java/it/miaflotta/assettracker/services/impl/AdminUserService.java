package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.mapper.UserMapper;
import it.miaflotta.assettracker.models.dto.user.request.CreateOrUpdateUserRequest;
import it.miaflotta.assettracker.models.entities.MapCategory;
import it.miaflotta.assettracker.models.entities.User;
import it.miaflotta.assettracker.services.IAdminUserService;
import it.miaflotta.assettracker.services.IMapService;
import it.miaflotta.assettracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminUserService implements IAdminUserService {
    private final IMapService mapService;
    private final IUserService userService;

    @Override
    public Long create(String token, CreateOrUpdateUserRequest request) throws NotFoundException {
        MapCategory map = mapService.findById(request.getMapCategoryId());
        User user = UserMapper.map(request.getName(), request.getSurname(), request.getRole(), null, map);
        return userService.save(user);
    }

    @Override
    public Long update(String token, Long id, CreateOrUpdateUserRequest request) throws NotFoundException {
        User user = userService.findEntityById(id);
        MapCategory map = null;
        if (Objects.nonNull(request.getMapCategoryId())) {
            map = mapService.findById(request.getMapCategoryId());
        }
        UserMapper.map(user, request.getName(), request.getSurname(), request.getRole(), null, map);
        return userService.save(user);
    }

    @Override
    public void delete(String token, Long id) {
        userService.delete(id);
    }
}
