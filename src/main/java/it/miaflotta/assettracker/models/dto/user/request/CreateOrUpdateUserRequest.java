package it.miaflotta.assettracker.models.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrUpdateUserRequest extends UserDTO {
    @NotNull(message = "Map cannot be null")
    private Long mapCategoryId;
}
