<template>
    <v-container>
    <div>
        <v-toolbar flat dark>
            <v-toolbar-title>Movies</v-toolbar-title>
            <v-divider
                    class="mx-2"
                    inset
                    vertical
            ></v-divider>
            <v-spacer></v-spacer>
            <v-dialog v-model="dialog" max-width="500px">
                <v-btn slot="activator" color="primary" dark class="mb-2">New Item</v-btn>
                <the-movie-form :close="close"
                                :save="save"
                                :formTitle="formTitle"
                                :edit="editedItem" />
            </v-dialog>
        </v-toolbar>
        <v-data-table
                dark
                :headers="headers"
                :items="movies.content"
                class="elevation-1"
                :pagination.sync="pagination"
                :total-items="movies.totalElements"
                :loading="loading"
                prev-icon="mdi-menu-left"
                next-icon="mdi-menu-right"
                sort-icon="mdi-menu-down"
        >
            <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
            <template slot="items" slot-scope="props">
                <td>{{ props.item.id }}</td>
                <td class="text-xs-right">{{ props.item.title }}</td>
                <td class="text-xs-right">{{ props.item.director }}</td>
                <td class="text-xs-right">{{ props.item.lengthMinutes }}</td>
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
                <v-alert :value="true" color="error" icon="warning">
                    Sorry, nothing to display here :(
                    <v-btn color="accent" @click="fetch">Reset</v-btn>
                </v-alert>
            </template>
        </v-data-table>
    </div>
    </v-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import TheMovieForm from '../components/movies/TheMovieForm.vue';
import { actionTypes } from '../store/movie/movie.types';

export default {
  name: 'Movies',
  data() {
    return {
      dialog: false,
      loading: true,
      pagination: {},
      isEditing: false,
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
          value: 'lengthMinutes',
        },
        {
          text: 'Actions',
          value: 'actions',
        },
      ],
      editedItem: {
        title: null,
        director: null,
        lengthMinutes: null,
        description: null,
      },
      defaultItem: {
        title: null,
        director: null,
        lengthMinutes: null,
        description: null,
      },
    };
  },
  watch: {
    pagination: {
      handler() {
        this.fetch();
      },
      deep: true,
    },
    dialog(val) {
      if (!val) {
        this.close();
      }
    },
  },
  mounted() {
    this.fetch();
  },
  methods: {
    fetch() {
      this.loading = true;
      this.fetchMovies(this.pagination);
      this.loading = false;
    },
    editItem(item) {
      this.loading = true;
      this.editedItem = Object.assign({}, item);
      this.isEditing = true;
      this.dialog = true;
      this.loading = false;
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.isEditing = false;
      }, 300);
    },
    save(data) {
      if (!this.isEditing) {
        this.saveMovie(data).then(() => this.fetch());
      } else {
        this.updateMovie(data).then(() => this.fetch());
      }
      this.dialog = false;
    },
    ...mapActions({
      fetchMovies: actionTypes.FETCH_MOVIES,
      saveMovie: actionTypes.CREATE_MOVIE,
      updateMovie: actionTypes.UPDATE_MOVIE,
      selectMovie: actionTypes.FETCH_MOVIE,
    }),
  },
  components: {
    TheMovieForm,
  },
  computed: {
    ...mapGetters([
      'movies',
    ]),
    formTitle() {
      return !this.isEditing ? 'New Item' : 'Edit Item';
    },
  },
};
</script>

<style scoped>

</style>
