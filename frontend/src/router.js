import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Registration from './views/Registration.vue';
import Home from './views/Home.vue';
import routeNames from './routes';

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
            name: routeNames.SEANCES,
            component: () => import('./views/Seances')
        },
        {
            path: '/movie/:movieId',
            name: routeNames.MOVIE_DETAILS,
            component: () => import('./views/MovieDetails')
        },
        {
            path: '/seances/:seanceId',
            name: routeNames.SEANCE_BOOKING,
            component: () => import('./views/SeanceBooking')
        }
    ],
});
