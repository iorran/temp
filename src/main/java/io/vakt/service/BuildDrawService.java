package io.vakt.service;

import io.vakt.domain.BuildDraw;
import io.vakt.repository.BuildDrawRepository;
import io.vakt.service.dto.BuildDrawDTO;
import io.vakt.service.mapper.BuildDrawMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BuildDraw}.
 */
@Service
@Transactional
public class BuildDrawService {

    private final Logger log = LoggerFactory.getLogger(BuildDrawService.class);

    private final BuildDrawRepository buildDrawRepository;

    private final BuildDrawMapper buildDrawMapper;

    public BuildDrawService(BuildDrawRepository buildDrawRepository, BuildDrawMapper buildDrawMapper) {
        this.buildDrawRepository = buildDrawRepository;
        this.buildDrawMapper = buildDrawMapper;
    }

    /**
     * Save a buildDraw.
     *
     * @param buildDrawDTO the entity to save.
     * @return the persisted entity.
     */
    public BuildDrawDTO save(BuildDrawDTO buildDrawDTO) {
        log.debug("Request to save BuildDraw : {}", buildDrawDTO);
        BuildDraw buildDraw = buildDrawMapper.toEntity(buildDrawDTO);
        buildDraw = buildDrawRepository.save(buildDraw);
        return buildDrawMapper.toDto(buildDraw);
    }

    /**
     * Get all the buildDraws.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BuildDrawDTO> findAll() {
        log.debug("Request to get all BuildDraws");
        return buildDrawRepository.findAll().stream()
            .map(buildDrawMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one buildDraw by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BuildDrawDTO> findOne(Long id) {
        log.debug("Request to get BuildDraw : {}", id);
        return buildDrawRepository.findById(id)
            .map(buildDrawMapper::toDto);
    }

    /**
     * Delete the buildDraw by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BuildDraw : {}", id);
        buildDrawRepository.deleteById(id);
    }
}
