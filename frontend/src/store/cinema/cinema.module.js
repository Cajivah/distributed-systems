import { actions } from './cinema.actions';
import { mutations } from './cinema.mutations';

const state = {
  activeCinema: null,
  cinemas: [],
};

const getters = {
  activeCinema: state => state.activeCinema,
  cinemas: state => state.cinemas,
};

export default {
  state,
  actions,
  mutations,
  getters,
};
