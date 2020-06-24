import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/leg-nomination">
      <Translate contentKey="global.menu.entities.legNomination" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/parcel">
      <Translate contentKey="global.menu.entities.parcel" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/placeholder-parcel">
      <Translate contentKey="global.menu.entities.placeholderParcel" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/build-draw">
      <Translate contentKey="global.menu.entities.buildDraw" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/nomination-metadata">
      <Translate contentKey="global.menu.entities.nominationMetadata" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
