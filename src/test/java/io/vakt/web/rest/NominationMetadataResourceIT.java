package io.vakt.web.rest;

import io.vakt.NominationApp;
import io.vakt.domain.NominationMetadata;
import io.vakt.repository.NominationMetadataRepository;
import io.vakt.service.NominationMetadataService;
import io.vakt.service.dto.NominationMetadataDTO;
import io.vakt.service.mapper.NominationMetadataMapper;

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
 * Integration tests for the {@link NominationMetadataResource} REST controller.
 */
@SpringBootTest(classes = NominationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NominationMetadataResourceIT {

    private static final String DEFAULT_FAKE_PROP = "AAAAAAAAAA";
    private static final String UPDATED_FAKE_PROP = "BBBBBBBBBB";

    @Autowired
    private NominationMetadataRepository nominationMetadataRepository;

    @Autowired
    private NominationMetadataMapper nominationMetadataMapper;

    @Autowired
    private NominationMetadataService nominationMetadataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNominationMetadataMockMvc;

    private NominationMetadata nominationMetadata;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NominationMetadata createEntity(EntityManager em) {
        NominationMetadata nominationMetadata = new NominationMetadata()
            .fakeProp(DEFAULT_FAKE_PROP);
        return nominationMetadata;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NominationMetadata createUpdatedEntity(EntityManager em) {
        NominationMetadata nominationMetadata = new NominationMetadata()
            .fakeProp(UPDATED_FAKE_PROP);
        return nominationMetadata;
    }

    @BeforeEach
    public void initTest() {
        nominationMetadata = createEntity(em);
    }

    @Test
    @Transactional
    public void createNominationMetadata() throws Exception {
        int databaseSizeBeforeCreate = nominationMetadataRepository.findAll().size();
        // Create the NominationMetadata
        NominationMetadataDTO nominationMetadataDTO = nominationMetadataMapper.toDto(nominationMetadata);
        restNominationMetadataMockMvc.perform(post("/api/nomination-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nominationMetadataDTO)))
            .andExpect(status().isCreated());

        // Validate the NominationMetadata in the database
        List<NominationMetadata> nominationMetadataList = nominationMetadataRepository.findAll();
        assertThat(nominationMetadataList).hasSize(databaseSizeBeforeCreate + 1);
        NominationMetadata testNominationMetadata = nominationMetadataList.get(nominationMetadataList.size() - 1);
        assertThat(testNominationMetadata.getFakeProp()).isEqualTo(DEFAULT_FAKE_PROP);
    }

    @Test
    @Transactional
    public void createNominationMetadataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nominationMetadataRepository.findAll().size();

        // Create the NominationMetadata with an existing ID
        nominationMetadata.setId(1L);
        NominationMetadataDTO nominationMetadataDTO = nominationMetadataMapper.toDto(nominationMetadata);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNominationMetadataMockMvc.perform(post("/api/nomination-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nominationMetadataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NominationMetadata in the database
        List<NominationMetadata> nominationMetadataList = nominationMetadataRepository.findAll();
        assertThat(nominationMetadataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNominationMetadata() throws Exception {
        // Initialize the database
        nominationMetadataRepository.saveAndFlush(nominationMetadata);

        // Get all the nominationMetadataList
        restNominationMetadataMockMvc.perform(get("/api/nomination-metadata?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nominationMetadata.getId().intValue())))
            .andExpect(jsonPath("$.[*].fakeProp").value(hasItem(DEFAULT_FAKE_PROP)));
    }
    
    @Test
    @Transactional
    public void getNominationMetadata() throws Exception {
        // Initialize the database
        nominationMetadataRepository.saveAndFlush(nominationMetadata);

        // Get the nominationMetadata
        restNominationMetadataMockMvc.perform(get("/api/nomination-metadata/{id}", nominationMetadata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nominationMetadata.getId().intValue()))
            .andExpect(jsonPath("$.fakeProp").value(DEFAULT_FAKE_PROP));
    }
    @Test
    @Transactional
    public void getNonExistingNominationMetadata() throws Exception {
        // Get the nominationMetadata
        restNominationMetadataMockMvc.perform(get("/api/nomination-metadata/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNominationMetadata() throws Exception {
        // Initialize the database
        nominationMetadataRepository.saveAndFlush(nominationMetadata);

        int databaseSizeBeforeUpdate = nominationMetadataRepository.findAll().size();

        // Update the nominationMetadata
        NominationMetadata updatedNominationMetadata = nominationMetadataRepository.findById(nominationMetadata.getId()).get();
        // Disconnect from session so that the updates on updatedNominationMetadata are not directly saved in db
        em.detach(updatedNominationMetadata);
        updatedNominationMetadata
            .fakeProp(UPDATED_FAKE_PROP);
        NominationMetadataDTO nominationMetadataDTO = nominationMetadataMapper.toDto(updatedNominationMetadata);

        restNominationMetadataMockMvc.perform(put("/api/nomination-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nominationMetadataDTO)))
            .andExpect(status().isOk());

        // Validate the NominationMetadata in the database
        List<NominationMetadata> nominationMetadataList = nominationMetadataRepository.findAll();
        assertThat(nominationMetadataList).hasSize(databaseSizeBeforeUpdate);
        NominationMetadata testNominationMetadata = nominationMetadataList.get(nominationMetadataList.size() - 1);
        assertThat(testNominationMetadata.getFakeProp()).isEqualTo(UPDATED_FAKE_PROP);
    }

    @Test
    @Transactional
    public void updateNonExistingNominationMetadata() throws Exception {
        int databaseSizeBeforeUpdate = nominationMetadataRepository.findAll().size();

        // Create the NominationMetadata
        NominationMetadataDTO nominationMetadataDTO = nominationMetadataMapper.toDto(nominationMetadata);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNominationMetadataMockMvc.perform(put("/api/nomination-metadata")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nominationMetadataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NominationMetadata in the database
        List<NominationMetadata> nominationMetadataList = nominationMetadataRepository.findAll();
        assertThat(nominationMetadataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNominationMetadata() throws Exception {
        // Initialize the database
        nominationMetadataRepository.saveAndFlush(nominationMetadata);

        int databaseSizeBeforeDelete = nominationMetadataRepository.findAll().size();

        // Delete the nominationMetadata
        restNominationMetadataMockMvc.perform(delete("/api/nomination-metadata/{id}", nominationMetadata.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NominationMetadata> nominationMetadataList = nominationMetadataRepository.findAll();
        assertThat(nominationMetadataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
