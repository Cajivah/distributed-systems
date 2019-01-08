export const SET_CINEMAS = 'setCinemas';
export const SET_ACTIVE_CINEMA = 'setActiveCinema';

export const mutations = {
  [SET_CINEMAS](state, data) {
    state.cinemas = data;
  },
  [SET_ACTIVE_CINEMA](state, data) {
    state.activeCinema = data;
  },
};

