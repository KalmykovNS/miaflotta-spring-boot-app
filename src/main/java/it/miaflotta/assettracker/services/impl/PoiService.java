package it.miaflotta.assettracker.services.impl;

import it.miaflotta.assettracker.annotations.MethodExecutionTime;
import it.miaflotta.assettracker.exteptions.NotFoundException;
import it.miaflotta.assettracker.mapper.PoiMapper;
import it.miaflotta.assettracker.models.dto.position.poi.CreateOrUpdatePoiRequest;
import it.miaflotta.assettracker.models.dto.position.poi.PoiDTO;
import it.miaflotta.assettracker.models.dto.user.UserDTO;
import it.miaflotta.assettracker.models.entities.Poi;
import it.miaflotta.assettracker.models.entities.PoiCategory;
import it.miaflotta.assettracker.repositories.PoiCategoryRepository;
import it.miaflotta.assettracker.repositories.PoiRepository;
import it.miaflotta.assettracker.services.IPoiService;
import it.miaflotta.assettracker.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoiService implements IPoiService {
    private final IUserService userService;
    private final PoiRepository poiRepo;
    private final PoiCategoryRepository categoryRepo;
    private final MessageSource messageSource;

    @Override
    @MethodExecutionTime
    public Long create(String token, CreateOrUpdatePoiRequest request) throws NotFoundException {
        UserDTO user = userService.findByToken(token);
        request.setUserId(user.getId());
        PoiCategory category = findCategoryById(request.getPoiCategoryId());
        Poi poi = PoiMapper.map(request, category);
        return poiRepo.save(poi).getId();
    }

    @Override
    @MethodExecutionTime
    public List<PoiDTO> findAll(String token) {
        UserDTO user = userService.findByToken(token);
        List<Poi> list = poiRepo.findAllByUserId(user.getId());
        return PoiMapper.map(list);
    }

    @Override
    @MethodExecutionTime
    public Long update(String token, Long poiId, CreateOrUpdatePoiRequest request) throws NotFoundException {
        Poi poi = findEntityById(poiId);
        PoiCategory category = (Objects.nonNull(request.getPoiCategoryId())) ? findCategoryById(request.getPoiCategoryId()) : null;
        PoiMapper.map(poi, request, category);
        return poiRepo.save(poi).getId();
    }

    @Override
    @MethodExecutionTime
    public void delete(String token, Long id) throws NotFoundException {
        Poi poi = findEntityById(id);
        poiRepo.delete(poi);
    }

    @Override
    public Poi findEntityById(Long id) throws NotFoundException {
        log.info("Search p.o.i. by id: " + id);
        final String errorMessage = messageSource.getMessage("exception.poi.notfound", null, Locale.ITALIAN);
        return poiRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(errorMessage));
    }

    @Override
    public PoiCategory findCategoryById(Long poiCategoryId) throws NotFoundException {
        log.info("Search p.o.i. category by id: " + poiCategoryId);
        final String errorMessage = messageSource.getMessage("exception.poi.category.notfound", null, Locale.ITALIAN);
        return categoryRepo.findById(poiCategoryId)
                .orElseThrow(() -> new NotFoundException(errorMessage));
    }
}
