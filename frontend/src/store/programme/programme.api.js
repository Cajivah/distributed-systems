import Vue from 'vue';

const fetchProgramme = (cinemaId, date) => Vue.axios.get(`/programme/${cinemaId}/${date}`)
  .then(response => response.data);

export default {
  fetchProgramme,
};
