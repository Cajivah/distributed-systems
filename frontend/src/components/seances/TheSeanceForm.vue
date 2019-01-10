<template>
    <v-card>
        <v-card-title>
            <span class="headline">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text>
            <v-container grid-list-md>
                <v-layout wrap>
                    <v-flex xs12>
                        <v-datetime-picker
                                label="Select start"
                                v-model="form.start">
                        </v-datetime-picker>
                        <v-select
                                :items="rooms.content"
                                v-model="form.room.id"
                                label="Choose room..."
                                item-text="name"
                                item-value="id"
                        ></v-select>
                        <v-select
                                :items="movies.content"
                                v-model="form.movie.id"
                                label="Choose movie..."
                                item-text="title"
                                item-value="id"
                        ></v-select>
                        <v-text-field v-model="form.prices.REGULAR.value"
                                      label="Price"
                        ></v-text-field>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
            <v-btn color="blue darken-1" flat @click="saveForm">Save</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import { actionTypes as movieActions } from '../../store/movie/movie.types';
import { actionTypes as roomActions } from '../../store/rooms/rooms.types';


export default {
  name: 'TheRoomForm',
  data() {
    return {
      form: {
        id: null,
        room: {
          id: null,
        },
        movie: {
          id: null,
        },
        prices: {
          REGULAR: {
            value: null,
            currency: 'PLN',
          },
        },
      },
    };
  },
  props: {
    close: Function,
    save: Function,
    edit: Object,
    formTitle: String,
  },
  mounted() {
    this.fetchRooms({ cinema: this.activeCinema });
    this.fetchMovies({ cinemaId: this.activeCinema });
  },
  methods: {
    saveForm() {
      this.save(this.form);
    },
    ...mapActions({
      fetchMovies: movieActions.FETCH_MOVIES,
      fetchRooms: roomActions.FETCH_ROOMS,
    }),
  },
  watch: {
    edit(newVal) {
      this.form = newVal;
    },
  },
  computed: {
    ...mapGetters([
      'rooms',
      'movies',
      'activeCinema',
    ]),
  },
};
</script>

<style scoped>

</style>
