package it.miaflotta.assettracker.models.dto.position.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteCalendarResponse {
    private LocalDate date;
}
