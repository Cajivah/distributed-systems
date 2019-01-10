import { actionTypes, mutationTypes } from './seances.types';
import { fetchSeances, createSeance, updateSeance } from './seances.api';
import { showErrorToasts } from '../../ToastConfigurer';

const state = {
  cinemaSeances: {},
};

const getters = {
  cinemaSeances: state => state.cinemaSeances,
};

const actions = {
  [actionTypes.FETCH_SEANCES]({ commit }, data) {
    return fetchSeances(data)
      .then(movies => commit(mutationTypes.SET_SEANCES, movies))
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [actionTypes.CREATE_SEANCE](_, data) {
    return createSeance(data)
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [actionTypes.UPDATE_SEANCE](_, data) {
    return updateSeance(data)
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
};

const mutations = {
  [mutationTypes.SET_SEANCES](state, seances) {
    state.cinemaSeances = seances;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
