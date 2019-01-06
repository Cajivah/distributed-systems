import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth/auth.module';
import cinema from './cinema/cinema.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    cinema,
  },
});
