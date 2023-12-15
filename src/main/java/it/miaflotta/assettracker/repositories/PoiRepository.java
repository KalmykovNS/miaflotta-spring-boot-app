package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
    List<Poi> findAllByUserId(Long id);
}
