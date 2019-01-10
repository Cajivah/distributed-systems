const FETCH_DETAILS = 'fetchDetails';
const SET_SEANCE_DETAILS = 'fetchSeancesCompleted';
const OPEN_RESERVATION_DIALOG = 'openReservationDialog';
const CLOSE_RESERVATION_DIALOG = 'closeReservationDialog';
const SELECT_SEATS = 'selectSeat';
const SET_SELECTED_SEATS = 'setSelectSeat';
const SET_DIALOG = 'setDialog';
const TYPE_FIRST_NAME = 'typeFirstName';
const TYPE_SURNAME = 'typeLastName';
const TYPE_EMAIL = 'typeEmail';
const SET_CLIENT_DETAIL = 'setClientDetail';
const MAKE_RESERVATION = 'makeReservation';
const SEND_RESERVATION_REQUEST = 'sendReservationRequest';
const SET_RESERVATION_SUCCESSFUL = 'reservationSuccessful';
const SET_RESERVATION_ERROR = 'reservationError';
const SET_RESERVATION_PROGRESS = 'reservationInProgress';
const SELL_SEATS = 'sellSeats';
const SET_SELLING_SEATS_ERROR = 'sellingSeatsError';
const SET_SELLING_SEATS_PROGRESS = 'sellingSeatsProgress';


export const actions = {
  FETCH_DETAILS,
  OPEN_RESERVATION_DIALOG,
  CLOSE_RESERVATION_DIALOG,
  SELECT_SEATS,
  MAKE_RESERVATION,
  TYPE_FIRST_NAME,
  TYPE_SURNAME,
  TYPE_EMAIL,
  SEND_RESERVATION_REQUEST,
  SELL_SEATS,
};

export const mutations = {
  SET_SEANCE_DETAILS,
  SET_SELECTED_SEATS,
  SET_DIALOG,
  SET_CLIENT_DETAIL,
  SET_RESERVATION_ERROR,
  SET_RESERVATION_PROGRESS,
  SET_RESERVATION_SUCCESSFUL,
  SET_SELLING_SEATS_ERROR,
  SET_SELLING_SEATS_PROGRESS,
};
