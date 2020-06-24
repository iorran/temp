import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LegNomination from './leg-nomination';
import Parcel from './parcel';
import PlaceholderParcel from './placeholder-parcel';
import BuildDraw from './build-draw';
import NominationMetadata from './nomination-metadata';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}leg-nomination`} component={LegNomination} />
      <ErrorBoundaryRoute path={`${match.url}parcel`} component={Parcel} />
      <ErrorBoundaryRoute path={`${match.url}placeholder-parcel`} component={PlaceholderParcel} />
      <ErrorBoundaryRoute path={`${match.url}build-draw`} component={BuildDraw} />
      <ErrorBoundaryRoute path={`${match.url}nomination-metadata`} component={NominationMetadata} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
