<template>
    <v-card>
        <TheLoadingIndicator v-if="inProgress"></TheLoadingIndicator>
        <v-card-title class="headline grey lighten-2" primary-title>
            Reservation summary
        </v-card-title>
            <v-alert
                    :value="reservationError"
                    type="error">
                Reservation error, please try again:
                {{reservationError}}
            </v-alert>
        <v-card-text>
            Movie: {{movie.title}}
            Date: {{formatDate(movie.start, "LL")}}
            Seats: {{seats}}
            Owner: {{`${reservationDetails.name} ${reservationDetails.surname} (${reservationDetails.email})`}}
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
            <v-spacer></v-spacer>
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>


<script>
import { formatDate } from '@/utils/dateFormatter';
import TheLoadingIndicator from '@/components/TheLoadingIndicator';

export default {
  name: 'the-booking-summary',
  props: {
    movie: {
      required: true,
    },
    seats: {
      required: true,
    },
    reservationDetails: {
      required: true,
    },
      inProgress: {
        required: true,
          type: Boolean
      },
      reservationError: {
        required: true
      },
  },
    components: {
        TheLoadingIndicator,
    },
  data() {
    return {};
  },
  methods: {
    formatDate,
  },
};
</script>
