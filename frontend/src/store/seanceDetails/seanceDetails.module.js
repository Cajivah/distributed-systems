import Vue from 'vue';
import types from './seanceDetails.types';
import {fetchSeanceDetails} from "./seanceDetails.api";


const state = {
    seance: null,
    movie: null,
    rows: null,
    dialogIsOpen: false,
    reservationDetails: {
        name: null,
        surname: null,
        email: null,
        selectedSeats: []
    }

};

const getters = {
    movie: state => state.movie,
    seance: state => state.seance,
    rows: state => state.rows,
    reservationDialog: state => state.dialogIsOpen,
    reservationDetails: state => state.reservationDetails
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
};

export default {
    state,
    actions,
    mutations,
    getters,
};