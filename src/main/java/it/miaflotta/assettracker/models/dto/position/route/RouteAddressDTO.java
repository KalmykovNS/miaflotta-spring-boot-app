package it.miaflotta.assettracker.models.dto.position.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteAddressDTO {
    String startAddress;
    String stopAddress;
}
