package io.vakt.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import io.vakt.domain.enumeration.CustomsStatus;
import io.vakt.domain.enumeration.NominationStatus;
import io.vakt.domain.enumeration.ModeOfTransport;
import io.vakt.domain.enumeration.MovementTransferType;

/**
 * A DTO for the {@link io.vakt.domain.LegNomination} entity.
 */
public class LegNominationDTO implements Serializable {
    
    private Long id;

    private String customerId;

    private String agent;

    private String last3Cargoes;

    private String productId;

    private String ownVatNo;

    private String vettingReference;

    private String warehouseAddress;

    private String warehouseNo;

    private String warehouseTaxNo;

    private String productReceiverVat;

    private CustomsStatus customsStatus;

    private String documentType;

    private String clientClauseDetails;

    private NominationStatus status;

    private NominationStatus counterPartyStatus;

    private String comment;

    private Instant nominationTimestamp;

    private String cnCode;

    private String financialHold;

    private String consignor;

    private String blendingInstructions;

    private String productAdditives;

    private String homogenisation;

    private String productReceiverId;

    private ModeOfTransport modeOfTransport;

    private String modeOfTransportEquipment;

    private LocalDate dischargeDate;

    private LocalDate loadportDate;

    private String loadLocation;

    private String dischargeLocation;

    private Boolean pleaseAdviseWarehouseTaxNo;

    private Boolean pleaseAdviseWarehouseNo;

    private Boolean pleaseAdviseWarehouseAddress;

    private Boolean pleaseAdviseProductReceiverVAT;

    private Boolean pleaseAdviseProductReceiver;

    private Boolean pleaseAdviseLoadport;

    private Boolean pleaseAdviseDisport;

    private String preferentialOrigin;

    private String productGrade;

    private String productSpecification;

    private String productAdditionalComments;

    private String adn;

    private String cdni;

    private String customAdn;

    private String customCdni;

    private MovementTransferType movementTransferType;

    private String customLoadLocation;

    private String customDischargeLocation;

    private String customReceiverName;

    private String customReceiverAddress;

    private String customConsignor;

    private String notes;

    private String terminalCompanyLegalEntity;

    private String customTerminalCompanyLegalEntity;

    private Boolean iceDelivery;


    private Long nominationMetadataId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getLast3Cargoes() {
        return last3Cargoes;
    }

