package io.vakt.web.rest;

import io.vakt.NominationApp;
import io.vakt.domain.Parcel;
import io.vakt.repository.ParcelRepository;
import io.vakt.service.ParcelService;
import io.vakt.service.dto.ParcelDTO;
import io.vakt.service.mapper.ParcelMapper;

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
 * Integration tests for the {@link ParcelResource} REST controller.
 */
@SpringBootTest(classes = NominationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParcelResourceIT {

    private static final String DEFAULT_FAKE_PROP = "AAAAAAAAAA";
    private static final String UPDATED_FAKE_PROP = "BBBBBBBBBB";

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private ParcelMapper parcelMapper;

    @Autowired
    private ParcelService parcelService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParcelMockMvc;

    private Parcel parcel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parcel createEntity(EntityManager em) {
        Parcel parcel = new Parcel()
            .fakeProp(DEFAULT_FAKE_PROP);
        return parcel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parcel createUpdatedEntity(EntityManager em) {
        Parcel parcel = new Parcel()
            .fakeProp(UPDATED_FAKE_PROP);
        return parcel;
    }

    @BeforeEach
    public void initTest() {
        parcel = createEntity(em);
    }

    @Test
    @Transactional
    public void createParcel() throws Exception {
        int databaseSizeBeforeCreate = parcelRepository.findAll().size();
        // Create the Parcel
        ParcelDTO parcelDTO = parcelMapper.toDto(parcel);
        restParcelMockMvc.perform(post("/api/parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelDTO)))
            .andExpect(status().isCreated());

        // Validate the Parcel in the database
        List<Parcel> parcelList = parcelRepository.findAll();
        assertThat(parcelList).hasSize(databaseSizeBeforeCreate + 1);
        Parcel testParcel = parcelList.get(parcelList.size() - 1);
        assertThat(testParcel.getFakeProp()).isEqualTo(DEFAULT_FAKE_PROP);
    }

    @Test
    @Transactional
    public void createParcelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parcelRepository.findAll().size();

        // Create the Parcel with an existing ID
        parcel.setId(1L);
        ParcelDTO parcelDTO = parcelMapper.toDto(parcel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParcelMockMvc.perform(post("/api/parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Parcel in the database
        List<Parcel> parcelList = parcelRepository.findAll();
        assertThat(parcelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllParcels() throws Exception {
        // Initialize the database
        parcelRepository.saveAndFlush(parcel);

        // Get all the parcelList
        restParcelMockMvc.perform(get("/api/parcels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parcel.getId().intValue())))
            .andExpect(jsonPath("$.[*].fakeProp").value(hasItem(DEFAULT_FAKE_PROP)));
    }
    
    @Test
    @Transactional
    public void getParcel() throws Exception {
        // Initialize the database
        parcelRepository.saveAndFlush(parcel);

        // Get the parcel
        restParcelMockMvc.perform(get("/api/parcels/{id}", parcel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parcel.getId().intValue()))
            .andExpect(jsonPath("$.fakeProp").value(DEFAULT_FAKE_PROP));
    }
    @Test
    @Transactional
    public void getNonExistingParcel() throws Exception {
        // Get the parcel
        restParcelMockMvc.perform(get("/api/parcels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParcel() throws Exception {
        // Initialize the database
        parcelRepository.saveAndFlush(parcel);

        int databaseSizeBeforeUpdate = parcelRepository.findAll().size();

        // Update the parcel
        Parcel updatedParcel = parcelRepository.findById(parcel.getId()).get();
        // Disconnect from session so that the updates on updatedParcel are not directly saved in db
        em.detach(updatedParcel);
        updatedParcel
            .fakeProp(UPDATED_FAKE_PROP);
        ParcelDTO parcelDTO = parcelMapper.toDto(updatedParcel);

        restParcelMockMvc.perform(put("/api/parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelDTO)))
            .andExpect(status().isOk());

        // Validate the Parcel in the database
        List<Parcel> parcelList = parcelRepository.findAll();
        assertThat(parcelList).hasSize(databaseSizeBeforeUpdate);
        Parcel testParcel = parcelList.get(parcelList.size() - 1);
        assertThat(testParcel.getFakeProp()).isEqualTo(UPDATED_FAKE_PROP);
    }

    @Test
    @Transactional
    public void updateNonExistingParcel() throws Exception {
        int databaseSizeBeforeUpdate = parcelRepository.findAll().size();

        // Create the Parcel
        ParcelDTO parcelDTO = parcelMapper.toDto(parcel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParcelMockMvc.perform(put("/api/parcels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parcelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Parcel in the database
        List<Parcel> parcelList = parcelRepository.findAll();
        assertThat(parcelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParcel() throws Exception {
        // Initialize the database
        parcelRepository.saveAndFlush(parcel);

        int databaseSizeBeforeDelete = parcelRepository.findAll().size();

        // Delete the parcel
        restParcelMockMvc.perform(delete("/api/parcels/{id}", parcel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Parcel> parcelList = parcelRepository.findAll();
        assertThat(parcelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
