<template>
    <v-card>
        <v-card-title class="headline grey lighten-2" primary-title>
            Reservation summary
        </v-card-title>

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
  },
  data() {
    return {};
  },
  methods: {
    formatDate,
  },
};
</script>
