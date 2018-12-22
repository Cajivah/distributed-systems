import Vue from 'vue';
import Vuetify from 'vuetify';

export default () => {
  Vue.use(Vuetify, {
    iconfont: 'mdi',
    theme: {
      primary: '#475B63',
      secondary: '#729B79',
      accent: '#2E2C2F',
      error: '#B0413E',
      info: '#587291',
      success: '#BACDB0',
      warning: '#FFC107',
      // F3E8EE
    },
  });
};
