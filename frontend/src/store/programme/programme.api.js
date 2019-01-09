import Vue from 'vue';

const fetchProgramme = (cinemaId, date) => Vue.axios.get(`/programme/${cinemaId}/${date}`);

export default {
  fetchProgramme,
};
