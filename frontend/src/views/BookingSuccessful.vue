<template>
    <div>
    <v-alert
            :value="true"
            type="success">
        Reservation was successful
    </v-alert>
    <v-btn :to="{name: HOME_ROUTE}" color="info">Go back to cinema home</v-btn>
    </div>
</template>

<script>
import { HOME } from '@/routes';

export default {
  name: 'booking-successful',
  data() {
    return {
      HOME_ROUTE: HOME,
    };
  },
};
</script>

<style scoped>

</style>
