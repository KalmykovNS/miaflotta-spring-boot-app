package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.models.entities.User;
import it.miaflotta.assettracker.services.IKeyCloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyCloakService implements IKeyCloakService {

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void enable(User user, Boolean enable) {

    }
}
