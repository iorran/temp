import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBuildDraw, defaultValue } from 'app/shared/model/build-draw.model';

export const ACTION_TYPES = {
  FETCH_BUILDDRAW_LIST: 'buildDraw/FETCH_BUILDDRAW_LIST',
  FETCH_BUILDDRAW: 'buildDraw/FETCH_BUILDDRAW',
  CREATE_BUILDDRAW: 'buildDraw/CREATE_BUILDDRAW',
  UPDATE_BUILDDRAW: 'buildDraw/UPDATE_BUILDDRAW',
  DELETE_BUILDDRAW: 'buildDraw/DELETE_BUILDDRAW',
  RESET: 'buildDraw/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBuildDraw>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type BuildDrawState = Readonly<typeof initialState>;

// Reducer

export default (state: BuildDrawState = initialState, action): BuildDrawState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BUILDDRAW_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BUILDDRAW):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BUILDDRAW):
    case REQUEST(ACTION_TYPES.UPDATE_BUILDDRAW):
    case REQUEST(ACTION_TYPES.DELETE_BUILDDRAW):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BUILDDRAW_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BUILDDRAW):
    case FAILURE(ACTION_TYPES.CREATE_BUILDDRAW):
    case FAILURE(ACTION_TYPES.UPDATE_BUILDDRAW):
    case FAILURE(ACTION_TYPES.DELETE_BUILDDRAW):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BUILDDRAW_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BUILDDRAW):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BUILDDRAW):
    case SUCCESS(ACTION_TYPES.UPDATE_BUILDDRAW):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BUILDDRAW):
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

const apiUrl = 'api/build-draws';

// Actions

export const getEntities: ICrudGetAllAction<IBuildDraw> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BUILDDRAW_LIST,
  payload: axios.get<IBuildDraw>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IBuildDraw> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BUILDDRAW,
    payload: axios.get<IBuildDraw>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBuildDraw> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BUILDDRAW,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBuildDraw> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BUILDDRAW,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBuildDraw> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BUILDDRAW,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
