package it.miaflotta.assettracker.models.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PositionDTO {
    private Long id;

    private BigDecimal lat;

    private BigDecimal lng;

    private Long speed;

    private Long angle;

    private String status;

    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}