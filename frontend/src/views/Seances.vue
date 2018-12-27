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
                <the-seances-table :tableData="tableData" :loading="loading"></the-seances-table>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    import {formatDate} from "@/utils/dateFormatter";
    import {actions} from '@/store/seances/seances.types'
    import moment from 'moment'
    import TheLoadingIndicator from '@/components/TheLoadingIndicator'
    import TheSeancesTable from '@/components/seances/TheSeancesTable'

    export default {
        data() {
            return {
                loading: true,
                selectedDay: moment().format('YYYY-MM-DD')
            }
        },
        name: 'seances',
        components: {
            TheLoadingIndicator,
            TheSeancesTable
        },
        computed: {
            ...mapGetters([
                'seances'
            ]),
            tableData: function () {
                return this.seances
            }
        },
        methods: {
            formatDate: formatDate,
            fetchSeances: function (day) {
                this.loading = true;
                this.$store.dispatch(actions.FETCH_SEANCES, day)
                    .finally(() => {
                        this.loading = false;
                    });
            }
        },
        created: function () {
            this.fetchSeances(this.selectedDay);
        }
    };
</script>
