import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nomination-metadata.reducer';
import { INominationMetadata } from 'app/shared/model/nomination-metadata.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INominationMetadataProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NominationMetadata = (props: INominationMetadataProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { nominationMetadataList, match, loading } = props;
  return (
    <div>
      <h2 id="nomination-metadata-heading">
        <Translate contentKey="nominationApp.nominationMetadata.home.title">Nomination Metadata</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="nominationApp.nominationMetadata.home.createLabel">Create new Nomination Metadata</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {nominationMetadataList && nominationMetadataList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.nominationMetadata.fakeProp">Fake Prop</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nominationMetadataList.map((nominationMetadata, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${nominationMetadata.id}`} color="link" size="sm">
                      {nominationMetadata.id}
                    </Button>
                  </td>
                  <td>{nominationMetadata.fakeProp}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${nominationMetadata.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nominationMetadata.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nominationMetadata.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="nominationApp.nominationMetadata.home.notFound">No Nomination Metadata found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ nominationMetadata }: IRootState) => ({
  nominationMetadataList: nominationMetadata.entities,
  loading: nominationMetadata.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NominationMetadata);
