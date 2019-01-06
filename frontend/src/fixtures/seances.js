import moment from 'moment';

const fixture = {
    "programme": [
        {
            "movieId": 1,
            "movieTitle": "Ogniem i mieczem",
            "seances": [
                {
                    "id": 0,
                    "start": moment().toDate()
                },
                {
                    "id": 1,
                    "start": moment().add(1, 'hours').toDate()
                }
            ]
        },
        {
            "movieId": 2,
            "movieTitle": "Potop",
            "seances": [
                {
                    "id": 2,
                    "start": moment().add(3, 'hours').toDate()
                },
                {
                    "id": 3,
                    "start": moment().add(3, 'hours').toDate()
                }
            ]
        },
        {
            "movieId": 3,
            "movieTitle": "Pan Wołodyjowski",
            "seances": [
                {
                    "id": 4,
                    "start": moment().add(1, 'hours').toDate()
                },
                {
                    "id": 5,
                    "start": moment().add(30, 'minutes').toDate()
                }
            ]
        }
    ]
};

export default fixture;