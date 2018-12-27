<template>
    <v-data-table
            :headers="tableHeaders"
            :items="tableData"
            class="elevation-1">
        <template slot="items" slot-scope="{ item }">
            <td>
                <router-link :to="{ name: movieDetailsRoute, params: {movieId: item.movieId}}">
                    {{ item.movieTitle }}
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
    import {MOVIE_DETAILS, SEANCE_BOOKING} from '@/routes'
    import {formatDate} from "@/utils/dateFormatter";

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
                movieDetailsRoute: MOVIE_DETAILS,
                bookingRoute: SEANCE_BOOKING
            }
        },
        methods: {
            formatDate: formatDate
        }
    };
</script>
