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
                            <!-- TODO(bgulowaty): i have no idea how to wrap this nicely
                            since v-model = :value @input, and yet component reacts
                            to separate :value -->
                            <v-checkbox v-for="seat in row.seats"
                                        :key="seat.id"
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
            <TheBookingDialog :seatCount="seats.length">
                <v-text-field slot="firstNameField"
                              v-model="firstName"
                              label="First name*"
                              :error-messages="errors.collect('firstname')"
                              v-validate="'required'"
                              data-vv-name="firstname"></v-text-field>
                <v-text-field slot="surnameField"
                              v-model="surname"
                              :error="errors.has('surname')"
                              :error-messages="errors.collect('surname')"
                              v-validate="'required'"
                              data-vv-name="surname"
                              label="Last name*"
                              required></v-text-field>
                <v-text-field slot="emailField"
                              :error="errors.has('email')"
                              :error-messages="errors.collect('email')"
                              data-vv-name="email"
                              v-model="email"
                              label="Email*"
                              v-validate="'required|email'"></v-text-field>
                <template slot="actions">
                    <v-btn color="blue darken-1" flat
                           @click="summary()"
                           :disabled="errors.any() || seats.length === 0 || !isFormDirty">
                        Summary</v-btn>
                    <v-btn color="blue darken-1" flat @click="closeReservationDialog()">
                        Cancel</v-btn>
                </template>
            </TheBookingDialog>
        </v-dialog>
        Selected: {{seats}}
        <v-dialog v-if="summaryDialog" v-model="summaryDialog"
                  width="600">
            <TheBookingSummary :movie="movie"
                               :seats="seats"
                               :reservationDetails="reservationDetails"
                               :inProgress="reservationInProgress"
                               :reservationError="reservationError">
                <template slot="actions">
                    <v-btn color="primary" flat @click="cancelReservation()">Cancel</v-btn>
                    <v-btn color="primary" flat @click="makeReservation()">Make reservation</v-btn>
                </template>
            </TheBookingSummary>
        </v-dialog>
    </v-container>
</template>

<script>
import { createNamespacedHelpers } from 'vuex';
import { formatDate } from '@/utils/dateUtils';
import { actions } from '@/store/seanceDetails/seanceDetails.types';
import { keys } from 'ramda';
import TheLoadingIndicator from '@/components/TheLoadingIndicator.vue';
import TheBookingDialog from '@/components/seanceDetails/TheBookingDialog.vue';
import TheMovieDetails from '@/components/seanceDetails/TheMovieDetails.vue';
import TheSeanceDetails from '@/components/seanceDetails/TheSeanceDetails.vue';
import TheSeatSelection from '@/components/seanceDetails/TheSeatSelection.vue';
import TheBookingSummary from '@/components/seanceDetails/TheBookingSummary.vue';
import { SEANCE_DETAILS_STORE } from '@/store/seanceDetails/seanceDetails.module';

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
    TheBookingSummary,
  },
  computed: {
    ...mapGetters([
      'movie', 'seance', 'rows', 'reservationDetails', 'reservationInProgress', 'reservationError',
    ]),
    isFormDirty() {
      return keys(this.fields).every(key => this.fields[key].dirty);
    },
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
      this.sendReservationRequest()
        .then(() => this.closeSummaryDialog());
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
      sendReservationRequest: actions.MAKE_RESERVATION,
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
