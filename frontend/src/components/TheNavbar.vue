<template>
    <v-toolbar app dark color="accent">
        <router-link to="/" class="text--no-decoration">
            <v-toolbar-title>Cinema</v-toolbar-title>
        </router-link>
        <v-spacer></v-spacer>
        <v-toolbar-items class="hidden-sm-and-down toolbar">
            <template v-if="isAuthenticated" >
                <template v-if="isAdmin">
                    <v-btn flat to="/movies">Movies</v-btn>
                </template>
                <a class="mr-3 text-xs-center text--color-white text--size-lg text--weight-md">
                    {{ userGreeter }}
                </a>
                <v-btn flat icon @click="handleLogout"><v-icon>logout</v-icon></v-btn>
            </template>
            <template v-else >
                <v-btn flat to="/login">Sign in</v-btn>
                <v-btn flat to="/registration">Registration</v-btn>
            </template>
        </v-toolbar-items>
    </v-toolbar>
</template>

<script>
import { mapGetters } from 'vuex';
import { LOGOUT } from '../store/auth/auth.actions';

export default {
  name: 'navbar',
  props: ['app'],
  computed: {
    userGreeter() {
      return `Hi, ${this.$store.getters.loggedUser.username}!`;
    },
    isAdmin() {
      return !!this.loggedUser.roles.find(role => role.name === 'ADMIN');
    },
    ...mapGetters([
      'isAuthenticated',
      'loggedUser',
    ]),
  },
  methods: {
    handleLogout() {
      this.$store.dispatch(LOGOUT)
        .then(() => this.$router.push({ name: 'login' }));
    },
  },
};
</script>

<style lang="scss">
    .v {
        &-toolbar {
            &__title {
                text-decoration: none;
                color: #fff;
            }
            &__items {
                line-height: 64px;
            }
        }
    }
    .greeter {
        margin-right: 10px;
    }
</style>
