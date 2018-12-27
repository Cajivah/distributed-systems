import { actions } from './auth.actions';
import { mutations } from './auth.mutations';

const state = {
  loggedUser: null,
};

const getters = {
  isAuthenticated: state => !!state.loggedUser,
  loggedUser: state => state.loggedUser,
};

export default {
  state,
  actions,
  mutations,
  getters,
};
