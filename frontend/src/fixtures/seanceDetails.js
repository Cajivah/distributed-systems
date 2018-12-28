import moment from 'moment'

const fixture = {
    seance: {
        id: 0,
        start: moment().add(1, 'hours'),
        prices: {
            REGULAR: {
                value: 20,
                currency: "PLN"
            },
            VIP: {
                value: 40,
                currency: "PLN"
            }
        }
    },
    movie: {
        id: 0,
        lengthMinutes: 120,
        director: "Clint Eastwood",
        title: "The Mule",
        description: "nice movie"
    },
    rows: [
        {
            id: 0,
            rowCode: "abc",
            seats: [
                {
                    id: 0,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: false
                },
                {
                    id: 1,
                    seatCode: "bca",
                    seatCategory: "REGULAR",
                    occupied: false
                },
                {
                    id: 2,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: true
                },
                {
                    id: 3,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: false
                },
                {
                    id: 4,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: false
                }
            ]
        },
        {
            id: 1,
            rowCode: "abc",
            seats: [
                {
                    id: 5,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: true
                },
                {
                    id: 6,
                    seatCode: "bca",
                    seatCategory: "REGULAR",
                    occupied: true
                },
                {
                    id: 7,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: true
                },
                {
                    id: 8,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: false
                },
                {
                    id: 9,
                    seatCode: "bca",
                    seatCategory: "VIP",
                    occupied: false
                }
            ]
        },
    ]
};

export default fixture;