import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BuildDraw from './build-draw';
import BuildDrawDetail from './build-draw-detail';
import BuildDrawUpdate from './build-draw-update';
import BuildDrawDeleteDialog from './build-draw-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BuildDrawDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BuildDrawUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BuildDrawUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BuildDrawDetail} />
      <ErrorBoundaryRoute path={match.url} component={BuildDraw} />
    </Switch>
  </>
);

export default Routes;
