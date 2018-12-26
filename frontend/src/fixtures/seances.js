import moment from 'moment';

const fixture = {
  "content": [
    {
      "id": 1,
      "start": moment().add(1, 'days').toDate(),
      "prices": {
        "REGULAR": {
          "value": 21312,
          "currency": "PLN"
        },
        "VIP": {
          "value": 2131,
          "currency": "PLN"
        }
      },
      "room": {
        "id": 1,
        "name": "sala",
        "cinema": {
          "id": 1,
          "name": "helios"
        }
      },
      "movie": {
        "id": 1,
        "lengthMinutes": 125,
        "title": "Ogniem i mieczem",
        "description": "abaabcabc"
      },
      "bookings": []
    },
    {
      "id": 2,
      "start": moment().add(2, 'days').add(5, 'hours').toDate(),
      "prices": {
        "REGULAR": {
          "value": 21312,
          "currency": "PLN"
        },
        "VIP": {
          "value": 2131,
          "currency": "PLN"
        }
      },
      "room": {
        "id": 1,
        "name": "sala",
        "cinema": {
          "id": 1,
          "name": "helios"
        }
      },
      "movie": {
        "id": 1,
        "lengthMinutes": 125,
        "title": "Ogniem i mieczem",
        "description": "abaabcabc"
      },
      "bookings": []
    }
  ],
  "totalElements": 2,
  "totalPages": 1,
  "numberOfElements": 2
};

export default fixture;