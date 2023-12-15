package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.annotations.MethodExecutionTime;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.mapper.PositionMapper;
import it.miaflotta.assettracker.models.dto.position.PositionDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteCalendarResponse;
import it.miaflotta.assettracker.models.dto.position.route.RouteDTO;
import it.miaflotta.assettracker.models.dto.position.route.RouteResponse;
import it.miaflotta.assettracker.models.dto.position.route.RouteStatisticDTO;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.models.dto.vehicle.VehicleDTO;
import it.miaflotta.assettracker.models.entities.position.IPositionCalendar;
import it.miaflotta.assettracker.models.entities.position.Position;
import it.miaflotta.assettracker.repositories.PositionRepository;
import it.miaflotta.assettracker.services.IPositionService;
import it.miaflotta.assettracker.services.IUserService;
import it.miaflotta.assettracker.services.IVehicleService;
import it.miaflotta.assettracker.utils.RouteUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionService implements IPositionService {
    private final MessageSource messageSource;
    private final IUserService userService;
    private final IVehicleService vehicleService;
    private final PositionRepository repo;
    private final PositionMapper mapper;
    private final RouteUtils routeUtils;

    @Override
    @MethodExecutionTime
    public PositionDTO findById(Long id) throws NotFoundException {
        Position position = findEntityById(id);
        return mapper.map(position);
    }

    @Override
    public Position findEntityById(Long id) throws NotFoundException {
        log.info("Search position by id: " + id);
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(messageSource.getMessage("exception.position.notfound", null, Locale.ITALIAN)));
    }

    @Override
    @MethodExecutionTime
    public List<PositionDTO> findLastPositions(String token, List<Long> vehicleIds) {
        UserDTO user = userService.findByToken(token);
        vehicleIds.retainAll(user.getVehicles());
        List<PositionDTO> lastPositions = new LinkedList<>();
        for (Long vehicleId : vehicleIds) {
            lastPositions.add(findLastByVehicleId(vehicleId));
        }
        return lastPositions.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    @MethodExecutionTime
    public PositionDTO findLastByVehicleId(Long vehicleId) {
        log.info("Search last position vehicle id: " + vehicleId);
        Position position = repo.findTop1ByVehicleIdOrderByDateTimeDesc(vehicleId);
        PositionDTO positionDTO = mapper.map(position);
        if (Objects.nonNull(positionDTO)) {
            positionDTO.setVehicleId(vehicleId);
            return positionDTO;
        }

        log.info("Position not found");
        return null;
    }

    @Override
    @MethodExecutionTime
    public RouteResponse findRoutes(String token, Long vehicleId, LocalDate date) throws NotFoundException {
        log.info("Search routes vehicleId: " + vehicleId + " date: " + date);
        VehicleDTO vehicleDTO = vehicleService.findById(token, vehicleId);
        LocalDate calendarDate = (Objects.isNull(date)) ? findEntityById(vehicleDTO.getId()).getDateTime().toLocalDate() : date;
        List<Position> positionEntities = repo.findAllByVehicleIdAndDateOrderByDateTimeAsc(vehicleId, calendarDate);
        List<RouteDTO> routes =
                routeUtils.calculateSubRoutes(mapper.mapRoutes(positionEntities), vehicleDTO.getDevice().getOffModeInSec());

        RouteStatisticDTO routeStatistic =
                routeUtils.calculateRouteStatistic(routes);

        return PositionMapper.mapRouteResponse(vehicleDTO, calendarDate, routes, routeStatistic);
    }

    @Override
    @MethodExecutionTime
    public List<RouteCalendarResponse> findRoutesCalendars(Long vehicleId) {
        List<IPositionCalendar> calendarEntities = repo.findPositionCalendarsByVehicleId(vehicleId);
        return PositionMapper.mapCalendars(calendarEntities);
    }
}
