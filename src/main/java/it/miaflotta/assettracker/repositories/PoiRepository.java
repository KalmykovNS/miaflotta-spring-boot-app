package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
}
