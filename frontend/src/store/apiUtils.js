const safeGetPagination = (page, size, sort, descending) => {
  let pagination = {};

  if (page) {
    pagination = { ...pagination, page: page - 1 };
  }

  if (size) {
    pagination = { ...pagination, size };
  }

  if (sort) {
    pagination = { ...pagination, sort: `${sort},${descending ? 'desc' : 'asc'}` };
  }

  return pagination;
};

export default {
  safeGetPagination,
};
