<template>
    <v-list dense>
        <v-list-tile>
            <v-list-tile-content>Movie:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ booking && booking.seance.movie.title }}
            </v-list-tile-content>
        </v-list-tile>
        <v-list-tile>
            <v-list-tile-content>Starts at:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ booking && formatDate(booking.seance.start) }}
            </v-list-tile-content>
        </v-list-tile>
        <v-list-tile>
            <v-list-tile-content>Room:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ booking && booking.seance.room.name }}
            </v-list-tile-content>
        </v-list-tile>
        <v-list-tile>
            <v-list-tile-content>Seats:</v-list-tile-content>
            <v-list-tile-content class="align-end">
                {{ booking && seatsConcatenated }}
            </v-list-tile-content>
        </v-list-tile>
    </v-list>
</template>

<script>
import { formatDate } from '@/utils/dateFormatter';

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
