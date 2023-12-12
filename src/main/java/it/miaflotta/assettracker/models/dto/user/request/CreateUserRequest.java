package it.miaflotta.assettracker.models.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest extends UserDTO {
    @NotNull(message = "Map cannot be null")
    private Long mapCategoryId;

    @Email(message = "Email should be valid")
    private String email;
}
