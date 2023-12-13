package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    void deleteByVehicleIdInAndDateTimeBefore(List<Long> ids, LocalDateTime dateTime);

    void deleteByVehicleIdIn(List<Long> ids);
}
