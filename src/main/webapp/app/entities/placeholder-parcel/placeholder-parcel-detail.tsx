import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './placeholder-parcel.reducer';
import { IPlaceholderParcel } from 'app/shared/model/placeholder-parcel.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlaceholderParcelDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PlaceholderParcelDetail = (props: IPlaceholderParcelDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { placeholderParcelEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nominationApp.placeholderParcel.detail.title">PlaceholderParcel</Translate> [
          <b>{placeholderParcelEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fakeProp">
              <Translate contentKey="nominationApp.placeholderParcel.fakeProp">Fake Prop</Translate>
            </span>
          </dt>
          <dd>{placeholderParcelEntity.fakeProp}</dd>
          <dt>
            <Translate contentKey="nominationApp.placeholderParcel.legNomination">Leg Nomination</Translate>
          </dt>
          <dd>{placeholderParcelEntity.legNominationId ? placeholderParcelEntity.legNominationId : ''}</dd>
        </dl>
        <Button tag={Link} to="/placeholder-parcel" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/placeholder-parcel/${placeholderParcelEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ placeholderParcel }: IRootState) => ({
  placeholderParcelEntity: placeholderParcel.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlaceholderParcelDetail);
