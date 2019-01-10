<template>
    <v-container>
    <div>
        <v-toolbar flat dark>
            <v-toolbar-title>Seances</v-toolbar-title>
            <v-divider
                    class="mx-2"
                    inset
                    vertical
            ></v-divider>
            <v-spacer></v-spacer>
            <v-dialog v-model="dialog" max-width="500px">
                <v-btn slot="activator" color="primary" dark class="mb-2">New Item</v-btn>
                <the-seance-form :close="close"
                                 :save="save"
                                 :formTitle="formTitle"
                                 :edit="editedItem" />
            </v-dialog>
        </v-toolbar>
        <template v-if="activeCinema">

        <v-data-table
                dark
                :headers="headers"
                :items="cinemaSeances.content"
                class="elevation-1"
                :pagination.sync="pagination"
                :total-items="cinemaSeances.totalElements"
                :loading="loading"
                prev-icon="mdi-menu-left"
                next-icon="mdi-menu-right"
                sort-icon="mdi-menu-down"
        >
            <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
            <template slot="items" slot-scope="props">
                <td>{{ props.item.id }}</td>
                <td class="text-xs-right">{{ props.item.movie.title }}</td>
                <td class="text-xs-right">
                    {{ formatDate(props.item.start, 'MMMM Do YYYY, h:mm:ss a') }}
                </td>
                <td class="text-xs-right">{{ props.item.movie.lengthMinutes }}</td>
                <td class="text-xs-right">{{ props.item.room.name }}</td>
                <td class="justify-center align-center layout px-0">
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
</template>
<template v-else>
    <v-alert :value="true" color="error" icon="warning">
        You need to select cinema first
    </v-alert>
</template>
    </div>
    </v-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import TheSeanceForm from '../components/seances/TheSeanceForm.vue';
import { actionTypes } from '../store/seances/seances.types';
import { formatDate } from '../utils/dateUtils';

export default {
  name: 'SeancesManagement',
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
          text: 'Movie title',
          value: 'movie.title',
        },
        {
          text: 'Start',
          value: 'start',
        },
        {
          text: 'Length',
          value: 'movie.lengthMinutes',
        },
        {
          text: 'Room name',
          value: 'room.name',
        },
        {
          text: 'Actions',
          value: 'actions',
        },
      ],
      editedItem: {
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
      defaultItem: {
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
      this.fetchSeances({ ...this.pagination, cinemaId: this.activeCinema });
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
      const payload = { ...data };
      if (!this.isEditing) {
        this.createSeance(payload).then(() => this.fetch());
      } else {
        this.updateSeance(payload).then(() => this.fetch());
      }
      this.dialog = false;
    },
    formatDate,
    ...mapActions({
      fetchSeances: actionTypes.FETCH_SEANCES,
      createSeance: actionTypes.CREATE_SEANCE,
      updateSeance: actionTypes.UPDATE_SEANCE,
    }),
  },
  components: {
    TheSeanceForm,
  },
  computed: {
    ...mapGetters([
      'cinemaSeances',
      'activeCinema',
    ]),
    formTitle() {
      return !this.isEditing ? 'New Item' : 'Edit Item';
    },
  },
};
</script>

<style scoped>

</style>
