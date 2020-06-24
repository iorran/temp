package io.vakt.web.rest;

import io.vakt.NominationApp;
import io.vakt.domain.LegNomination;
import io.vakt.repository.LegNominationRepository;
import io.vakt.service.LegNominationService;
import io.vakt.service.dto.LegNominationDTO;
import io.vakt.service.mapper.LegNominationMapper;

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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.vakt.domain.enumeration.CustomsStatus;
import io.vakt.domain.enumeration.NominationStatus;
import io.vakt.domain.enumeration.NominationStatus;
import io.vakt.domain.enumeration.ModeOfTransport;
import io.vakt.domain.enumeration.MovementTransferType;
/**
 * Integration tests for the {@link LegNominationResource} REST controller.
 */
@SpringBootTest(classes = NominationApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LegNominationResourceIT {

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT = "AAAAAAAAAA";
    private static final String UPDATED_AGENT = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_3_CARGOES = "AAAAAAAAAA";
    private static final String UPDATED_LAST_3_CARGOES = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OWN_VAT_NO = "AAAAAAAAAA";
    private static final String UPDATED_OWN_VAT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_VETTING_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_VETTING_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_WAREHOUSE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_WAREHOUSE_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_WAREHOUSE_NO = "AAAAAAAAAA";
    private static final String UPDATED_WAREHOUSE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_WAREHOUSE_TAX_NO = "AAAAAAAAAA";
    private static final String UPDATED_WAREHOUSE_TAX_NO = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_RECEIVER_VAT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_RECEIVER_VAT = "BBBBBBBBBB";

    private static final CustomsStatus DEFAULT_CUSTOMS_STATUS = CustomsStatus.EAD;
    private static final CustomsStatus UPDATED_CUSTOMS_STATUS = CustomsStatus.T1;

    private static final String DEFAULT_DOCUMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_CLAUSE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_CLAUSE_DETAILS = "BBBBBBBBBB";

    private static final NominationStatus DEFAULT_STATUS = NominationStatus.PENDING;
    private static final NominationStatus UPDATED_STATUS = NominationStatus.CANCELLED;

    private static final NominationStatus DEFAULT_COUNTER_PARTY_STATUS = NominationStatus.PENDING;
    private static final NominationStatus UPDATED_COUNTER_PARTY_STATUS = NominationStatus.CANCELLED;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_NOMINATION_TIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NOMINATION_TIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCIAL_HOLD = "AAAAAAAAAA";
    private static final String UPDATED_FINANCIAL_HOLD = "BBBBBBBBBB";

    private static final String DEFAULT_CONSIGNOR = "AAAAAAAAAA";
    private static final String UPDATED_CONSIGNOR = "BBBBBBBBBB";

    private static final String DEFAULT_BLENDING_INSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_BLENDING_INSTRUCTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ADDITIVES = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ADDITIVES = "BBBBBBBBBB";

    private static final String DEFAULT_HOMOGENISATION = "AAAAAAAAAA";
    private static final String UPDATED_HOMOGENISATION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_RECEIVER_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_RECEIVER_ID = "BBBBBBBBBB";

    private static final ModeOfTransport DEFAULT_MODE_OF_TRANSPORT = ModeOfTransport.TRANSPORT1;
    private static final ModeOfTransport UPDATED_MODE_OF_TRANSPORT = ModeOfTransport.TRANSPORT2;

    private static final String DEFAULT_MODE_OF_TRANSPORT_EQUIPMENT = "AAAAAAAAAA";
    private static final String UPDATED_MODE_OF_TRANSPORT_EQUIPMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DISCHARGE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DISCHARGE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LOADPORT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOADPORT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LOAD_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOAD_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_DISCHARGE_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_DISCHARGE_LOCATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PLEASE_ADVISE_WAREHOUSE_TAX_NO = false;
    private static final Boolean UPDATED_PLEASE_ADVISE_WAREHOUSE_TAX_NO = true;

    private static final Boolean DEFAULT_PLEASE_ADVISE_WAREHOUSE_NO = false;
    private static final Boolean UPDATED_PLEASE_ADVISE_WAREHOUSE_NO = true;

    private static final Boolean DEFAULT_PLEASE_ADVISE_WAREHOUSE_ADDRESS = false;
    private static final Boolean UPDATED_PLEASE_ADVISE_WAREHOUSE_ADDRESS = true;

    private static final Boolean DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT = false;
    private static final Boolean UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT = true;

    private static final Boolean DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER = false;
    private static final Boolean UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER = true;

    private static final Boolean DEFAULT_PLEASE_ADVISE_LOADPORT = false;
    private static final Boolean UPDATED_PLEASE_ADVISE_LOADPORT = true;

    private static final Boolean DEFAULT_PLEASE_ADVISE_DISPORT = false;
    private static final Boolean UPDATED_PLEASE_ADVISE_DISPORT = true;

    private static final String DEFAULT_PREFERENTIAL_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_PREFERENTIAL_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_SPECIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_SPECIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ADDITIONAL_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ADDITIONAL_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_ADN = "AAAAAAAAAA";
    private static final String UPDATED_ADN = "BBBBBBBBBB";

    private static final String DEFAULT_CDNI = "AAAAAAAAAA";
    private static final String UPDATED_CDNI = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_ADN = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_ADN = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_CDNI = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_CDNI = "BBBBBBBBBB";

    private static final MovementTransferType DEFAULT_MOVEMENT_TRANSFER_TYPE = MovementTransferType.TYPE1;
    private static final MovementTransferType UPDATED_MOVEMENT_TRANSFER_TYPE = MovementTransferType.TYPE2;

    private static final String DEFAULT_CUSTOM_LOAD_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_LOAD_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_DISCHARGE_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_DISCHARGE_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_RECEIVER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_RECEIVER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_RECEIVER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_RECEIVER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_CONSIGNOR = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_CONSIGNOR = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_TERMINAL_COMPANY_LEGAL_ENTITY = "AAAAAAAAAA";
    private static final String UPDATED_TERMINAL_COMPANY_LEGAL_ENTITY = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ICE_DELIVERY = false;
    private static final Boolean UPDATED_ICE_DELIVERY = true;

    @Autowired
    private LegNominationRepository legNominationRepository;

    @Autowired
    private LegNominationMapper legNominationMapper;

    @Autowired
    private LegNominationService legNominationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLegNominationMockMvc;

    private LegNomination legNomination;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LegNomination createEntity(EntityManager em) {
        LegNomination legNomination = new LegNomination()
            .customerId(DEFAULT_CUSTOMER_ID)
            .agent(DEFAULT_AGENT)
            .last3Cargoes(DEFAULT_LAST_3_CARGOES)
            .productId(DEFAULT_PRODUCT_ID)
            .ownVatNo(DEFAULT_OWN_VAT_NO)
            .vettingReference(DEFAULT_VETTING_REFERENCE)
            .warehouseAddress(DEFAULT_WAREHOUSE_ADDRESS)
            .warehouseNo(DEFAULT_WAREHOUSE_NO)
            .warehouseTaxNo(DEFAULT_WAREHOUSE_TAX_NO)
            .productReceiverVat(DEFAULT_PRODUCT_RECEIVER_VAT)
            .customsStatus(DEFAULT_CUSTOMS_STATUS)
            .documentType(DEFAULT_DOCUMENT_TYPE)
            .clientClauseDetails(DEFAULT_CLIENT_CLAUSE_DETAILS)
            .status(DEFAULT_STATUS)
            .counterPartyStatus(DEFAULT_COUNTER_PARTY_STATUS)
            .comment(DEFAULT_COMMENT)
            .nominationTimestamp(DEFAULT_NOMINATION_TIMESTAMP)
            .cnCode(DEFAULT_CN_CODE)
            .financialHold(DEFAULT_FINANCIAL_HOLD)
            .consignor(DEFAULT_CONSIGNOR)
            .blendingInstructions(DEFAULT_BLENDING_INSTRUCTIONS)
            .productAdditives(DEFAULT_PRODUCT_ADDITIVES)
            .homogenisation(DEFAULT_HOMOGENISATION)
            .productReceiverId(DEFAULT_PRODUCT_RECEIVER_ID)
            .modeOfTransport(DEFAULT_MODE_OF_TRANSPORT)
            .modeOfTransportEquipment(DEFAULT_MODE_OF_TRANSPORT_EQUIPMENT)
            .dischargeDate(DEFAULT_DISCHARGE_DATE)
            .loadportDate(DEFAULT_LOADPORT_DATE)
            .loadLocation(DEFAULT_LOAD_LOCATION)
            .dischargeLocation(DEFAULT_DISCHARGE_LOCATION)
            .pleaseAdviseWarehouseTaxNo(DEFAULT_PLEASE_ADVISE_WAREHOUSE_TAX_NO)
            .pleaseAdviseWarehouseNo(DEFAULT_PLEASE_ADVISE_WAREHOUSE_NO)
            .pleaseAdviseWarehouseAddress(DEFAULT_PLEASE_ADVISE_WAREHOUSE_ADDRESS)
            .pleaseAdviseProductReceiverVAT(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT)
            .pleaseAdviseProductReceiver(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER)
            .pleaseAdviseLoadport(DEFAULT_PLEASE_ADVISE_LOADPORT)
            .pleaseAdviseDisport(DEFAULT_PLEASE_ADVISE_DISPORT)
            .preferentialOrigin(DEFAULT_PREFERENTIAL_ORIGIN)
            .productGrade(DEFAULT_PRODUCT_GRADE)
            .productSpecification(DEFAULT_PRODUCT_SPECIFICATION)
            .productAdditionalComments(DEFAULT_PRODUCT_ADDITIONAL_COMMENTS)
            .adn(DEFAULT_ADN)
            .cdni(DEFAULT_CDNI)
            .customAdn(DEFAULT_CUSTOM_ADN)
            .customCdni(DEFAULT_CUSTOM_CDNI)
            .movementTransferType(DEFAULT_MOVEMENT_TRANSFER_TYPE)
            .customLoadLocation(DEFAULT_CUSTOM_LOAD_LOCATION)
            .customDischargeLocation(DEFAULT_CUSTOM_DISCHARGE_LOCATION)
            .customReceiverName(DEFAULT_CUSTOM_RECEIVER_NAME)
            .customReceiverAddress(DEFAULT_CUSTOM_RECEIVER_ADDRESS)
            .customConsignor(DEFAULT_CUSTOM_CONSIGNOR)
            .notes(DEFAULT_NOTES)
            .terminalCompanyLegalEntity(DEFAULT_TERMINAL_COMPANY_LEGAL_ENTITY)
            .customTerminalCompanyLegalEntity(DEFAULT_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY)
            .iceDelivery(DEFAULT_ICE_DELIVERY);
        return legNomination;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LegNomination createUpdatedEntity(EntityManager em) {
        LegNomination legNomination = new LegNomination()
            .customerId(UPDATED_CUSTOMER_ID)
            .agent(UPDATED_AGENT)
            .last3Cargoes(UPDATED_LAST_3_CARGOES)
            .productId(UPDATED_PRODUCT_ID)
            .ownVatNo(UPDATED_OWN_VAT_NO)
            .vettingReference(UPDATED_VETTING_REFERENCE)
            .warehouseAddress(UPDATED_WAREHOUSE_ADDRESS)
            .warehouseNo(UPDATED_WAREHOUSE_NO)
            .warehouseTaxNo(UPDATED_WAREHOUSE_TAX_NO)
            .productReceiverVat(UPDATED_PRODUCT_RECEIVER_VAT)
            .customsStatus(UPDATED_CUSTOMS_STATUS)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .clientClauseDetails(UPDATED_CLIENT_CLAUSE_DETAILS)
            .status(UPDATED_STATUS)
            .counterPartyStatus(UPDATED_COUNTER_PARTY_STATUS)
            .comment(UPDATED_COMMENT)
            .nominationTimestamp(UPDATED_NOMINATION_TIMESTAMP)
            .cnCode(UPDATED_CN_CODE)
            .financialHold(UPDATED_FINANCIAL_HOLD)
            .consignor(UPDATED_CONSIGNOR)
            .blendingInstructions(UPDATED_BLENDING_INSTRUCTIONS)
            .productAdditives(UPDATED_PRODUCT_ADDITIVES)
            .homogenisation(UPDATED_HOMOGENISATION)
            .productReceiverId(UPDATED_PRODUCT_RECEIVER_ID)
            .modeOfTransport(UPDATED_MODE_OF_TRANSPORT)
            .modeOfTransportEquipment(UPDATED_MODE_OF_TRANSPORT_EQUIPMENT)
            .dischargeDate(UPDATED_DISCHARGE_DATE)
            .loadportDate(UPDATED_LOADPORT_DATE)
            .loadLocation(UPDATED_LOAD_LOCATION)
            .dischargeLocation(UPDATED_DISCHARGE_LOCATION)
            .pleaseAdviseWarehouseTaxNo(UPDATED_PLEASE_ADVISE_WAREHOUSE_TAX_NO)
            .pleaseAdviseWarehouseNo(UPDATED_PLEASE_ADVISE_WAREHOUSE_NO)
            .pleaseAdviseWarehouseAddress(UPDATED_PLEASE_ADVISE_WAREHOUSE_ADDRESS)
            .pleaseAdviseProductReceiverVAT(UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT)
            .pleaseAdviseProductReceiver(UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER)
            .pleaseAdviseLoadport(UPDATED_PLEASE_ADVISE_LOADPORT)
            .pleaseAdviseDisport(UPDATED_PLEASE_ADVISE_DISPORT)
            .preferentialOrigin(UPDATED_PREFERENTIAL_ORIGIN)
            .productGrade(UPDATED_PRODUCT_GRADE)
            .productSpecification(UPDATED_PRODUCT_SPECIFICATION)
            .productAdditionalComments(UPDATED_PRODUCT_ADDITIONAL_COMMENTS)
            .adn(UPDATED_ADN)
            .cdni(UPDATED_CDNI)
            .customAdn(UPDATED_CUSTOM_ADN)
            .customCdni(UPDATED_CUSTOM_CDNI)
            .movementTransferType(UPDATED_MOVEMENT_TRANSFER_TYPE)
            .customLoadLocation(UPDATED_CUSTOM_LOAD_LOCATION)
            .customDischargeLocation(UPDATED_CUSTOM_DISCHARGE_LOCATION)
            .customReceiverName(UPDATED_CUSTOM_RECEIVER_NAME)
            .customReceiverAddress(UPDATED_CUSTOM_RECEIVER_ADDRESS)
            .customConsignor(UPDATED_CUSTOM_CONSIGNOR)
            .notes(UPDATED_NOTES)
            .terminalCompanyLegalEntity(UPDATED_TERMINAL_COMPANY_LEGAL_ENTITY)
            .customTerminalCompanyLegalEntity(UPDATED_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY)
            .iceDelivery(UPDATED_ICE_DELIVERY);
        return legNomination;
    }

    @BeforeEach
    public void initTest() {
        legNomination = createEntity(em);
    }

    @Test
    @Transactional
    public void createLegNomination() throws Exception {
        int databaseSizeBeforeCreate = legNominationRepository.findAll().size();
        // Create the LegNomination
        LegNominationDTO legNominationDTO = legNominationMapper.toDto(legNomination);
        restLegNominationMockMvc.perform(post("/api/leg-nominations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(legNominationDTO)))
            .andExpect(status().isCreated());

        // Validate the LegNomination in the database
        List<LegNomination> legNominationList = legNominationRepository.findAll();
        assertThat(legNominationList).hasSize(databaseSizeBeforeCreate + 1);
        LegNomination testLegNomination = legNominationList.get(legNominationList.size() - 1);
        assertThat(testLegNomination.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testLegNomination.getAgent()).isEqualTo(DEFAULT_AGENT);
        assertThat(testLegNomination.getLast3Cargoes()).isEqualTo(DEFAULT_LAST_3_CARGOES);
        assertThat(testLegNomination.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testLegNomination.getOwnVatNo()).isEqualTo(DEFAULT_OWN_VAT_NO);
        assertThat(testLegNomination.getVettingReference()).isEqualTo(DEFAULT_VETTING_REFERENCE);
        assertThat(testLegNomination.getWarehouseAddress()).isEqualTo(DEFAULT_WAREHOUSE_ADDRESS);
        assertThat(testLegNomination.getWarehouseNo()).isEqualTo(DEFAULT_WAREHOUSE_NO);
        assertThat(testLegNomination.getWarehouseTaxNo()).isEqualTo(DEFAULT_WAREHOUSE_TAX_NO);
        assertThat(testLegNomination.getProductReceiverVat()).isEqualTo(DEFAULT_PRODUCT_RECEIVER_VAT);
        assertThat(testLegNomination.getCustomsStatus()).isEqualTo(DEFAULT_CUSTOMS_STATUS);
        assertThat(testLegNomination.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testLegNomination.getClientClauseDetails()).isEqualTo(DEFAULT_CLIENT_CLAUSE_DETAILS);
        assertThat(testLegNomination.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLegNomination.getCounterPartyStatus()).isEqualTo(DEFAULT_COUNTER_PARTY_STATUS);
        assertThat(testLegNomination.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testLegNomination.getNominationTimestamp()).isEqualTo(DEFAULT_NOMINATION_TIMESTAMP);
        assertThat(testLegNomination.getCnCode()).isEqualTo(DEFAULT_CN_CODE);
        assertThat(testLegNomination.getFinancialHold()).isEqualTo(DEFAULT_FINANCIAL_HOLD);
        assertThat(testLegNomination.getConsignor()).isEqualTo(DEFAULT_CONSIGNOR);
        assertThat(testLegNomination.getBlendingInstructions()).isEqualTo(DEFAULT_BLENDING_INSTRUCTIONS);
        assertThat(testLegNomination.getProductAdditives()).isEqualTo(DEFAULT_PRODUCT_ADDITIVES);
        assertThat(testLegNomination.getHomogenisation()).isEqualTo(DEFAULT_HOMOGENISATION);
        assertThat(testLegNomination.getProductReceiverId()).isEqualTo(DEFAULT_PRODUCT_RECEIVER_ID);
        assertThat(testLegNomination.getModeOfTransport()).isEqualTo(DEFAULT_MODE_OF_TRANSPORT);
        assertThat(testLegNomination.getModeOfTransportEquipment()).isEqualTo(DEFAULT_MODE_OF_TRANSPORT_EQUIPMENT);
        assertThat(testLegNomination.getDischargeDate()).isEqualTo(DEFAULT_DISCHARGE_DATE);
        assertThat(testLegNomination.getLoadportDate()).isEqualTo(DEFAULT_LOADPORT_DATE);
        assertThat(testLegNomination.getLoadLocation()).isEqualTo(DEFAULT_LOAD_LOCATION);
        assertThat(testLegNomination.getDischargeLocation()).isEqualTo(DEFAULT_DISCHARGE_LOCATION);
        assertThat(testLegNomination.isPleaseAdviseWarehouseTaxNo()).isEqualTo(DEFAULT_PLEASE_ADVISE_WAREHOUSE_TAX_NO);
        assertThat(testLegNomination.isPleaseAdviseWarehouseNo()).isEqualTo(DEFAULT_PLEASE_ADVISE_WAREHOUSE_NO);
        assertThat(testLegNomination.isPleaseAdviseWarehouseAddress()).isEqualTo(DEFAULT_PLEASE_ADVISE_WAREHOUSE_ADDRESS);
        assertThat(testLegNomination.isPleaseAdviseProductReceiverVAT()).isEqualTo(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT);
        assertThat(testLegNomination.isPleaseAdviseProductReceiver()).isEqualTo(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER);
        assertThat(testLegNomination.isPleaseAdviseLoadport()).isEqualTo(DEFAULT_PLEASE_ADVISE_LOADPORT);
        assertThat(testLegNomination.isPleaseAdviseDisport()).isEqualTo(DEFAULT_PLEASE_ADVISE_DISPORT);
        assertThat(testLegNomination.getPreferentialOrigin()).isEqualTo(DEFAULT_PREFERENTIAL_ORIGIN);
        assertThat(testLegNomination.getProductGrade()).isEqualTo(DEFAULT_PRODUCT_GRADE);
        assertThat(testLegNomination.getProductSpecification()).isEqualTo(DEFAULT_PRODUCT_SPECIFICATION);
        assertThat(testLegNomination.getProductAdditionalComments()).isEqualTo(DEFAULT_PRODUCT_ADDITIONAL_COMMENTS);
        assertThat(testLegNomination.getAdn()).isEqualTo(DEFAULT_ADN);
        assertThat(testLegNomination.getCdni()).isEqualTo(DEFAULT_CDNI);
        assertThat(testLegNomination.getCustomAdn()).isEqualTo(DEFAULT_CUSTOM_ADN);
        assertThat(testLegNomination.getCustomCdni()).isEqualTo(DEFAULT_CUSTOM_CDNI);
        assertThat(testLegNomination.getMovementTransferType()).isEqualTo(DEFAULT_MOVEMENT_TRANSFER_TYPE);
        assertThat(testLegNomination.getCustomLoadLocation()).isEqualTo(DEFAULT_CUSTOM_LOAD_LOCATION);
        assertThat(testLegNomination.getCustomDischargeLocation()).isEqualTo(DEFAULT_CUSTOM_DISCHARGE_LOCATION);
        assertThat(testLegNomination.getCustomReceiverName()).isEqualTo(DEFAULT_CUSTOM_RECEIVER_NAME);
        assertThat(testLegNomination.getCustomReceiverAddress()).isEqualTo(DEFAULT_CUSTOM_RECEIVER_ADDRESS);
        assertThat(testLegNomination.getCustomConsignor()).isEqualTo(DEFAULT_CUSTOM_CONSIGNOR);
        assertThat(testLegNomination.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testLegNomination.getTerminalCompanyLegalEntity()).isEqualTo(DEFAULT_TERMINAL_COMPANY_LEGAL_ENTITY);
        assertThat(testLegNomination.getCustomTerminalCompanyLegalEntity()).isEqualTo(DEFAULT_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY);
        assertThat(testLegNomination.isIceDelivery()).isEqualTo(DEFAULT_ICE_DELIVERY);
    }

    @Test
    @Transactional
    public void createLegNominationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = legNominationRepository.findAll().size();

        // Create the LegNomination with an existing ID
        legNomination.setId(1L);
        LegNominationDTO legNominationDTO = legNominationMapper.toDto(legNomination);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLegNominationMockMvc.perform(post("/api/leg-nominations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(legNominationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LegNomination in the database
        List<LegNomination> legNominationList = legNominationRepository.findAll();
        assertThat(legNominationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLegNominations() throws Exception {
        // Initialize the database
        legNominationRepository.saveAndFlush(legNomination);

        // Get all the legNominationList
        restLegNominationMockMvc.perform(get("/api/leg-nominations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(legNomination.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].agent").value(hasItem(DEFAULT_AGENT)))
            .andExpect(jsonPath("$.[*].last3Cargoes").value(hasItem(DEFAULT_LAST_3_CARGOES)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].ownVatNo").value(hasItem(DEFAULT_OWN_VAT_NO)))
            .andExpect(jsonPath("$.[*].vettingReference").value(hasItem(DEFAULT_VETTING_REFERENCE)))
            .andExpect(jsonPath("$.[*].warehouseAddress").value(hasItem(DEFAULT_WAREHOUSE_ADDRESS)))
            .andExpect(jsonPath("$.[*].warehouseNo").value(hasItem(DEFAULT_WAREHOUSE_NO)))
            .andExpect(jsonPath("$.[*].warehouseTaxNo").value(hasItem(DEFAULT_WAREHOUSE_TAX_NO)))
            .andExpect(jsonPath("$.[*].productReceiverVat").value(hasItem(DEFAULT_PRODUCT_RECEIVER_VAT)))
            .andExpect(jsonPath("$.[*].customsStatus").value(hasItem(DEFAULT_CUSTOMS_STATUS.toString())))
            .andExpect(jsonPath("$.[*].documentType").value(hasItem(DEFAULT_DOCUMENT_TYPE)))
            .andExpect(jsonPath("$.[*].clientClauseDetails").value(hasItem(DEFAULT_CLIENT_CLAUSE_DETAILS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].counterPartyStatus").value(hasItem(DEFAULT_COUNTER_PARTY_STATUS.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].nominationTimestamp").value(hasItem(DEFAULT_NOMINATION_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].cnCode").value(hasItem(DEFAULT_CN_CODE)))
            .andExpect(jsonPath("$.[*].financialHold").value(hasItem(DEFAULT_FINANCIAL_HOLD)))
            .andExpect(jsonPath("$.[*].consignor").value(hasItem(DEFAULT_CONSIGNOR)))
            .andExpect(jsonPath("$.[*].blendingInstructions").value(hasItem(DEFAULT_BLENDING_INSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].productAdditives").value(hasItem(DEFAULT_PRODUCT_ADDITIVES)))
            .andExpect(jsonPath("$.[*].homogenisation").value(hasItem(DEFAULT_HOMOGENISATION)))
            .andExpect(jsonPath("$.[*].productReceiverId").value(hasItem(DEFAULT_PRODUCT_RECEIVER_ID)))
            .andExpect(jsonPath("$.[*].modeOfTransport").value(hasItem(DEFAULT_MODE_OF_TRANSPORT.toString())))
            .andExpect(jsonPath("$.[*].modeOfTransportEquipment").value(hasItem(DEFAULT_MODE_OF_TRANSPORT_EQUIPMENT)))
            .andExpect(jsonPath("$.[*].dischargeDate").value(hasItem(DEFAULT_DISCHARGE_DATE.toString())))
            .andExpect(jsonPath("$.[*].loadportDate").value(hasItem(DEFAULT_LOADPORT_DATE.toString())))
            .andExpect(jsonPath("$.[*].loadLocation").value(hasItem(DEFAULT_LOAD_LOCATION)))
            .andExpect(jsonPath("$.[*].dischargeLocation").value(hasItem(DEFAULT_DISCHARGE_LOCATION)))
            .andExpect(jsonPath("$.[*].pleaseAdviseWarehouseTaxNo").value(hasItem(DEFAULT_PLEASE_ADVISE_WAREHOUSE_TAX_NO.booleanValue())))
            .andExpect(jsonPath("$.[*].pleaseAdviseWarehouseNo").value(hasItem(DEFAULT_PLEASE_ADVISE_WAREHOUSE_NO.booleanValue())))
            .andExpect(jsonPath("$.[*].pleaseAdviseWarehouseAddress").value(hasItem(DEFAULT_PLEASE_ADVISE_WAREHOUSE_ADDRESS.booleanValue())))
            .andExpect(jsonPath("$.[*].pleaseAdviseProductReceiverVAT").value(hasItem(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT.booleanValue())))
            .andExpect(jsonPath("$.[*].pleaseAdviseProductReceiver").value(hasItem(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER.booleanValue())))
            .andExpect(jsonPath("$.[*].pleaseAdviseLoadport").value(hasItem(DEFAULT_PLEASE_ADVISE_LOADPORT.booleanValue())))
            .andExpect(jsonPath("$.[*].pleaseAdviseDisport").value(hasItem(DEFAULT_PLEASE_ADVISE_DISPORT.booleanValue())))
            .andExpect(jsonPath("$.[*].preferentialOrigin").value(hasItem(DEFAULT_PREFERENTIAL_ORIGIN)))
            .andExpect(jsonPath("$.[*].productGrade").value(hasItem(DEFAULT_PRODUCT_GRADE)))
            .andExpect(jsonPath("$.[*].productSpecification").value(hasItem(DEFAULT_PRODUCT_SPECIFICATION)))
            .andExpect(jsonPath("$.[*].productAdditionalComments").value(hasItem(DEFAULT_PRODUCT_ADDITIONAL_COMMENTS)))
            .andExpect(jsonPath("$.[*].adn").value(hasItem(DEFAULT_ADN)))
            .andExpect(jsonPath("$.[*].cdni").value(hasItem(DEFAULT_CDNI)))
            .andExpect(jsonPath("$.[*].customAdn").value(hasItem(DEFAULT_CUSTOM_ADN)))
            .andExpect(jsonPath("$.[*].customCdni").value(hasItem(DEFAULT_CUSTOM_CDNI)))
            .andExpect(jsonPath("$.[*].movementTransferType").value(hasItem(DEFAULT_MOVEMENT_TRANSFER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].customLoadLocation").value(hasItem(DEFAULT_CUSTOM_LOAD_LOCATION)))
            .andExpect(jsonPath("$.[*].customDischargeLocation").value(hasItem(DEFAULT_CUSTOM_DISCHARGE_LOCATION)))
            .andExpect(jsonPath("$.[*].customReceiverName").value(hasItem(DEFAULT_CUSTOM_RECEIVER_NAME)))
            .andExpect(jsonPath("$.[*].customReceiverAddress").value(hasItem(DEFAULT_CUSTOM_RECEIVER_ADDRESS)))
            .andExpect(jsonPath("$.[*].customConsignor").value(hasItem(DEFAULT_CUSTOM_CONSIGNOR)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].terminalCompanyLegalEntity").value(hasItem(DEFAULT_TERMINAL_COMPANY_LEGAL_ENTITY)))
            .andExpect(jsonPath("$.[*].customTerminalCompanyLegalEntity").value(hasItem(DEFAULT_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY)))
            .andExpect(jsonPath("$.[*].iceDelivery").value(hasItem(DEFAULT_ICE_DELIVERY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getLegNomination() throws Exception {
        // Initialize the database
        legNominationRepository.saveAndFlush(legNomination);

        // Get the legNomination
        restLegNominationMockMvc.perform(get("/api/leg-nominations/{id}", legNomination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(legNomination.getId().intValue()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.agent").value(DEFAULT_AGENT))
            .andExpect(jsonPath("$.last3Cargoes").value(DEFAULT_LAST_3_CARGOES))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.ownVatNo").value(DEFAULT_OWN_VAT_NO))
            .andExpect(jsonPath("$.vettingReference").value(DEFAULT_VETTING_REFERENCE))
            .andExpect(jsonPath("$.warehouseAddress").value(DEFAULT_WAREHOUSE_ADDRESS))
            .andExpect(jsonPath("$.warehouseNo").value(DEFAULT_WAREHOUSE_NO))
            .andExpect(jsonPath("$.warehouseTaxNo").value(DEFAULT_WAREHOUSE_TAX_NO))
            .andExpect(jsonPath("$.productReceiverVat").value(DEFAULT_PRODUCT_RECEIVER_VAT))
            .andExpect(jsonPath("$.customsStatus").value(DEFAULT_CUSTOMS_STATUS.toString()))
            .andExpect(jsonPath("$.documentType").value(DEFAULT_DOCUMENT_TYPE))
            .andExpect(jsonPath("$.clientClauseDetails").value(DEFAULT_CLIENT_CLAUSE_DETAILS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.counterPartyStatus").value(DEFAULT_COUNTER_PARTY_STATUS.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.nominationTimestamp").value(DEFAULT_NOMINATION_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.cnCode").value(DEFAULT_CN_CODE))
            .andExpect(jsonPath("$.financialHold").value(DEFAULT_FINANCIAL_HOLD))
            .andExpect(jsonPath("$.consignor").value(DEFAULT_CONSIGNOR))
            .andExpect(jsonPath("$.blendingInstructions").value(DEFAULT_BLENDING_INSTRUCTIONS))
            .andExpect(jsonPath("$.productAdditives").value(DEFAULT_PRODUCT_ADDITIVES))
            .andExpect(jsonPath("$.homogenisation").value(DEFAULT_HOMOGENISATION))
            .andExpect(jsonPath("$.productReceiverId").value(DEFAULT_PRODUCT_RECEIVER_ID))
            .andExpect(jsonPath("$.modeOfTransport").value(DEFAULT_MODE_OF_TRANSPORT.toString()))
            .andExpect(jsonPath("$.modeOfTransportEquipment").value(DEFAULT_MODE_OF_TRANSPORT_EQUIPMENT))
            .andExpect(jsonPath("$.dischargeDate").value(DEFAULT_DISCHARGE_DATE.toString()))
            .andExpect(jsonPath("$.loadportDate").value(DEFAULT_LOADPORT_DATE.toString()))
            .andExpect(jsonPath("$.loadLocation").value(DEFAULT_LOAD_LOCATION))
            .andExpect(jsonPath("$.dischargeLocation").value(DEFAULT_DISCHARGE_LOCATION))
            .andExpect(jsonPath("$.pleaseAdviseWarehouseTaxNo").value(DEFAULT_PLEASE_ADVISE_WAREHOUSE_TAX_NO.booleanValue()))
            .andExpect(jsonPath("$.pleaseAdviseWarehouseNo").value(DEFAULT_PLEASE_ADVISE_WAREHOUSE_NO.booleanValue()))
            .andExpect(jsonPath("$.pleaseAdviseWarehouseAddress").value(DEFAULT_PLEASE_ADVISE_WAREHOUSE_ADDRESS.booleanValue()))
            .andExpect(jsonPath("$.pleaseAdviseProductReceiverVAT").value(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT.booleanValue()))
            .andExpect(jsonPath("$.pleaseAdviseProductReceiver").value(DEFAULT_PLEASE_ADVISE_PRODUCT_RECEIVER.booleanValue()))
            .andExpect(jsonPath("$.pleaseAdviseLoadport").value(DEFAULT_PLEASE_ADVISE_LOADPORT.booleanValue()))
            .andExpect(jsonPath("$.pleaseAdviseDisport").value(DEFAULT_PLEASE_ADVISE_DISPORT.booleanValue()))
            .andExpect(jsonPath("$.preferentialOrigin").value(DEFAULT_PREFERENTIAL_ORIGIN))
            .andExpect(jsonPath("$.productGrade").value(DEFAULT_PRODUCT_GRADE))
            .andExpect(jsonPath("$.productSpecification").value(DEFAULT_PRODUCT_SPECIFICATION))
            .andExpect(jsonPath("$.productAdditionalComments").value(DEFAULT_PRODUCT_ADDITIONAL_COMMENTS))
            .andExpect(jsonPath("$.adn").value(DEFAULT_ADN))
            .andExpect(jsonPath("$.cdni").value(DEFAULT_CDNI))
            .andExpect(jsonPath("$.customAdn").value(DEFAULT_CUSTOM_ADN))
            .andExpect(jsonPath("$.customCdni").value(DEFAULT_CUSTOM_CDNI))
            .andExpect(jsonPath("$.movementTransferType").value(DEFAULT_MOVEMENT_TRANSFER_TYPE.toString()))
            .andExpect(jsonPath("$.customLoadLocation").value(DEFAULT_CUSTOM_LOAD_LOCATION))
            .andExpect(jsonPath("$.customDischargeLocation").value(DEFAULT_CUSTOM_DISCHARGE_LOCATION))
            .andExpect(jsonPath("$.customReceiverName").value(DEFAULT_CUSTOM_RECEIVER_NAME))
            .andExpect(jsonPath("$.customReceiverAddress").value(DEFAULT_CUSTOM_RECEIVER_ADDRESS))
            .andExpect(jsonPath("$.customConsignor").value(DEFAULT_CUSTOM_CONSIGNOR))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.terminalCompanyLegalEntity").value(DEFAULT_TERMINAL_COMPANY_LEGAL_ENTITY))
            .andExpect(jsonPath("$.customTerminalCompanyLegalEntity").value(DEFAULT_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY))
            .andExpect(jsonPath("$.iceDelivery").value(DEFAULT_ICE_DELIVERY.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingLegNomination() throws Exception {
        // Get the legNomination
        restLegNominationMockMvc.perform(get("/api/leg-nominations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLegNomination() throws Exception {
        // Initialize the database
        legNominationRepository.saveAndFlush(legNomination);

        int databaseSizeBeforeUpdate = legNominationRepository.findAll().size();

        // Update the legNomination
        LegNomination updatedLegNomination = legNominationRepository.findById(legNomination.getId()).get();
        // Disconnect from session so that the updates on updatedLegNomination are not directly saved in db
        em.detach(updatedLegNomination);
        updatedLegNomination
            .customerId(UPDATED_CUSTOMER_ID)
            .agent(UPDATED_AGENT)
            .last3Cargoes(UPDATED_LAST_3_CARGOES)
            .productId(UPDATED_PRODUCT_ID)
            .ownVatNo(UPDATED_OWN_VAT_NO)
            .vettingReference(UPDATED_VETTING_REFERENCE)
            .warehouseAddress(UPDATED_WAREHOUSE_ADDRESS)
            .warehouseNo(UPDATED_WAREHOUSE_NO)
            .warehouseTaxNo(UPDATED_WAREHOUSE_TAX_NO)
            .productReceiverVat(UPDATED_PRODUCT_RECEIVER_VAT)
            .customsStatus(UPDATED_CUSTOMS_STATUS)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .clientClauseDetails(UPDATED_CLIENT_CLAUSE_DETAILS)
            .status(UPDATED_STATUS)
            .counterPartyStatus(UPDATED_COUNTER_PARTY_STATUS)
            .comment(UPDATED_COMMENT)
            .nominationTimestamp(UPDATED_NOMINATION_TIMESTAMP)
            .cnCode(UPDATED_CN_CODE)
            .financialHold(UPDATED_FINANCIAL_HOLD)
            .consignor(UPDATED_CONSIGNOR)
            .blendingInstructions(UPDATED_BLENDING_INSTRUCTIONS)
            .productAdditives(UPDATED_PRODUCT_ADDITIVES)
            .homogenisation(UPDATED_HOMOGENISATION)
            .productReceiverId(UPDATED_PRODUCT_RECEIVER_ID)
            .modeOfTransport(UPDATED_MODE_OF_TRANSPORT)
            .modeOfTransportEquipment(UPDATED_MODE_OF_TRANSPORT_EQUIPMENT)
            .dischargeDate(UPDATED_DISCHARGE_DATE)
            .loadportDate(UPDATED_LOADPORT_DATE)
            .loadLocation(UPDATED_LOAD_LOCATION)
            .dischargeLocation(UPDATED_DISCHARGE_LOCATION)
            .pleaseAdviseWarehouseTaxNo(UPDATED_PLEASE_ADVISE_WAREHOUSE_TAX_NO)
            .pleaseAdviseWarehouseNo(UPDATED_PLEASE_ADVISE_WAREHOUSE_NO)
            .pleaseAdviseWarehouseAddress(UPDATED_PLEASE_ADVISE_WAREHOUSE_ADDRESS)
            .pleaseAdviseProductReceiverVAT(UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT)
            .pleaseAdviseProductReceiver(UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER)
            .pleaseAdviseLoadport(UPDATED_PLEASE_ADVISE_LOADPORT)
            .pleaseAdviseDisport(UPDATED_PLEASE_ADVISE_DISPORT)
            .preferentialOrigin(UPDATED_PREFERENTIAL_ORIGIN)
            .productGrade(UPDATED_PRODUCT_GRADE)
            .productSpecification(UPDATED_PRODUCT_SPECIFICATION)
            .productAdditionalComments(UPDATED_PRODUCT_ADDITIONAL_COMMENTS)
            .adn(UPDATED_ADN)
            .cdni(UPDATED_CDNI)
            .customAdn(UPDATED_CUSTOM_ADN)
            .customCdni(UPDATED_CUSTOM_CDNI)
            .movementTransferType(UPDATED_MOVEMENT_TRANSFER_TYPE)
            .customLoadLocation(UPDATED_CUSTOM_LOAD_LOCATION)
            .customDischargeLocation(UPDATED_CUSTOM_DISCHARGE_LOCATION)
            .customReceiverName(UPDATED_CUSTOM_RECEIVER_NAME)
            .customReceiverAddress(UPDATED_CUSTOM_RECEIVER_ADDRESS)
            .customConsignor(UPDATED_CUSTOM_CONSIGNOR)
            .notes(UPDATED_NOTES)
            .terminalCompanyLegalEntity(UPDATED_TERMINAL_COMPANY_LEGAL_ENTITY)
            .customTerminalCompanyLegalEntity(UPDATED_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY)
            .iceDelivery(UPDATED_ICE_DELIVERY);
        LegNominationDTO legNominationDTO = legNominationMapper.toDto(updatedLegNomination);

        restLegNominationMockMvc.perform(put("/api/leg-nominations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(legNominationDTO)))
            .andExpect(status().isOk());

        // Validate the LegNomination in the database
        List<LegNomination> legNominationList = legNominationRepository.findAll();
        assertThat(legNominationList).hasSize(databaseSizeBeforeUpdate);
        LegNomination testLegNomination = legNominationList.get(legNominationList.size() - 1);
        assertThat(testLegNomination.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testLegNomination.getAgent()).isEqualTo(UPDATED_AGENT);
        assertThat(testLegNomination.getLast3Cargoes()).isEqualTo(UPDATED_LAST_3_CARGOES);
        assertThat(testLegNomination.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testLegNomination.getOwnVatNo()).isEqualTo(UPDATED_OWN_VAT_NO);
        assertThat(testLegNomination.getVettingReference()).isEqualTo(UPDATED_VETTING_REFERENCE);
        assertThat(testLegNomination.getWarehouseAddress()).isEqualTo(UPDATED_WAREHOUSE_ADDRESS);
        assertThat(testLegNomination.getWarehouseNo()).isEqualTo(UPDATED_WAREHOUSE_NO);
        assertThat(testLegNomination.getWarehouseTaxNo()).isEqualTo(UPDATED_WAREHOUSE_TAX_NO);
        assertThat(testLegNomination.getProductReceiverVat()).isEqualTo(UPDATED_PRODUCT_RECEIVER_VAT);
        assertThat(testLegNomination.getCustomsStatus()).isEqualTo(UPDATED_CUSTOMS_STATUS);
        assertThat(testLegNomination.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testLegNomination.getClientClauseDetails()).isEqualTo(UPDATED_CLIENT_CLAUSE_DETAILS);
        assertThat(testLegNomination.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLegNomination.getCounterPartyStatus()).isEqualTo(UPDATED_COUNTER_PARTY_STATUS);
        assertThat(testLegNomination.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testLegNomination.getNominationTimestamp()).isEqualTo(UPDATED_NOMINATION_TIMESTAMP);
        assertThat(testLegNomination.getCnCode()).isEqualTo(UPDATED_CN_CODE);
        assertThat(testLegNomination.getFinancialHold()).isEqualTo(UPDATED_FINANCIAL_HOLD);
        assertThat(testLegNomination.getConsignor()).isEqualTo(UPDATED_CONSIGNOR);
        assertThat(testLegNomination.getBlendingInstructions()).isEqualTo(UPDATED_BLENDING_INSTRUCTIONS);
        assertThat(testLegNomination.getProductAdditives()).isEqualTo(UPDATED_PRODUCT_ADDITIVES);
        assertThat(testLegNomination.getHomogenisation()).isEqualTo(UPDATED_HOMOGENISATION);
        assertThat(testLegNomination.getProductReceiverId()).isEqualTo(UPDATED_PRODUCT_RECEIVER_ID);
        assertThat(testLegNomination.getModeOfTransport()).isEqualTo(UPDATED_MODE_OF_TRANSPORT);
        assertThat(testLegNomination.getModeOfTransportEquipment()).isEqualTo(UPDATED_MODE_OF_TRANSPORT_EQUIPMENT);
        assertThat(testLegNomination.getDischargeDate()).isEqualTo(UPDATED_DISCHARGE_DATE);
        assertThat(testLegNomination.getLoadportDate()).isEqualTo(UPDATED_LOADPORT_DATE);
        assertThat(testLegNomination.getLoadLocation()).isEqualTo(UPDATED_LOAD_LOCATION);
        assertThat(testLegNomination.getDischargeLocation()).isEqualTo(UPDATED_DISCHARGE_LOCATION);
        assertThat(testLegNomination.isPleaseAdviseWarehouseTaxNo()).isEqualTo(UPDATED_PLEASE_ADVISE_WAREHOUSE_TAX_NO);
        assertThat(testLegNomination.isPleaseAdviseWarehouseNo()).isEqualTo(UPDATED_PLEASE_ADVISE_WAREHOUSE_NO);
        assertThat(testLegNomination.isPleaseAdviseWarehouseAddress()).isEqualTo(UPDATED_PLEASE_ADVISE_WAREHOUSE_ADDRESS);
        assertThat(testLegNomination.isPleaseAdviseProductReceiverVAT()).isEqualTo(UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER_VAT);
        assertThat(testLegNomination.isPleaseAdviseProductReceiver()).isEqualTo(UPDATED_PLEASE_ADVISE_PRODUCT_RECEIVER);
        assertThat(testLegNomination.isPleaseAdviseLoadport()).isEqualTo(UPDATED_PLEASE_ADVISE_LOADPORT);
        assertThat(testLegNomination.isPleaseAdviseDisport()).isEqualTo(UPDATED_PLEASE_ADVISE_DISPORT);
        assertThat(testLegNomination.getPreferentialOrigin()).isEqualTo(UPDATED_PREFERENTIAL_ORIGIN);
        assertThat(testLegNomination.getProductGrade()).isEqualTo(UPDATED_PRODUCT_GRADE);
        assertThat(testLegNomination.getProductSpecification()).isEqualTo(UPDATED_PRODUCT_SPECIFICATION);
        assertThat(testLegNomination.getProductAdditionalComments()).isEqualTo(UPDATED_PRODUCT_ADDITIONAL_COMMENTS);
        assertThat(testLegNomination.getAdn()).isEqualTo(UPDATED_ADN);
        assertThat(testLegNomination.getCdni()).isEqualTo(UPDATED_CDNI);
        assertThat(testLegNomination.getCustomAdn()).isEqualTo(UPDATED_CUSTOM_ADN);
        assertThat(testLegNomination.getCustomCdni()).isEqualTo(UPDATED_CUSTOM_CDNI);
        assertThat(testLegNomination.getMovementTransferType()).isEqualTo(UPDATED_MOVEMENT_TRANSFER_TYPE);
        assertThat(testLegNomination.getCustomLoadLocation()).isEqualTo(UPDATED_CUSTOM_LOAD_LOCATION);
        assertThat(testLegNomination.getCustomDischargeLocation()).isEqualTo(UPDATED_CUSTOM_DISCHARGE_LOCATION);
        assertThat(testLegNomination.getCustomReceiverName()).isEqualTo(UPDATED_CUSTOM_RECEIVER_NAME);
        assertThat(testLegNomination.getCustomReceiverAddress()).isEqualTo(UPDATED_CUSTOM_RECEIVER_ADDRESS);
        assertThat(testLegNomination.getCustomConsignor()).isEqualTo(UPDATED_CUSTOM_CONSIGNOR);
        assertThat(testLegNomination.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testLegNomination.getTerminalCompanyLegalEntity()).isEqualTo(UPDATED_TERMINAL_COMPANY_LEGAL_ENTITY);
        assertThat(testLegNomination.getCustomTerminalCompanyLegalEntity()).isEqualTo(UPDATED_CUSTOM_TERMINAL_COMPANY_LEGAL_ENTITY);
        assertThat(testLegNomination.isIceDelivery()).isEqualTo(UPDATED_ICE_DELIVERY);
    }

    @Test
    @Transactional
    public void updateNonExistingLegNomination() throws Exception {
        int databaseSizeBeforeUpdate = legNominationRepository.findAll().size();

        // Create the LegNomination
        LegNominationDTO legNominationDTO = legNominationMapper.toDto(legNomination);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLegNominationMockMvc.perform(put("/api/leg-nominations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(legNominationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LegNomination in the database
        List<LegNomination> legNominationList = legNominationRepository.findAll();
        assertThat(legNominationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLegNomination() throws Exception {
        // Initialize the database
        legNominationRepository.saveAndFlush(legNomination);

        int databaseSizeBeforeDelete = legNominationRepository.findAll().size();

        // Delete the legNomination
        restLegNominationMockMvc.perform(delete("/api/leg-nominations/{id}", legNomination.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LegNomination> legNominationList = legNominationRepository.findAll();
        assertThat(legNominationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
