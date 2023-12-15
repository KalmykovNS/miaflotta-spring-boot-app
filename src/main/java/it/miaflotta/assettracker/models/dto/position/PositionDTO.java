package it.miaflotta.assettracker.models.dto.position;

import it.miaflotta.assettracker.enumerations.PositionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO {
    private Long id;

    private Long vehicleId;

    private BigDecimal lat;

    private BigDecimal lng;

    private Integer speed;

    private Integer angle;

    private LocalDateTime dateTime;

    private PositionStatus status;

    private String address;

    private Boolean closingRoute;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
