import moment from 'moment';

const fixture = {
    "content": [
        {
            "id": 1,
            "start": moment().add(1, 'days').toDate(),
            "room": {
                "id": 1,
                "name": "sala"
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
            "id": 0,
            "start": moment().add(1, 'days').add(1, 'hours').toDate(),
            "room": {
                "id": 1,
                "name": "sala"
            },
            "movie": {
                "id": 3,
                "lengthMinutes": 125,
                "title": "COśtam cośtam",
                "description": "abaabcabc"
            },
            "bookings": []
        },
        {
            "id": 2,
            "start": moment().add(2, 'days').add(5, 'hours').toDate(),
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
            "id": 3,
            "start": moment().add(2, 'days').add(5, 'hours').toDate(),
            "room": {
                "id": 1,
                "name": "sala"
            },
            "movie": {
                "id": 2,
                "lengthMinutes": 125,
                "title": "Potop",
                "description": "abaabcabc"
            },
            "bookings": []
        },
        {
            "id": 4,
            "start": moment().add(2, 'days').add(3, 'hours').toDate(),
            "room": {
                "id": 2,
                "name": "sala2"
            },
            "movie": {
                "id": 2,
                "lengthMinutes": 125,
                "title": "Potop",
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