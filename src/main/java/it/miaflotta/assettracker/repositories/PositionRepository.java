package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.position.Position;
import it.miaflotta.assettracker.models.entities.position.IPositionCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    void deleteByVehicleIdInAndDateTimeBefore(List<Long> ids, LocalDateTime dateTime);

    void deleteByVehicleIdIn(List<Long> ids);

    Position findTop1ByVehicleIdOrderByDateTimeDesc(Long id);

    @Query("SELECT DATE(p.dateTime) as calendarDate " +
            "from Position p " +
            "where p.vehicleId =:vehicleId " +
            "group by DATE(p.dateTime) " +
            "order by calendarDate desc")
    List<IPositionCalendar> findPositionCalendarsByVehicleId(Long vehicleId);

    @Query("select p from Position p " +
            "where p.vehicleId =:vehicleId and DATE(p.dateTime) = DATE(:calendarDate) " +
            "order by p.dateTime asc")
    List<Position> findAllByVehicleIdAndDateOrderByDateTimeAsc(Long vehicleId, LocalDate calendarDate);
}
