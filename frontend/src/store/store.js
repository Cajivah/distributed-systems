import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth/auth.module';
import cinema from './cinema/cinema.module';
import booking from './booking/booking.module';
import seances from './seances/seances.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    cinema,
    booking,
    seances,
  },
});
