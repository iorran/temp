package io.vakt.web.rest;

import io.vakt.NominationApp;
import io.vakt.domain.PlaceholderParcel;
import io.vakt.repository.PlaceholderParcelRepository;
import io.vakt.service.PlaceholderParcelService;
import io.vakt.service.dto.PlaceholderParcelDTO;
import io.vakt.service.mapper.PlaceholderParcelMapper;

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
 * Integration tests for the {@link PlaceholderParcelResource} REST controller.
 */
@SpringBootTest(classes = NominationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlaceholderParcelResourceIT {

    private static final String DEFAULT_FAKE_PROP = "AAAAAAAAAA";
    private static final String UPDATED_FAKE_PROP = "BBBBBBBBBB";

    @Autowired
    private PlaceholderParcelRepository placeholderParcelRepository;

    @Autowired
    private PlaceholderParcelMapper placeholderParcelMapper;

    @Autowired
    private PlaceholderParcelService placeholderParcelService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlaceholderParcelMockMvc;

    private PlaceholderParcel placeholderParcel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlaceholderParcel createEntity(EntityManager em) {
        PlaceholderParcel placeholderParcel = new PlaceholderParcel()
            .fakeProp(DEFAULT_FAKE_PROP);
        return placeholderParcel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlaceholderParcel createUpdatedEntity(EntityManager em) {
        PlaceholderParcel placeholderParcel = new PlaceholderParcel()
            .fakeProp(UPDATED_FAKE_PROP);
        return placeholderParcel;
    }

    @BeforeEach
    public void initTest() {
        placeholderParcel = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlaceholderParcel() throws Exception {
        int databaseSizeBeforeCreate = placeholderParcelRepository.findAll().size();
        // Create the PlaceholderParcel
        PlaceholderParcelDTO placeholderParcelDTO = placeholderParcelMapper.toDto(placeholderParcel);
        restPlaceholderParcelMockMvc.perform(post("/api/placeholder-parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(placeholderParcelDTO)))
            .andExpect(status().isCreated());

        // Validate the PlaceholderParcel in the database
        List<PlaceholderParcel> placeholderParcelList = placeholderParcelRepository.findAll();
        assertThat(placeholderParcelList).hasSize(databaseSizeBeforeCreate + 1);
        PlaceholderParcel testPlaceholderParcel = placeholderParcelList.get(placeholderParcelList.size() - 1);
        assertThat(testPlaceholderParcel.getFakeProp()).isEqualTo(DEFAULT_FAKE_PROP);
    }

    @Test
    @Transactional
    public void createPlaceholderParcelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = placeholderParcelRepository.findAll().size();

        // Create the PlaceholderParcel with an existing ID
        placeholderParcel.setId(1L);
        PlaceholderParcelDTO placeholderParcelDTO = placeholderParcelMapper.toDto(placeholderParcel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlaceholderParcelMockMvc.perform(post("/api/placeholder-parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(placeholderParcelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlaceholderParcel in the database
        List<PlaceholderParcel> placeholderParcelList = placeholderParcelRepository.findAll();
        assertThat(placeholderParcelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlaceholderParcels() throws Exception {
        // Initialize the database
        placeholderParcelRepository.saveAndFlush(placeholderParcel);

        // Get all the placeholderParcelList
        restPlaceholderParcelMockMvc.perform(get("/api/placeholder-parcels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(placeholderParcel.getId().intValue())))
            .andExpect(jsonPath("$.[*].fakeProp").value(hasItem(DEFAULT_FAKE_PROP)));
    }
    
    @Test
    @Transactional
    public void getPlaceholderParcel() throws Exception {
        // Initialize the database
        placeholderParcelRepository.saveAndFlush(placeholderParcel);

        // Get the placeholderParcel
        restPlaceholderParcelMockMvc.perform(get("/api/placeholder-parcels/{id}", placeholderParcel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(placeholderParcel.getId().intValue()))
            .andExpect(jsonPath("$.fakeProp").value(DEFAULT_FAKE_PROP));
    }
    @Test
    @Transactional
    public void getNonExistingPlaceholderParcel() throws Exception {
        // Get the placeholderParcel
        restPlaceholderParcelMockMvc.perform(get("/api/placeholder-parcels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlaceholderParcel() throws Exception {
        // Initialize the database
        placeholderParcelRepository.saveAndFlush(placeholderParcel);

        int databaseSizeBeforeUpdate = placeholderParcelRepository.findAll().size();

        // Update the placeholderParcel
        PlaceholderParcel updatedPlaceholderParcel = placeholderParcelRepository.findById(placeholderParcel.getId()).get();
        // Disconnect from session so that the updates on updatedPlaceholderParcel are not directly saved in db
        em.detach(updatedPlaceholderParcel);
        updatedPlaceholderParcel
            .fakeProp(UPDATED_FAKE_PROP);
        PlaceholderParcelDTO placeholderParcelDTO = placeholderParcelMapper.toDto(updatedPlaceholderParcel);

        restPlaceholderParcelMockMvc.perform(put("/api/placeholder-parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(placeholderParcelDTO)))
            .andExpect(status().isOk());

        // Validate the PlaceholderParcel in the database
        List<PlaceholderParcel> placeholderParcelList = placeholderParcelRepository.findAll();
        assertThat(placeholderParcelList).hasSize(databaseSizeBeforeUpdate);
        PlaceholderParcel testPlaceholderParcel = placeholderParcelList.get(placeholderParcelList.size() - 1);
        assertThat(testPlaceholderParcel.getFakeProp()).isEqualTo(UPDATED_FAKE_PROP);
    }

    @Test
    @Transactional
    public void updateNonExistingPlaceholderParcel() throws Exception {
        int databaseSizeBeforeUpdate = placeholderParcelRepository.findAll().size();

        // Create the PlaceholderParcel
        PlaceholderParcelDTO placeholderParcelDTO = placeholderParcelMapper.toDto(placeholderParcel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlaceholderParcelMockMvc.perform(put("/api/placeholder-parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(placeholderParcelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlaceholderParcel in the database
        List<PlaceholderParcel> placeholderParcelList = placeholderParcelRepository.findAll();
        assertThat(placeholderParcelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlaceholderParcel() throws Exception {
        // Initialize the database
        placeholderParcelRepository.saveAndFlush(placeholderParcel);

        int databaseSizeBeforeDelete = placeholderParcelRepository.findAll().size();

        // Delete the placeholderParcel
        restPlaceholderParcelMockMvc.perform(delete("/api/placeholder-parcels/{id}", placeholderParcel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlaceholderParcel> placeholderParcelList = placeholderParcelRepository.findAll();
        assertThat(placeholderParcelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
