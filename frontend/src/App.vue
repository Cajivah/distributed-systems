<template>
  <v-app>
    <the-navbar />
    <v-content>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-content>
    <the-footer />
  </v-app>
</template>
<script>
import { mapActions } from 'vuex';
import TheNavbar from './components/TheNavbar.vue';
import TheFooter from './components/TheFooter.vue';
import { FETCH_LOGGED_USER } from './store/auth/auth.actions';
import { FETCH_CINEMAS, UPDATE_CINEMA } from './store/cinema/cinema.actions';

export default {
  name: 'App',
  components: {
    TheNavbar,
    TheFooter,
  },
  created() {
    this.fetchLoggedUser();
    this.fetchCinemas().then(() => this.updateCinema());
  },
  methods: {
    ...mapActions({
      updateCinema: UPDATE_CINEMA,
      fetchCinemas: FETCH_CINEMAS,
      fetchLoggedUser: FETCH_LOGGED_USER,
    }),
  },
};
</script>
<style lang="scss">
</style>
