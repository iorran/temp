import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NominationMetadata from './nomination-metadata';
import NominationMetadataDetail from './nomination-metadata-detail';
import NominationMetadataUpdate from './nomination-metadata-update';
import NominationMetadataDeleteDialog from './nomination-metadata-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NominationMetadataDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NominationMetadataUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NominationMetadataUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NominationMetadataDetail} />
      <ErrorBoundaryRoute path={match.url} component={NominationMetadata} />
    </Switch>
  </>
);

export default Routes;
