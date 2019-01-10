import Vue from 'vue';
import 'vuetify/dist/vuetify.min.css';
import 'material-design-icons-iconfont/dist/material-design-icons.css';
import '@mdi/font/css/materialdesignicons.css';
import DatetimePicker from 'vuetify-datetime-picker';
// import 'vuetify-datetime-picker/src/stylus/main.styl';
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

Vue.use(DatetimePicker);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
