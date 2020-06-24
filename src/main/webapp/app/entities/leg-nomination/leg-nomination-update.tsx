import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INominationMetadata } from 'app/shared/model/nomination-metadata.model';
import { getEntities as getNominationMetadata } from 'app/entities/nomination-metadata/nomination-metadata.reducer';
import { getEntity, updateEntity, createEntity, reset } from './leg-nomination.reducer';
import { ILegNomination } from 'app/shared/model/leg-nomination.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILegNominationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LegNominationUpdate = (props: ILegNominationUpdateProps) => {
  const [nominationMetadataId, setNominationMetadataId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { legNominationEntity, nominationMetadata, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/leg-nomination');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNominationMetadata();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.nominationTimestamp = convertDateTimeToServer(values.nominationTimestamp);

    if (errors.length === 0) {
      const entity = {
        ...legNominationEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="nominationApp.legNomination.home.createOrEditLabel">
            <Translate contentKey="nominationApp.legNomination.home.createOrEditLabel">Create or edit a LegNomination</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : legNominationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="leg-nomination-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="leg-nomination-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="customerIdLabel" for="leg-nomination-customerId">
                  <Translate contentKey="nominationApp.legNomination.customerId">Customer Id</Translate>
                </Label>
                <AvField id="leg-nomination-customerId" type="text" name="customerId" />
              </AvGroup>
              <AvGroup>
                <Label id="agentLabel" for="leg-nomination-agent">
                  <Translate contentKey="nominationApp.legNomination.agent">Agent</Translate>
                </Label>
                <AvField id="leg-nomination-agent" type="text" name="agent" />
              </AvGroup>
              <AvGroup>
                <Label id="last3CargoesLabel" for="leg-nomination-last3Cargoes">
                  <Translate contentKey="nominationApp.legNomination.last3Cargoes">Last 3 Cargoes</Translate>
                </Label>
                <AvField id="leg-nomination-last3Cargoes" type="text" name="last3Cargoes" />
              </AvGroup>
              <AvGroup>
                <Label id="productIdLabel" for="leg-nomination-productId">
                  <Translate contentKey="nominationApp.legNomination.productId">Product Id</Translate>
                </Label>
                <AvField id="leg-nomination-productId" type="text" name="productId" />
              </AvGroup>
              <AvGroup>
                <Label id="ownVatNoLabel" for="leg-nomination-ownVatNo">
                  <Translate contentKey="nominationApp.legNomination.ownVatNo">Own Vat No</Translate>
                </Label>
                <AvField id="leg-nomination-ownVatNo" type="text" name="ownVatNo" />
              </AvGroup>
              <AvGroup>
                <Label id="vettingReferenceLabel" for="leg-nomination-vettingReference">
                  <Translate contentKey="nominationApp.legNomination.vettingReference">Vetting Reference</Translate>
                </Label>
                <AvField id="leg-nomination-vettingReference" type="text" name="vettingReference" />
              </AvGroup>
              <AvGroup>
                <Label id="warehouseAddressLabel" for="leg-nomination-warehouseAddress">
                  <Translate contentKey="nominationApp.legNomination.warehouseAddress">Warehouse Address</Translate>
                </Label>
                <AvField id="leg-nomination-warehouseAddress" type="text" name="warehouseAddress" />
              </AvGroup>
              <AvGroup>
                <Label id="warehouseNoLabel" for="leg-nomination-warehouseNo">
                  <Translate contentKey="nominationApp.legNomination.warehouseNo">Warehouse No</Translate>
                </Label>
                <AvField id="leg-nomination-warehouseNo" type="text" name="warehouseNo" />
              </AvGroup>
              <AvGroup>
                <Label id="warehouseTaxNoLabel" for="leg-nomination-warehouseTaxNo">
                  <Translate contentKey="nominationApp.legNomination.warehouseTaxNo">Warehouse Tax No</Translate>
                </Label>
                <AvField id="leg-nomination-warehouseTaxNo" type="text" name="warehouseTaxNo" />
              </AvGroup>
              <AvGroup>
                <Label id="productReceiverVatLabel" for="leg-nomination-productReceiverVat">
                  <Translate contentKey="nominationApp.legNomination.productReceiverVat">Product Receiver Vat</Translate>
                </Label>
                <AvField id="leg-nomination-productReceiverVat" type="text" name="productReceiverVat" />
              </AvGroup>
              <AvGroup>
                <Label id="customsStatusLabel" for="leg-nomination-customsStatus">
                  <Translate contentKey="nominationApp.legNomination.customsStatus">Customs Status</Translate>
                </Label>
                <AvInput
                  id="leg-nomination-customsStatus"
                  type="select"
                  className="form-control"
                  name="customsStatus"
                  value={(!isNew && legNominationEntity.customsStatus) || 'EAD'}
                >
                  <option value="EAD">{translate('nominationApp.CustomsStatus.EAD')}</option>
                  <option value="T1">{translate('nominationApp.CustomsStatus.T1')}</option>
                  <option value="T2">{translate('nominationApp.CustomsStatus.T2')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="documentTypeLabel" for="leg-nomination-documentType">
                  <Translate contentKey="nominationApp.legNomination.documentType">Document Type</Translate>
                </Label>
                <AvField id="leg-nomination-documentType" type="text" name="documentType" />
              </AvGroup>
              <AvGroup>
                <Label id="clientClauseDetailsLabel" for="leg-nomination-clientClauseDetails">
                  <Translate contentKey="nominationApp.legNomination.clientClauseDetails">Client Clause Details</Translate>
                </Label>
                <AvField id="leg-nomination-clientClauseDetails" type="text" name="clientClauseDetails" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="leg-nomination-status">
                  <Translate contentKey="nominationApp.legNomination.status">Status</Translate>
                </Label>
                <AvInput
                  id="leg-nomination-status"
                  type="select"
                  className="form-control"
                  name="status"
                  value={(!isNew && legNominationEntity.status) || 'PENDING'}
                >
                  <option value="PENDING">{translate('nominationApp.NominationStatus.PENDING')}</option>
                  <option value="CANCELLED">{translate('nominationApp.NominationStatus.CANCELLED')}</option>
                  <option value="NOMINATED">{translate('nominationApp.NominationStatus.NOMINATED')}</option>
                  <option value="REVISED">{translate('nominationApp.NominationStatus.REVISED')}</option>
                  <option value="REJECTED">{translate('nominationApp.NominationStatus.REJECTED')}</option>
                  <option value="REVISION">{translate('nominationApp.NominationStatus.REVISION')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="counterPartyStatusLabel" for="leg-nomination-counterPartyStatus">
                  <Translate contentKey="nominationApp.legNomination.counterPartyStatus">Counter Party Status</Translate>
                </Label>
                <AvInput
                  id="leg-nomination-counterPartyStatus"
                  type="select"
                  className="form-control"
                  name="counterPartyStatus"
                  value={(!isNew && legNominationEntity.counterPartyStatus) || 'PENDING'}
                >
                  <option value="PENDING">{translate('nominationApp.NominationStatus.PENDING')}</option>
                  <option value="CANCELLED">{translate('nominationApp.NominationStatus.CANCELLED')}</option>
                  <option value="NOMINATED">{translate('nominationApp.NominationStatus.NOMINATED')}</option>
                  <option value="REVISED">{translate('nominationApp.NominationStatus.REVISED')}</option>
                  <option value="REJECTED">{translate('nominationApp.NominationStatus.REJECTED')}</option>
                  <option value="REVISION">{translate('nominationApp.NominationStatus.REVISION')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="leg-nomination-comment">
                  <Translate contentKey="nominationApp.legNomination.comment">Comment</Translate>
                </Label>
                <AvField id="leg-nomination-comment" type="text" name="comment" />
              </AvGroup>
              <AvGroup>
                <Label id="nominationTimestampLabel" for="leg-nomination-nominationTimestamp">
                  <Translate contentKey="nominationApp.legNomination.nominationTimestamp">Nomination Timestamp</Translate>
                </Label>
                <AvInput
                  id="leg-nomination-nominationTimestamp"
                  type="datetime-local"
                  className="form-control"
                  name="nominationTimestamp"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.legNominationEntity.nominationTimestamp)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="cnCodeLabel" for="leg-nomination-cnCode">
                  <Translate contentKey="nominationApp.legNomination.cnCode">Cn Code</Translate>
                </Label>
                <AvField id="leg-nomination-cnCode" type="text" name="cnCode" />
              </AvGroup>
              <AvGroup>
                <Label id="financialHoldLabel" for="leg-nomination-financialHold">
                  <Translate contentKey="nominationApp.legNomination.financialHold">Financial Hold</Translate>
                </Label>
                <AvField id="leg-nomination-financialHold" type="text" name="financialHold" />
              </AvGroup>
              <AvGroup>
                <Label id="consignorLabel" for="leg-nomination-consignor">
                  <Translate contentKey="nominationApp.legNomination.consignor">Consignor</Translate>
                </Label>
                <AvField id="leg-nomination-consignor" type="text" name="consignor" />
              </AvGroup>
              <AvGroup>
                <Label id="blendingInstructionsLabel" for="leg-nomination-blendingInstructions">
                  <Translate contentKey="nominationApp.legNomination.blendingInstructions">Blending Instructions</Translate>
                </Label>
                <AvField id="leg-nomination-blendingInstructions" type="text" name="blendingInstructions" />
              </AvGroup>
              <AvGroup>
                <Label id="productAdditivesLabel" for="leg-nomination-productAdditives">
                  <Translate contentKey="nominationApp.legNomination.productAdditives">Product Additives</Translate>
                </Label>
                <AvField id="leg-nomination-productAdditives" type="text" name="productAdditives" />
              </AvGroup>
              <AvGroup>
                <Label id="homogenisationLabel" for="leg-nomination-homogenisation">
                  <Translate contentKey="nominationApp.legNomination.homogenisation">Homogenisation</Translate>
                </Label>
                <AvField id="leg-nomination-homogenisation" type="text" name="homogenisation" />
              </AvGroup>
              <AvGroup>
                <Label id="productReceiverIdLabel" for="leg-nomination-productReceiverId">
                  <Translate contentKey="nominationApp.legNomination.productReceiverId">Product Receiver Id</Translate>
                </Label>
                <AvField id="leg-nomination-productReceiverId" type="text" name="productReceiverId" />
              </AvGroup>
              <AvGroup>
                <Label id="modeOfTransportLabel" for="leg-nomination-modeOfTransport">
                  <Translate contentKey="nominationApp.legNomination.modeOfTransport">Mode Of Transport</Translate>
                </Label>
                <AvInput
                  id="leg-nomination-modeOfTransport"
                  type="select"
                  className="form-control"
                  name="modeOfTransport"
                  value={(!isNew && legNominationEntity.modeOfTransport) || 'TRANSPORT1'}
                >
                  <option value="TRANSPORT1">{translate('nominationApp.ModeOfTransport.TRANSPORT1')}</option>
                  <option value="TRANSPORT2">{translate('nominationApp.ModeOfTransport.TRANSPORT2')}</option>
                  <option value="TRANSPORT3">{translate('nominationApp.ModeOfTransport.TRANSPORT3')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="modeOfTransportEquipmentLabel" for="leg-nomination-modeOfTransportEquipment">
                  <Translate contentKey="nominationApp.legNomination.modeOfTransportEquipment">Mode Of Transport Equipment</Translate>
                </Label>
                <AvField id="leg-nomination-modeOfTransportEquipment" type="text" name="modeOfTransportEquipment" />
              </AvGroup>
              <AvGroup>
                <Label id="dischargeDateLabel" for="leg-nomination-dischargeDate">
                  <Translate contentKey="nominationApp.legNomination.dischargeDate">Discharge Date</Translate>
                </Label>
                <AvField id="leg-nomination-dischargeDate" type="date" className="form-control" name="dischargeDate" />
              </AvGroup>
              <AvGroup>
                <Label id="loadportDateLabel" for="leg-nomination-loadportDate">
                  <Translate contentKey="nominationApp.legNomination.loadportDate">Loadport Date</Translate>
                </Label>
                <AvField id="leg-nomination-loadportDate" type="date" className="form-control" name="loadportDate" />
              </AvGroup>
              <AvGroup>
                <Label id="loadLocationLabel" for="leg-nomination-loadLocation">
                  <Translate contentKey="nominationApp.legNomination.loadLocation">Load Location</Translate>
                </Label>
                <AvField id="leg-nomination-loadLocation" type="text" name="loadLocation" />
              </AvGroup>
              <AvGroup>
                <Label id="dischargeLocationLabel" for="leg-nomination-dischargeLocation">
                  <Translate contentKey="nominationApp.legNomination.dischargeLocation">Discharge Location</Translate>
                </Label>
                <AvField id="leg-nomination-dischargeLocation" type="text" name="dischargeLocation" />
              </AvGroup>
              <AvGroup check>
                <Label id="pleaseAdviseWarehouseTaxNoLabel">
                  <AvInput
                    id="leg-nomination-pleaseAdviseWarehouseTaxNo"
                    type="checkbox"
                    className="form-check-input"
                    name="pleaseAdviseWarehouseTaxNo"
                  />
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseTaxNo">Please Advise Warehouse Tax No</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="pleaseAdviseWarehouseNoLabel">
                  <AvInput
                    id="leg-nomination-pleaseAdviseWarehouseNo"
                    type="checkbox"
                    className="form-check-input"
                    name="pleaseAdviseWarehouseNo"
                  />
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseNo">Please Advise Warehouse No</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="pleaseAdviseWarehouseAddressLabel">
                  <AvInput
                    id="leg-nomination-pleaseAdviseWarehouseAddress"
                    type="checkbox"
                    className="form-check-input"
                    name="pleaseAdviseWarehouseAddress"
                  />
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseAddress">
                    Please Advise Warehouse Address
                  </Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="pleaseAdviseProductReceiverVATLabel">
                  <AvInput
                    id="leg-nomination-pleaseAdviseProductReceiverVAT"
                    type="checkbox"
                    className="form-check-input"
                    name="pleaseAdviseProductReceiverVAT"
                  />
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseProductReceiverVAT">
                    Please Advise Product Receiver VAT
                  </Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="pleaseAdviseProductReceiverLabel">
                  <AvInput
                    id="leg-nomination-pleaseAdviseProductReceiver"
                    type="checkbox"
                    className="form-check-input"
                    name="pleaseAdviseProductReceiver"
                  />
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseProductReceiver">Please Advise Product Receiver</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="pleaseAdviseLoadportLabel">
                  <AvInput
                    id="leg-nomination-pleaseAdviseLoadport"
                    type="checkbox"
                    className="form-check-input"
                    name="pleaseAdviseLoadport"
                  />
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseLoadport">Please Advise Loadport</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="pleaseAdviseDisportLabel">
                  <AvInput
                    id="leg-nomination-pleaseAdviseDisport"
                    type="checkbox"
                    className="form-check-input"
                    name="pleaseAdviseDisport"
                  />
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseDisport">Please Advise Disport</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="preferentialOriginLabel" for="leg-nomination-preferentialOrigin">
                  <Translate contentKey="nominationApp.legNomination.preferentialOrigin">Preferential Origin</Translate>
                </Label>
                <AvField id="leg-nomination-preferentialOrigin" type="text" name="preferentialOrigin" />
              </AvGroup>
              <AvGroup>
                <Label id="productGradeLabel" for="leg-nomination-productGrade">
                  <Translate contentKey="nominationApp.legNomination.productGrade">Product Grade</Translate>
                </Label>
                <AvField id="leg-nomination-productGrade" type="text" name="productGrade" />
              </AvGroup>
              <AvGroup>
                <Label id="productSpecificationLabel" for="leg-nomination-productSpecification">
                  <Translate contentKey="nominationApp.legNomination.productSpecification">Product Specification</Translate>
                </Label>
                <AvField id="leg-nomination-productSpecification" type="text" name="productSpecification" />
              </AvGroup>
              <AvGroup>
                <Label id="productAdditionalCommentsLabel" for="leg-nomination-productAdditionalComments">
                  <Translate contentKey="nominationApp.legNomination.productAdditionalComments">Product Additional Comments</Translate>
                </Label>
                <AvField id="leg-nomination-productAdditionalComments" type="text" name="productAdditionalComments" />
              </AvGroup>
              <AvGroup>
                <Label id="adnLabel" for="leg-nomination-adn">
                  <Translate contentKey="nominationApp.legNomination.adn">Adn</Translate>
                </Label>
                <AvField id="leg-nomination-adn" type="text" name="adn" />
              </AvGroup>
              <AvGroup>
                <Label id="cdniLabel" for="leg-nomination-cdni">
                  <Translate contentKey="nominationApp.legNomination.cdni">Cdni</Translate>
                </Label>
                <AvField id="leg-nomination-cdni" type="text" name="cdni" />
              </AvGroup>
              <AvGroup>
                <Label id="customAdnLabel" for="leg-nomination-customAdn">
                  <Translate contentKey="nominationApp.legNomination.customAdn">Custom Adn</Translate>
                </Label>
                <AvField id="leg-nomination-customAdn" type="text" name="customAdn" />
              </AvGroup>
              <AvGroup>
                <Label id="customCdniLabel" for="leg-nomination-customCdni">
                  <Translate contentKey="nominationApp.legNomination.customCdni">Custom Cdni</Translate>
                </Label>
                <AvField id="leg-nomination-customCdni" type="text" name="customCdni" />
              </AvGroup>
              <AvGroup>
                <Label id="movementTransferTypeLabel" for="leg-nomination-movementTransferType">
                  <Translate contentKey="nominationApp.legNomination.movementTransferType">Movement Transfer Type</Translate>
                </Label>
                <AvInput
                  id="leg-nomination-movementTransferType"
                  type="select"
                  className="form-control"
                  name="movementTransferType"
                  value={(!isNew && legNominationEntity.movementTransferType) || 'TYPE1'}
                >
                  <option value="TYPE1">{translate('nominationApp.MovementTransferType.TYPE1')}</option>
                  <option value="TYPE2">{translate('nominationApp.MovementTransferType.TYPE2')}</option>
                  <option value="TYPE3">{translate('nominationApp.MovementTransferType.TYPE3')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="customLoadLocationLabel" for="leg-nomination-customLoadLocation">
                  <Translate contentKey="nominationApp.legNomination.customLoadLocation">Custom Load Location</Translate>
                </Label>
                <AvField id="leg-nomination-customLoadLocation" type="text" name="customLoadLocation" />
              </AvGroup>
              <AvGroup>
                <Label id="customDischargeLocationLabel" for="leg-nomination-customDischargeLocation">
                  <Translate contentKey="nominationApp.legNomination.customDischargeLocation">Custom Discharge Location</Translate>
                </Label>
                <AvField id="leg-nomination-customDischargeLocation" type="text" name="customDischargeLocation" />
              </AvGroup>
              <AvGroup>
                <Label id="customReceiverNameLabel" for="leg-nomination-customReceiverName">
                  <Translate contentKey="nominationApp.legNomination.customReceiverName">Custom Receiver Name</Translate>
                </Label>
                <AvField id="leg-nomination-customReceiverName" type="text" name="customReceiverName" />
              </AvGroup>
              <AvGroup>
                <Label id="customReceiverAddressLabel" for="leg-nomination-customReceiverAddress">
                  <Translate contentKey="nominationApp.legNomination.customReceiverAddress">Custom Receiver Address</Translate>
                </Label>
                <AvField id="leg-nomination-customReceiverAddress" type="text" name="customReceiverAddress" />
              </AvGroup>
              <AvGroup>
                <Label id="customConsignorLabel" for="leg-nomination-customConsignor">
                  <Translate contentKey="nominationApp.legNomination.customConsignor">Custom Consignor</Translate>
                </Label>
                <AvField id="leg-nomination-customConsignor" type="text" name="customConsignor" />
              </AvGroup>
              <AvGroup>
                <Label id="notesLabel" for="leg-nomination-notes">
                  <Translate contentKey="nominationApp.legNomination.notes">Notes</Translate>
                </Label>
                <AvField id="leg-nomination-notes" type="text" name="notes" />
              </AvGroup>
              <AvGroup>
                <Label id="terminalCompanyLegalEntityLabel" for="leg-nomination-terminalCompanyLegalEntity">
                  <Translate contentKey="nominationApp.legNomination.terminalCompanyLegalEntity">Terminal Company Legal Entity</Translate>
                </Label>
                <AvField id="leg-nomination-terminalCompanyLegalEntity" type="text" name="terminalCompanyLegalEntity" />
              </AvGroup>
              <AvGroup>
                <Label id="customTerminalCompanyLegalEntityLabel" for="leg-nomination-customTerminalCompanyLegalEntity">
                  <Translate contentKey="nominationApp.legNomination.customTerminalCompanyLegalEntity">
                    Custom Terminal Company Legal Entity
                  </Translate>
                </Label>
                <AvField id="leg-nomination-customTerminalCompanyLegalEntity" type="text" name="customTerminalCompanyLegalEntity" />
              </AvGroup>
              <AvGroup check>
                <Label id="iceDeliveryLabel">
                  <AvInput id="leg-nomination-iceDelivery" type="checkbox" className="form-check-input" name="iceDelivery" />
                  <Translate contentKey="nominationApp.legNomination.iceDelivery">Ice Delivery</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="leg-nomination-nominationMetadata">
                  <Translate contentKey="nominationApp.legNomination.nominationMetadata">Nomination Metadata</Translate>
                </Label>
                <AvInput id="leg-nomination-nominationMetadata" type="select" className="form-control" name="nominationMetadataId">
                  <option value="" key="0" />
                  {nominationMetadata
                    ? nominationMetadata.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/leg-nomination" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  nominationMetadata: storeState.nominationMetadata.entities,
  legNominationEntity: storeState.legNomination.entity,
  loading: storeState.legNomination.loading,
  updating: storeState.legNomination.updating,
  updateSuccess: storeState.legNomination.updateSuccess,
});

const mapDispatchToProps = {
  getNominationMetadata,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LegNominationUpdate);
