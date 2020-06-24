import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './build-draw.reducer';
import { IBuildDraw } from 'app/shared/model/build-draw.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBuildDrawDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BuildDrawDetail = (props: IBuildDrawDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { buildDrawEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="nominationApp.buildDraw.detail.title">BuildDraw</Translate> [<b>{buildDrawEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fakeProp">
              <Translate contentKey="nominationApp.buildDraw.fakeProp">Fake Prop</Translate>
            </span>
          </dt>
          <dd>{buildDrawEntity.fakeProp}</dd>
          <dt>
            <Translate contentKey="nominationApp.buildDraw.legNomination">Leg Nomination</Translate>
          </dt>
          <dd>{buildDrawEntity.legNominationId ? buildDrawEntity.legNominationId : ''}</dd>
        </dl>
        <Button tag={Link} to="/build-draw" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/build-draw/${buildDrawEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ buildDraw }: IRootState) => ({
  buildDrawEntity: buildDraw.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BuildDrawDetail);
