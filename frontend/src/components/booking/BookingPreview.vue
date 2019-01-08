<template>
    <v-list dense v-if="booking">
        <v-list-tile>
            <v-list-tile-content>Movie:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ booking.seance.movie.title }}
            </v-list-tile-content>
        </v-list-tile>
        <v-list-tile>
            <v-list-tile-content>Starts at:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ formatDate(booking.seance.start) }}
            </v-list-tile-content>
        </v-list-tile>
        <v-list-tile>
            <v-list-tile-content>Room:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ booking.seance.room.name }}
            </v-list-tile-content>
        </v-list-tile>
        <v-list-tile>
            <v-list-tile-content>Seats:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ seatsConcatenated }}
            </v-list-tile-content>
        </v-list-tile>
    </v-list>
</template>

<script>
import { formatDate } from '@/utils/dateUtils';

export default {
  name: 'BookingPreview',
  props: {
    booking: {
      type: Object,
      required: true,
    },
  },
  computed: {
    seatsConcatenated() {
      return this.booking.seats.map(bookedSeat => bookedSeat.seat.seatCode).join(', ');
    },
  },
  methods: {
    formatDate,
  },
};
</script>

<style scoped>

</style>
