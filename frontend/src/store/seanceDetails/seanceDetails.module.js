import Vue from 'vue';
import types from './seanceDetails.types';
import {fetchSeanceDetails, makeReservation} from "./seanceDetails.api";


const state = {
    seance: null,
    movie: null,
    rows: null,
    reservationDetails: {
        name: null,
        surname: null,
        email: null,
        selectedSeats: []
    },
    reservationInProgress: false,
    reservationError: null
};

const getters = {
    movie: state => state.movie,
    seance: state => state.seance,
    rows: state => state.rows,
    reservationDetails: state => state.reservationDetails,
    reservationInProgress: state => state.reservationInProgress,
    reservationError: state => state.reservationError
};

const actions = {
    [types.actions.FETCH_DETAILS]({commit}, id) {
        return fetchSeanceDetails(id)
            .then(seanceDetails => commit(types.mutations.SET_SEANCE_DETAILS, seanceDetails))
    },
    [types.actions.OPEN_RESERVATION_DIALOG]({commit}) {
        commit(types.mutations.SET_DIALOG, true)
    },
    [types.actions.CLOSE_RESERVATION_DIALOG]({commit}) {
        commit(types.mutations.SET_DIALOG, false)
    },
    [types.actions.SELECT_SEATS]({commit}, seats) {
        commit(types.mutations.SET_SELECTED_SEATS, seats)
    },
    [types.actions.MAKE_RESERVATION]({commit}) {
        commit(types.mutations.SET_RESERVATION_PROGRESS, true);
        commit(types.mutations.SET_RESERVATION_ERROR, null);
        return makeReservation({...state.reservationDetails})
            .catch(reason => {
                commit(types.mutations.SET_RESERVATION_ERROR, reason);
                throw reason;
            })
            .finally(() => commit(types.mutations.SET_RESERVATION_PROGRESS, false))
    },
    [types.actions.TYPE_EMAIL]({commit}, email) {
        commit(types.mutations.SET_CLIENT_DETAIL, {
            type: "email",
            value: email
        })
    },
    [types.actions.TYPE_FIRST_NAME]({commit}, name) {
        commit(types.mutations.SET_CLIENT_DETAIL, {
            type: "name",
            value: name
        })
    },
    [types.actions.TYPE_SURNAME]({commit}, surname) {
        commit(types.mutations.SET_CLIENT_DETAIL, {
            type: "surname",
            value: surname
        })
    },
};

const mutations = {
    [types.mutations.SET_SEANCE_DETAILS]({commit}, seanceDetails) {
        Vue.set(state, 'seance', seanceDetails.seance);
        Vue.set(state, 'movie', seanceDetails.movie);
        Vue.set(state, 'rows', seanceDetails.rows);
    },
    [types.mutations.SET_DIALOG]({commit}, dialogState) {
        Vue.set(state, 'dialogIsOpen', dialogState);
    },
    [types.mutations.SET_SELECTED_SEATS]({commit}, selectedSeats) {
        Vue.set(state.reservationDetails, 'selectedSeats', selectedSeats)
    },
    [types.mutations.SET_CLIENT_DETAIL]({commit}, {type, value}) {
        Vue.set(state.reservationDetails, type, value);
    },
    [types.mutations.SET_RESERVATION_PROGRESS]({commit}, value) {
        Vue.set(state, 'reservationInProgress', value);
    },
    [types.mutations.SET_RESERVATION_ERROR]({commit}, error) {
        Vue.set(state, 'reservationError', error);
    },
};

export default {
    state,
    actions,
    mutations,
    getters,
};