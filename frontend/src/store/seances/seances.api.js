import fixture from '../../fixtures/seances'

export const fetchSeances = () => {
  return new Promise(resolve => {
      console.log(fixture);
      setTimeout(function(){
          resolve(fixture)
      }, 1500)
  })
};