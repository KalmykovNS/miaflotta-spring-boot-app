package it.miaflotta.assettracker.models.dto.user;

import lombok.Data;

@Data
public class UpdateUserRequest extends UserDTO {
    private Long mapCategoryId;
}
