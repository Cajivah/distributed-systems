import Vue from 'vue';
import Router from 'vue-router';
import VeeValidate from 'vee-validate';
import Login from './views/Login.vue';
import Registration from './views/Registration.vue';
import Home from './views/Home.vue';
import Cancellation from './views/Cancellation.vue';
import { MOVIE_DETAILS, SEANCE_DETAILS, SEANCES } from './routes';

Vue.use(Router);
Vue.use(VeeValidate);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/cancellation',
      name: 'cancellation',
      component: Cancellation,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/registration',
      name: 'registration',
      component: Registration,
    },
    {
      path: '/seances',
      name: SEANCES,
      component: () => import('./views/Seances'),
    },
    {
      path: '/movie/:movieId',
      name: MOVIE_DETAILS,
      component: () => import('./views/MovieDetails'),
    },
    {
      path: '/seances/:seanceId',
      name: SEANCE_DETAILS,
      component: () => import('./views/SeanceBooking'),
    },
  ],
});
