import Vue from 'vue';
import types from './seances.types';
import {fetchSeances} from "./seances.api";
import {groupBy, prop, map, pipe, sortBy, filter, uniqBy} from 'ramda';
import moment from 'moment';


const state = {
    seances: null,
};

const getters = {
    seanceDates: (state) => {
        const seanceDates = pipe(
            map(seance => seance.start),
            sortBy(startDate => startDate.getTime()),
        uniqBy(startDate => extractDate(startDate)),
        );
        return seanceDates(state.seances);
    },
    moviesForDate: state => date => {
        const moviesForDate = pipe(
            filter(seance => isEqualByDate(seance.start, date)),
            map(seance => seance.movie),
            uniqBy(movie => movie.id)
        );
        return moviesForDate(state.seances);
    },
    seancesForMovieAndDay: state => (date, movieId) => {
        const seancesForMovieAndDate = pipe(
            filter(seance => isEqualByDate(seance.start, date)),
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

const extractDate = (dateTime) => dateTime.toLocaleDateString();
const isEqualByDate = (date1, date2) => extractDate(date1) === extractDate(date2);

export default {
  state,
  actions,
  mutations,
  getters,
};