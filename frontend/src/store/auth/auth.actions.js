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
    return Vue.axios.post('/login', qs.stringify(payload), {
      headers: {
        'Content-type': 'application/x-www-form-urlencoded',
      },
    })
      .then(({ data }) => commit(SET_AUTH, data))
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [FETCH_LOGGED_USER]({ commit }) {
    return Vue.axios.get('/accounts/me')
      .then(({ data }) => commit(SET_AUTH, data))
      .catch(({ response }) => { throw response.data; });
  },
  [REGISTER](_, payload) {
    return Vue.axios.post('/accounts', payload)
      .catch(({ response }) => {
        showErrorToasts(response.data);
        throw response.data;
      });
  },
  [LOGOUT]({ commit }) {
    return Vue.axios.post('/logout')
      .then(() => commit(PURGE_AUTH))
      .catch(({ response }) => { throw response.data; });
  },
  [ACTIVATE_ACCOUNT](_, token) {
    return Vue.axios.patch(`/verification?token=${token}`)
      .then(({ data }) => data)
      .catch(({ response }) => { throw response.data; });
  },
  [RESET_PASSWORD](_, { token, password, matchingPassword }) {
    const body = {
      token,
      passwordPairDTO: {
        password,
        matchingPassword,
      },
    };
    return Vue.axios.patch('/users/accounts/reset-password', body)
      .then(({ data }) => data)
      .catch(({ response }) => { throw response.data; });
  },
  [REQUEST_RESET_PASSWORD](_, { email }) {
    return Vue.axios.post('/users/accounts/reset-password', { email })
      .then(({ data }) => data)
      .catch(({ response }) => { throw response.data; });
  },
};
