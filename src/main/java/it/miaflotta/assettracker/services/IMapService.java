package it.miaflotta.assettracker.services;

import it.miaflotta.assettracker.enumerations.MapType;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.entities.MapCategory;

public interface IMapService {
    MapCategory findById(Long mapCategoryId) throws NotFoundException;

    MapCategory findByType(MapType osm) throws NotFoundException;
}
