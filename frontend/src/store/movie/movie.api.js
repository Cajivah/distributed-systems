import Vue from 'vue';
import safeGetPagination from '../apiUtils';

const routes = {
  movies: '/movies',
};

export const fetchMovies = params => new Promise((resolve, reject) => {
  const {
    descending, page, rowsPerPage, sortBy,
  } = params;

  const pagination = safeGetPagination(page, rowsPerPage, sortBy, descending);

  Vue.axios.get(routes.movies, { params: { ...pagination } })
    .then(({ data }) => resolve(data), reject);
});

export const fetchMovie = id => new Promise((resolve, reject) => {
  Vue.axios.get(`${routes.bookings}/${id}`)
    .then(({ data }) => resolve(data), reject);
});

export const createMovie = movie => new Promise((resolve, reject) => {
  Vue.axios.post(routes.movies, movie)
    .then(({ data }) => resolve(data), reject);
});

export const updateMovie = movie => new Promise((resolve, reject) => {
  Vue.axios.put(routes.movies, movie)
    .then(({ data }) => resolve(data), reject);
});
