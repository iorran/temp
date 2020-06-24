import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILegNomination, defaultValue } from 'app/shared/model/leg-nomination.model';

export const ACTION_TYPES = {
  FETCH_LEGNOMINATION_LIST: 'legNomination/FETCH_LEGNOMINATION_LIST',
  FETCH_LEGNOMINATION: 'legNomination/FETCH_LEGNOMINATION',
  CREATE_LEGNOMINATION: 'legNomination/CREATE_LEGNOMINATION',
  UPDATE_LEGNOMINATION: 'legNomination/UPDATE_LEGNOMINATION',
  DELETE_LEGNOMINATION: 'legNomination/DELETE_LEGNOMINATION',
  RESET: 'legNomination/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILegNomination>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type LegNominationState = Readonly<typeof initialState>;

// Reducer

export default (state: LegNominationState = initialState, action): LegNominationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LEGNOMINATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LEGNOMINATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_LEGNOMINATION):
    case REQUEST(ACTION_TYPES.UPDATE_LEGNOMINATION):
    case REQUEST(ACTION_TYPES.DELETE_LEGNOMINATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_LEGNOMINATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LEGNOMINATION):
    case FAILURE(ACTION_TYPES.CREATE_LEGNOMINATION):
    case FAILURE(ACTION_TYPES.UPDATE_LEGNOMINATION):
    case FAILURE(ACTION_TYPES.DELETE_LEGNOMINATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_LEGNOMINATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_LEGNOMINATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_LEGNOMINATION):
    case SUCCESS(ACTION_TYPES.UPDATE_LEGNOMINATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_LEGNOMINATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/leg-nominations';

// Actions

export const getEntities: ICrudGetAllAction<ILegNomination> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LEGNOMINATION_LIST,
  payload: axios.get<ILegNomination>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ILegNomination> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LEGNOMINATION,
    payload: axios.get<ILegNomination>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ILegNomination> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LEGNOMINATION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILegNomination> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LEGNOMINATION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILegNomination> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LEGNOMINATION,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
