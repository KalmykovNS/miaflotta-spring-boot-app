package it.miaflotta.assettracker.mapper;

import it.miaflotta.assettracker.models.dto.position.PoiDTO;
import it.miaflotta.assettracker.models.entities.Poi;

import java.util.Objects;

public class PoiMapper {
    public static Poi map(PoiDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }

        return new Poi(dto.getName() , null, 1L, dto.getLat(), dto.getLng(), dto.getIconName(), dto.getDescription());
    }
}
