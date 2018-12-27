import Vue from 'vue';
import qs from 'qs';
import { PURGE_AUTH, SET_AUTH } from './auth.mutations';
import { showErrorToasts } from '../../ToastConfigurer';

export const LOGIN = 'login';
export const FETCH_LOGGED_USER = 'fetchLoggedUser';
export const LOGOUT = 'logout';
export const REGISTER = 'register';
export const ACTIVATE_ACCOUNT = 'verifyAccount';
export const RESET_PASSWORD = 'resetPassword';
export const REQUEST_RESET_PASSWORD = 'requestResetPassword';

export const actions = {
  [LOGIN]({ commit }, payload) {
    return new Promise(resolve =>
      Vue.axios
        .post('/login', qs.stringify(payload), {
          headers: {
            'Content-type': 'application/x-www-form-urlencoded',
          },
        })
        .then(({ data }) => {
          commit(SET_AUTH, data);
          resolve(data);
        })
        .catch(error => showErrorToasts(error.response.data)));
  },
  [FETCH_LOGGED_USER]({ commit }) {
    return new Promise((resolve, reject) => {
      Vue.axios.get('/users/accounts/me')
        .then(({ data }) => {
          commit(SET_AUTH, data);
          resolve(data);
        })
        .catch((error) => {
          reject(error.response.data);
        });
    });
  },
  [REGISTER](_, payload) {
    return new Promise((resolve) => {
      Vue.axios.post('/accounts', payload)
        .catch(error => showErrorToasts(error.response.data));
      resolve();
    });
  },
  [LOGOUT]({ commit }) {
    return new Promise((resolve) => {
      Vue.axios.post('/logout')
        .then(() => {
          commit(PURGE_AUTH);
          resolve();
        })
        .catch(error => error);
    });
  },
  [ACTIVATE_ACCOUNT](_, token) {
    return new Promise((resolve, reject) =>
      Vue.axios.patch(`/verification?token=${token}`)
        .then(resolve)
        .catch(reject));
  },
  [RESET_PASSWORD](_, { token, password, matchingPassword }) {
    const body = {
      token,
      passwordPairDTO: {
        password,
        matchingPassword,
      },
    };
    return new Promise((resolve, reject) => {
      Vue.axios.patch('/users/accounts/reset-password', body)
        .then(({ data }) => {
          resolve(data);
        })
        .catch(() => reject());
    });
  },
  [REQUEST_RESET_PASSWORD](_, { email }) {
    return new Promise((resolve, reject) => {
      Vue.axios.post('/users/accounts/reset-password', { email })
        .then(({ data }) => {
          resolve(data);
        })
        .catch(() => reject());
    });
  },
};
