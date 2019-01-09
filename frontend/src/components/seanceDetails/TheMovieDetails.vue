<template>
    <v-card>
        <v-card-text>
            Title: {{movie.title}}
            Director: {{movie.director}}
            Length: {{movie.length}}
            Description: {{movie.description}}
        </v-card-text>
    </v-card>
</template>


<script>
export default {
  name: 'the-movie-details',
  props: {
    movie: {
      required: true,
    },
  },
};
</script>
