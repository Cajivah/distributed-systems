import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth/auth.module';
import cinema from './cinema/cinema.module';
import booking from './booking/booking.module';
import programme from './programme/programme.module';
import seanceDetails, { SEANCE_DETAILS_STORE } from './seanceDetails/seanceDetails.module';
import movies from './movie/movie.module';
import rooms from './rooms/rooms.module';
import seances from './seances/seances.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    cinema,
    booking,
    movies,
    rooms,
    seances,
    programme,
    [SEANCE_DETAILS_STORE]: {
      namespaced: true,
      ...seanceDetails,
    },
  },
});
