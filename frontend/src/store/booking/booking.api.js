import Vue from 'vue';

const routes = {
  bookings: '/bookings',
};

export const fetchBookingToCancel = token => new Promise((resolve, reject) => {
  Vue.axios.get(routes.bookings, { params: { token } })
    .then(({ data }) => resolve(data))
    .catch(error => reject(error));
});

export const cancelBooking = token => new Promise((resolve, reject) => {
  Vue.axios.delete(routes.bookings, { params: { token } })
    .then(({ data }) => resolve(data))
    .catch(error => reject(error));
});
