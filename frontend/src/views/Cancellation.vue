<template>
    <v-container fluid fill-height>
        <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                    <v-toolbar dark color="primary">
                        <v-toolbar-title>Booking cancellation</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <template v-if="!cancelled">
                            You are cancelling your booking for seance: {{}}
                        </template>
                        <template v-else>
                            Email verification failed. Try again by reloading the page.
                        </template>
                    </v-card-text>
                    <v-card-actions v-if="!loading">
                        <v-spacer></v-spacer>
                        <v-btn v-if="inProgress" color="error" to="home">
                            Cancel booking
                        </v-btn>
                        <v-btn v-else color="primary" to="home">
                            Go to seances
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
      cancelled: false,
      inProgress: true,
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
