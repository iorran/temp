package io.vakt.web.rest;

import io.vakt.NominationApp;
import io.vakt.domain.BuildDraw;
import io.vakt.repository.BuildDrawRepository;
import io.vakt.service.BuildDrawService;
import io.vakt.service.dto.BuildDrawDTO;
import io.vakt.service.mapper.BuildDrawMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BuildDrawResource} REST controller.
 */
@SpringBootTest(classes = NominationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BuildDrawResourceIT {

    private static final String DEFAULT_FAKE_PROP = "AAAAAAAAAA";
    private static final String UPDATED_FAKE_PROP = "BBBBBBBBBB";

    @Autowired
    private BuildDrawRepository buildDrawRepository;

    @Autowired
    private BuildDrawMapper buildDrawMapper;

    @Autowired
    private BuildDrawService buildDrawService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBuildDrawMockMvc;

    private BuildDraw buildDraw;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BuildDraw createEntity(EntityManager em) {
        BuildDraw buildDraw = new BuildDraw()
            .fakeProp(DEFAULT_FAKE_PROP);
        return buildDraw;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BuildDraw createUpdatedEntity(EntityManager em) {
        BuildDraw buildDraw = new BuildDraw()
            .fakeProp(UPDATED_FAKE_PROP);
        return buildDraw;
    }

    @BeforeEach
    public void initTest() {
        buildDraw = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuildDraw() throws Exception {
        int databaseSizeBeforeCreate = buildDrawRepository.findAll().size();
        // Create the BuildDraw
        BuildDrawDTO buildDrawDTO = buildDrawMapper.toDto(buildDraw);
        restBuildDrawMockMvc.perform(post("/api/build-draws")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buildDrawDTO)))
            .andExpect(status().isCreated());

        // Validate the BuildDraw in the database
        List<BuildDraw> buildDrawList = buildDrawRepository.findAll();
        assertThat(buildDrawList).hasSize(databaseSizeBeforeCreate + 1);
        BuildDraw testBuildDraw = buildDrawList.get(buildDrawList.size() - 1);
        assertThat(testBuildDraw.getFakeProp()).isEqualTo(DEFAULT_FAKE_PROP);
    }

    @Test
    @Transactional
    public void createBuildDrawWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buildDrawRepository.findAll().size();

        // Create the BuildDraw with an existing ID
        buildDraw.setId(1L);
        BuildDrawDTO buildDrawDTO = buildDrawMapper.toDto(buildDraw);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuildDrawMockMvc.perform(post("/api/build-draws")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buildDrawDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuildDraw in the database
        List<BuildDraw> buildDrawList = buildDrawRepository.findAll();
        assertThat(buildDrawList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBuildDraws() throws Exception {
        // Initialize the database
        buildDrawRepository.saveAndFlush(buildDraw);

        // Get all the buildDrawList
        restBuildDrawMockMvc.perform(get("/api/build-draws?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buildDraw.getId().intValue())))
            .andExpect(jsonPath("$.[*].fakeProp").value(hasItem(DEFAULT_FAKE_PROP)));
    }
    
    @Test
    @Transactional
    public void getBuildDraw() throws Exception {
        // Initialize the database
        buildDrawRepository.saveAndFlush(buildDraw);

        // Get the buildDraw
        restBuildDrawMockMvc.perform(get("/api/build-draws/{id}", buildDraw.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(buildDraw.getId().intValue()))
            .andExpect(jsonPath("$.fakeProp").value(DEFAULT_FAKE_PROP));
    }
    @Test
    @Transactional
    public void getNonExistingBuildDraw() throws Exception {
        // Get the buildDraw
        restBuildDrawMockMvc.perform(get("/api/build-draws/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuildDraw() throws Exception {
        // Initialize the database
        buildDrawRepository.saveAndFlush(buildDraw);

        int databaseSizeBeforeUpdate = buildDrawRepository.findAll().size();

        // Update the buildDraw
        BuildDraw updatedBuildDraw = buildDrawRepository.findById(buildDraw.getId()).get();
        // Disconnect from session so that the updates on updatedBuildDraw are not directly saved in db
        em.detach(updatedBuildDraw);
        updatedBuildDraw
            .fakeProp(UPDATED_FAKE_PROP);
        BuildDrawDTO buildDrawDTO = buildDrawMapper.toDto(updatedBuildDraw);

        restBuildDrawMockMvc.perform(put("/api/build-draws")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buildDrawDTO)))
            .andExpect(status().isOk());

        // Validate the BuildDraw in the database
        List<BuildDraw> buildDrawList = buildDrawRepository.findAll();
        assertThat(buildDrawList).hasSize(databaseSizeBeforeUpdate);
        BuildDraw testBuildDraw = buildDrawList.get(buildDrawList.size() - 1);
        assertThat(testBuildDraw.getFakeProp()).isEqualTo(UPDATED_FAKE_PROP);
    }

    @Test
    @Transactional
    public void updateNonExistingBuildDraw() throws Exception {
        int databaseSizeBeforeUpdate = buildDrawRepository.findAll().size();

        // Create the BuildDraw
        BuildDrawDTO buildDrawDTO = buildDrawMapper.toDto(buildDraw);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBuildDrawMockMvc.perform(put("/api/build-draws")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(buildDrawDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuildDraw in the database
        List<BuildDraw> buildDrawList = buildDrawRepository.findAll();
        assertThat(buildDrawList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBuildDraw() throws Exception {
        // Initialize the database
        buildDrawRepository.saveAndFlush(buildDraw);

        int databaseSizeBeforeDelete = buildDrawRepository.findAll().size();

        // Delete the buildDraw
        restBuildDrawMockMvc.perform(delete("/api/build-draws/{id}", buildDraw.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BuildDraw> buildDrawList = buildDrawRepository.findAll();
        assertThat(buildDrawList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
