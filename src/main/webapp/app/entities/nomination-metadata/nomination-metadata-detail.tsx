import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nomination-metadata.reducer';
import { INominationMetadata } from 'app/shared/model/nomination-metadata.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INominationMetadataDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NominationMetadataDetail = (props: INominationMetadataDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { nominationMetadataEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nominationApp.nominationMetadata.detail.title">NominationMetadata</Translate> [
          <b>{nominationMetadataEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fakeProp">
              <Translate contentKey="nominationApp.nominationMetadata.fakeProp">Fake Prop</Translate>
            </span>
          </dt>
          <dd>{nominationMetadataEntity.fakeProp}</dd>
        </dl>
        <Button tag={Link} to="/nomination-metadata" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nomination-metadata/${nominationMetadataEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ nominationMetadata }: IRootState) => ({
  nominationMetadataEntity: nominationMetadata.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NominationMetadataDetail);
