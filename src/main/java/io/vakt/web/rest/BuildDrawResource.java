package io.vakt.web.rest;

import io.vakt.service.BuildDrawService;
import io.vakt.web.rest.errors.BadRequestAlertException;
import io.vakt.service.dto.BuildDrawDTO;

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
 * REST controller for managing {@link io.vakt.domain.BuildDraw}.
 */
@RestController
@RequestMapping("/api")
public class BuildDrawResource {

    private final Logger log = LoggerFactory.getLogger(BuildDrawResource.class);

    private static final String ENTITY_NAME = "buildDraw";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BuildDrawService buildDrawService;

    public BuildDrawResource(BuildDrawService buildDrawService) {
        this.buildDrawService = buildDrawService;
    }

    /**
     * {@code POST  /build-draws} : Create a new buildDraw.
     *
     * @param buildDrawDTO the buildDrawDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buildDrawDTO, or with status {@code 400 (Bad Request)} if the buildDraw has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/build-draws")
    public ResponseEntity<BuildDrawDTO> createBuildDraw(@RequestBody BuildDrawDTO buildDrawDTO) throws URISyntaxException {
        log.debug("REST request to save BuildDraw : {}", buildDrawDTO);
        if (buildDrawDTO.getId() != null) {
            throw new BadRequestAlertException("A new buildDraw cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BuildDrawDTO result = buildDrawService.save(buildDrawDTO);
        return ResponseEntity.created(new URI("/api/build-draws/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /build-draws} : Updates an existing buildDraw.
     *
     * @param buildDrawDTO the buildDrawDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buildDrawDTO,
     * or with status {@code 400 (Bad Request)} if the buildDrawDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buildDrawDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/build-draws")
    public ResponseEntity<BuildDrawDTO> updateBuildDraw(@RequestBody BuildDrawDTO buildDrawDTO) throws URISyntaxException {
        log.debug("REST request to update BuildDraw : {}", buildDrawDTO);
        if (buildDrawDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BuildDrawDTO result = buildDrawService.save(buildDrawDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, buildDrawDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /build-draws} : get all the buildDraws.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buildDraws in body.
     */
    @GetMapping("/build-draws")
    public List<BuildDrawDTO> getAllBuildDraws() {
        log.debug("REST request to get all BuildDraws");
        return buildDrawService.findAll();
    }

    /**
     * {@code GET  /build-draws/:id} : get the "id" buildDraw.
     *
     * @param id the id of the buildDrawDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buildDrawDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/build-draws/{id}")
    public ResponseEntity<BuildDrawDTO> getBuildDraw(@PathVariable Long id) {
        log.debug("REST request to get BuildDraw : {}", id);
        Optional<BuildDrawDTO> buildDrawDTO = buildDrawService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buildDrawDTO);
    }

    /**
     * {@code DELETE  /build-draws/:id} : delete the "id" buildDraw.
     *
     * @param id the id of the buildDrawDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/build-draws/{id}")
    public ResponseEntity<Void> deleteBuildDraw(@PathVariable Long id) {
        log.debug("REST request to delete BuildDraw : {}", id);
        buildDrawService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
