package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.mapper.PoiMapper;
import it.miaflotta.assettracker.models.dto.position.PoiDTO;
import it.miaflotta.assettracker.models.entities.Poi;
import it.miaflotta.assettracker.repositories.PoiRepository;
import it.miaflotta.assettracker.services.IPoiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoiService implements IPoiService {
    private final PoiRepository repo;

    @Override
    public Long create(String token, PoiDTO poiDto) {
        Poi entity = PoiMapper.map(poiDto);
        return save(entity);
    }

    @Override
    public List<PoiDTO> findAll(String token) {
        return null;
    }

    @Override
    public Long update(String token, Long poiId) {
        return null;
    }

    @Override
    public Long delete(String token, Long poiId) {
        return null;
    }

    @Override
    public Long save(Poi entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return repo.save(entity).getId();
    }
}
