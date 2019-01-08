import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth/auth.module';
import seances from './seances/seances.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    seances,
  },
});
