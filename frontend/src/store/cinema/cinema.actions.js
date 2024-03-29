import Vue from 'vue';
import { SET_CINEMAS, SET_ACTIVE_CINEMA, SET_CINEMA_FROM_LOCAL } from './cinema.mutations';

export const FETCH_CINEMAS = 'fetchCinemas';
export const SELECT_CINEMA = 'selectCinema';
export const UPDATE_CINEMA = 'updateCinema';

export const actions = {
  [FETCH_CINEMAS]({ commit }) {
    return Vue.axios.get('/cinema')
      .then(({ data }) => commit(SET_CINEMAS, data))
      .catch(({ response }) => { throw response.data; });
  },
  [SELECT_CINEMA]({ commit }, payload) {
    const { cinema } = payload;
    return new Promise((resolve) => {
      commit(SET_ACTIVE_CINEMA, cinema);
      resolve(cinema);
    });
  },
  [UPDATE_CINEMA]({ commit }) {
    return new Promise((resolve) => {
      commit(SET_CINEMA_FROM_LOCAL);
      resolve();
    });
  },
};
