import fixture from '../../fixtures/seanceDetails';

export const fetchSeanceDetails = id => new Promise((resolve) => {
  setTimeout(() => {
    resolve(fixture);
  }, 1500);
});
export const makeReservation = ({
  name, surname, email, selectedSeats,
}) => new Promise((resolve, reject) => {
  setTimeout(() => {
    reject('oh shit');
  }, 1500);
});