    public void setLast3Cargoes(String last3Cargoes) {
        this.last3Cargoes = last3Cargoes;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOwnVatNo() {
        return ownVatNo;
    }

    public void setOwnVatNo(String ownVatNo) {
        this.ownVatNo = ownVatNo;
    }

    public String getVettingReference() {
        return vettingReference;
    }

    public void setVettingReference(String vettingReference) {
        this.vettingReference = vettingReference;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getWarehouseTaxNo() {
        return warehouseTaxNo;
    }

    public void setWarehouseTaxNo(String warehouseTaxNo) {
        this.warehouseTaxNo = warehouseTaxNo;
    }

    public String getProductReceiverVat() {
        return productReceiverVat;
    }

    public void setProductReceiverVat(String productReceiverVat) {
        this.productReceiverVat = productReceiverVat;
    }

    public CustomsStatus getCustomsStatus() {
        return customsStatus;
    }

    public void setCustomsStatus(CustomsStatus customsStatus) {
        this.customsStatus = customsStatus;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getClientClauseDetails() {
        return clientClauseDetails;
    }

    public void setClientClauseDetails(String clientClauseDetails) {
        this.clientClauseDetails = clientClauseDetails;
    }

    public NominationStatus getStatus() {
        return status;
    }

    public void setStatus(NominationStatus status) {
        this.status = status;
    }

    public NominationStatus getCounterPartyStatus() {
        return counterPartyStatus;
    }

    public void setCounterPartyStatus(NominationStatus counterPartyStatus) {
        this.counterPartyStatus = counterPartyStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getNominationTimestamp() {
        return nominationTimestamp;
    }

    public void setNominationTimestamp(Instant nominationTimestamp) {
        this.nominationTimestamp = nominationTimestamp;
    }

    public String getCnCode() {
        return cnCode;
    }

    public void setCnCode(String cnCode) {
        this.cnCode = cnCode;
    }

    public String getFinancialHold() {
        return financialHold;
    }

    public void setFinancialHold(String financialHold) {
        this.financialHold = financialHold;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getBlendingInstructions() {
        return blendingInstructions;
    }

    public void setBlendingInstructions(String blendingInstructions) {
        this.blendingInstructions = blendingInstructions;
    }

    public String getProductAdditives() {
        return productAdditives;
    }

    public void setProductAdditives(String productAdditives) {
        this.productAdditives = productAdditives;
    }

    public String getHomogenisation() {
        return homogenisation;
    }

    public void setHomogenisation(String homogenisation) {
        this.homogenisation = homogenisation;
    }

    public String getProductReceiverId() {
        return productReceiverId;
    }

    public void setProductReceiverId(String productReceiverId) {
        this.productReceiverId = productReceiverId;
    }

    public ModeOfTransport getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(ModeOfTransport modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }

    public String getModeOfTransportEquipment() {
        return modeOfTransportEquipment;
    }

    public void setModeOfTransportEquipment(String modeOfTransportEquipment) {
        this.modeOfTransportEquipment = modeOfTransportEquipment;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public LocalDate getLoadportDate() {
        return loadportDate;
    }

    public void setLoadportDate(LocalDate loadportDate) {
        this.loadportDate = loadportDate;
    }

    public String getLoadLocation() {
        return loadLocation;
    }

    public void setLoadLocation(String loadLocation) {
        this.loadLocation = loadLocation;
    }

    public String getDischargeLocation() {
        return dischargeLocation;
    }

    public void setDischargeLocation(String dischargeLocation) {
        this.dischargeLocation = dischargeLocation;
    }

    public Boolean isPleaseAdviseWarehouseTaxNo() {
        return pleaseAdviseWarehouseTaxNo;
    }

    public void setPleaseAdviseWarehouseTaxNo(Boolean pleaseAdviseWarehouseTaxNo) {
        this.pleaseAdviseWarehouseTaxNo = pleaseAdviseWarehouseTaxNo;
    }

    public Boolean isPleaseAdviseWarehouseNo() {
        return pleaseAdviseWarehouseNo;
    }

    public void setPleaseAdviseWarehouseNo(Boolean pleaseAdviseWarehouseNo) {
        this.pleaseAdviseWarehouseNo = pleaseAdviseWarehouseNo;
    }

    public Boolean isPleaseAdviseWarehouseAddress() {
        return pleaseAdviseWarehouseAddress;
    }

    public void setPleaseAdviseWarehouseAddress(Boolean pleaseAdviseWarehouseAddress) {
        this.pleaseAdviseWarehouseAddress = pleaseAdviseWarehouseAddress;
    }

    public Boolean isPleaseAdviseProductReceiverVAT() {
        return pleaseAdviseProductReceiverVAT;
    }

    public void setPleaseAdviseProductReceiverVAT(Boolean pleaseAdviseProductReceiverVAT) {
        this.pleaseAdviseProductReceiverVAT = pleaseAdviseProductReceiverVAT;
    }

    public Boolean isPleaseAdviseProductReceiver() {
        return pleaseAdviseProductReceiver;
    }

    public void setPleaseAdviseProductReceiver(Boolean pleaseAdviseProductReceiver) {
        this.pleaseAdviseProductReceiver = pleaseAdviseProductReceiver;
    }

    public Boolean isPleaseAdviseLoadport() {
        return pleaseAdviseLoadport;
    }

    public void setPleaseAdviseLoadport(Boolean pleaseAdviseLoadport) {
        this.pleaseAdviseLoadport = pleaseAdviseLoadport;
    }

    public Boolean isPleaseAdviseDisport() {
        return pleaseAdviseDisport;
    }

    public void setPleaseAdviseDisport(Boolean pleaseAdviseDisport) {
        this.pleaseAdviseDisport = pleaseAdviseDisport;
    }

    public String getPreferentialOrigin() {
        return preferentialOrigin;
    }

    public void setPreferentialOrigin(String preferentialOrigin) {
        this.preferentialOrigin = preferentialOrigin;
    }

    public String getProductGrade() {
        return productGrade;
    }

    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade;
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getProductAdditionalComments() {
        return productAdditionalComments;
    }

    public void setProductAdditionalComments(String productAdditionalComments) {
        this.productAdditionalComments = productAdditionalComments;
    }

    public String getAdn() {
        return adn;
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public String getCdni() {
        return cdni;
    }

    public void setCdni(String cdni) {
        this.cdni = cdni;
    }

    public String getCustomAdn() {
        return customAdn;
    }

    public void setCustomAdn(String customAdn) {
        this.customAdn = customAdn;
    }

    public String getCustomCdni() {
        return customCdni;
    }

    public void setCustomCdni(String customCdni) {
        this.customCdni = customCdni;
    }

    public MovementTransferType getMovementTransferType() {
        return movementTransferType;
    }

    public void setMovementTransferType(MovementTransferType movementTransferType) {
        this.movementTransferType = movementTransferType;
    }

    public String getCustomLoadLocation() {
        return customLoadLocation;
    }

    public void setCustomLoadLocation(String customLoadLocation) {
        this.customLoadLocation = customLoadLocation;
    }

    public String getCustomDischargeLocation() {
        return customDischargeLocation;
    }

    public void setCustomDischargeLocation(String customDischargeLocation) {
        this.customDischargeLocation = customDischargeLocation;
    }

    public String getCustomReceiverName() {
        return customReceiverName;
    }

    public void setCustomReceiverName(String customReceiverName) {
        this.customReceiverName = customReceiverName;
    }

    public String getCustomReceiverAddress() {
        return customReceiverAddress;
    }

    public void setCustomReceiverAddress(String customReceiverAddress) {
        this.customReceiverAddress = customReceiverAddress;
    }

    public String getCustomConsignor() {
        return customConsignor;
    }

    public void setCustomConsignor(String customConsignor) {
        this.customConsignor = customConsignor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTerminalCompanyLegalEntity() {
        return terminalCompanyLegalEntity;
    }

    public void setTerminalCompanyLegalEntity(String terminalCompanyLegalEntity) {
        this.terminalCompanyLegalEntity = terminalCompanyLegalEntity;
    }

    public String getCustomTerminalCompanyLegalEntity() {
        return customTerminalCompanyLegalEntity;
    }

    public void setCustomTerminalCompanyLegalEntity(String customTerminalCompanyLegalEntity) {
        this.customTerminalCompanyLegalEntity = customTerminalCompanyLegalEntity;
    }

    public Boolean isIceDelivery() {
        return iceDelivery;
    }

    public void setIceDelivery(Boolean iceDelivery) {
        this.iceDelivery = iceDelivery;
    }

    public Long getNominationMetadataId() {
        return nominationMetadataId;
    }

    public void setNominationMetadataId(Long nominationMetadataId) {
        this.nominationMetadataId = nominationMetadataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LegNominationDTO)) {
            return false;
        }

        return id != null && id.equals(((LegNominationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LegNominationDTO{" +
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
            ", nominationMetadataId=" + getNominationMetadataId() +
            "}";
    }
}
