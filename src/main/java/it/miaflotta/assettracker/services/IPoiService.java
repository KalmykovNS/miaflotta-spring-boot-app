package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.models.dto.PoiDTO;

import java.util.List;

public interface IPoiService {
    Long create(PoiDTO poi);

    List<PoiDTO> findAll(String token);

    Long update(Long poiId);

    Long delete(Long poiId);
}
