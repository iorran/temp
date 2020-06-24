import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './build-draw.reducer';
import { IBuildDraw } from 'app/shared/model/build-draw.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBuildDrawProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BuildDraw = (props: IBuildDrawProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { buildDrawList, match, loading } = props;
  return (
    <div>
      <h2 id="build-draw-heading">
        <Translate contentKey="nominationApp.buildDraw.home.title">Build Draws</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="nominationApp.buildDraw.home.createLabel">Create new Build Draw</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {buildDrawList && buildDrawList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.buildDraw.fakeProp">Fake Prop</Translate>
                </th>
                <th>
                  <Translate contentKey="nominationApp.buildDraw.legNomination">Leg Nomination</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {buildDrawList.map((buildDraw, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${buildDraw.id}`} color="link" size="sm">
                      {buildDraw.id}
                    </Button>
                  </td>
                  <td>{buildDraw.fakeProp}</td>
                  <td>
                    {buildDraw.legNominationId ? (
                      <Link to={`leg-nomination/${buildDraw.legNominationId}`}>{buildDraw.legNominationId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${buildDraw.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${buildDraw.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${buildDraw.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="nominationApp.buildDraw.home.notFound">No Build Draws found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ buildDraw }: IRootState) => ({
  buildDrawList: buildDraw.entities,
  loading: buildDraw.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BuildDraw);
