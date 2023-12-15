package it.miaflotta.assettracker.mapper;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteCalendarResponse;
import it.miaflotta.assettracker.models.dto.position.route.RouteDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteResponse;
import it.miaflotta.assettracker.models.dto.position.route.RouteStatisticDTO;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.models.entities.position.IPositionCalendar;
import it.miaflotta.assettracker.models.entities.position.Position;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PositionMapper {
    public PositionDTO map(Position position) {
        if (Objects.isNull(position)) {
            return null;
        }

        return new PositionDTO(position.getId(), null, null, null, null, null, null, null, null, null, null, null);
    }

    public static List<RouteCalendarResponse> mapCalendars(List<IPositionCalendar> entities) {
        List<RouteCalendarResponse> calendars = new LinkedList<>();
        if (CollectionUtils.isEmpty(entities))
            return calendars;

        for (IPositionCalendar entity : entities) {
            RouteCalendarResponse calendar = new RouteCalendarResponse();
            calendar.setDate(entity.getCalendarDate());
            calendars.add(calendar);
        }

        return calendars;
    }

    public static RouteResponse mapRouteResponse(VehicleDTO vehicleDTO, LocalDate calendarDate, List<RouteDTO> routes, RouteStatisticDTO routeStatistic) {
        RouteResponse response = new RouteResponse();
        response.setVehicle(vehicleDTO);
        response.setDate(calendarDate);
        response.setRoutes(routes);
        response.setStatistic(routeStatistic);
        return response;
    }

    public List<PositionDTO> mapRoutes(List<Position> positionEntities) {
        if (CollectionUtils.isEmpty(positionEntities)) {
            return new LinkedList<>();
        }

        return positionEntities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
