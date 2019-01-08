import fixture from '../../fixtures/seances';

const fetchSeances = () => new Promise((resolve) => {
  setTimeout(() => {
    resolve(fixture);
  }, 1500);
});

export default {
  fetchSeances,
};
