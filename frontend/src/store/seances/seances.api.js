import fixture from '../../fixtures/seances'

export const fetchSeances = (date) => {
  return new Promise(resolve => {
      setTimeout(function(){
          resolve(fixture)
      }, 1500)
  });
};
