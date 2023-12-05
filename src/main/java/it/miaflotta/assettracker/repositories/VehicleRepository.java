package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByIdIn(List<Long> vehicles);
}
