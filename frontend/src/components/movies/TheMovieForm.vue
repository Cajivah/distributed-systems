<template>
    <v-card>
        <v-card-title>
            <span class="headline">{{ formTitle }}</span>
        </v-card-title>
        <v-card-text>
            <v-container grid-list-md>
                <v-layout wrap>
                    <v-flex xs12>
                        <v-text-field v-model="form.title"
                                      label="Title">

                        </v-text-field>
                    </v-flex>
                    <v-flex xs12>
                        <v-text-field v-model="form.director"
                                      label="Director">

                        </v-text-field>
                    </v-flex>
                    <v-flex xs12>
                        <v-text-field v-model="form.lengthInMinutes"
                                      label="Length in minutes">
                        </v-text-field>
                    </v-flex>
                    <v-flex xs12>
                        <v-text-field v-model="form.description"
                                      label="Description">

                        </v-text-field>
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
export default {
  name: 'TheMovieForm',
  data() {
    return {
      form: {
        title: '',
        director: '',
        lengthInMinutes: 0,
        description: '',
      },
    };
  },
  props: {
    close: Function,
    save: Function,
    edit: Object,
    formTitle: String,
  },
  methods: {
    saveForm() {
      this.save(this.form);
    },
  },
  watch: {
    edit(newVal) {
      this.form = newVal;
    },
  },
};
</script>

<style scoped>

</style>
