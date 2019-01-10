<template>
    <v-card>
        <v-card-title>
            <span class="headline">Selected seats</span>
        </v-card-title>
        <v-card-text>
            <TheLoadingIndicator v-if="inProgress"></TheLoadingIndicator>
            <v-alert
                    :value="error"
                    type="error">
                Seats selling error, please try again:
                {{error}}
            </v-alert>
            <v-alert
                    :value="selectedSeats.length===0"
                    type="error">
                No seats are selected
            </v-alert>
            <v-container grid-list-md>
                You have selected following seats: {{selectedSeats}}
            </v-container>
        </v-card-text>
        <v-card-actions>
            <v-spacer></v-spacer>
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>


<script>
import TheLoadingIndicator from '@/components/TheLoadingIndicator.vue';

export default {
  name: 'the-seats-selling-dialog',
  components: {
    TheLoadingIndicator,
  },
  props: {
    selectedSeats: {
      type: Array,
      required: true,
    },
    error: {
      required: true,
    },
    inProgress: {
      required: true,
    },
  },
};
</script>
