import moment from 'moment';

export const formatDate = (date, formatters) => {
    return moment(date).format(formatters);
};