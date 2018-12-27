export const SET_AUTH = 'setAuth';
export const PURGE_AUTH = 'purgeAuth';
export const CLEAR_USERS = 'clearUsers';

export const mutations = {
  [SET_AUTH](state, data) {
    state.loggedUser = data;
    state.afterLoginRedirect = null;
    state.error = null;
  },
  [PURGE_AUTH](state) {
    state.loggedUser = null;
    state.afterLoginRedirect = null;
    state.error = null;
  },
  [CLEAR_USERS](state) {
    state.users = [];
    state.error = null;
  },
};
