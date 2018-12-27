import Vue from 'vue';
import 'vuetify/dist/vuetify.min.css';
import './styles/main.scss';
import App from './App.vue';
import router from './router';
import store from './store/store';
import HttpConfigurer from './config/HttpConfigurer';
import VuetifyConfigurer from './config/VuetifyConfigurer';
import ValidatorConfigurer from './config/ValidatorConfigurer';

Vue.config.productionTip = false;

HttpConfigurer();
VuetifyConfigurer();
ValidatorConfigurer();

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
