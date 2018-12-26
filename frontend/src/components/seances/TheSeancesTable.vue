<template>
    <v-data-table
            :headers="tableHeaders"
            :items="tableData"
            class="elevation-1">
        <template slot="items" slot-scope="{ item }">
            <td>
                <router-link :to="{ name: movieDetailsRoute, params: {movieId: item.movie.id}}">
                    {{ item.movie.title }} ({{ item.movie.lengthMinutes }} min.)
                </router-link>

            </td>
            <td class="text-xs-right">
                <template v-for="seance in item.seances">
                    <router-link :to="{ name: bookingRoute, params: {seanceId: seance.id}}">
                        {{formatDate(seance.start, 'HH:mm')}}
                    </router-link>
                </template>
            </td>
        </template>
    </v-data-table>
</template>

<script>
    import routes from '@/routes'
    import moment from 'moment'

    export default {
        name: 'the-seances-table',
        props: {
            tableData: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                tableHeaders: [
                    {text: "Title", value: "title"},
                    {text: "Seances", value: "seances"},
                ],
                movieDetailsRoute: routes.MOVIE_DETAILS,
                bookingRoute: routes.SEANCE_BOOKING
            }
        },
        methods: {
            formatDate: function (date, formatters) {
                return moment(date).format(formatters);
            }
        }
    };
</script>
