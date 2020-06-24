package io.vakt.web.rest;

import io.vakt.service.LegNominationService;
import io.vakt.web.rest.errors.BadRequestAlertException;
import io.vakt.service.dto.LegNominationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.vakt.domain.LegNomination}.
 */
@RestController
@RequestMapping("/api")
public class LegNominationResource {

    private final Logger log = LoggerFactory.getLogger(LegNominationResource.class);

    private static final String ENTITY_NAME = "legNomination";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LegNominationService legNominationService;

    public LegNominationResource(LegNominationService legNominationService) {
        this.legNominationService = legNominationService;
    }

    /**
     * {@code POST  /leg-nominations} : Create a new legNomination.
     *
     * @param legNominationDTO the legNominationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new legNominationDTO, or with status {@code 400 (Bad Request)} if the legNomination has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leg-nominations")
    public ResponseEntity<LegNominationDTO> createLegNomination(@RequestBody LegNominationDTO legNominationDTO) throws URISyntaxException {
        log.debug("REST request to save LegNomination : {}", legNominationDTO);
        if (legNominationDTO.getId() != null) {
            throw new BadRequestAlertException("A new legNomination cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LegNominationDTO result = legNominationService.save(legNominationDTO);
        return ResponseEntity.created(new URI("/api/leg-nominations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leg-nominations} : Updates an existing legNomination.
     *
     * @param legNominationDTO the legNominationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated legNominationDTO,
     * or with status {@code 400 (Bad Request)} if the legNominationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the legNominationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leg-nominations")
    public ResponseEntity<LegNominationDTO> updateLegNomination(@RequestBody LegNominationDTO legNominationDTO) throws URISyntaxException {
        log.debug("REST request to update LegNomination : {}", legNominationDTO);
        if (legNominationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LegNominationDTO result = legNominationService.save(legNominationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, legNominationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /leg-nominations} : get all the legNominations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of legNominations in body.
     */
    @GetMapping("/leg-nominations")
    public List<LegNominationDTO> getAllLegNominations() {
        log.debug("REST request to get all LegNominations");
        return legNominationService.findAll();
    }

    /**
     * {@code GET  /leg-nominations/:id} : get the "id" legNomination.
     *
     * @param id the id of the legNominationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the legNominationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leg-nominations/{id}")
    public ResponseEntity<LegNominationDTO> getLegNomination(@PathVariable Long id) {
        log.debug("REST request to get LegNomination : {}", id);
        Optional<LegNominationDTO> legNominationDTO = legNominationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(legNominationDTO);
    }

    /**
     * {@code DELETE  /leg-nominations/:id} : delete the "id" legNomination.
     *
     * @param id the id of the legNominationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leg-nominations/{id}")
    public ResponseEntity<Void> deleteLegNomination(@PathVariable Long id) {
        log.debug("REST request to delete LegNomination : {}", id);
        legNominationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
