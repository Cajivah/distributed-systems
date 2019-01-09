import Vue from 'vue';
import safeGetPagination from '../apiUtils';

const routes = {
  rooms: '/rooms',
};

export const fetchRooms = (params) => {
  const {
    descending, page, rowsPerPage, sortBy, cinema,
  } = params;

  const pagination = safeGetPagination(page, rowsPerPage, sortBy, descending);

  return Vue.axios.get(routes.rooms, { params: { ...pagination, cinemaId: cinema } })
    .then(({ data }) => data);
};

export const createRoom = room => Vue.axios.post(routes.rooms, room)
  .then(({ data }) => data);

export const updateRoom = room => Vue.axios.put(routes.rooms, room)
  .then(({ data }) => data);

export const prepareRoomPayload = (data) => {
  const { room, cinema } = data;
  const {
    rowsCount, columnsCount, name, id,
  } = room;

  let rows = [];

  for (let i = 0; i < rowsCount; i += 1) {
    let seats = [];
    for (let j = 0; j < columnsCount; j += 1) {
      const seat = { seatCode: j, seatCategory: 'REGULAR' };
      seats = [...seats, seat];
    }
    const row = { rowCode: i, seats };
    rows = [...rows, row];
  }
  return {
    id, name, rows, cinema,
  };
};
