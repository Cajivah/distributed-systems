import fixture from '../../fixtures/seanceDetails'

export const fetchSeanceDetails = (id) => {
    return new Promise(resolve => {
        setTimeout(function () {
            resolve(fixture)
        }, 1500)
    });
};
