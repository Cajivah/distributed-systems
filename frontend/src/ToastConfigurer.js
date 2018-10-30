import Vue from 'vue';
import VueToasted from 'vue-toasted';

Vue.use(VueToasted);

export const showErrorToasts = (error) => {
  if (error) {
    if (error.messages) {
      error.messages.forEach(msg =>
        Vue.toasted.global.error_toast({ message: msg }).goAway(2500));
    } else if (error.message) {
      Vue.toasted.global.error_toast({ message: error.message }).goAway(2500);
    }
  }
};

export const showSuccessToasts = (success = {}) => {
  if (success.messages) {
    success.messages.forEach(msg =>
      Vue.toasted.global.success_toast({ message: msg }).goAway(2500));
  } else if (success.message) {
    Vue.toasted.global.success({ message: success.message }).goAway(3500);
  }
};

Vue.toasted.register(
  'error_toast',
  (payload) => {
    if (!payload.message) {
      return 'Oops.. Something Went Wrong..';
    }
    return `Oops...${payload.message}`;
  },
  {
    type: 'error',
    icon: 'fa-exclamation-circle',
    position: 'top-center',
    iconPack: 'fontawesome',
  },
);

Vue.toasted.register(
  'success_toast',
  (payload) => {
    if (!payload.message) {
      return 'Success! ';
    }
    return `Success! ${payload.message}`;
  },
  {
    type: 'success',
    icon: 'fa-check-circle',
    position: 'top-center',
    iconPack: 'fontawesome',
  },
);

