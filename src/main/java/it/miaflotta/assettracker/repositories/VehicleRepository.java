package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByIdIn(List<Long> vehicles);

    @Query("select e from Vehicle e join e.device d where d.serialNumber=:serialNumber")
    Optional<Vehicle> findBySerialNumber(String serialNumber);
}
