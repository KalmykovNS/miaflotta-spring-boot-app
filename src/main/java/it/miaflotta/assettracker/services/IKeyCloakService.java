package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.entities.User;

public interface IKeyCloakService {
    void save(User user);

    void delete(User user);

    void enable(User user, Boolean enable);
}
