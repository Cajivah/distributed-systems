<template>
    <v-container fluid fill-height>
        <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                    <v-toolbar dark color="primary">
                        <v-toolbar-title>Booking cancellation</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <template v-if="!cancelled && booking">
                            <h3>You are about to cancel your booking:</h3>
                            <booking-preview :booking="bookingToCancel"></booking-preview>
                        </template>
                        <template v-else>
                            {{ cancellationResultMessage }}
                        </template>
                    </v-card-text>
                    <v-card-actions v-if="!loading">
                        <v-btn color="secondary" to="/home">
                            Go to seances
                        </v-btn>
                        <v-spacer></v-spacer>
                        <v-btn :disabled="cancelled || !booking" color="error" @click="cancelBooking">
                            Cancel booking
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>

</template>

<script>
import { mapGetters } from 'vuex';
import types from '../store/booking/booking.types';
import BookingPreview from '../components/booking/BookingPreview.vue';

export default {
  name: 'Cancellation',
  data() {
    return {
      cancelled: false,
      finished: false,
    };
  },
  components: {
    BookingPreview,
  },
  mounted() {
    const { token } = this.$route.query;
    this.$store.dispatch(types.actions.FETCH_BOOKING_TO_CANCEL, token);
  },
  computed: {
    cancellationResultMessage() {
      return this.cancelled && this.finished ? 'Your booking has been successfully cancelled' : 'Booking could not be cancelled';
    },
    ...mapGetters([
      'bookingToCancel',
    ]),
  },
  methods: {
    cancelBooking() {
      const { bookingIdentifier } = this.bookingToCancel;
      this.$store.dispatch(types.actions.CANCEL_BOOKING, bookingIdentifier)
        .then(() => {
          this.cancelled = true;
        })
        .catch(() => {
          this.cancelled = false;
        });
      this.finished = true;
    },
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
