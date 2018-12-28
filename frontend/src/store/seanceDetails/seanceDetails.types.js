const FETCH_DETAILS = "fetchDetails";
const SET_SEANCE_DETAILS = "fetchSeancesCompleted";
const OPEN_RESERVATION_DIALOG = "openReservationDialog";
const CLOSE_RESERVATION_DIALOG = "closeReservationDialog";
const SELECT_SEATS = "selectSeat";
const SET_SELECTED_SEATS = "setSelectSeat";
const SET_DIALOG = "setDialog";
const TYPE_FIRST_NAME = "typeFirstName";
const TYPE_SURNAME = "typeLastName";
const TYPE_EMAIL = "typeEmail";
const SET_CLIENT_DETAIL = "setClientDetail";

export const actions = {
    FETCH_DETAILS,
    OPEN_RESERVATION_DIALOG,
    CLOSE_RESERVATION_DIALOG,
    SELECT_SEATS,
    TYPE_FIRST_NAME,
    TYPE_SURNAME,
    TYPE_EMAIL
};

export const mutations = {
    SET_SEANCE_DETAILS,
    SET_SELECTED_SEATS,
    SET_DIALOG,
    SET_CLIENT_DETAIL,
};

export default {
    actions,
    mutations
};