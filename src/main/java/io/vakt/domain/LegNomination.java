package io.vakt.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import io.vakt.domain.enumeration.CustomsStatus;

import io.vakt.domain.enumeration.NominationStatus;

import io.vakt.domain.enumeration.ModeOfTransport;

import io.vakt.domain.enumeration.MovementTransferType;

/**
 * A LegNomination.
 */
@Entity
@Table(name = "leg_nomination")
public class LegNomination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "agent")
    private String agent;

    @Column(name = "last_3_cargoes")
    private String last3Cargoes;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "own_vat_no")
    private String ownVatNo;

    @Column(name = "vetting_reference")
    private String vettingReference;

    @Column(name = "warehouse_address")
    private String warehouseAddress;

    @Column(name = "warehouse_no")
    private String warehouseNo;

    @Column(name = "warehouse_tax_no")
    private String warehouseTaxNo;

    @Column(name = "product_receiver_vat")
    private String productReceiverVat;

    @Enumerated(EnumType.STRING)
    @Column(name = "customs_status")
    private CustomsStatus customsStatus;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "client_clause_details")
    private String clientClauseDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NominationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "counter_party_status")
    private NominationStatus counterPartyStatus;

    @Column(name = "comment")
    private String comment;

    @Column(name = "nomination_timestamp")
    private Instant nominationTimestamp;

    @Column(name = "cn_code")
    private String cnCode;

    @Column(name = "financial_hold")
    private String financialHold;

    @Column(name = "consignor")
    private String consignor;

    @Column(name = "blending_instructions")
    private String blendingInstructions;

    @Column(name = "product_additives")
    private String productAdditives;

    @Column(name = "homogenisation")
    private String homogenisation;

    @Column(name = "product_receiver_id")
    private String productReceiverId;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_of_transport")
    private ModeOfTransport modeOfTransport;

    @Column(name = "mode_of_transport_equipment")
    private String modeOfTransportEquipment;

    @Column(name = "discharge_date")
    private LocalDate dischargeDate;

    @Column(name = "loadport_date")
    private LocalDate loadportDate;

    @Column(name = "load_location")
    private String loadLocation;

    @Column(name = "discharge_location")
    private String dischargeLocation;

    @Column(name = "please_advise_warehouse_tax_no")
    private Boolean pleaseAdviseWarehouseTaxNo;

    @Column(name = "please_advise_warehouse_no")
    private Boolean pleaseAdviseWarehouseNo;

    @Column(name = "please_advise_warehouse_address")
    private Boolean pleaseAdviseWarehouseAddress;

    @Column(name = "please_advise_product_receiver_vat")
    private Boolean pleaseAdviseProductReceiverVAT;

    @Column(name = "please_advise_product_receiver")
    private Boolean pleaseAdviseProductReceiver;

    @Column(name = "please_advise_loadport")
    private Boolean pleaseAdviseLoadport;

    @Column(name = "please_advise_disport")
    private Boolean pleaseAdviseDisport;

    @Column(name = "preferential_origin")
    private String preferentialOrigin;

    @Column(name = "product_grade")
    private String productGrade;

    @Column(name = "product_specification")
    private String productSpecification;

    @Column(name = "product_additional_comments")
    private String productAdditionalComments;

    @Column(name = "adn")
    private String adn;

    @Column(name = "cdni")
    private String cdni;

    @Column(name = "custom_adn")
    private String customAdn;

    @Column(name = "custom_cdni")
    private String customCdni;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_transfer_type")
    private MovementTransferType movementTransferType;

    @Column(name = "custom_load_location")
    private String customLoadLocation;

    @Column(name = "custom_discharge_location")
    private String customDischargeLocation;

    @Column(name = "custom_receiver_name")
    private String customReceiverName;

    @Column(name = "custom_receiver_address")
    private String customReceiverAddress;

    @Column(name = "custom_consignor")
    private String customConsignor;

    @Column(name = "notes")
    private String notes;

    @Column(name = "terminal_company_legal_entity")
    private String terminalCompanyLegalEntity;

    @Column(name = "custom_terminal_company_legal_entity")
    private String customTerminalCompanyLegalEntity;

    @Column(name = "ice_delivery")
    private Boolean iceDelivery;

    @OneToOne
    @JoinColumn(unique = true)
    private NominationMetadata nominationMetadata;

    @OneToMany(mappedBy = "legNomination")
    private Set<Parcel> parcels = new HashSet<>();

    @OneToMany(mappedBy = "legNomination")
    private Set<BuildDraw> buildDraws = new HashSet<>();

    @OneToMany(mappedBy = "legNomination")
    private Set<PlaceholderParcel> placeholderParcels = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LegNomination customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAgent() {
        return agent;
    }

    public LegNomination agent(String agent) {
        this.agent = agent;
        return this;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getLast3Cargoes() {
        return last3Cargoes;
    }

    public LegNomination last3Cargoes(String last3Cargoes) {
        this.last3Cargoes = last3Cargoes;
        return this;
    }

    public void setLast3Cargoes(String last3Cargoes) {
        this.last3Cargoes = last3Cargoes;
    }

    public String getProductId() {
        return productId;
    }

    public LegNomination productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOwnVatNo() {
        return ownVatNo;
    }

    public LegNomination ownVatNo(String ownVatNo) {
        this.ownVatNo = ownVatNo;
        return this;
    }

    public void setOwnVatNo(String ownVatNo) {
        this.ownVatNo = ownVatNo;
    }

    public String getVettingReference() {
        return vettingReference;
    }

    public LegNomination vettingReference(String vettingReference) {
        this.vettingReference = vettingReference;
        return this;
    }

    public void setVettingReference(String vettingReference) {
        this.vettingReference = vettingReference;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public LegNomination warehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
        return this;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public LegNomination warehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
        return this;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getWarehouseTaxNo() {
        return warehouseTaxNo;
    }

    public LegNomination warehouseTaxNo(String warehouseTaxNo) {
        this.warehouseTaxNo = warehouseTaxNo;
        return this;
    }

    public void setWarehouseTaxNo(String warehouseTaxNo) {
        this.warehouseTaxNo = warehouseTaxNo;
    }

    public String getProductReceiverVat() {
        return productReceiverVat;
    }

    public LegNomination productReceiverVat(String productReceiverVat) {
        this.productReceiverVat = productReceiverVat;
        return this;
    }

    public void setProductReceiverVat(String productReceiverVat) {
        this.productReceiverVat = productReceiverVat;
    }

    public CustomsStatus getCustomsStatus() {
        return customsStatus;
    }

    public LegNomination customsStatus(CustomsStatus customsStatus) {
        this.customsStatus = customsStatus;
        return this;
    }

    public void setCustomsStatus(CustomsStatus customsStatus) {
        this.customsStatus = customsStatus;
    }

    public String getDocumentType() {
        return documentType;
    }

    public LegNomination documentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getClientClauseDetails() {
        return clientClauseDetails;
    }

    public LegNomination clientClauseDetails(String clientClauseDetails) {
        this.clientClauseDetails = clientClauseDetails;
        return this;
    }

    public void setClientClauseDetails(String clientClauseDetails) {
        this.clientClauseDetails = clientClauseDetails;
    }

    public NominationStatus getStatus() {
        return status;
    }

    public LegNomination status(NominationStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(NominationStatus status) {
        this.status = status;
    }

    public NominationStatus getCounterPartyStatus() {
        return counterPartyStatus;
    }

    public LegNomination counterPartyStatus(NominationStatus counterPartyStatus) {
        this.counterPartyStatus = counterPartyStatus;
        return this;
    }

    public void setCounterPartyStatus(NominationStatus counterPartyStatus) {
        this.counterPartyStatus = counterPartyStatus;
    }

    public String getComment() {
        return comment;
    }

    public LegNomination comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getNominationTimestamp() {
        return nominationTimestamp;
    }

    public LegNomination nominationTimestamp(Instant nominationTimestamp) {
        this.nominationTimestamp = nominationTimestamp;
        return this;
    }

    public void setNominationTimestamp(Instant nominationTimestamp) {
        this.nominationTimestamp = nominationTimestamp;
    }

    public String getCnCode() {
        return cnCode;
    }

    public LegNomination cnCode(String cnCode) {
        this.cnCode = cnCode;
        return this;
    }

    public void setCnCode(String cnCode) {
        this.cnCode = cnCode;
    }

    public String getFinancialHold() {
        return financialHold;
    }

    public LegNomination financialHold(String financialHold) {
        this.financialHold = financialHold;
        return this;
    }

    public void setFinancialHold(String financialHold) {
        this.financialHold = financialHold;
    }

    public String getConsignor() {
        return consignor;
    }

    public LegNomination consignor(String consignor) {
        this.consignor = consignor;
        return this;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getBlendingInstructions() {
        return blendingInstructions;
    }

    public LegNomination blendingInstructions(String blendingInstructions) {
        this.blendingInstructions = blendingInstructions;
        return this;
    }

    public void setBlendingInstructions(String blendingInstructions) {
        this.blendingInstructions = blendingInstructions;
    }

    public String getProductAdditives() {
        return productAdditives;
    }

    public LegNomination productAdditives(String productAdditives) {
        this.productAdditives = productAdditives;
        return this;
    }

    public void setProductAdditives(String productAdditives) {
        this.productAdditives = productAdditives;
    }

    public String getHomogenisation() {
        return homogenisation;
    }

    public LegNomination homogenisation(String homogenisation) {
        this.homogenisation = homogenisation;
        return this;
    }

    public void setHomogenisation(String homogenisation) {
        this.homogenisation = homogenisation;
    }

    public String getProductReceiverId() {
        return productReceiverId;
    }

    public LegNomination productReceiverId(String productReceiverId) {
        this.productReceiverId = productReceiverId;
        return this;
    }

    public void setProductReceiverId(String productReceiverId) {
        this.productReceiverId = productReceiverId;
    }

    public ModeOfTransport getModeOfTransport() {
        return modeOfTransport;
    }

    public LegNomination modeOfTransport(ModeOfTransport modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
        return this;
    }

    public void setModeOfTransport(ModeOfTransport modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public String getModeOfTransportEquipment() {
        return modeOfTransportEquipment;
    }

    public LegNomination modeOfTransportEquipment(String modeOfTransportEquipment) {
        this.modeOfTransportEquipment = modeOfTransportEquipment;
        return this;
    }

    public void setModeOfTransportEquipment(String modeOfTransportEquipment) {
        this.modeOfTransportEquipment = modeOfTransportEquipment;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public LegNomination dischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
        return this;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public LocalDate getLoadportDate() {
        return loadportDate;
    }

    public LegNomination loadportDate(LocalDate loadportDate) {
        this.loadportDate = loadportDate;
        return this;
    }

    public void setLoadportDate(LocalDate loadportDate) {
        this.loadportDate = loadportDate;
    }

    public String getLoadLocation() {
        return loadLocation;
    }

    public LegNomination loadLocation(String loadLocation) {
        this.loadLocation = loadLocation;
        return this;
    }

    public void setLoadLocation(String loadLocation) {
        this.loadLocation = loadLocation;
    }

    public String getDischargeLocation() {
        return dischargeLocation;
    }

    public LegNomination dischargeLocation(String dischargeLocation) {
        this.dischargeLocation = dischargeLocation;
        return this;
    }

    public void setDischargeLocation(String dischargeLocation) {
        this.dischargeLocation = dischargeLocation;
    }

    public Boolean isPleaseAdviseWarehouseTaxNo() {
        return pleaseAdviseWarehouseTaxNo;
    }

    public LegNomination pleaseAdviseWarehouseTaxNo(Boolean pleaseAdviseWarehouseTaxNo) {
        this.pleaseAdviseWarehouseTaxNo = pleaseAdviseWarehouseTaxNo;
        return this;
    }

    public void setPleaseAdviseWarehouseTaxNo(Boolean pleaseAdviseWarehouseTaxNo) {
        this.pleaseAdviseWarehouseTaxNo = pleaseAdviseWarehouseTaxNo;
    }

    public Boolean isPleaseAdviseWarehouseNo() {
        return pleaseAdviseWarehouseNo;
    }

    public LegNomination pleaseAdviseWarehouseNo(Boolean pleaseAdviseWarehouseNo) {
        this.pleaseAdviseWarehouseNo = pleaseAdviseWarehouseNo;
        return this;
    }

    public void setPleaseAdviseWarehouseNo(Boolean pleaseAdviseWarehouseNo) {
        this.pleaseAdviseWarehouseNo = pleaseAdviseWarehouseNo;
    }

    public Boolean isPleaseAdviseWarehouseAddress() {
        return pleaseAdviseWarehouseAddress;
    }

    public LegNomination pleaseAdviseWarehouseAddress(Boolean pleaseAdviseWarehouseAddress) {
        this.pleaseAdviseWarehouseAddress = pleaseAdviseWarehouseAddress;
        return this;
    }

    public void setPleaseAdviseWarehouseAddress(Boolean pleaseAdviseWarehouseAddress) {
        this.pleaseAdviseWarehouseAddress = pleaseAdviseWarehouseAddress;
    }

    public Boolean isPleaseAdviseProductReceiverVAT() {
        return pleaseAdviseProductReceiverVAT;
    }

    public LegNomination pleaseAdviseProductReceiverVAT(Boolean pleaseAdviseProductReceiverVAT) {
        this.pleaseAdviseProductReceiverVAT = pleaseAdviseProductReceiverVAT;
        return this;
    }

    public void setPleaseAdviseProductReceiverVAT(Boolean pleaseAdviseProductReceiverVAT) {
        this.pleaseAdviseProductReceiverVAT = pleaseAdviseProductReceiverVAT;
    }

    public Boolean isPleaseAdviseProductReceiver() {
        return pleaseAdviseProductReceiver;
    }

    public LegNomination pleaseAdviseProductReceiver(Boolean pleaseAdviseProductReceiver) {
        this.pleaseAdviseProductReceiver = pleaseAdviseProductReceiver;
        return this;
    }

    public void setPleaseAdviseProductReceiver(Boolean pleaseAdviseProductReceiver) {
        this.pleaseAdviseProductReceiver = pleaseAdviseProductReceiver;
    }

    public Boolean isPleaseAdviseLoadport() {
        return pleaseAdviseLoadport;
    }

    public LegNomination pleaseAdviseLoadport(Boolean pleaseAdviseLoadport) {
        this.pleaseAdviseLoadport = pleaseAdviseLoadport;
        return this;
    }

    public void setPleaseAdviseLoadport(Boolean pleaseAdviseLoadport) {
        this.pleaseAdviseLoadport = pleaseAdviseLoadport;
    }

    public Boolean isPleaseAdviseDisport() {
        return pleaseAdviseDisport;
    }

    public LegNomination pleaseAdviseDisport(Boolean pleaseAdviseDisport) {
        this.pleaseAdviseDisport = pleaseAdviseDisport;
        return this;
    }

    public void setPleaseAdviseDisport(Boolean pleaseAdviseDisport) {
        this.pleaseAdviseDisport = pleaseAdviseDisport;
    }

    public String getPreferentialOrigin() {
        return preferentialOrigin;
    }

    public LegNomination preferentialOrigin(String preferentialOrigin) {
        this.preferentialOrigin = preferentialOrigin;
        return this;
    }

    public void setPreferentialOrigin(String preferentialOrigin) {
        this.preferentialOrigin = preferentialOrigin;
    }

    public String getProductGrade() {
        return productGrade;
    }

    public LegNomination productGrade(String productGrade) {
        this.productGrade = productGrade;
        return this;
    }

    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade;
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public LegNomination productSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
        return this;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getProductAdditionalComments() {
        return productAdditionalComments;
    }

    public LegNomination productAdditionalComments(String productAdditionalComments) {
        this.productAdditionalComments = productAdditionalComments;
        return this;
    }

    public void setProductAdditionalComments(String productAdditionalComments) {
        this.productAdditionalComments = productAdditionalComments;
    }

    public String getAdn() {
        return adn;
    }

    public LegNomination adn(String adn) {
        this.adn = adn;
        return this;
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public String getCdni() {
        return cdni;
    }

    public LegNomination cdni(String cdni) {
        this.cdni = cdni;
        return this;
    }

    public void setCdni(String cdni) {
        this.cdni = cdni;
    }

    public String getCustomAdn() {
        return customAdn;
    }

    public LegNomination customAdn(String customAdn) {
        this.customAdn = customAdn;
        return this;
    }

    public void setCustomAdn(String customAdn) {
        this.customAdn = customAdn;
    }

    public String getCustomCdni() {
        return customCdni;
    }

    public LegNomination customCdni(String customCdni) {
        this.customCdni = customCdni;
        return this;
    }

    public void setCustomCdni(String customCdni) {
        this.customCdni = customCdni;
    }

    public MovementTransferType getMovementTransferType() {
        return movementTransferType;
    }

    public LegNomination movementTransferType(MovementTransferType movementTransferType) {
        this.movementTransferType = movementTransferType;
        return this;
    }

    public void setMovementTransferType(MovementTransferType movementTransferType) {
        this.movementTransferType = movementTransferType;
    }

    public String getCustomLoadLocation() {
        return customLoadLocation;
    }

    public LegNomination customLoadLocation(String customLoadLocation) {
        this.customLoadLocation = customLoadLocation;
        return this;
    }

    public void setCustomLoadLocation(String customLoadLocation) {
        this.customLoadLocation = customLoadLocation;
    }

    public String getCustomDischargeLocation() {
        return customDischargeLocation;
    }

    public LegNomination customDischargeLocation(String customDischargeLocation) {
        this.customDischargeLocation = customDischargeLocation;
        return this;
    }

    public void setCustomDischargeLocation(String customDischargeLocation) {
        this.customDischargeLocation = customDischargeLocation;
    }

    public String getCustomReceiverName() {
        return customReceiverName;
    }

    public LegNomination customReceiverName(String customReceiverName) {
        this.customReceiverName = customReceiverName;
        return this;
    }

    public void setCustomReceiverName(String customReceiverName) {
        this.customReceiverName = customReceiverName;
    }

    public String getCustomReceiverAddress() {
        return customReceiverAddress;
    }

    public LegNomination customReceiverAddress(String customReceiverAddress) {
        this.customReceiverAddress = customReceiverAddress;
        return this;
    }

    public void setCustomReceiverAddress(String customReceiverAddress) {
        this.customReceiverAddress = customReceiverAddress;
    }

    public String getCustomConsignor() {
        return customConsignor;
    }

    public LegNomination customConsignor(String customConsignor) {
        this.customConsignor = customConsignor;
        return this;
    }

    public void setCustomConsignor(String customConsignor) {
        this.customConsignor = customConsignor;
    }

    public String getNotes() {
        return notes;
    }

    public LegNomination notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTerminalCompanyLegalEntity() {
        return terminalCompanyLegalEntity;
    }

    public LegNomination terminalCompanyLegalEntity(String terminalCompanyLegalEntity) {
        this.terminalCompanyLegalEntity = terminalCompanyLegalEntity;
        return this;
    }

    public void setTerminalCompanyLegalEntity(String terminalCompanyLegalEntity) {
        this.terminalCompanyLegalEntity = terminalCompanyLegalEntity;
    }

    public String getCustomTerminalCompanyLegalEntity() {
        return customTerminalCompanyLegalEntity;
    }

    public LegNomination customTerminalCompanyLegalEntity(String customTerminalCompanyLegalEntity) {
        this.customTerminalCompanyLegalEntity = customTerminalCompanyLegalEntity;
        return this;
    }

    public void setCustomTerminalCompanyLegalEntity(String customTerminalCompanyLegalEntity) {
        this.customTerminalCompanyLegalEntity = customTerminalCompanyLegalEntity;
    }

    public Boolean isIceDelivery() {
        return iceDelivery;
    }

    public LegNomination iceDelivery(Boolean iceDelivery) {
        this.iceDelivery = iceDelivery;
        return this;
    }

    public void setIceDelivery(Boolean iceDelivery) {
        this.iceDelivery = iceDelivery;
    }

    public NominationMetadata getNominationMetadata() {
        return nominationMetadata;
    }

    public LegNomination nominationMetadata(NominationMetadata nominationMetadata) {
        this.nominationMetadata = nominationMetadata;
        return this;
    }

    public void setNominationMetadata(NominationMetadata nominationMetadata) {
        this.nominationMetadata = nominationMetadata;
    }

    public Set<Parcel> getParcels() {
        return parcels;
    }

    public LegNomination parcels(Set<Parcel> parcels) {
        this.parcels = parcels;
        return this;
    }

    public LegNomination addParcels(Parcel parcel) {
        this.parcels.add(parcel);
        parcel.setLegNomination(this);
        return this;
    }

    public LegNomination removeParcels(Parcel parcel) {
        this.parcels.remove(parcel);
        parcel.setLegNomination(null);
        return this;
    }

    public void setParcels(Set<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Set<BuildDraw> getBuildDraws() {
        return buildDraws;
    }

    public LegNomination buildDraws(Set<BuildDraw> buildDraws) {
        this.buildDraws = buildDraws;
        return this;
    }

    public LegNomination addBuildDraws(BuildDraw buildDraw) {
        this.buildDraws.add(buildDraw);
        buildDraw.setLegNomination(this);
        return this;
    }

    public LegNomination removeBuildDraws(BuildDraw buildDraw) {
        this.buildDraws.remove(buildDraw);
        buildDraw.setLegNomination(null);
        return this;
    }

    public void setBuildDraws(Set<BuildDraw> buildDraws) {
        this.buildDraws = buildDraws;
    }

    public Set<PlaceholderParcel> getPlaceholderParcels() {
        return placeholderParcels;
    }

    public LegNomination placeholderParcels(Set<PlaceholderParcel> placeholderParcels) {
        this.placeholderParcels = placeholderParcels;
        return this;
    }

    public LegNomination addPlaceholderParcels(PlaceholderParcel placeholderParcel) {
        this.placeholderParcels.add(placeholderParcel);
        placeholderParcel.setLegNomination(this);
        return this;
    }

    public LegNomination removePlaceholderParcels(PlaceholderParcel placeholderParcel) {
        this.placeholderParcels.remove(placeholderParcel);
        placeholderParcel.setLegNomination(null);
        return this;
    }

    public void setPlaceholderParcels(Set<PlaceholderParcel> placeholderParcels) {
        this.placeholderParcels = placeholderParcels;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LegNomination)) {
            return false;
        }
        return id != null && id.equals(((LegNomination) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LegNomination{" +
            "id=" + getId() +
            ", customerId='" + getCustomerId() + "'" +
            ", agent='" + getAgent() + "'" +
            ", last3Cargoes='" + getLast3Cargoes() + "'" +
            ", productId='" + getProductId() + "'" +
            ", ownVatNo='" + getOwnVatNo() + "'" +
            ", vettingReference='" + getVettingReference() + "'" +
            ", warehouseAddress='" + getWarehouseAddress() + "'" +
            ", warehouseNo='" + getWarehouseNo() + "'" +
            ", warehouseTaxNo='" + getWarehouseTaxNo() + "'" +
            ", productReceiverVat='" + getProductReceiverVat() + "'" +
            ", customsStatus='" + getCustomsStatus() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", clientClauseDetails='" + getClientClauseDetails() + "'" +
            ", status='" + getStatus() + "'" +
            ", counterPartyStatus='" + getCounterPartyStatus() + "'" +
            ", comment='" + getComment() + "'" +
            ", nominationTimestamp='" + getNominationTimestamp() + "'" +
            ", cnCode='" + getCnCode() + "'" +
            ", financialHold='" + getFinancialHold() + "'" +
            ", consignor='" + getConsignor() + "'" +
            ", blendingInstructions='" + getBlendingInstructions() + "'" +
            ", productAdditives='" + getProductAdditives() + "'" +
            ", homogenisation='" + getHomogenisation() + "'" +
            ", productReceiverId='" + getProductReceiverId() + "'" +
            ", modeOfTransport='" + getModeOfTransport() + "'" +
            ", modeOfTransportEquipment='" + getModeOfTransportEquipment() + "'" +
            ", dischargeDate='" + getDischargeDate() + "'" +
            ", loadportDate='" + getLoadportDate() + "'" +
            ", loadLocation='" + getLoadLocation() + "'" +
            ", dischargeLocation='" + getDischargeLocation() + "'" +
            ", pleaseAdviseWarehouseTaxNo='" + isPleaseAdviseWarehouseTaxNo() + "'" +
            ", pleaseAdviseWarehouseNo='" + isPleaseAdviseWarehouseNo() + "'" +
            ", pleaseAdviseWarehouseAddress='" + isPleaseAdviseWarehouseAddress() + "'" +
            ", pleaseAdviseProductReceiverVAT='" + isPleaseAdviseProductReceiverVAT() + "'" +
            ", pleaseAdviseProductReceiver='" + isPleaseAdviseProductReceiver() + "'" +
            ", pleaseAdviseLoadport='" + isPleaseAdviseLoadport() + "'" +
            ", pleaseAdviseDisport='" + isPleaseAdviseDisport() + "'" +
            ", preferentialOrigin='" + getPreferentialOrigin() + "'" +
            ", productGrade='" + getProductGrade() + "'" +
            ", productSpecification='" + getProductSpecification() + "'" +
            ", productAdditionalComments='" + getProductAdditionalComments() + "'" +
            ", adn='" + getAdn() + "'" +
            ", cdni='" + getCdni() + "'" +
            ", customAdn='" + getCustomAdn() + "'" +
            ", customCdni='" + getCustomCdni() + "'" +
            ", movementTransferType='" + getMovementTransferType() + "'" +
            ", customLoadLocation='" + getCustomLoadLocation() + "'" +
            ", customDischargeLocation='" + getCustomDischargeLocation() + "'" +
            ", customReceiverName='" + getCustomReceiverName() + "'" +
            ", customReceiverAddress='" + getCustomReceiverAddress() + "'" +
            ", customConsignor='" + getCustomConsignor() + "'" +
            ", notes='" + getNotes() + "'" +
            ", terminalCompanyLegalEntity='" + getTerminalCompanyLegalEntity() + "'" +
            ", customTerminalCompanyLegalEntity='" + getCustomTerminalCompanyLegalEntity() + "'" +
            ", iceDelivery='" + isIceDelivery() + "'" +
            "}";
    }
}
