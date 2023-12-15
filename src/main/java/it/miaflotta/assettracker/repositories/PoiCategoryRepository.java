package it.miaflotta.assettracker.repositories;

import it.miaflotta.assettracker.models.entities.position.PoiCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiCategoryRepository extends JpaRepository<PoiCategory, Long> {

}
