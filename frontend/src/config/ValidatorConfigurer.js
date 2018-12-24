import Vue from 'vue';
import VeeValidate, { Validator } from 'vee-validate';

const PASSWORD_REGEX = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})');

const password = {
  getMessage: field => `${field} must contain at least: 1 uppercase letter, 1 lowercase character, 
  1 digit, 1 special character and be at least 8 characters long`,
  validate: value => value && PASSWORD_REGEX.test(value),
};

Validator.extend('password', password);

export default () => Vue.use(VeeValidate);
