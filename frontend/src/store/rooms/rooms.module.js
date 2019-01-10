import { actionTypes, mutationTypes } from './rooms.types';
import { fetchRooms, createRoom, updateRoom, prepareRoomPayload } from './rooms.api';
import { showErrorToasts } from '../../ToastConfigurer';

const state = {
  rooms: {},
};

const getters = {
  rooms: state => state.rooms,
};

const actions = {
  [actionTypes.FETCH_ROOMS]({ commit }, data) {
    return fetchRooms(data)
      .then(rooms => commit(mutationTypes.SET_ROOMS, rooms))
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [actionTypes.CREATE_ROOM](_, data) {
    const payload = prepareRoomPayload(data);
    return createRoom(payload)
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [actionTypes.UPDATE_ROOM](_, data) {
    const payload = prepareRoomPayload(data);
    return updateRoom(payload)
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
};

const mutations = {
  [mutationTypes.SET_ROOMS](state, rooms) {
    state.rooms = rooms;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
