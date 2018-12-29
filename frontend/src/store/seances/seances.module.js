import Vue from 'vue';
import types from './seances.types';
import {fetchSeances} from "./seances.api";

const state = {
    seances: [],
};

const getters = {
    seances: state => state.seances,
};

const actions = {
    [types.actions.FETCH_SEANCES]({commit}, date) {
        return fetchSeances(date)
            .then(seances => commit(types.mutations.SET_SEANCES, seances))
    },
};

const mutations = {
    [types.mutations.SET_SEANCES]({commit}, seances) {
        Vue.set(state, 'seances', seances.programme);
    },
};

export default {
  state,
  actions,
  mutations,
  getters,
};