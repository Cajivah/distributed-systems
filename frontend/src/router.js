import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Registration from './views/Registration.vue';
import Home from './views/Home.vue';
import Cancellation from './views/Cancellation.vue';
import { CANCELLATION, MOVIE_DETAILS, SEANCE_BOOKING, SEANCES } from './routes';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
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
      name: SEANCE_BOOKING,
      component: () => import('./views/SeanceBooking'),
    },
    {
      path: '/cancellation',
      name: CANCELLATION,
      component: Cancellation,
    },
  ],
});
