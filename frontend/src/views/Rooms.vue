<template>
    <v-container>
    <div>
        <v-toolbar flat dark>
            <v-toolbar-title>Rooms</v-toolbar-title>
            <v-divider
                    class="mx-2"
                    inset
                    vertical
            ></v-divider>
            <v-spacer></v-spacer>
            <v-dialog v-model="dialog" max-width="500px">
                <v-btn slot="activator" color="primary" dark class="mb-2">New Item</v-btn>
                <the-room-form :close="close"
                               :save="save"
                               :formTitle="formTitle"
                               :edit="simplifiedEditItem" />
            </v-dialog>
        </v-toolbar>
        <template v-if="activeCinema">
        <v-data-table
                dark
                :headers="headers"
                :items="rooms.content"
                class="elevation-1"
                :pagination.sync="pagination"
                :total-items="rooms.totalElements"
                :loading="loading"
                prev-icon="mdi-menu-left"
                next-icon="mdi-menu-right"
                sort-icon="mdi-menu-down"
        >
            <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
            <template slot="items" slot-scope="props">
                <td>{{ props.item.id }}</td>
                <td class="text-xs-right">{{ props.item.name }}</td>
                <td class="justify-center align-center layout px-0">
                    <v-icon small class="mr-2" @click="editItem(props.item)">
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
import { actionTypes } from '../store/rooms/rooms.types';
import TheRoomForm from '../components/rooms/TheRoomForm.vue';

export default {
  name: 'Rooms',
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
          text: 'Name',
          value: 'name',
        },
        {
          text: 'Actions',
          value: 'actions',
        },
      ],
      editedItem: {
        id: null,
        name: null,
        rows: [],
      },
      defaultItem: {
        id: null,
        name: null,
        rows: [],
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
      this.fetchRooms({ ...this.pagination, cinema: this.activeCinema });
      this.loading = false;
    },
    editItem(item) {
      this.loading = true;
      this.isEditing = true;
      this.editedItem = Object.assign({}, item);
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
      const cinema = { id: this.activeCinema };

      const payload = { room: data, cinema };

      if (!this.isEditing) {
        this.createRoom(payload).then(() => this.fetch());
      } else {
        this.updateRoom(payload).then(() => this.fetch());
      }
      this.dialog = false;
    },
    ...mapActions({
      fetchRooms: actionTypes.FETCH_ROOMS,
      createRoom: actionTypes.CREATE_ROOM,
      updateRoom: actionTypes.UPDATE_ROOM,
    }),
  },
  components: {
    TheRoomForm,
  },
  computed: {
    ...mapGetters([
      'rooms',
      'activeCinema',
    ]),
    formTitle() {
      return !this.isEditing ? 'New Item' : 'Edit Item';
    },
    simplifiedEditItem() {
      return {
        id: this.editedItem.id,
        name: this.editedItem.name,
        rowsCount: this.editedItem ? this.editedItem.rows.length : null,
        columnsCount: (this.editedItem && this.editedItem.rows && this.editedItem.rows[0])
          ? this.editedItem.rows[0].seats.length : null,
      };
    },
  },
};
</script>

<style scoped>

</style>
