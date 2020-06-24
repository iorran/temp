package io.vakt.web.rest;

import io.vakt.service.PlaceholderParcelService;
import io.vakt.web.rest.errors.BadRequestAlertException;
import io.vakt.service.dto.PlaceholderParcelDTO;

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
 * REST controller for managing {@link io.vakt.domain.PlaceholderParcel}.
 */
@RestController
@RequestMapping("/api")
public class PlaceholderParcelResource {

    private final Logger log = LoggerFactory.getLogger(PlaceholderParcelResource.class);

    private static final String ENTITY_NAME = "placeholderParcel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlaceholderParcelService placeholderParcelService;

    public PlaceholderParcelResource(PlaceholderParcelService placeholderParcelService) {
        this.placeholderParcelService = placeholderParcelService;
    }

    /**
     * {@code POST  /placeholder-parcels} : Create a new placeholderParcel.
     *
     * @param placeholderParcelDTO the placeholderParcelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new placeholderParcelDTO, or with status {@code 400 (Bad Request)} if the placeholderParcel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/placeholder-parcels")
    public ResponseEntity<PlaceholderParcelDTO> createPlaceholderParcel(@RequestBody PlaceholderParcelDTO placeholderParcelDTO) throws URISyntaxException {
        log.debug("REST request to save PlaceholderParcel : {}", placeholderParcelDTO);
        if (placeholderParcelDTO.getId() != null) {
            throw new BadRequestAlertException("A new placeholderParcel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlaceholderParcelDTO result = placeholderParcelService.save(placeholderParcelDTO);
        return ResponseEntity.created(new URI("/api/placeholder-parcels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /placeholder-parcels} : Updates an existing placeholderParcel.
     *
     * @param placeholderParcelDTO the placeholderParcelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated placeholderParcelDTO,
     * or with status {@code 400 (Bad Request)} if the placeholderParcelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the placeholderParcelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/placeholder-parcels")
    public ResponseEntity<PlaceholderParcelDTO> updatePlaceholderParcel(@RequestBody PlaceholderParcelDTO placeholderParcelDTO) throws URISyntaxException {
        log.debug("REST request to update PlaceholderParcel : {}", placeholderParcelDTO);
        if (placeholderParcelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlaceholderParcelDTO result = placeholderParcelService.save(placeholderParcelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, placeholderParcelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /placeholder-parcels} : get all the placeholderParcels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of placeholderParcels in body.
     */
    @GetMapping("/placeholder-parcels")
    public List<PlaceholderParcelDTO> getAllPlaceholderParcels() {
        log.debug("REST request to get all PlaceholderParcels");
        return placeholderParcelService.findAll();
    }

    /**
     * {@code GET  /placeholder-parcels/:id} : get the "id" placeholderParcel.
     *
     * @param id the id of the placeholderParcelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the placeholderParcelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/placeholder-parcels/{id}")
    public ResponseEntity<PlaceholderParcelDTO> getPlaceholderParcel(@PathVariable Long id) {
        log.debug("REST request to get PlaceholderParcel : {}", id);
        Optional<PlaceholderParcelDTO> placeholderParcelDTO = placeholderParcelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(placeholderParcelDTO);
    }

    /**
     * {@code DELETE  /placeholder-parcels/:id} : delete the "id" placeholderParcel.
     *
     * @param id the id of the placeholderParcelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/placeholder-parcels/{id}")
    public ResponseEntity<Void> deletePlaceholderParcel(@PathVariable Long id) {
        log.debug("REST request to delete PlaceholderParcel : {}", id);
        placeholderParcelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
