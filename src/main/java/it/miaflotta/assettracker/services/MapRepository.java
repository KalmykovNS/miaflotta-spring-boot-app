package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.entities.MapCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<MapCategory, Long> {
}
