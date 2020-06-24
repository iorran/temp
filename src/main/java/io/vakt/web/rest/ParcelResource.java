package io.vakt.web.rest;

import io.vakt.service.ParcelService;
import io.vakt.web.rest.errors.BadRequestAlertException;
import io.vakt.service.dto.ParcelDTO;

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
 * REST controller for managing {@link io.vakt.domain.Parcel}.
 */
@RestController
@RequestMapping("/api")
public class ParcelResource {

    private final Logger log = LoggerFactory.getLogger(ParcelResource.class);

    private static final String ENTITY_NAME = "parcel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParcelService parcelService;

    public ParcelResource(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    /**
     * {@code POST  /parcels} : Create a new parcel.
     *
     * @param parcelDTO the parcelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parcelDTO, or with status {@code 400 (Bad Request)} if the parcel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parcels")
    public ResponseEntity<ParcelDTO> createParcel(@RequestBody ParcelDTO parcelDTO) throws URISyntaxException {
        log.debug("REST request to save Parcel : {}", parcelDTO);
        if (parcelDTO.getId() != null) {
            throw new BadRequestAlertException("A new parcel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParcelDTO result = parcelService.save(parcelDTO);
        return ResponseEntity.created(new URI("/api/parcels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parcels} : Updates an existing parcel.
     *
     * @param parcelDTO the parcelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parcelDTO,
     * or with status {@code 400 (Bad Request)} if the parcelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parcelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parcels")
    public ResponseEntity<ParcelDTO> updateParcel(@RequestBody ParcelDTO parcelDTO) throws URISyntaxException {
        log.debug("REST request to update Parcel : {}", parcelDTO);
        if (parcelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParcelDTO result = parcelService.save(parcelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, parcelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parcels} : get all the parcels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parcels in body.
     */
    @GetMapping("/parcels")
    public List<ParcelDTO> getAllParcels() {
        log.debug("REST request to get all Parcels");
        return parcelService.findAll();
    }

    /**
     * {@code GET  /parcels/:id} : get the "id" parcel.
     *
     * @param id the id of the parcelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parcelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parcels/{id}")
    public ResponseEntity<ParcelDTO> getParcel(@PathVariable Long id) {
        log.debug("REST request to get Parcel : {}", id);
        Optional<ParcelDTO> parcelDTO = parcelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parcelDTO);
    }

    /**
     * {@code DELETE  /parcels/:id} : delete the "id" parcel.
     *
     * @param id the id of the parcelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parcels/{id}")
    public ResponseEntity<Void> deleteParcel(@PathVariable Long id) {
        log.debug("REST request to delete Parcel : {}", id);
        parcelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
