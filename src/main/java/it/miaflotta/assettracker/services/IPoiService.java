package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.position.PoiDTO;
import it.miaflotta.assettracker.models.entities.Poi;

import java.util.List;

public interface IPoiService {
    Long create(String token, PoiDTO poi);

    List<PoiDTO> findAll(String token);

    Long update(String token, Long poiId);

    Long delete(String token, Long poiId);

    Long save(Poi entity);
}
