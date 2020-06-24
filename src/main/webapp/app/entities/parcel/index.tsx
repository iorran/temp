import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Parcel from './parcel';
import ParcelDetail from './parcel-detail';
import ParcelUpdate from './parcel-update';
import ParcelDeleteDialog from './parcel-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ParcelDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ParcelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ParcelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ParcelDetail} />
      <ErrorBoundaryRoute path={match.url} component={Parcel} />
    </Switch>
  </>
);

export default Routes;
