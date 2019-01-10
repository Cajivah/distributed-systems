import Vue from 'vue';
import apiUtils from '../apiUtils';

const routes = {
  seances: '/seances',
};

export const fetchSeances = (params) => {
  const {
    descending, page, rowsPerPage, sortBy, cinemaId,
  } = params;

  const pagination = apiUtils.safeGetPagination(page, rowsPerPage, sortBy, descending);

  return Vue.axios.get(routes.seances, { params: { ...pagination, cinemaId } })
    .then(({ data }) => data);
};

export const createSeance = seance => Vue.axios.post(routes.seances, seance)
  .then(({ data }) => data);

export const updateSeance = seance => Vue.axios.put(routes.seances, seance)
  .then(({ data }) => data);
