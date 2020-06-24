import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPlaceholderParcel, defaultValue } from 'app/shared/model/placeholder-parcel.model';

export const ACTION_TYPES = {
  FETCH_PLACEHOLDERPARCEL_LIST: 'placeholderParcel/FETCH_PLACEHOLDERPARCEL_LIST',
  FETCH_PLACEHOLDERPARCEL: 'placeholderParcel/FETCH_PLACEHOLDERPARCEL',
  CREATE_PLACEHOLDERPARCEL: 'placeholderParcel/CREATE_PLACEHOLDERPARCEL',
  UPDATE_PLACEHOLDERPARCEL: 'placeholderParcel/UPDATE_PLACEHOLDERPARCEL',
  DELETE_PLACEHOLDERPARCEL: 'placeholderParcel/DELETE_PLACEHOLDERPARCEL',
  RESET: 'placeholderParcel/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPlaceholderParcel>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type PlaceholderParcelState = Readonly<typeof initialState>;

// Reducer

export default (state: PlaceholderParcelState = initialState, action): PlaceholderParcelState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PLACEHOLDERPARCEL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PLACEHOLDERPARCEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PLACEHOLDERPARCEL):
    case REQUEST(ACTION_TYPES.UPDATE_PLACEHOLDERPARCEL):
    case REQUEST(ACTION_TYPES.DELETE_PLACEHOLDERPARCEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PLACEHOLDERPARCEL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PLACEHOLDERPARCEL):
    case FAILURE(ACTION_TYPES.CREATE_PLACEHOLDERPARCEL):
    case FAILURE(ACTION_TYPES.UPDATE_PLACEHOLDERPARCEL):
    case FAILURE(ACTION_TYPES.DELETE_PLACEHOLDERPARCEL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLACEHOLDERPARCEL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PLACEHOLDERPARCEL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PLACEHOLDERPARCEL):
    case SUCCESS(ACTION_TYPES.UPDATE_PLACEHOLDERPARCEL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PLACEHOLDERPARCEL):
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

const apiUrl = 'api/placeholder-parcels';

// Actions

export const getEntities: ICrudGetAllAction<IPlaceholderParcel> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PLACEHOLDERPARCEL_LIST,
  payload: axios.get<IPlaceholderParcel>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IPlaceholderParcel> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PLACEHOLDERPARCEL,
    payload: axios.get<IPlaceholderParcel>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPlaceholderParcel> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PLACEHOLDERPARCEL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPlaceholderParcel> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PLACEHOLDERPARCEL,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPlaceholderParcel> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PLACEHOLDERPARCEL,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
