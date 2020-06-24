import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INominationMetadata, defaultValue } from 'app/shared/model/nomination-metadata.model';

export const ACTION_TYPES = {
  FETCH_NOMINATIONMETADATA_LIST: 'nominationMetadata/FETCH_NOMINATIONMETADATA_LIST',
  FETCH_NOMINATIONMETADATA: 'nominationMetadata/FETCH_NOMINATIONMETADATA',
  CREATE_NOMINATIONMETADATA: 'nominationMetadata/CREATE_NOMINATIONMETADATA',
  UPDATE_NOMINATIONMETADATA: 'nominationMetadata/UPDATE_NOMINATIONMETADATA',
  DELETE_NOMINATIONMETADATA: 'nominationMetadata/DELETE_NOMINATIONMETADATA',
  RESET: 'nominationMetadata/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INominationMetadata>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type NominationMetadataState = Readonly<typeof initialState>;

// Reducer

export default (state: NominationMetadataState = initialState, action): NominationMetadataState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NOMINATIONMETADATA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NOMINATIONMETADATA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_NOMINATIONMETADATA):
    case REQUEST(ACTION_TYPES.UPDATE_NOMINATIONMETADATA):
    case REQUEST(ACTION_TYPES.DELETE_NOMINATIONMETADATA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_NOMINATIONMETADATA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NOMINATIONMETADATA):
    case FAILURE(ACTION_TYPES.CREATE_NOMINATIONMETADATA):
    case FAILURE(ACTION_TYPES.UPDATE_NOMINATIONMETADATA):
    case FAILURE(ACTION_TYPES.DELETE_NOMINATIONMETADATA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOMINATIONMETADATA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_NOMINATIONMETADATA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_NOMINATIONMETADATA):
    case SUCCESS(ACTION_TYPES.UPDATE_NOMINATIONMETADATA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_NOMINATIONMETADATA):
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

const apiUrl = 'api/nomination-metadata';

// Actions

export const getEntities: ICrudGetAllAction<INominationMetadata> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NOMINATIONMETADATA_LIST,
  payload: axios.get<INominationMetadata>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<INominationMetadata> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NOMINATIONMETADATA,
    payload: axios.get<INominationMetadata>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<INominationMetadata> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NOMINATIONMETADATA,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INominationMetadata> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NOMINATIONMETADATA,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<INominationMetadata> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NOMINATIONMETADATA,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
