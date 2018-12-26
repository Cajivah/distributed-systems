import axios from 'axios';
import Vue from 'vue';
import VueAxios from 'vue-axios';

export default () => {
  Vue.use(VueAxios, axios.create({
    baseURL: process.env.API_URL,
    withCredentials: true,
    timeout: 3000,
  }));
};
