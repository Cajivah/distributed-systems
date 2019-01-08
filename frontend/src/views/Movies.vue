<template>
    <div>
        <v-toolbar flat color="white">
            <v-toolbar-title>Movies</v-toolbar-title>
            <v-divider
                    class="mx-2"
                    inset
                    vertical
            ></v-divider>
            <v-spacer></v-spacer>
            <v-dialog v-model="dialog" max-width="500px">
                <v-btn slot="activator" color="primary" dark class="mb-2">New Item</v-btn>
                <v-card>
                    <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                    </v-card-title>

                    <v-card-text>
                        <v-container grid-list-md>
                            <v-layout wrap>
                                <v-flex xs12>
                                    <v-text-field v-model="editedItem.title" label="Title"></v-text-field>
                                </v-flex>
                                <v-flex xs12>
                                    <v-text-field v-model="editedItem.director" label="Director"></v-text-field>
                                </v-flex>
                                <v-flex xs12>
                                    <v-text-field v-model="editedItem.lengthInMinutes" label="Length in minutes"></v-text-field>
                                </v-flex>
                                <v-flex xs12>
                                    <v-text-field v-model="editedItem.description" label="Description"></v-text-field>
                                </v-flex>
                            </v-layout>
                        </v-container>
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
                        <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-toolbar>
        <v-data-table
                :headers="headers"
                :items="movies.content"
                class="elevation-1"
                :pagination.sync="pagination"
                :total-items="movie.total"
        >
            <template slot="items" slot-scope="props">
                <td>{{ props.item.id }}</td>
                <td class="text-xs-right">{{ props.item.title }}</td>
                <td class="text-xs-right">{{ props.item.director }}</td>
                <td class="text-xs-right">{{ props.item.lengthInMinutes }}</td>
                <td class="justify-center layout px-0">
                    <v-icon
                            small
                            class="mr-2"
                            @click="editItem(props.item)"
                    >
                        edit
                    </v-icon>
                </td>
            </template>
            <template slot="no-data">
                <v-btn color="primary" @click="initialize">Reset</v-btn>
            </template>
        </v-data-table>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import TheMoviesTable from '../components/movies/TheMoviesTable.vue';
import types from '../store/movie/movie.types';

export default {
  name: 'Movies.vue',
  data() {
    return {
      dialog: false,
      loading: true,
      pagination: {},
      editedIndex: -1,
      headers: [
        {
          text: 'ID',
          value: 'id',
        },
        {
          text: 'Title',
          value: 'title',
        },
        {
          text: 'Director',
          value: 'director',
        },
        {
          text: 'Length',
          value: 'lengthInMinutes',
        },
      ],
      editedItem: {
        title: null,
        director: null,
        lengthInMinutes: null,
        description: null,
      },
    };
  },
  watch: {
    pagination: {
      handler() {
        this.fetchMovies();
      },
      deep: true,
    },
    dialog(val) {
      val || this.close();
    },
  },
  mounted() {
    this.fetchMovies();
  },
  methods: {
    fetchMovies() {
      this.loading = true;
      this.$store.commit(types.actions.FETCH_MOVIES, this.pagination);
      this.loading = false;
    },
    editItem(item) {
      this.loading = true;
      this.loading = false;
    },
  },
  components: {
    TheMoviesTable,
  },
  computed: {
    ...mapGetters([
      'movies',
    ]),
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item';
    },
  },
};
</script>

<style scoped>

</style>
