<template>
    <v-card>
        <v-card-text>
                <slot name="seats"></slot>
        </v-card-text>
    </v-card>
</template>


<script>
export default {
  name: 'the-seat-selection',
};
</script>
