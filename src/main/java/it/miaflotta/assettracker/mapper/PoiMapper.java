package it.miaflotta.assettracker.mapper;

import it.miaflotta.assettracker.models.dto.position.poi.CreateOrUpdatePoiRequest;
import it.miaflotta.assettracker.models.dto.position.poi.PoiCategoryDTO;
import it.miaflotta.assettracker.models.dto.position.poi.PoiDTO;
import it.miaflotta.assettracker.models.entities.position.Poi;
import it.miaflotta.assettracker.models.entities.position.PoiCategory;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PoiMapper {
    public static Poi map(PoiDTO dto, PoiCategory category) {
        return new Poi(dto.getName(), dto.getUserId(), category, dto.getLat(), dto.getLng(), dto.getIconName(), dto.getDescription());
    }

    public static PoiDTO map(Poi entity) {
        return new PoiDTO(entity.getName(), entity.getUserId(), mapCategory(entity.getCategory()), entity.getLat(), entity.getLng(), entity.getIconName(), entity.getDescription());
    }

    private static PoiCategoryDTO mapCategory(PoiCategory category) {
        return (Objects.nonNull(category))
                ? new PoiCategoryDTO(category.getId(), category.getName())
                : null;
    }

    public static List<PoiDTO> map(List<Poi> entities) {
        return (!CollectionUtils.isEmpty(entities))
                ? entities.stream().map(PoiMapper::map).collect(Collectors.toList())
                : new LinkedList<>();
    }


    public static void map(Poi poi, CreateOrUpdatePoiRequest req, PoiCategory category) {
        if (Objects.nonNull(category)) {
            poi.setCategory(category);
        }

        if (Objects.nonNull(req.getName())) {
            poi.setName(req.getName());
        }

        if (Objects.nonNull(req.getDescription())) {
            poi.setDescription(req.getDescription());
        }

        if (Objects.nonNull(req.getIconName())) {
            poi.setIconName(req.getIconName());
        }
    }
}
