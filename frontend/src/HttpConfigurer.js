import axios from 'axios';
import Vue from 'vue';
import VueAxios from 'vue-axios';

export const apiURL = 'https://localhost:8080/api';

const extractRoute = url => url.replace(apiURL, '');

export const isExcluded = (url) => {
  const excludedRoutesNames = ['landingPage', 'login'];
  const route = extractRoute(url);
  return excludedRoutesNames.indexOf(route) > -1;
};

Vue.use(VueAxios, axios.create({
  baseURL: apiURL,
  withCredentials: true,
  timeout: 3000,
}));
