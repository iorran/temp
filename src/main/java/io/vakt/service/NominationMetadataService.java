package io.vakt.service;

import io.vakt.domain.NominationMetadata;
import io.vakt.repository.NominationMetadataRepository;
import io.vakt.service.dto.NominationMetadataDTO;
import io.vakt.service.mapper.NominationMetadataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NominationMetadata}.
 */
@Service
@Transactional
public class NominationMetadataService {

    private final Logger log = LoggerFactory.getLogger(NominationMetadataService.class);

    private final NominationMetadataRepository nominationMetadataRepository;

    private final NominationMetadataMapper nominationMetadataMapper;

    public NominationMetadataService(NominationMetadataRepository nominationMetadataRepository, NominationMetadataMapper nominationMetadataMapper) {
        this.nominationMetadataRepository = nominationMetadataRepository;
        this.nominationMetadataMapper = nominationMetadataMapper;
    }

    /**
     * Save a nominationMetadata.
     *
     * @param nominationMetadataDTO the entity to save.
     * @return the persisted entity.
     */
    public NominationMetadataDTO save(NominationMetadataDTO nominationMetadataDTO) {
        log.debug("Request to save NominationMetadata : {}", nominationMetadataDTO);
        NominationMetadata nominationMetadata = nominationMetadataMapper.toEntity(nominationMetadataDTO);
        nominationMetadata = nominationMetadataRepository.save(nominationMetadata);
        return nominationMetadataMapper.toDto(nominationMetadata);
    }

    /**
     * Get all the nominationMetadata.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NominationMetadataDTO> findAll() {
        log.debug("Request to get all NominationMetadata");
        return nominationMetadataRepository.findAll().stream()
            .map(nominationMetadataMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nominationMetadata by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NominationMetadataDTO> findOne(Long id) {
        log.debug("Request to get NominationMetadata : {}", id);
        return nominationMetadataRepository.findById(id)
            .map(nominationMetadataMapper::toDto);
    }

    /**
     * Delete the nominationMetadata by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NominationMetadata : {}", id);
        nominationMetadataRepository.deleteById(id);
    }
}
