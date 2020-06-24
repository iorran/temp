import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './parcel.reducer';
import { IParcel } from 'app/shared/model/parcel.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IParcelDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ParcelDetail = (props: IParcelDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { parcelEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nominationApp.parcel.detail.title">Parcel</Translate> [<b>{parcelEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fakeProp">
              <Translate contentKey="nominationApp.parcel.fakeProp">Fake Prop</Translate>
            </span>
          </dt>
          <dd>{parcelEntity.fakeProp}</dd>
          <dt>
            <Translate contentKey="nominationApp.parcel.legNomination">Leg Nomination</Translate>
          </dt>
          <dd>{parcelEntity.legNominationId ? parcelEntity.legNominationId : ''}</dd>
        </dl>
        <Button tag={Link} to="/parcel" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/parcel/${parcelEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ parcel }: IRootState) => ({
  parcelEntity: parcel.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ParcelDetail);
