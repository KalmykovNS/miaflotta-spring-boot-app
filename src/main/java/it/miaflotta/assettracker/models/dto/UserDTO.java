package it.miaflotta.assettracker.models.dto;

import it.miaflotta.assettracker.enumerations.MapType;
import it.miaflotta.assettracker.enumerations.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String name;

    private String surname;

    private UserRole role;

    private MapType mapType;

    private List<UserContactDTO> contacts;

    private List<UserDTO> guests;

    private List<Long> vehicles;
}
