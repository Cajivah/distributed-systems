import types from './booking.types';
import { fetchBookingToCancel, cancelBooking } from './booking.api';

const state = {
  bookingToCancel: null,
};

const getters = {
  bookingToCancel: state => state.bookingToCancel,
};

const actions = {
  [types.actions.FETCH_BOOKING_TO_CANCEL]({ commit }, data) {
    return fetchBookingToCancel(data)
      .then(booking => commit(types.mutations.SET_BOOKING_TO_CANCEL, booking));
  },
  [types.actions.CANCEL_BOOKING]({ commit }, data) {
    return cancelBooking(data)
      .then(() => commit(types.mutations.REMOVE_CANCELLED_BOOKING));
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
