import Vue from 'vue';
import types from './seances.types';
import {fetchSeances} from "./seances.api";
import {groupBy, prop, map, pipe, uniq, filter, uniqBy} from 'ramda';

const state = {
    seances: null,
};

const getters = {
    seancesByDateAndMovie: (state) => {
        const byDateAndMovie = pipe(
            groupBy(prop('start')),
            map(groupBy(seance => seance.movie.title))
        );
        return byDateAndMovie(state.seances);
    },
    seanceDates: (state) => {
        const seanceDates = pipe(
            map(seance => seance.start),
            uniq()
        );
        return seanceDates(state.seances);
    },
    moviesForDate: state => date => {
        const moviesForDate = pipe(
            filter(seance => seance.start === date),
            map(seance => seance.movie),
            uniqBy(movie => movie.id)
        );
        return moviesForDate(state.seances);
    },
    seancesForMovieAndDate: state => (date, movieId) => {
        const seancesForMovieAndDate = pipe(
            filter(seance => seance.start === date),
            filter(seance => seance.movie.id === movieId)
        );
        return seancesForMovieAndDate(state.seances);
    },
    seances: state => state.seances,
};

const actions = {
    [types.actions.FETCH_SEANCES]({ commit }) {
        return fetchSeances()
            .then(seances => commit(types.mutations.FETCH_SEANCES_COMPLETED, seances))
    },
};

const mutations = {
    [types.mutations.FETCH_SEANCES_COMPLETED]({commit}, seances) {
        Vue.set(state, 'seances', seances.content);
    },
};

export default {
  state,
  actions,
  mutations,
  getters,
};