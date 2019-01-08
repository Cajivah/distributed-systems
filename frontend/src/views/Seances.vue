<template>
    <v-container grid-list-md fluid>
        <v-layout justify-end>
            <v-flex>
                <v-date-picker
                        v-model="selectedDay"
                        no-title
                        v-on:input="fetchSeances"
                ></v-date-picker>
            </v-flex>
            <v-flex>
                <the-seances-table :tableData="seances" :loading="loading"></the-seances-table>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
import { actions } from '@/store/seances/seances.types';
import { currentDate } from '@/utils/dateUtils';
import TheLoadingIndicator from '@/components/TheLoadingIndicator.vue';
import TheSeancesTable from '@/components/seances/TheSeancesTable.vue';

export default {
  data() {
    return {
      loading: false,
      selectedDay: currentDate('YYYY-MM-DD'),
    };
  },
  name: 'seances',
  components: {
    TheLoadingIndicator,
    TheSeancesTable,
  },
  computed: {
    ...mapGetters([
      'seances',
    ]),
  },
  methods: {
    fetchSeances(day) {
      this.loading = true;
      this.$store.dispatch(actions.FETCH_SEANCES, day)
        .finally(() => {
          this.loading = false;
        });
    },
  },
  created() {
    this.fetchSeances(this.selectedDay);
  },
};
</script>
