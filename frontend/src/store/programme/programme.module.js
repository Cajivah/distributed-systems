import Vue from 'vue';
import { mutations as mutationTypes, actions as actionTypes } from './programme.types';
import api from './programme.api';

const state = {
  seances: [],
};

const getters = {
  seances: state => state.seances,
};

const actions = {
  [actionTypes.FETCH_PROGRAMME]({ commit, rootGetters }, date) {
    return api.fetchSeances(rootGetters.activeCinema, date)
      .then(seances => commit(mutationTypes.SET_PROGRAMME, seances));
  },
};

const mutations = {
  [mutationTypes.SET_PROGRAMME](context, seances) {
    Vue.set(state, 'seances', seances.programme);
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
