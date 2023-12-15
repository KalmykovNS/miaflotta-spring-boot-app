package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.annotations.MethodExecutionTime;
import it.miaflotta.assettracker.enumerations.MapType;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.models.entities.MapCategory;
import it.miaflotta.assettracker.services.IMapService;
import it.miaflotta.assettracker.services.MapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService implements IMapService {
    private final MessageSource messageSource;
    private final MapRepository repo;

    @Override
    @MethodExecutionTime
    public MapCategory findById(Long id) throws NotFoundException {
        log.info("Search map category by id: " + id);
        String message = messageSource.getMessage("exception.map.notfound", null, Locale.ITALIAN);
        return repo.findById(id).orElseThrow(() -> new NotFoundException(message));
    }

    @Override
    @MethodExecutionTime
    public MapCategory findByType(MapType type) throws NotFoundException {
        log.info("Search map category by type: " + type.toString());
        String message = messageSource.getMessage("exception.map.notfound", null, Locale.ITALIAN);
        return repo.findByType(type).orElseThrow(() -> new NotFoundException(message));
    }
}
