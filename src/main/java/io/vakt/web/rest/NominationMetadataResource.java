package io.vakt.web.rest;

import io.vakt.service.NominationMetadataService;
import io.vakt.web.rest.errors.BadRequestAlertException;
import io.vakt.service.dto.NominationMetadataDTO;

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
 * REST controller for managing {@link io.vakt.domain.NominationMetadata}.
 */
@RestController
@RequestMapping("/api")
public class NominationMetadataResource {

    private final Logger log = LoggerFactory.getLogger(NominationMetadataResource.class);

    private static final String ENTITY_NAME = "nominationMetadata";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NominationMetadataService nominationMetadataService;

    public NominationMetadataResource(NominationMetadataService nominationMetadataService) {
        this.nominationMetadataService = nominationMetadataService;
    }

    /**
     * {@code POST  /nomination-metadata} : Create a new nominationMetadata.
     *
     * @param nominationMetadataDTO the nominationMetadataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nominationMetadataDTO, or with status {@code 400 (Bad Request)} if the nominationMetadata has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nomination-metadata")
    public ResponseEntity<NominationMetadataDTO> createNominationMetadata(@RequestBody NominationMetadataDTO nominationMetadataDTO) throws URISyntaxException {
        log.debug("REST request to save NominationMetadata : {}", nominationMetadataDTO);
        if (nominationMetadataDTO.getId() != null) {
            throw new BadRequestAlertException("A new nominationMetadata cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NominationMetadataDTO result = nominationMetadataService.save(nominationMetadataDTO);
        return ResponseEntity.created(new URI("/api/nomination-metadata/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nomination-metadata} : Updates an existing nominationMetadata.
     *
     * @param nominationMetadataDTO the nominationMetadataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nominationMetadataDTO,
     * or with status {@code 400 (Bad Request)} if the nominationMetadataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nominationMetadataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nomination-metadata")
    public ResponseEntity<NominationMetadataDTO> updateNominationMetadata(@RequestBody NominationMetadataDTO nominationMetadataDTO) throws URISyntaxException {
        log.debug("REST request to update NominationMetadata : {}", nominationMetadataDTO);
        if (nominationMetadataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NominationMetadataDTO result = nominationMetadataService.save(nominationMetadataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nominationMetadataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nomination-metadata} : get all the nominationMetadata.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nominationMetadata in body.
     */
    @GetMapping("/nomination-metadata")
    public List<NominationMetadataDTO> getAllNominationMetadata() {
        log.debug("REST request to get all NominationMetadata");
        return nominationMetadataService.findAll();
    }

    /**
     * {@code GET  /nomination-metadata/:id} : get the "id" nominationMetadata.
     *
     * @param id the id of the nominationMetadataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nominationMetadataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nomination-metadata/{id}")
    public ResponseEntity<NominationMetadataDTO> getNominationMetadata(@PathVariable Long id) {
        log.debug("REST request to get NominationMetadata : {}", id);
        Optional<NominationMetadataDTO> nominationMetadataDTO = nominationMetadataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nominationMetadataDTO);
    }

    /**
     * {@code DELETE  /nomination-metadata/:id} : delete the "id" nominationMetadata.
     *
     * @param id the id of the nominationMetadataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nomination-metadata/{id}")
    public ResponseEntity<Void> deleteNominationMetadata(@PathVariable Long id) {
        log.debug("REST request to delete NominationMetadata : {}", id);
        nominationMetadataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
