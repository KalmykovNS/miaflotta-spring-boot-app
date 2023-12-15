package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.dto.position.poi.PoiDTO;
import it.miaflotta.assettracker.models.dto.position.poi.CreateOrUpdatePoiRequest;
import it.miaflotta.assettracker.models.entities.position.Poi;
import it.miaflotta.assettracker.models.entities.position.PoiCategory;

import java.util.List;

public interface IPoiService {
    Long create(String token, CreateOrUpdatePoiRequest req) throws NotFoundException;

    PoiCategory findCategoryById(Long poiCategoryId) throws NotFoundException;

    List<PoiDTO> findAll(String token);

    Long update(String token, Long id, CreateOrUpdatePoiRequest req) throws NotFoundException;

    void delete(String token, Long id) throws NotFoundException;

    Poi findEntityById(Long id) throws NotFoundException;
}
