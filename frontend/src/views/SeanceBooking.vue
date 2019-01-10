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
                        <v-layout row :key="row" slot="seats" v-for="row in rows">
                            <!-- TODO(bgulowaty): i have no idea how to wrap this nicely
                            since v-model = :value @input, and yet component reacts
                            to separate :value -->
                            <v-flex grow pa-1 :key="seat.id" v-for="seat in row.seats">
                                <v-checkbox
                                            v-model="seats"
                                            :disabled="seat.occupied"
                                            :label="`${row.id}-${seat.id}`"
                                            :value="`${seat.id}`"></v-checkbox>
                            </v-flex>

                        </v-layout>
                </TheSeatSelection>
            </v-expansion-panel-content>
        </v-expansion-panel>
        <v-dialog v-model="sellingSeatsCompleted" width="600">
            <v-alert
                    :value="true"
                    type="success">
                Successfully sold seats
            </v-alert>
            <v-btn
                    color="primary"
                    flat
                    @click="sellingSeatsCompleted = false">Close</v-btn>
        </v-dialog>
        <v-dialog v-if="isAuthenticated" v-model="seatsSellingDialog" width="600">
            <v-btn slot="activator" color="primary" dark>Sell seats</v-btn>
            <TheSeatsSellingDialog
                    :selectedSeats="seats"
                    :error="sellingSeatsError"
            :inProgress="sellingSeatsInProgress">
                <template slot="actions">
                    <v-btn color="primary" flat @click="cancelSeatSelling()">Cancel</v-btn>
                    <v-btn color="primary" flat @click="sellSeats()"
                    :disabled="seats.length === 0">Sell selected seats</v-btn>
                </template>
            </TheSeatsSellingDialog>
        </v-dialog>
        <v-dialog v-if="!isAuthenticated" v-model="reservationDialog" persistent max-width="600px">
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
import { BOOKING_SUCCESSFUL } from '@/routes';
import TheSeatsSellingDialog from '@/components/seanceDetails/TheSeatsSellingDialog.vue';

const { mapActions, mapGetters } = createNamespacedHelpers(SEANCE_DETAILS_STORE);

export default {
  data() {
    return {
      panel: [false, false, true],
      loading: true,
      reservationDialog: false,
      seatsSellingDialog: false,
      sellingSeatsCompleted: false,
      summaryDialog: false,
    };
  },
  name: 'seance-booking',
  components: {
    TheSeatsSellingDialog,
    TheLoadingIndicator,
    TheBookingDialog,
    TheMovieDetails,
    TheSeanceDetails,
    TheSeatSelection,
    TheBookingSummary,
  },
  computed: {
    ...mapGetters([
      'movie', 'seance', 'rows', 'reservationDetails', 'reservationInProgress', 'reservationError', 'sellingSeatsError',
      'sellingSeatsInProgress',
    ]),
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
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
        .then(() => {
          this.closeSummaryDialog();
          this.$router.push({ name: BOOKING_SUCCESSFUL });
        });
    },
    sellSeats() {
      this.sendSeatSellingRequest()
        .then(() => {
          this.closeSeatSellingDialog();
          this.fetchPageData();
          this.sellingSeatsCompleted = true;
        });
    },
    cancelReservation() {
      this.closeSummaryDialog();
    },
    cancelSeatSelling() {
      this.closeSeatSellingDialog();
    },
    closeSeatSellingDialog() {
      this.seatsSellingDialog = false;
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
      sendSeatSellingRequest: actions.SELL_SEATS,
    }),
    fetchPageData() {
      this.loading = true;
      this.fetchDetails(this.$route.params.seanceId)
        .finally(() => {
          this.loading = false;
        });
    },
  },
  created() {
    this.fetchPageData();
  },
};
</script>
