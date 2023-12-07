package it.miaflotta.assettracker.models.dto.position;

import it.miaflotta.assettracker.enumerations.PositionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PositionDTO {
    private Long id;

    private BigDecimal lat;

    private BigDecimal lng;

    private Integer speed;

    private Integer angle;

    private PositionStatus status;

    private String address;

    private Boolean closingRoute;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}