import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PlaceholderParcel from './placeholder-parcel';
import PlaceholderParcelDetail from './placeholder-parcel-detail';
import PlaceholderParcelUpdate from './placeholder-parcel-update';
import PlaceholderParcelDeleteDialog from './placeholder-parcel-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={PlaceholderParcelDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PlaceholderParcelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PlaceholderParcelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PlaceholderParcelDetail} />
      <ErrorBoundaryRoute path={match.url} component={PlaceholderParcel} />
    </Switch>
  </>
);

export default Routes;
