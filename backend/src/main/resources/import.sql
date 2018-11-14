INSERT INTO user_credentials (id, created, email, modified, password, username, verified, locked)
        VALUES (1, '2018-03-05 03:08:10.898380', 'kuba@kuba.com', '2018-03-05 03:08:10.898380',
                        '$2a$10$5SEEZxF3UwGYqgiTEkIXNOx7O16mvXu/zP4haioE9QwC026k8RuR.', 'malyjasiak', TRUE, FALSE), -- password: KubaKuba0-
                (2, '2018-03-05 03:08:10.898380', 'admin@kuba.com', '2018-03-20 03:08:10.898380',
                        '$2a$04$ynq.Yqu.FiQitU234BEvJ.h36Dezxst3ZNh6wpWW.hXnTpL9Q15XO', 'admin', TRUE, FALSE), -- password: AdminAdmin0-
                        (3, '2018-03-05 03:08:10.898380', 'malyjasiak.kuba@gmail.com', '2018-03-05 03:08:10.898380',
                        '$2a$10$5SEEZxF3UwGYqgiTEkIXNOx7O16mvXu/zP4haioE9QwC026k8RuR.', 'kuba_cs', TRUE, FALSE), -- password: KubaKuba0-
                        (4, '2018-03-05 03:08:10.898380', 'cinema.friend@gmail.com', '2018-03-05 03:08:10.898380',
                        '$2a$10$5SEEZxF3UwGYqgiTEkIXNOx7O16mvXu/zP4haioE9QwC026k8RuR.', 'friend', TRUE, FALSE) -- password: KubaKuba0-
;

INSERT INTO role (id, name) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN')
;

INSERT INTO user_credentials_roles VALUES (1, 1), (2, 1), (2, 2), (3, 1), (4, 1)
;

INSERT INTO public.cinema (id, name) VALUES (1, 'Cinema 1')
;

INSERT INTO public.movie (id, description, director, length_minutes, title) VALUES (1, 'In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owners vengeful plans for the same.', 'Quentin Tarantino', 120, 'Inglourious Bastards')
;

INSERT INTO public.room (id, name, cinema_id) VALUES (1, 'Room 1', 1)
;

INSERT INTO public.seats_row (id, row_code, room_id, index) VALUES (1, '1', 1, 0), (2, '2', 1, 1)
;

INSERT INTO public.seat (id, seat_category, seat_code, index, seats_row_id) VALUES (1, 'VIP', '1', 0, 1),
  (2, 'VIP', '2', 1, 1),
  (3, 'VIP', '1', 0, 2),
  (4, 'VIP', '2', 1, 2)
;

INSERT INTO public.seance (id, start, movie_id, room_id) VALUES (1, '2018-11-11 21:30:00.000000', 1, 1)
;

INSERT INTO public.seance_prices (seance_id, currency, price, seat_category) VALUES (1, 'PLN', 120, 'VIP'),
  (1, 'PLN', 80, 'REGULAR');

INSERT INTO public.booking_owner (id, email, first_name, last_name, phone) VALUES (1, 'jan@kowalski', 'Jan', 'Kowalski', null);

INSERT INTO public.booking (id, booking_identifier, owner_id, seance_id) VALUES (1, '7e9715c3-2ae4-4db7-897b-91681f2ef9a7', 1, 1);

INSERT INTO public.booking_seat (id, currency, price, seat_status, seat_id, booking_id) VALUES
  (1, 'PLN', 120.00, 'RESERVED', 1, 1),
  (2, 'PLN', 120.00, 'RESERVED', 2, 1)
;

-- workaround for sequence not being updated
select setval('hibernate_sequence', 1000);