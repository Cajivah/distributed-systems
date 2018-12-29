<template>
    <v-card>
        <v-card-title>
            <span class="headline">Reservation Details</span>
        </v-card-title>
        <v-card-text>
            <v-container grid-list-md>
                <v-layout wrap>
                    <v-flex xs12 sm6 md4>
                        <slot name="firstNameField"></slot>
                    </v-flex>
                    <v-flex xs12 sm6 md4>
                        <slot name="surnameField"></slot>
                    </v-flex>
                    <v-flex xs12>
                        <slot name="emailField"></slot>
                    </v-flex>
                </v-layout>
            </v-container>
            <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
            <v-spacer></v-spacer>
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>


<script>
export default {
  name: 'the-booking-dialog',
};
</script>
