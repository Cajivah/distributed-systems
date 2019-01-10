<template>
    <v-card>
        <v-card-text>
                <slot name="seats"></slot>
                <v-container text-xs-center>
                    <v-layout>
                        <v-flex xs12>
                            <v-card dark color="primary">
                                <v-card-text class="font-weight-bold">Screen</v-card-text>
                            </v-card>
                        </v-flex>
                    </v-layout>
                </v-container>
        </v-card-text>
    </v-card>
</template>


<script>
export default {
  name: 'the-seat-selection',
};
</script>
