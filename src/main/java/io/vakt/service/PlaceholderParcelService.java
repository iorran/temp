package io.vakt.service;

import io.vakt.domain.PlaceholderParcel;
import io.vakt.repository.PlaceholderParcelRepository;
import io.vakt.service.dto.PlaceholderParcelDTO;
import io.vakt.service.mapper.PlaceholderParcelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PlaceholderParcel}.
 */
@Service
@Transactional
public class PlaceholderParcelService {

    private final Logger log = LoggerFactory.getLogger(PlaceholderParcelService.class);

    private final PlaceholderParcelRepository placeholderParcelRepository;

    private final PlaceholderParcelMapper placeholderParcelMapper;

    public PlaceholderParcelService(PlaceholderParcelRepository placeholderParcelRepository, PlaceholderParcelMapper placeholderParcelMapper) {
        this.placeholderParcelRepository = placeholderParcelRepository;
        this.placeholderParcelMapper = placeholderParcelMapper;
    }

    /**
     * Save a placeholderParcel.
     *
     * @param placeholderParcelDTO the entity to save.
     * @return the persisted entity.
     */
    public PlaceholderParcelDTO save(PlaceholderParcelDTO placeholderParcelDTO) {
        log.debug("Request to save PlaceholderParcel : {}", placeholderParcelDTO);
        PlaceholderParcel placeholderParcel = placeholderParcelMapper.toEntity(placeholderParcelDTO);
        placeholderParcel = placeholderParcelRepository.save(placeholderParcel);
        return placeholderParcelMapper.toDto(placeholderParcel);
    }

    /**
     * Get all the placeholderParcels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PlaceholderParcelDTO> findAll() {
        log.debug("Request to get all PlaceholderParcels");
        return placeholderParcelRepository.findAll().stream()
            .map(placeholderParcelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one placeholderParcel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PlaceholderParcelDTO> findOne(Long id) {
        log.debug("Request to get PlaceholderParcel : {}", id);
        return placeholderParcelRepository.findById(id)
            .map(placeholderParcelMapper::toDto);
    }

    /**
     * Delete the placeholderParcel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PlaceholderParcel : {}", id);
        placeholderParcelRepository.deleteById(id);
    }
}
