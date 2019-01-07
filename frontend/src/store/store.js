import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth/auth.module';
import booking from './booking/booking.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    booking,
  },
});
