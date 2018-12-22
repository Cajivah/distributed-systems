import Vue from 'vue';
import VeeValidate, { Validator } from 'vee-validate';

const passwordRegex = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})');

const password = {
  getMessage: field => `${field} must contain at least: 1 uppercase letter, 1 lowercase character, 
  1 digit, 1 special character and be at least 8 characters long`,
  validate: value => value && passwordRegex.test(value),
};

Validator.extend('password', password);

export default () => Vue.use(VeeValidate);
