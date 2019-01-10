import Vue from 'vue';

export const fetchSeanceDetails = id => Vue.axios.get(`/seances/${id}`)
  .then(response => response.data);
export const makeReservation = ({
  seanceId, firstName, lastName, email, phone, selectedSeats,
}) => Vue.axios.post(`/seances/${seanceId}/bookings`, {
  owner: {
    email,
    firstName,
    lastName,
    phone,
  },
  seatIds: selectedSeats,
});
export const sellSeats = (id, seats) => Vue.axios.post(`/seances/${id}/sell`, {
  seatIds: seats,
});
