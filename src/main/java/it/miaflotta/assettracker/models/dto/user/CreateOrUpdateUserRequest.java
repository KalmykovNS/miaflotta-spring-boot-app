package it.miaflotta.assettracker.models.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrUpdateUserRequest extends UserDTO {
    @NotNull(message = "Map cannot be null")
    private Long mapCategoryId;

    @Email(message = "Email should be valid")
    private String email;
}
