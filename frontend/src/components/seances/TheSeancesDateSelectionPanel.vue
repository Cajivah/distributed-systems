<template>
    <v-btn-toggle v-model="selectedDayIndex" mandatory v-on:change="print">
        <v-btn flat depressed v-for="day in seanceDates" v-on:click="selectDay(day)">
            {{formatDate(day, 'DD MMM')}}
        </v-btn>
    </v-btn-toggle>
</template>

<script>
    import {formatDate} from "@/utils/dateFormatter";

    export default {
        name: 'the-seances-date-selection-panel',
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
