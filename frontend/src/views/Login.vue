<template>
    <v-container fluid fill-height>
        <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                    <v-toolbar dark color="primary">
                        <v-toolbar-title>Sign in</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form>
                            <v-text-field prepend-icon="person"
                                          v-model="loginForm.username"
                                          name="Username or email"
                                          label="Username or email"
                                          v-validate="'required'"
                                          :error-messages="errors.collect('Username or email')"
                                          type="text">
                            </v-text-field>
                            <v-text-field prepend-icon="lock"
                                          v-model="loginForm.password"
                                          name="Password"
                                          label="Password"
                                          v-validate="'required'"
                                          :error-messages="errors.collect('Password')"
                                          id="password"
                                          type="password">
                            </v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <router-link to="registration">Sign up</router-link>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="submit" >Login</v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import { LOGIN } from '../store/auth/auth.actions';

export default {
  name: 'login',
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    submit() {
      this.$validator.validate().then((valid) => {
        if (!valid) {
          return;
        }
        this.$store
          .dispatch(LOGIN, { ...this.loginForm })
          .then(() => this.$router.push('/'));
        this.resetForm();
      });
    },
    resetForm() {
      this.loginForm.username = '';
      this.loginForm.password = '';
    },
  },
};
</script>

<style scoped>

</style>
