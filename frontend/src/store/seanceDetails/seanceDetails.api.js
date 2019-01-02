import fixture from '../../fixtures/seanceDetails'

export const fetchSeanceDetails = (id) => {
    return new Promise(resolve => {
        setTimeout(function () {
            resolve(fixture)
        }, 1500)
    });
};
export const makeReservation = ({name, surname, email, selectedSeats}) => {
    return new Promise((resolve, reject) => {
        setTimeout(function () {
            reject( 'oh shit');
        }, 1500)
    });
};

