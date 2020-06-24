package io.vakt.service;

import io.vakt.domain.LegNomination;
import io.vakt.repository.LegNominationRepository;
import io.vakt.service.dto.LegNominationDTO;
import io.vakt.service.mapper.LegNominationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LegNomination}.
 */
@Service
@Transactional
public class LegNominationService {

    private final Logger log = LoggerFactory.getLogger(LegNominationService.class);

    private final LegNominationRepository legNominationRepository;

    private final LegNominationMapper legNominationMapper;

    public LegNominationService(LegNominationRepository legNominationRepository, LegNominationMapper legNominationMapper) {
        this.legNominationRepository = legNominationRepository;
        this.legNominationMapper = legNominationMapper;
    }

    /**
     * Save a legNomination.
     *
     * @param legNominationDTO the entity to save.
     * @return the persisted entity.
     */
    public LegNominationDTO save(LegNominationDTO legNominationDTO) {
        log.debug("Request to save LegNomination : {}", legNominationDTO);
        LegNomination legNomination = legNominationMapper.toEntity(legNominationDTO);
        legNomination = legNominationRepository.save(legNomination);
        return legNominationMapper.toDto(legNomination);
    }

    /**
     * Get all the legNominations.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LegNominationDTO> findAll() {
        log.debug("Request to get all LegNominations");
        return legNominationRepository.findAll().stream()
            .map(legNominationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one legNomination by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LegNominationDTO> findOne(Long id) {
        log.debug("Request to get LegNomination : {}", id);
        return legNominationRepository.findById(id)
            .map(legNominationMapper::toDto);
    }

    /**
     * Delete the legNomination by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LegNomination : {}", id);
        legNominationRepository.deleteById(id);
    }
}
