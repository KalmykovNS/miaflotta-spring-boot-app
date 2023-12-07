package it.miaflotta.assettracker.models.dto.user.request;

import it.miaflotta.assettracker.models.dto.UserDTO;
import lombok.Data;

@Data
public class CreateOrUpdateUserRequest extends UserDTO {
    private Long mapCategoryId;
}
