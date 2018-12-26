<template>
    <div>
        <button v-on:click="fetchSeances">przycisk</button>
        <button v-on:click="showSeances">wyswietl</button>
        <template v-if="!loading && seances">
            Selected day: {{selectedDay}}
            <button v-for="day in seanceDates" v-on:click="selectDay(day)">{{formatDate(day, 'DD MMM')}}</button>

            <v-data-table
                    :headers="tableHeaders"
                    :items="tableData"
                    class="elevation-1">
                <template slot="items" slot-scope="props">
                    <td>{{ props.item.movie.title }}</td>
                    <td class="text-xs-right">
                        <p v-for="seance in props.item.seances">
                            {{formatDate(seance.start, 'HH:mm')}}
                        </p>
                    </td>
                </template>
            </v-data-table>
            <article v-for="seance in seances"
                 :key="seance.id">
            {{seance.movie.title}}
            ahlo
        </article>
            halo
        </template>
        <template v-else>Loading gif</template>

    </div>
</template>

<script>
    // @ is an alias to /src
    import {mapGetters} from 'vuex'
    import consts from '../store/seances/seances.types'
    import moment from 'moment'

    export default {
        data() {
            return {
                loading: true,
                selectedDay: null,
                tableHeaders: [
                    { text: "Title", value: "title"},
                    { text: "Seances", value: "seances"},
                ]
            }
        },
        name: 'seances',
        components: {
        },
        computed: {
            ...mapGetters([
                'seances', 'seanceDates', 'moviesForDate', 'seancesForMovieAndDate'
            ]),
            tableData: function () {
                return this.moviesForDate(this.selectedDay).map(
                    movie => {
                        return {
                            movie: movie,
                            seances: this.seancesForMovieAndDate(this.selectedDay, movie.id)
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
                this.$store.dispatch(consts.actions.FETCH_SEANCES)
                    .finally(() => {
                        this.selectedDay = this.seanceDates[0];
                        this.loading = false
                    });
            },
            showSeances: function () {
                console.log(this.seanceDates);
                console.log(this.seanceDates[0]);
                console.log(this.moviesForDate(this.seanceDates[0]));
                console.log(this.tableData);
            },
            selectDay: function (day) {
                this.selectedDay = day;
            }
        },
        created: function () {
            this.fetchSeances();
        }
    };
</script>
