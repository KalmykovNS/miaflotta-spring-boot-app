package it.miaflotta.assettracker.models.dto.position;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PositionCountDTO {
    private Long tot;
    private LocalDate date;
}
