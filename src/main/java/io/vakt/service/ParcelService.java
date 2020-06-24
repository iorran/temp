package io.vakt.service;

import io.vakt.domain.Parcel;
import io.vakt.repository.ParcelRepository;
import io.vakt.service.dto.ParcelDTO;
import io.vakt.service.mapper.ParcelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Parcel}.
 */
@Service
@Transactional
public class ParcelService {

    private final Logger log = LoggerFactory.getLogger(ParcelService.class);

    private final ParcelRepository parcelRepository;

    private final ParcelMapper parcelMapper;

    public ParcelService(ParcelRepository parcelRepository, ParcelMapper parcelMapper) {
        this.parcelRepository = parcelRepository;
        this.parcelMapper = parcelMapper;
    }

    /**
     * Save a parcel.
     *
     * @param parcelDTO the entity to save.
     * @return the persisted entity.
     */
    public ParcelDTO save(ParcelDTO parcelDTO) {
        log.debug("Request to save Parcel : {}", parcelDTO);
        Parcel parcel = parcelMapper.toEntity(parcelDTO);
        parcel = parcelRepository.save(parcel);
        return parcelMapper.toDto(parcel);
    }

    /**
     * Get all the parcels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ParcelDTO> findAll() {
        log.debug("Request to get all Parcels");
        return parcelRepository.findAll().stream()
            .map(parcelMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one parcel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ParcelDTO> findOne(Long id) {
        log.debug("Request to get Parcel : {}", id);
        return parcelRepository.findById(id)
            .map(parcelMapper::toDto);
    }

    /**
     * Delete the parcel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Parcel : {}", id);
        parcelRepository.deleteById(id);
    }
}
