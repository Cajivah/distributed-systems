import Vue from 'vue';
import Router from 'vue-router';
import Login from './views/Login.vue';
import Registration from './views/Registration.vue';
import Home from './views/Home.vue';

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
          name: 'seances',
              component: () => import('./views/Seances')
      },
  ],
});
