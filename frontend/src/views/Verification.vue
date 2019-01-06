<template>
    <v-container fluid fill-height>
        <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                    <v-toolbar dark color="primary">
                        <v-toolbar-title>Email verification</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-progress-circular
                                v-if="loading"
                                indeterminate
                                color="primary"
                        ></v-progress-circular>
                        <template v-if="!loading && verified">
                            Your email address has been successfully verified!
                        </template>
                        <template v-else-if="!loading && !verified">
                            Email verification failed. Try again by reloading the page.
                        </template>
                    </v-card-text>
                    <v-card-actions v-if="!loading">
                        <v-spacer></v-spacer>
                        <v-btn v-if="verified" color="primary" to="login">
                            Go to Login
                        </v-btn>
                        <v-btn v-else color="primary" to="registration">
                            Go to Registration
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>

</template>

<script>
import { ACTIVATE_ACCOUNT } from '../store/auth/auth.actions';

export default {
  name: 'Verification',
  data() {
    return {
      verified: false,
      loading: true,
    };
  },
  mounted() {
    const { token } = this.$route.query;
    this.$store.dispatch(ACTIVATE_ACCOUNT, token)
      .then(() => {
        this.verified = true;
      })
      .catch(() => {
        this.verified = false;
      });
    this.loading = false;
  },
};
</script>

<style scoped lang="scss">
.v {
    &-card {
        &__text {
            text-align: center;
        }
    }
}
</style>
