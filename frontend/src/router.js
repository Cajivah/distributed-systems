import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Registration from './views/Registration.vue';
import Verification from './views/Verification.vue';
import Cancellation from './views/Cancellation.vue';
import { MOVIE_DETAILS, SEANCE_BOOKING, SEANCES, MOVIES_MANAGEMENT, ROOMS_MANAGEMENT, SEANCES_MANAGEMENT } from './routes';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: { name: SEANCES },
    },
    {
      path: '/verification',
      name: 'verification',
      component: Verification,
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
      path: '/movies-management',
      name: MOVIES_MANAGEMENT,
      component: () => import('./views/Movies'),
    },
    {
      path: '/rooms-management',
      name: ROOMS_MANAGEMENT,
      component: () => import('./views/Rooms'),
    },
    {
      path: '/seances-management',
      name: SEANCES_MANAGEMENT,
      component: () => import('./views/SeancesManagement'),
    },
    {
      path: '/seances/:seanceId',
      name: SEANCE_BOOKING,
      component: () => import('./views/SeanceBooking'),
    },
  ],
});
