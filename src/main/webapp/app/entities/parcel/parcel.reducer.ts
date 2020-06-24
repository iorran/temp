import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IParcel, defaultValue } from 'app/shared/model/parcel.model';

export const ACTION_TYPES = {
  FETCH_PARCEL_LIST: 'parcel/FETCH_PARCEL_LIST',
  FETCH_PARCEL: 'parcel/FETCH_PARCEL',
  CREATE_PARCEL: 'parcel/CREATE_PARCEL',
  UPDATE_PARCEL: 'parcel/UPDATE_PARCEL',
  DELETE_PARCEL: 'parcel/DELETE_PARCEL',
  RESET: 'parcel/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IParcel>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ParcelState = Readonly<typeof initialState>;

// Reducer

export default (state: ParcelState = initialState, action): ParcelState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PARCEL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PARCEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PARCEL):
    case REQUEST(ACTION_TYPES.UPDATE_PARCEL):
    case REQUEST(ACTION_TYPES.DELETE_PARCEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PARCEL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PARCEL):
    case FAILURE(ACTION_TYPES.CREATE_PARCEL):
    case FAILURE(ACTION_TYPES.UPDATE_PARCEL):
    case FAILURE(ACTION_TYPES.DELETE_PARCEL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PARCEL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PARCEL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PARCEL):
    case SUCCESS(ACTION_TYPES.UPDATE_PARCEL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PARCEL):
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

const apiUrl = 'api/parcels';

// Actions

export const getEntities: ICrudGetAllAction<IParcel> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PARCEL_LIST,
  payload: axios.get<IParcel>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IParcel> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PARCEL,
    payload: axios.get<IParcel>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IParcel> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PARCEL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IParcel> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PARCEL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IParcel> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PARCEL,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
