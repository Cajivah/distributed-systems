import { actionTypes, mutationTypes } from './movie.types';
import { fetchMovies, fetchMovie, createMovie, updateMovie } from './movie.api';
import { showErrorToasts } from '../../ToastConfigurer';

const state = {
  movies: {},
  selectedMovie: {},
};

const getters = {
  movies: state => state.movies,
  selectedMovie: state => state.selectedMovie,
};

const actions = {
  [actionTypes.FETCH_MOVIES]({ commit }, data) {
    return fetchMovies(data)
      .then(movies => commit(mutationTypes.SET_MOVIES, movies));
  },
  [actionTypes.CREATE_MOVIE]({ commit }, data) {
    return createMovie(data)
      .then(movie => commit(mutationTypes.SET_SELECTED_MOVIE, movie))
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [actionTypes.FETCH_MOVIE]({ commit }, id) {
    return fetchMovie(id)
      .then(movie => commit(mutationTypes.SET_SELECTED_MOVIE, movie));
  },
  [actionTypes.UPDATE_MOVIE]({ commit }, data) {
    return updateMovie(data)
      .then(movie => commit(mutationTypes.SET_SELECTED_MOVIE, movie));
  },
};

const mutations = {
  [mutationTypes.SET_MOVIES](state, movies) {
    state.movies = movies;
  },
  [mutationTypes.SET_SELECTED_MOVIE](state, movie) {
    state.selectedMovie = movie;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
