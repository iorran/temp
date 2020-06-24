import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './leg-nomination.reducer';
import { ILegNomination } from 'app/shared/model/leg-nomination.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILegNominationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const LegNomination = (props: ILegNominationProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { legNominationList, match, loading } = props;
  return (
    <div>
      <h2 id="leg-nomination-heading">
        <Translate contentKey="nominationApp.legNomination.home.title">Leg Nominations</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="nominationApp.legNomination.home.createLabel">Create new Leg Nomination</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {legNominationList && legNominationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customerId">Customer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.agent">Agent</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.last3Cargoes">Last 3 Cargoes</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.productId">Product Id</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.ownVatNo">Own Vat No</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.vettingReference">Vetting Reference</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.warehouseAddress">Warehouse Address</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.warehouseNo">Warehouse No</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.warehouseTaxNo">Warehouse Tax No</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.productReceiverVat">Product Receiver Vat</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customsStatus">Customs Status</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.documentType">Document Type</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.clientClauseDetails">Client Clause Details</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.counterPartyStatus">Counter Party Status</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.comment">Comment</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.nominationTimestamp">Nomination Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.cnCode">Cn Code</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.financialHold">Financial Hold</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.consignor">Consignor</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.blendingInstructions">Blending Instructions</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.productAdditives">Product Additives</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.homogenisation">Homogenisation</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.productReceiverId">Product Receiver Id</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.modeOfTransport">Mode Of Transport</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.modeOfTransportEquipment">Mode Of Transport Equipment</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.dischargeDate">Discharge Date</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.loadportDate">Loadport Date</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.loadLocation">Load Location</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.dischargeLocation">Discharge Location</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseTaxNo">Please Advise Warehouse Tax No</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseNo">Please Advise Warehouse No</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseWarehouseAddress">
                    Please Advise Warehouse Address
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseProductReceiverVAT">
                    Please Advise Product Receiver VAT
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseProductReceiver">Please Advise Product Receiver</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseLoadport">Please Advise Loadport</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.pleaseAdviseDisport">Please Advise Disport</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.preferentialOrigin">Preferential Origin</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.productGrade">Product Grade</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.productSpecification">Product Specification</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.productAdditionalComments">Product Additional Comments</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.adn">Adn</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.cdni">Cdni</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customAdn">Custom Adn</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customCdni">Custom Cdni</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.movementTransferType">Movement Transfer Type</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customLoadLocation">Custom Load Location</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customDischargeLocation">Custom Discharge Location</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customReceiverName">Custom Receiver Name</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customReceiverAddress">Custom Receiver Address</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customConsignor">Custom Consignor</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.notes">Notes</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.terminalCompanyLegalEntity">Terminal Company Legal Entity</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.customTerminalCompanyLegalEntity">
                    Custom Terminal Company Legal Entity
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.iceDelivery">Ice Delivery</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.legNomination.nominationMetadata">Nomination Metadata</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {legNominationList.map((legNomination, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${legNomination.id}`} color="link" size="sm">
                      {legNomination.id}
                    </Button>
                  </td>
                  <td>{legNomination.customerId}</td>
                  <td>{legNomination.agent}</td>
                  <td>{legNomination.last3Cargoes}</td>
                  <td>{legNomination.productId}</td>
                  <td>{legNomination.ownVatNo}</td>
                  <td>{legNomination.vettingReference}</td>
                  <td>{legNomination.warehouseAddress}</td>
                  <td>{legNomination.warehouseNo}</td>
                  <td>{legNomination.warehouseTaxNo}</td>
                  <td>{legNomination.productReceiverVat}</td>
                  <td>
                    <Translate contentKey={`nominationApp.CustomsStatus.${legNomination.customsStatus}`} />
                  </td>
                  <td>{legNomination.documentType}</td>
                  <td>{legNomination.clientClauseDetails}</td>
                  <td>
                    <Translate contentKey={`nominationApp.NominationStatus.${legNomination.status}`} />
                  </td>
                  <td>
                    <Translate contentKey={`nominationApp.NominationStatus.${legNomination.counterPartyStatus}`} />
                  </td>
                  <td>{legNomination.comment}</td>
                  <td>
                    {legNomination.nominationTimestamp ? (
                      <TextFormat type="date" value={legNomination.nominationTimestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{legNomination.cnCode}</td>
                  <td>{legNomination.financialHold}</td>
                  <td>{legNomination.consignor}</td>
                  <td>{legNomination.blendingInstructions}</td>
                  <td>{legNomination.productAdditives}</td>
                  <td>{legNomination.homogenisation}</td>
                  <td>{legNomination.productReceiverId}</td>
                  <td>
                    <Translate contentKey={`nominationApp.ModeOfTransport.${legNomination.modeOfTransport}`} />
                  </td>
                  <td>{legNomination.modeOfTransportEquipment}</td>
                  <td>
                    {legNomination.dischargeDate ? (
                      <TextFormat type="date" value={legNomination.dischargeDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {legNomination.loadportDate ? (
                      <TextFormat type="date" value={legNomination.loadportDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{legNomination.loadLocation}</td>
                  <td>{legNomination.dischargeLocation}</td>
                  <td>{legNomination.pleaseAdviseWarehouseTaxNo ? 'true' : 'false'}</td>
                  <td>{legNomination.pleaseAdviseWarehouseNo ? 'true' : 'false'}</td>
                  <td>{legNomination.pleaseAdviseWarehouseAddress ? 'true' : 'false'}</td>
                  <td>{legNomination.pleaseAdviseProductReceiverVAT ? 'true' : 'false'}</td>
                  <td>{legNomination.pleaseAdviseProductReceiver ? 'true' : 'false'}</td>
                  <td>{legNomination.pleaseAdviseLoadport ? 'true' : 'false'}</td>
                  <td>{legNomination.pleaseAdviseDisport ? 'true' : 'false'}</td>
                  <td>{legNomination.preferentialOrigin}</td>
                  <td>{legNomination.productGrade}</td>
                  <td>{legNomination.productSpecification}</td>
                  <td>{legNomination.productAdditionalComments}</td>
                  <td>{legNomination.adn}</td>
                  <td>{legNomination.cdni}</td>
                  <td>{legNomination.customAdn}</td>
                  <td>{legNomination.customCdni}</td>
                  <td>
                    <Translate contentKey={`nominationApp.MovementTransferType.${legNomination.movementTransferType}`} />
                  </td>
                  <td>{legNomination.customLoadLocation}</td>
                  <td>{legNomination.customDischargeLocation}</td>
                  <td>{legNomination.customReceiverName}</td>
                  <td>{legNomination.customReceiverAddress}</td>
                  <td>{legNomination.customConsignor}</td>
                  <td>{legNomination.notes}</td>
                  <td>{legNomination.terminalCompanyLegalEntity}</td>
                  <td>{legNomination.customTerminalCompanyLegalEntity}</td>
                  <td>{legNomination.iceDelivery ? 'true' : 'false'}</td>
                  <td>
                    {legNomination.nominationMetadataId ? (
                      <Link to={`nomination-metadata/${legNomination.nominationMetadataId}`}>{legNomination.nominationMetadataId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${legNomination.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${legNomination.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${legNomination.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="nominationApp.legNomination.home.notFound">No Leg Nominations found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ legNomination }: IRootState) => ({
  legNominationList: legNomination.entities,
  loading: legNomination.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LegNomination);
