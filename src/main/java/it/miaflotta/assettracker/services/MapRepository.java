package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.enumerations.MapType;
import it.miaflotta.assettracker.models.entities.MapCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<MapCategory, Long> {
    Optional<MapCategory> findByType(MapType type);
}
