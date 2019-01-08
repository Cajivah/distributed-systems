import moment from 'moment';

export const formatDate = (date, formatters) => moment(date).format(formatters);

export const currentDate = formatters => moment().format(formatters);
