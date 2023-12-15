package it.miaflotta.assettracker.utils;

import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.position.route.DrivingDistanceDTO;
import it.miaflotta.assettracker.models.dto.position.route.DrivingSpeedDTO;
import it.miaflotta.assettracker.models.dto.position.route.DrivingTimeDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteStatisticDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.time.temporal.ChronoUnit.MINUTES;
@Slf4j
@Component
@RequiredArgsConstructor
public class RouteUtils {
    private final DistanceUtils distanceUtils;

    public List<RouteDTO> calculateSubRoutes(List<PositionDTO> positions, Long limit) {
        List<RouteDTO> routes = new LinkedList<>();
        if (CollectionUtils.isEmpty(positions)) {
            return routes;
        }
        List<PositionDTO> subRoute = new LinkedList<>();
        LocalDateTime startTime = positions.get(0).getDateTime();
        for (int i = 0; i < positions.size(); i++) {
            PositionDTO pos = positions.get(i);
            subRoute.add(pos);
            if (pos.getSpeed() > 0) {
                startTime = positions.get(i).getDateTime();
            }
            if (i == (positions.size() - 1)) {
                routes.add(buildRoute(subRoute, null, routes.size()));
                break;
            }
            LocalDateTime endTime = positions.get(i + 1).getDateTime();
            Duration duration = Duration.between(startTime, endTime);
            if (duration.getSeconds() >= limit) {
                routes.add(buildRoute(subRoute, positions.get(i + 1), routes.size()));
                subRoute = new ArrayList<>();
                startTime = endTime;
            }
        }
        return routes;
    }

    private RouteDTO buildRoute(List<PositionDTO> subRoute, PositionDTO position, int size) {
        RouteDTO route = new RouteDTO();
        route.setPositions(subRoute);
        route.setStatistic(calculateSubRouteStatistic(subRoute, position));
        route.setPositionFrom(subRoute.get(0));
        route.setPositionTo(subRoute.get(subRoute.size() - 1));
        route.setDateFrom(subRoute.get(0).getDateTime().toLocalTime());
        route.setDateTo(subRoute.get(subRoute.size() - 1).getDateTime().toLocalTime());
        Integer idxFrom = size + (size + 1);
        Integer idxTo = idxFrom + 1;
        route.setIndexFrom(idxFrom);
        route.setIndexTo(idxTo);
        return route;
    }

    private RouteStatisticDTO calculateSubRouteStatistic(List<PositionDTO> subRoute, PositionDTO nextSubRouteStartPosition) {
        RouteStatisticDTO statistic = new RouteStatisticDTO();
        DrivingTimeDTO drivingTime = new DrivingTimeDTO();
        LocalTime from = subRoute.get(0).getDateTime().toLocalTime();
        LocalTime to = subRoute.get(subRoute.size() - 1).getDateTime().toLocalTime();
        Duration drivingDuration = Duration.between(from, to);
        LocalTime drivingHours = LocalTime.ofSecondOfDay(drivingDuration.getSeconds());
        drivingTime.setActive(drivingHours);
        if (Objects.nonNull(nextSubRouteStartPosition)) {
            Duration pauseDuration = Duration.between(to, nextSubRouteStartPosition.getDateTime().toLocalTime());
            LocalTime pauseHours = LocalTime.ofSecondOfDay(pauseDuration.getSeconds());
            drivingTime.setPause(pauseHours);
        }
        statistic.setDrivingTime(drivingTime);

        //speed
        List<Integer> speedList = subRoute.stream()
                .map(PositionDTO::getSpeed)
                .collect(Collectors.toList());

        int max = speedList.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = speedList.stream().mapToInt(Integer::intValue).min().orElse(0);
        double avg = speedList.stream().mapToInt(Integer::intValue).average().orElse(0);
        DrivingSpeedDTO speed = new DrivingSpeedDTO(max, min, (int) avg);
        statistic.setSpeed(speed);

        //distance
        double mt = 0;
        for (int i = 0; i < subRoute.size() - 1; i++) {
            double meters = distanceUtils.calculateApproximateDistanceInMeters(subRoute.get(i).getLat().doubleValue(), subRoute.get(i).getLng().doubleValue(),
                    subRoute.get(i + 1).getLat().doubleValue(), subRoute.get(i + 1).getLng().doubleValue());

            mt += meters;
        }

        statistic.setDistance(new DrivingDistanceDTO(distanceUtils.convertFromMetersToKm(mt)));
        return statistic;
    }

    public RouteStatisticDTO calculateRouteStatistic(List<RouteDTO> routes) {
        RouteStatisticDTO routeStatistic = new RouteStatisticDTO();
        DrivingSpeedDTO speed = new DrivingSpeedDTO();
        ArrayList<Integer> speedList = routes.stream()
                .map(r -> r.getPositions().stream()
                        .map(PositionDTO::getSpeed)
                        .filter(pSpeed -> pSpeed > 0)
                        .collect(Collectors.toList()))
                .collect(ArrayList::new, List::addAll, List::addAll);

        int max = speedList.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = speedList.stream().mapToInt(Integer::intValue).min().orElse(0);
        double avg = speedList.stream()
                .mapToDouble(Integer::intValue).average().orElse(0);
        speed.setMax(max);
        speed.setMin(min);
        speed.setAvg((int) avg);
        routeStatistic.setSpeed(speed);

        Double km = 0.0;
        LocalTime tmpTimeDrivingHours = LocalTime.of(0, 0, 0);
        LocalTime tmpTimePauseHours = LocalTime.of(0, 0, 0);
        LocalTime tmpTimeInactiveHours = LocalTime.of(23, 59, 59);
        for (RouteDTO route : routes) {
            km += route.getStatistic().getDistance().getKm();
            LocalTime drivingHours = route.getStatistic().getDrivingTime().getActive();
            tmpTimeDrivingHours = tmpTimeDrivingHours.plusHours(drivingHours.getHour());
            tmpTimeDrivingHours = tmpTimeDrivingHours.plusMinutes(drivingHours.getMinute());
            tmpTimeDrivingHours = tmpTimeDrivingHours.plusSeconds(drivingHours.getSecond());
            long minusActiveMinutes = MINUTES.between(tmpTimeInactiveHours, tmpTimeDrivingHours);
            tmpTimeInactiveHours = LocalTime.MIN.plus(Duration.ofMinutes(minusActiveMinutes));
            LocalTime pauseHours = route.getStatistic().getDrivingTime().getPause();
            if (Objects.nonNull(pauseHours)) {
                tmpTimePauseHours = tmpTimePauseHours.plusHours(pauseHours.getHour());
                tmpTimePauseHours = tmpTimePauseHours.plusMinutes(pauseHours.getMinute());
                tmpTimePauseHours = tmpTimePauseHours.plusSeconds(pauseHours.getSecond());
                long minusPauseMinutes = MINUTES.between(tmpTimeInactiveHours, tmpTimePauseHours);
                tmpTimeInactiveHours = LocalTime.MIN.plus(Duration.ofMinutes(minusPauseMinutes));
            }
        }
        routeStatistic.setDistance(new DrivingDistanceDTO(km));
        DrivingTimeDTO drivingTime = new DrivingTimeDTO();
        drivingTime.setActive(tmpTimeDrivingHours);
        drivingTime.setPause(tmpTimePauseHours);
        drivingTime.setInactive(tmpTimeInactiveHours);
        routeStatistic.setDrivingTime(drivingTime);
        return routeStatistic;
    }
}
