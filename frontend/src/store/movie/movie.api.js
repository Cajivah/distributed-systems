import Vue from 'vue';

const routes = {
  movies: '/movies',
};

// export const fetchMovies = params => new Promise((resolve, reject) => {
//   Vue.axios.get(routes.movies, { params: { ...params } })
//     .then(({ data }) => resolve(data), reject);
// });

export const fetchMovies = params => new Promise(resolve => (resolve({
  content: [{
    id: 1,
    title: 'asdf',
    description: 'Lorem impsum',
    lengthInMinutes: 120,
    director: 'Coppola',
  }, {
    id: 2,
    title: 'asdfff',
    description: 'Lorem impsum',
    lengthInMinutes: 120,
    director: 'Coppola',
  }],
  total: 20,
})));

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
