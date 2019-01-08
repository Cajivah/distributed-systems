import Vue from 'vue';
import { mutations as mutationTypes, actions as actionTypes } from './seances.types';
import api from './seances.api';

const state = {
  seances: [],
};

const getters = {
  seances: state => state.seances,
};

const actions = {
  [actionTypes.FETCH_SEANCES]({ commit }, date) {
    return api.fetchSeances(date)
      .then(seances => commit(mutationTypes.SET_SEANCES, seances));
  },
};

const mutations = {
  [mutationTypes.SET_SEANCES](context, seances) {
    Vue.set(state, 'seances', seances.programme);
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
