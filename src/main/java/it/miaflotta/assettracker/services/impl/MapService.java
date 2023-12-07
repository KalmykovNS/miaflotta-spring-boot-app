package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.entities.MapCategory;
import it.miaflotta.assettracker.services.IMapService;
import it.miaflotta.assettracker.services.MapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService implements IMapService {
    private final MapRepository repo;

    @Override
    public MapCategory findById(Long id) throws NotFoundException {
        return repo.findById(id).orElseThrow(() -> new NotFoundException());
    }
}
