<template>
    <v-container grid-list-md fluid>
        <TheLoadingIndicator v-if="loading"></TheLoadingIndicator>
        <v-expansion-panel v-else expand v-model="panel">
            <v-expansion-panel-content>
                <div slot="header">Seance Details</div>
                <TheSeanceDetails :seance="seance"></TheSeanceDetails>
            </v-expansion-panel-content>
            <v-expansion-panel-content>
                <div slot="header">Movie Details</div>
                <TheMovieDetails :movie="movie"></TheMovieDetails>
            </v-expansion-panel-content>
            <v-expansion-panel-content>
                <div slot="header">Reservation</div>
                <TheSeatSelection>
                        <template slot="seats" v-for="row in rows">
                            <v-checkbox v-for="seat in row.seats"
                                        v-model="seats"
                                        :disabled="seat.occupied"
                                        :label="`${row.id}-${seat.id}`"
                                        :value="`${seat.id}`"></v-checkbox>
                        </template>
                </TheSeatSelection>
            </v-expansion-panel-content>
        </v-expansion-panel>
        <v-dialog v-model="reservationDialog" persistent max-width="600px">
            <v-btn slot="activator" color="primary" dark>Make a reservation</v-btn>
            <v-card>
                <v-card-title>
                    <span class="headline">Reservation Details</span>
                </v-card-title>
                <v-card-text>
                    <v-container grid-list-md>
                        <v-layout wrap>
                            <v-flex xs12 sm6 md4>
                                <v-text-field v-model="firstName" label="First name*" required></v-text-field>
                            </v-flex>
                            <v-flex xs12 sm6 md4>
                                <v-text-field
                                        v-model="surname"
                                        label="Last name*"
                                        required
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs12>
                                <v-text-field
                                        v-model="email"
                                        label="Email*"
                                        required>
                                </v-text-field>
                            </v-flex>
                        </v-layout>
                    </v-container>
                    <small>*indicates required field</small>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" flat @click="summary()">Summary</v-btn>
                    <v-btn color="blue darken-1" flat @click="closeReservationDialog()">Cancel</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
        Selected: {{seats}}
        <v-dialog v-if="summaryDialog" v-model="summaryDialog"
                  width="600">
            <v-card>
                <v-card-title class="headline grey lighten-2" primary-title>
                    Reservation summary
                </v-card-title>

                <v-card-text>
                    Movie: {{movie.title}}
                    Date: {{formatDate(movie.start, "LL")}}
                    Seats: {{seats}}
                    Owner: {{`${firstName} ${surname} (${email})`}}
                </v-card-text>

                <v-divider></v-divider>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" flat @click="cancelReservation()">Cancel</v-btn>
                    <v-btn color="primary" flat @click="makeReservation()">Make reservation</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-container>
</template>

<script>
import { createNamespacedHelpers } from 'vuex';
import { formatDate } from '@/utils/dateFormatter';
import { actions } from '@/store/seanceDetails/seanceDetails.types';
import { keys } from 'ramda';
import TheLoadingIndicator from '@/components/TheLoadingIndicator';
import TheBookingDialog from '@/components/seanceDetails/TheBookingDialog';
import TheMovieDetails from '@/components/seanceDetails/TheMovieDetails';
import TheSeanceDetails from '@/components/seanceDetails/TheSeanceDetails';
import TheSeatSelection from '@/components/seanceDetails/TheSeatSelection';
import { SEANCE_DETAILS_STORE } from '../store/store';

const { mapActions, mapGetters } = createNamespacedHelpers(SEANCE_DETAILS_STORE);

export default {
  data() {
    return {
      panel: [false, false, true],
      loading: true,
      reservationDialog: false,
      summaryDialog: false,
    };
  },
  name: 'seance-details',
  components: {
    TheLoadingIndicator,
    TheBookingDialog,
    TheMovieDetails,
    TheSeanceDetails,
    TheSeatSelection,
  },
  computed: {
    ...mapGetters([
      'movie', 'seance', 'rows', 'reservationDetails',
    ]),
    seats: {
      get() {
        return this.reservationDetails.selectedSeats;
      },
      set(value) {
        this.selectSeats(value);
      },
    },
    firstName: {
      get() {
        return this.reservationDetails.name;
      },
      set(value) {
        this.setName(value);
      },
    },
    surname: {
      get() {
        return this.reservationDetails.surname;
      },
      set(value) {
        this.setSurname(value);
      },
    },
    email: {
      get() {
        return this.reservationDetails.email;
      },
      set(value) {
        this.setEmail(value);
      },
    },
  },
  methods: {
    summary() {
      this.closeReservationDialog();
      this.openSummaryDialog();
    },
    closeReservationDialog() {
      this.reservationDialog = false;
    },
    openSummaryDialog() {
      this.summaryDialog = true;
    },
    makeReservation() {
      this.closeSummaryDialog();
    },
    cancelReservation() {
      this.closeSummaryDialog();
    },
    closeSummaryDialog() {
      this.summaryDialog = false;
    },
    formatDate,
    keys,
    ...mapActions({
      openDialog: actions.OPEN_RESERVATION_DIALOG,
      closeDialog: actions.CLOSE_RESERVATION_DIALOG,
      selectSeats: actions.SELECT_SEATS,
      setName: actions.TYPE_FIRST_NAME,
      setSurname: actions.TYPE_SURNAME,
      setEmail: actions.TYPE_EMAIL,
      fetchDetails: actions.FETCH_DETAILS,
    }),
  },
  created() {
    this.loading = true;
    this.fetchDetails(this.$route.params.seanceId)
      .finally(() => {
        this.loading = false;
      });
  },
};
</script>
