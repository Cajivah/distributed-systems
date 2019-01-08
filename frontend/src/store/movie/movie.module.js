import types from './movie.types';
import { fetchMovies, fetchMovie, createMovie, updateMovie } from './movie.api';

const state = {
  movies: {},
  selectedMovie: {},
};

const getters = {
  movies: state => state.movies,
  selectedMovie: state => state.selectedMovie,
};

const actions = {
  [types.actions.FETCH_MOVIES]({ commit }, data) {
    return fetchMovies(data)
      .then(movies => commit(types.mutations.SET_MOVIES, movies));
  },
  [types.actions.CREATE_MOVIE]({ commit }, data) {
    return createMovie(data)
      .then(movie => commit(types.mutations.SET_SELECTED_MOVIE, movie));
  },
  [types.actions.FETCH_MOVIE]({ commit }, id) {
    return fetchMovie(id)
      .then(movie => commit(types.mutations.SET_SELECTED_MOVIE, movie));
  },
  [types.actions.UPDATE_MOVIE]({ commit }, data) {
    return updateMovie(data)
      .then(movie => commit(types.mutations.SET_SELECTED_MOVIE, movie));
  },
};

const mutations = {
  [types.mutations.SET_BOOKING_TO_CANCEL](state, booking) {
    state.bookingToCancel = booking;
  },
  [types.mutations.REMOVE_CANCELLED_BOOKING](state) {
    state.bookingToCancel = null;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
