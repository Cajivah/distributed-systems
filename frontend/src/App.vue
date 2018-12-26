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

import TheNavbar from './components/TheNavbar.vue';
import TheFooter from './components/TheFooter.vue';

export default {
  name: 'App',
  components: {
    TheNavbar,
    TheFooter,
  },
};
</script>
<style lang="scss">
</style>
