import Vue from 'vue';
import { actions as actionTypes, mutations as mutationTypes} from './seanceDetails.types';
import { fetchSeanceDetails, makeReservation } from './seanceDetails.api';


const state = {
  seance: null,
  movie: null,
  rows: null,
  reservationDetails: {
    name: null,
    surname: null,
    email: null,
    selectedSeats: [],
  },
  reservationInProgress: false,
  reservationError: null,
};

const getters = {
  movie: state => state.movie,
  seance: state => state.seance,
  rows: state => state.rows,
  reservationDetails: state => state.reservationDetails,
  reservationInProgress: state => state.reservationInProgress,
  reservationError: state => state.reservationError,
};

const actions = {
  [actionTypes.FETCH_DETAILS]({ commit }, id) {
    return fetchSeanceDetails(id)
      .then(seanceDetails => commit(mutationTypes.SET_SEANCE_DETAILS, seanceDetails));
  },
  [actionTypes.OPEN_RESERVATION_DIALOG]({ commit }) {
    commit(mutationTypes.SET_DIALOG, true);
  },
  [actionTypes.CLOSE_RESERVATION_DIALOG]({ commit }) {
    commit(mutationTypes.SET_DIALOG, false);
  },
  [actionTypes.SELECT_SEATS]({ commit }, seats) {
    commit(mutationTypes.SET_SELECTED_SEATS, seats);
  },
  [actionTypes.MAKE_RESERVATION]({ commit }) {
    commit(mutationTypes.SET_RESERVATION_PROGRESS, true);
    commit(mutationTypes.SET_RESERVATION_ERROR, null);
    return makeReservation({ ...state.reservationDetails })
      .catch((reason) => {
        commit(mutationTypes.SET_RESERVATION_ERROR, reason);
        throw reason;
      })
      .finally(() => commit(mutationTypes.SET_RESERVATION_PROGRESS, false));
  },
  [actionTypes.TYPE_EMAIL]({ commit }, email) {
    commit(mutationTypes.SET_CLIENT_DETAIL, {
      type: 'email',
      value: email,
    });
  },
  [actionTypes.TYPE_FIRST_NAME]({ commit }, name) {
    commit(mutationTypes.SET_CLIENT_DETAIL, {
      type: 'name',
      value: name,
    });
  },
  [actionTypes.TYPE_SURNAME]({ commit }, surname) {
    commit(mutationTypes.SET_CLIENT_DETAIL, {
      type: 'surname',
      value: surname,
    });
  },
};

const mutations = {
  [mutationTypes.SET_SEANCE_DETAILS](context, seanceDetails) {
    Vue.set(state, 'seance', seanceDetails.seance);
    Vue.set(state, 'movie', seanceDetails.movie);
    Vue.set(state, 'rows', seanceDetails.rows);
  },
  [mutationTypes.SET_DIALOG](context, dialogState) {
    Vue.set(state, 'dialogIsOpen', dialogState);
  },
  [mutationTypes.SET_SELECTED_SEATS](context, selectedSeats) {
    Vue.set(state.reservationDetails, 'selectedSeats', selectedSeats);
  },
  [mutationTypes.SET_CLIENT_DETAIL](context, { type, value }) {
    Vue.set(state.reservationDetails, type, value);
  },
  [mutationTypes.SET_RESERVATION_PROGRESS](context, value) {
    Vue.set(state, 'reservationInProgress', value);
  },
  [mutationTypes.SET_RESERVATION_ERROR](context, error) {
    Vue.set(state, 'reservationError', error);
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
