import types from './rooms.types';
import { fetchRooms, createRoom, updateRoom, prepareRoomPayload } from './rooms.api';
import { showErrorToasts } from '../../ToastConfigurer';

const state = {
  rooms: {},
};

const getters = {
  rooms: state => state.rooms,
};

const actions = {
  [types.actions.FETCH_ROOMS]({ commit }, data) {
    return fetchRooms(data)
      .then(rooms => commit(types.mutations.SET_ROOMS, rooms));
  },
  [types.actions.CREATE_ROOM](_, data) {
    const payload = prepareRoomPayload(data);
    return createRoom(payload)
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [types.actions.UPDATE_ROOM](_, data) {
    const payload = prepareRoomPayload(data);
    return updateRoom(payload);
  },
};

const mutations = {
  [types.mutations.SET_ROOMS](state, rooms) {
    state.rooms = rooms;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
