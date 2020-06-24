import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './parcel.reducer';
import { IParcel } from 'app/shared/model/parcel.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IParcelProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Parcel = (props: IParcelProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { parcelList, match, loading } = props;
  return (
    <div>
      <h2 id="parcel-heading">
        <Translate contentKey="nominationApp.parcel.home.title">Parcels</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="nominationApp.parcel.home.createLabel">Create new Parcel</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {parcelList && parcelList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.parcel.fakeProp">Fake Prop</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.parcel.legNomination">Leg Nomination</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {parcelList.map((parcel, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${parcel.id}`} color="link" size="sm">
                      {parcel.id}
                    </Button>
                  </td>
                  <td>{parcel.fakeProp}</td>
                  <td>
                    {parcel.legNominationId ? <Link to={`leg-nomination/${parcel.legNominationId}`}>{parcel.legNominationId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${parcel.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${parcel.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${parcel.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="nominationApp.parcel.home.notFound">No Parcels found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ parcel }: IRootState) => ({
  parcelList: parcel.entities,
  loading: parcel.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Parcel);
