<template>
    <v-container grid-list-md fluid>
        <v-layout v-if="!loading" justify-end>
            <v-flex>
                <v-btn-toggle v-model="selectedDayIndex" mandatory v-on:change="print">
                    <v-btn flat depressed v-for="day in seanceDates" v-on:click="selectDay(day)">
                        {{formatDate(day, 'DD MMM')}}
                    </v-btn>
                </v-btn-toggle>
            </v-flex>
            <v-flex>
                <the-seances-table :tableData="tableData"></the-seances-table>
            </v-flex>
        </v-layout>

        <v-layout v-else>
            <the-loading-indicator></the-loading-indicator>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    import moment from 'moment'
    import {actions} from '@/store/seances/seances.types'
    import TheLoadingIndicator from '@/components/TheLoadingIndicator'
    import TheSeancesTable from '@/components/seances/TheSeancesTable'

    export default {
        data() {
            return {
                loading: true,
                selectedDayIndex: 0,
                selectedDay: null,
                tableHeaders: [
                    {text: "Title", value: "title"},
                    {text: "Seances", value: "seances"},
                ],
            }
        },
        name: 'seances',
        components: {
            TheLoadingIndicator,
            TheSeancesTable
        },
        computed: {
            ...mapGetters([
                'seances', 'seanceDates', 'moviesForDate', 'seancesForMovieAndDay'
            ]),
            tableData: function () {
                return this.moviesForDate(this.selectedDay).map(
                    movie => {
                        return {
                            movie: movie,
                            seances: this.seancesForMovieAndDay(this.selectedDay, movie.id)
                        }
                    }
                )
            },
        },
        methods: {
            formatDate: function (date, formatters) {
                return moment(date).format(formatters);
            },

            fetchSeances: function () {
                this.loading = true;
                this.$store.dispatch(actions.FETCH_SEANCES)
                    .finally(() => {
                        this.selectedDay = this.seanceDates[this.selectedDayIndex];
                        this.loading = false
                    });
            },
            selectDay: function (day) {
                this.selectedDay = day;
            },
            print: function (event) {
                console.log(print);
            }
        },
        created: function () {
            this.fetchSeances();
        }
    };
</script>
