<template>
    <v-card>
        <v-card-text>
            Start time: {{formatDate(seance.start, 'LL')}}
            <template v-for="type in keys(seance.prices)">
                {{type}} - {{seance.prices[type].value}} ({{seance.prices[type].currency}})
            </template>
        </v-card-text>
    </v-card>
</template>


<script>
import { formatDate } from '@/utils/dateFormatter';
import { keys } from 'ramda';

export default {
  name: 'the-seance-details',
  props: {
    seance: {
      required: true,
    },
  },
  methods: {
    formatDate,
    keys,
  },
};
</script>
