import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './leg-nomination.reducer';
import { ILegNomination } from 'app/shared/model/leg-nomination.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILegNominationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LegNominationDetail = (props: ILegNominationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { legNominationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nominationApp.legNomination.detail.title">LegNomination</Translate> [<b>{legNominationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="customerId">
              <Translate contentKey="nominationApp.legNomination.customerId">Customer Id</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customerId}</dd>
          <dt>
            <span id="agent">
              <Translate contentKey="nominationApp.legNomination.agent">Agent</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.agent}</dd>
          <dt>
            <span id="last3Cargoes">
              <Translate contentKey="nominationApp.legNomination.last3Cargoes">Last 3 Cargoes</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.last3Cargoes}</dd>
          <dt>
            <span id="productId">
              <Translate contentKey="nominationApp.legNomination.productId">Product Id</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.productId}</dd>
          <dt>
            <span id="ownVatNo">
              <Translate contentKey="nominationApp.legNomination.ownVatNo">Own Vat No</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.ownVatNo}</dd>
          <dt>
            <span id="vettingReference">
              <Translate contentKey="nominationApp.legNomination.vettingReference">Vetting Reference</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.vettingReference}</dd>
          <dt>
            <span id="warehouseAddress">
              <Translate contentKey="nominationApp.legNomination.warehouseAddress">Warehouse Address</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.warehouseAddress}</dd>
          <dt>
            <span id="warehouseNo">
              <Translate contentKey="nominationApp.legNomination.warehouseNo">Warehouse No</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.warehouseNo}</dd>
          <dt>
            <span id="warehouseTaxNo">
              <Translate contentKey="nominationApp.legNomination.warehouseTaxNo">Warehouse Tax No</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.warehouseTaxNo}</dd>
          <dt>
            <span id="productReceiverVat">
              <Translate contentKey="nominationApp.legNomination.productReceiverVat">Product Receiver Vat</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.productReceiverVat}</dd>
          <dt>
            <span id="customsStatus">
              <Translate contentKey="nominationApp.legNomination.customsStatus">Customs Status</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customsStatus}</dd>
          <dt>
            <span id="documentType">
              <Translate contentKey="nominationApp.legNomination.documentType">Document Type</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.documentType}</dd>
          <dt>
            <span id="clientClauseDetails">
              <Translate contentKey="nominationApp.legNomination.clientClauseDetails">Client Clause Details</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.clientClauseDetails}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="nominationApp.legNomination.status">Status</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.status}</dd>
          <dt>
            <span id="counterPartyStatus">
              <Translate contentKey="nominationApp.legNomination.counterPartyStatus">Counter Party Status</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.counterPartyStatus}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="nominationApp.legNomination.comment">Comment</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.comment}</dd>
          <dt>
            <span id="nominationTimestamp">
              <Translate contentKey="nominationApp.legNomination.nominationTimestamp">Nomination Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {legNominationEntity.nominationTimestamp ? (
              <TextFormat value={legNominationEntity.nominationTimestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="cnCode">
              <Translate contentKey="nominationApp.legNomination.cnCode">Cn Code</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.cnCode}</dd>
          <dt>
            <span id="financialHold">
              <Translate contentKey="nominationApp.legNomination.financialHold">Financial Hold</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.financialHold}</dd>
          <dt>
            <span id="consignor">
              <Translate contentKey="nominationApp.legNomination.consignor">Consignor</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.consignor}</dd>
          <dt>
            <span id="blendingInstructions">
              <Translate contentKey="nominationApp.legNomination.blendingInstructions">Blending Instructions</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.blendingInstructions}</dd>
          <dt>
            <span id="productAdditives">
              <Translate contentKey="nominationApp.legNomination.productAdditives">Product Additives</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.productAdditives}</dd>
          <dt>
            <span id="homogenisation">
              <Translate contentKey="nominationApp.legNomination.homogenisation">Homogenisation</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.homogenisation}</dd>
          <dt>
            <span id="productReceiverId">
              <Translate contentKey="nominationApp.legNomination.productReceiverId">Product Receiver Id</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.productReceiverId}</dd>
          <dt>
            <span id="modeOfTransport">
              <Translate contentKey="nominationApp.legNomination.modeOfTransport">Mode Of Transport</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.modeOfTransport}</dd>
          <dt>
            <span id="modeOfTransportEquipment">
              <Translate contentKey="nominationApp.legNomination.modeOfTransportEquipment">Mode Of Transport Equipment</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.modeOfTransportEquipment}</dd>
          <dt>
            <span id="dischargeDate">
              <Translate contentKey="nominationApp.legNomination.dischargeDate">Discharge Date</Translate>
            </span>
          </dt>
          <dd>
            {legNominationEntity.dischargeDate ? (
              <TextFormat value={legNominationEntity.dischargeDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="loadportDate">
              <Translate contentKey="nominationApp.legNomination.loadportDate">Loadport Date</Translate>
            </span>
          </dt>
          <dd>
            {legNominationEntity.loadportDate ? (
              <TextFormat value={legNominationEntity.loadportDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="loadLocation">
              <Translate contentKey="nominationApp.legNomination.loadLocation">Load Location</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.loadLocation}</dd>
          <dt>
            <span id="dischargeLocation">
              <Translate contentKey="nominationApp.legNomination.dischargeLocation">Discharge Location</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.dischargeLocation}</dd>
          <dt>
            <span id="pleaseAdviseWarehouseTaxNo">
              <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseTaxNo">Please Advise Warehouse Tax No</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.pleaseAdviseWarehouseTaxNo ? 'true' : 'false'}</dd>
          <dt>
            <span id="pleaseAdviseWarehouseNo">
              <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseNo">Please Advise Warehouse No</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.pleaseAdviseWarehouseNo ? 'true' : 'false'}</dd>
          <dt>
            <span id="pleaseAdviseWarehouseAddress">
              <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseAddress">Please Advise Warehouse Address</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.pleaseAdviseWarehouseAddress ? 'true' : 'false'}</dd>
          <dt>
            <span id="pleaseAdviseProductReceiverVAT">
              <Translate contentKey="nominationApp.legNomination.pleaseAdviseProductReceiverVAT">
                Please Advise Product Receiver VAT
              </Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.pleaseAdviseProductReceiverVAT ? 'true' : 'false'}</dd>
          <dt>
            <span id="pleaseAdviseProductReceiver">
              <Translate contentKey="nominationApp.legNomination.pleaseAdviseProductReceiver">Please Advise Product Receiver</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.pleaseAdviseProductReceiver ? 'true' : 'false'}</dd>
          <dt>
            <span id="pleaseAdviseLoadport">
              <Translate contentKey="nominationApp.legNomination.pleaseAdviseLoadport">Please Advise Loadport</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.pleaseAdviseLoadport ? 'true' : 'false'}</dd>
          <dt>
            <span id="pleaseAdviseDisport">
              <Translate contentKey="nominationApp.legNomination.pleaseAdviseDisport">Please Advise Disport</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.pleaseAdviseDisport ? 'true' : 'false'}</dd>
          <dt>
            <span id="preferentialOrigin">
              <Translate contentKey="nominationApp.legNomination.preferentialOrigin">Preferential Origin</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.preferentialOrigin}</dd>
          <dt>
            <span id="productGrade">
              <Translate contentKey="nominationApp.legNomination.productGrade">Product Grade</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.productGrade}</dd>
          <dt>
            <span id="productSpecification">
              <Translate contentKey="nominationApp.legNomination.productSpecification">Product Specification</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.productSpecification}</dd>
          <dt>
            <span id="productAdditionalComments">
              <Translate contentKey="nominationApp.legNomination.productAdditionalComments">Product Additional Comments</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.productAdditionalComments}</dd>
          <dt>
            <span id="adn">
              <Translate contentKey="nominationApp.legNomination.adn">Adn</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.adn}</dd>
          <dt>
            <span id="cdni">
              <Translate contentKey="nominationApp.legNomination.cdni">Cdni</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.cdni}</dd>
          <dt>
            <span id="customAdn">
              <Translate contentKey="nominationApp.legNomination.customAdn">Custom Adn</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customAdn}</dd>
          <dt>
            <span id="customCdni">
              <Translate contentKey="nominationApp.legNomination.customCdni">Custom Cdni</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customCdni}</dd>
          <dt>
            <span id="movementTransferType">
              <Translate contentKey="nominationApp.legNomination.movementTransferType">Movement Transfer Type</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.movementTransferType}</dd>
          <dt>
            <span id="customLoadLocation">
              <Translate contentKey="nominationApp.legNomination.customLoadLocation">Custom Load Location</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customLoadLocation}</dd>
          <dt>
            <span id="customDischargeLocation">
              <Translate contentKey="nominationApp.legNomination.customDischargeLocation">Custom Discharge Location</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customDischargeLocation}</dd>
          <dt>
            <span id="customReceiverName">
              <Translate contentKey="nominationApp.legNomination.customReceiverName">Custom Receiver Name</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customReceiverName}</dd>
          <dt>
            <span id="customReceiverAddress">
              <Translate contentKey="nominationApp.legNomination.customReceiverAddress">Custom Receiver Address</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customReceiverAddress}</dd>
          <dt>
            <span id="customConsignor">
              <Translate contentKey="nominationApp.legNomination.customConsignor">Custom Consignor</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customConsignor}</dd>
          <dt>
            <span id="notes">
              <Translate contentKey="nominationApp.legNomination.notes">Notes</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.notes}</dd>
          <dt>
            <span id="terminalCompanyLegalEntity">
              <Translate contentKey="nominationApp.legNomination.terminalCompanyLegalEntity">Terminal Company Legal Entity</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.terminalCompanyLegalEntity}</dd>
          <dt>
            <span id="customTerminalCompanyLegalEntity">
              <Translate contentKey="nominationApp.legNomination.customTerminalCompanyLegalEntity">
                Custom Terminal Company Legal Entity
              </Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.customTerminalCompanyLegalEntity}</dd>
          <dt>
            <span id="iceDelivery">
              <Translate contentKey="nominationApp.legNomination.iceDelivery">Ice Delivery</Translate>
            </span>
          </dt>
          <dd>{legNominationEntity.iceDelivery ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="nominationApp.legNomination.nominationMetadata">Nomination Metadata</Translate>
          </dt>
          <dd>{legNominationEntity.nominationMetadataId ? legNominationEntity.nominationMetadataId : ''}</dd>
        </dl>
        <Button tag={Link} to="/leg-nomination" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/leg-nomination/${legNominationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ legNomination }: IRootState) => ({
  legNominationEntity: legNomination.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LegNominationDetail);
