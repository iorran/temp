import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILegNomination } from 'app/shared/model/leg-nomination.model';
import { getEntities as getLegNominations } from 'app/entities/leg-nomination/leg-nomination.reducer';
import { getEntity, updateEntity, createEntity, reset } from './build-draw.reducer';
import { IBuildDraw } from 'app/shared/model/build-draw.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBuildDrawUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BuildDrawUpdate = (props: IBuildDrawUpdateProps) => {
  const [legNominationId, setLegNominationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { buildDrawEntity, legNominations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/build-draw');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getLegNominations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...buildDrawEntity,
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
          <h2 id="nominationApp.buildDraw.home.createOrEditLabel">
            <Translate contentKey="nominationApp.buildDraw.home.createOrEditLabel">Create or edit a BuildDraw</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : buildDrawEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="build-draw-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="build-draw-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="fakePropLabel" for="build-draw-fakeProp">
                  <Translate contentKey="nominationApp.buildDraw.fakeProp">Fake Prop</Translate>
                </Label>
                <AvField id="build-draw-fakeProp" type="text" name="fakeProp" />
              </AvGroup>
              <AvGroup>
                <Label for="build-draw-legNomination">
                  <Translate contentKey="nominationApp.buildDraw.legNomination">Leg Nomination</Translate>
                </Label>
                <AvInput id="build-draw-legNomination" type="select" className="form-control" name="legNominationId">
                  <option value="" key="0" />
                  {legNominations
                    ? legNominations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/build-draw" replace color="info">
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
  legNominations: storeState.legNomination.entities,
  buildDrawEntity: storeState.buildDraw.entity,
  loading: storeState.buildDraw.loading,
  updating: storeState.buildDraw.updating,
  updateSuccess: storeState.buildDraw.updateSuccess,
});

const mapDispatchToProps = {
  getLegNominations,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BuildDrawUpdate);
