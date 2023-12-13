package it.miaflotta.assettracker.models.dto.user;

import it.miaflotta.assettracker.enumerations.UserRole;
import it.miaflotta.assettracker.models.dto.MapCategoryDTO;
import it.miaflotta.assettracker.models.dto.UserContactDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 10, message
            = "Name must be between 1 and 10 characters")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 1, max = 10, message
            = "Surname must be between 1 and 10 characters")
    private String surname;

    private UserRole role;

    private List<UserContactDTO> contacts;

    private List<UserDTO> guests;

    private List<Long> vehicles;

    private MapCategoryDTO map;
}
