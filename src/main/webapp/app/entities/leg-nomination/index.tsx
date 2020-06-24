import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LegNomination from './leg-nomination';
import LegNominationDetail from './leg-nomination-detail';
import LegNominationUpdate from './leg-nomination-update';
import LegNominationDeleteDialog from './leg-nomination-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={LegNominationDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LegNominationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LegNominationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LegNominationDetail} />
      <ErrorBoundaryRoute path={match.url} component={LegNomination} />
    </Switch>
  </>
);

export default Routes;
