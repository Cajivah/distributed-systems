<template>
    <v-container fluid fill-height>
        <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                    <v-toolbar dark color="primary">
                        <v-toolbar-title>Sign up</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form>
                            <v-text-field v-model="registerForm.email"
                                          prepend-icon="email"
                                          name="Email"
                                          label="Email"
                                          v-validate="'required|email'"
                                          :error-messages="errors.collect('Email')"
                                          required />
                            <v-text-field v-model="registerForm.username"
                                          prepend-icon="person"
                                          name="Username"
                                          :counter="15"
                                          v-validate="'required|max:15'"
                                          :error-messages="errors.collect('Username')"
                                          label="Username" />
                            <v-text-field v-model="registerForm.passwordPair.password"
                                          prepend-icon="lock"
                                          name="Password"
                                          label="Password"
                                          v-validate="{ required: true, password: true}"
                                          :error-messages="errors.collect('Password')"
                                          ref="Password"
                                          type="password" />
                            <v-text-field v-model="registerForm.passwordPair.matchingPassword"
                                          prepend-icon="lock"
                                          name="Matching Password"
                                          label="Repeat password"
                                          v-validate="'required|confirmed:Password'"
                                          :error-messages="errors.collect('Matching Password')"
                                          type="password" />
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <router-link to="login">Sign in</router-link>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" @click="signUp">Sign up</v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import { REGISTER } from '../store/auth/auth.actions';

export default {
  name: 'registration',
  data() {
    return {
      registerForm: {
        email: '',
        username: '',
        passwordPair: {
          password: '',
          matchingPassword: '',
        },
      },
    };
  },
  methods: {
    signUp() {
      this.$validator.validateAll()
        .then(valid => new Promise((resolve, reject) => {
          if (!valid) {
            reject();
          }
          const body = {
            ...this.registerForm,
            passwordPair: {
              password: this.registerForm.passwordPair.password,
              matchingPassword: this.registerForm.passwordPair.matchingPassword,
            },
          };
          this.$store
            .dispatch(REGISTER, body)
            .then(() => this.$router.push('login'))
            .catch(() => reject());
          this.registerForm.passwordPair.password = '';
          this.registerForm.passwordPair.matchingPassword = '';
          resolve();
        }));
    },
  },
};
</script>

<style scoped>

</style>
