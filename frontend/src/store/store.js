import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth/auth.module';
import seances from './seances/seances.module'
import seanceDetails from './seanceDetails/seanceDetails.module'

Vue.use(Vuex);

export const SEANCE_DETAILS_STORE = "seanceDetails";

export default new Vuex.Store({
  modules: {
    auth,
      seances: seances,
      [SEANCE_DETAILS_STORE]: {
          namespaced: true,
          ...seanceDetails
      }
  },
});

