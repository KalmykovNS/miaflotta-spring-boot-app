package it.miaflotta.assettracker.mapper;

import it.miaflotta.assettracker.enumerations.UserRole;
import it.miaflotta.assettracker.models.dto.user.request.UserDTO;
import it.miaflotta.assettracker.models.entities.MapCategory;
import it.miaflotta.assettracker.models.entities.User;
import it.miaflotta.assettracker.models.entities.UserContact;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class UserMapper {
    public static UserDTO map(User user) {
        return null;
    }

    public static User map(String name, String surname, UserRole role, List<UserContact> contacts, MapCategory map) {
        User user =  new User();
        user.setName(name);
        user.setSurname(surname);
        user.setRole(role);
        user.setMap(map);
        return user;
    }

    public static void map(User user, String name, String surname, UserRole role, List<UserContact> contacts, MapCategory map) {
        if (Objects.nonNull(name)) {
            user.setName(name);
        }

        if (Objects.nonNull(surname)) {
            user.setSurname(surname);
        }

        if (Objects.nonNull(role)) {
            user.setRole(role);
        }

        if (!CollectionUtils.isEmpty(contacts)) {
            user.getContacts().addAll(contacts);
        }

        if (Objects.nonNull(map)) {
            user.setMap(map);
        }
    }
}
