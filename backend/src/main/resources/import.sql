-- dodawanie i modyfikowanie filmów, seansów, sal

---- kina
insert into cinema (id, name)
values (1, 'Wrocław Magnolia'),
       (2, 'Wrocław Arkady');

---- filmy
insert into movie (id, description, director, length_minutes, title)
values (1, 'In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a ' ||
           'group of Jewish U.S. soldiers coincides with a theatre owners vengeful plans for the same.',
        'Quentin Tarantino', 120, 'Inglourious Bastards'),
       (2, 'Porywająca opowieść o zespole Queen, jego muzyce i niezwykłym ' ||
           'wokaliście Freddiem Mercurym (Rami Malek – serial TV ”Mr. Robot”), ' ||
           'który przełamując stereotypy i konwencje, zdobył uwielbienie niezliczonych ' ||
           'fanów. Film pokazuje błyskotliwą karierę zespołu, który dzięki ikonicznym ' ||
           'utworom i rewolucyjnemu brzmieniu wspiął się na szczyty sławy, dopóki ' ||
           'skandalizujący styl życia Mercury’ego nie postawił wszystkiego pod znakiem ' ||
           'zapytania. Triumfalny powrót zespołu podczas koncertu na rzecz Live Aid z ' ||
           'udziałem cierpiącego na śmiertelną chorobę Mercury’ego wszedł na trwałe do ' ||
           'historii muzyki rockowej i po dziś dzień stanowi źródło inspiracji dla ' ||
           'wszystkich tych, którzy czują się inni, niepoprawnych marzycieli i wielbicieli ' ||
           'muzyki na całym świecie.',
        'Dexter Fletcher, Bryan Singer', 134, 'Bohemian Rhapsody'),
       (3, 'Fragment życia astronauty Neila Armstronga i jego legendarnej misji kosmicznej, ' ||
         'dzięki której jako pierwszy człowiek stanął na Księżycu.',
      'Damien Chazelle', 141, 'Pierwszy człowiek'),
       (4, 'Gwiazda irańskiego kina Behnaz Jafari pewnego dnia otrzymuje wstrząsającą wiadomość ' ||
         'od młodej dziewczyny z prowincji. Wraz z reżyserem Jafarem Panahim aktorka wyrusza ' ||
         'na północny wschód kraju, aby poznać prawdę o nastolatce, która zamierzała popełnić ' ||
         'samobójstwo, gdy rodzina nie pozwoliła jej studiować w szkole filmowej. Na poły kino' ||
         ' drogi, na poły film sensacyjny, Trzy twarze to niezapomniana podróż po kraju, ' ||
         'którego zmieniająca się jak w kalejdoskopie polityczno-społeczna rzeczywistość ' ||
         'wpłynęła na losy trzech pokoleń kobiet z aktorskimi aspiracjami.',
      'Se rokh', 100, 'Trzy twarze');

---- miniaturki
insert into movie_image (id, image_url, movie_id)
values (1, 'https://cdn.empireonline.com/jpg/80/0/0/1000/563/0/north/0/0/0/0/0/t/films/16869/images/bk0GylJLneaSbpQZXpgTwleYigq.jpg', 1),
(2, 'https://images-na.ssl-images-amazon.com/images/I/716EFPZDJOL._RI_SX300_.jpg', 1),
(3, 'https://thecriticalbreakdown.com/wp-content/uploads/2018/03/ib2-820x394.png', 1),
(4, 'https://i.wpimg.pl/O/600x264/d.wpimg.pl/366063362--937632025/bohemian-raphsody.jpg', 2),
(5, 'https://uptone.pl/wp-content/uploads/2016/10/bohemian_rhapsody.jpg', 2),
(6, 'https://uptone.pl/wp-content/uploads/2016/10/bohemian_rhapsody.jpg', 2),
(7, 'https://ssl-gfx.filmweb.pl/po/21/96/782196/7858511.6.jpg', 3),
(8, 'https://1.fwcdn.pl/wv/78/30/47830/thumbnail.47830.4.jpg', 3),
(9, 'https://i.wpimg.pl/O/644x362/d.wpimg.pl/1315714864--1073736335/pierwszy-czlowiek-ryan.png', 3),
(10, 'https://ssl-gfx.filmweb.pl/po/79/05/807905/7844190.6.jpg', 4),
(11, 'http://bok.bialystok.pl/pliki/trzy-twarze.jpg', 4),
(12, 'http://pelnasala.pl/wp-content/uploads/2018/05/panahi.jpg', 4)
;

---- sale
insert into room (id, name, cinema_id)
values (1, 'Sala 1', 1),
       (2, 'Sala 2', 1),
       (3, 'Sala 3', 1),
       (4, 'Sala 1', 2),
       (5, 'Sala 2', 2),
       (6, 'Sala 3', 2);

insert into seats_row (id, row_code, room_id, index)
values (1, '1', 1, 0),
       (2, '2', 1, 1),
       (3, '3', 1, 2),
       (4, '4', 1, 3),
       (5, '1', 2, 0),
       (6, '2', 2, 1),
       (7, '3', 2, 2),
       (8, '4', 2, 3),
       (9, '1', 3, 0),
       (10, '2', 3, 1),
       (11, '1', 4, 0),
       (12, '2', 4, 1);

insert into seat (id, seat_category, seat_code, seats_row_id, index)
values (1, 'VIP', '1', 1, 0),
       (3, 'VIP', '2', 1, 1),
       (5, 'REGULAR', '3', 1, 2),
       (6, 'REGULAR', '4', 1, 3),
       (7, 'REGULAR', '5', 1, 4),
       (2, 'VIP', '1', 2, 0),
       (4, 'VIP', '2', 2, 1),
       (8, 'REGULAR', '3', 2, 2),
       (9, 'REGULAR', '4', 2, 3),
       (11, 'REGULAR', '1', 3, 0),
       (12, 'REGULAR', '1', 4, 0),
       (13, 'REGULAR', '1', 5, 0),
       (14, 'REGULAR', '2', 5, 1),
       (15, 'REGULAR', '1', 6, 0),
       (16, 'REGULAR', '2', 6, 1),
       (17, 'REGULAR', '1', 7, 0),
       (18, 'REGULAR', '1', 8, 0),
       (19, 'REGULAR', '1', 9, 0),
       (20, 'REGULAR', '1', 10, 0),
       (21, 'REGULAR', '1', 11, 0);

---- seanse
insert into seance (id, start, movie_id, room_id)
values (1, timestamp '2018-12-11 21:30:00', 1, 1),
       (2, timestamp '2018-12-07 13:00:00', 2, 2),
       (3, timestamp '2018-12-07 15:00:00', 1, 1),
       (4, timestamp '2018-12-07 18:00:00', 1, 1),
       (5, timestamp '2018-12-07 18:00:00', 3, 3),
       (6, timestamp '2018-12-07 18:00:00', 1, 4);

---- ceny za seans
insert into seance_prices (seance_id, currency, price, seat_category)
values (1, 'PLN', 120, 'VIP'),
       (1, 'PLN', 80, 'REGULAR'),
       (2, 'PLN', 22, 'REGULAR'),
       (3, 'PLN', 22, 'REGULAR'),
       (4, 'PLN', 22, 'REGULAR'),
       (5, 'PLN', 22, 'REGULAR');

---------------------------------------------------------------------------------------------------------
-- Dokonanie rezerwacji
insert into booking_owner (id, email, first_name, last_name, phone)
values (1, 'adam@nowak', 'Adam', 'Nowak', '1122334455');

insert into booking (id, booking_identifier, owner_id, seance_id)
values (1,'7asd15c3-2ae4-4db7-8sdb-916sdf1ff9a7', 1, 1);

insert into booking_seat (id, currency, price, seat_status, seat_id, booking_id)
values (1, 'PLN', (select price
                  from seance_prices
                  where seance_id = 1
                  and seat_category = 'REGULAR'),
       'RESERVED', (select seat.id
                    from seat
                    join seats_row on seat.seats_row_id = seats_row.id
                    where seat_code = '1'
                    and seats_row.row_code = '1'
                    and seats_row.room_id = (select room_id from seance where seance.id = 1)),
       1);
---------------------------------------------------------------------------------------------------------
-- Oznaczanie miejsc jako sprzedanych
insert into booking_seat (id, currency, price, seat_status, seat_id, booking_id)
values (2, 'PLN', (select price
                  from seance_prices
                  where seance_id = 1
                  and seat_category = 'REGULAR'),
       'RESERVED', (select seat.id from seat
                    join seats_row on seat.seats_row_id = seats_row.id
                    where seat_code = '2'
                    and seats_row.row_code = '1'
                    and seats_row.room_id = (select room_id from seance where seance.id = 1)),
       null);
---------------------------------------------------------------------------------------------------------
-- Rejestracja w systemie
INSERT INTO user_credentials (id, created, email, modified, password, username, verified, locked)
VALUES (1, '2018-03-05 03:08:10.898380', 'kuba@kuba.com', '2018-03-05 03:08:10.898380',
        '$2a$10$5SEEZxF3UwGYqgiTEkIXNOx7O16mvXu/zP4haioE9QwC026k8RuR.', 'malyjasiak', TRUE, FALSE), -- password: KubaKuba0-
       (2, '2018-03-05 03:08:10.898380', 'admin@kuba.com', '2018-03-20 03:08:10.898380',
        '$2a$04$ynq.Yqu.FiQitU234BEvJ.h36Dezxst3ZNh6wpWW.hXnTpL9Q15XO', 'admin', TRUE, FALSE), -- password: AdminAdmin0-
       (3, '2018-03-05 03:08:10.898380', 'malyjasiak.kuba@gmail.com', '2018-03-05 03:08:10.898380',
        '$2a$10$5SEEZxF3UwGYqgiTEkIXNOx7O16mvXu/zP4haioE9QwC026k8RuR.', 'kuba_cs', TRUE, FALSE), -- password: KubaKuba0-
       (4, '2018-03-05 03:08:10.898380', 'cinema.friend@gmail.com', '2018-03-05 03:08:10.898380',
        '$2a$10$5SEEZxF3UwGYqgiTEkIXNOx7O16mvXu/zP4haioE9QwC026k8RuR.', 'friend', TRUE, FALSE); -- password: KubaKuba0;

INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO user_credentials_roles
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (4, 1);

insert into verification_token (id, created, token, user_id)
values (1, CURRENT_TIMESTAMP, 'token112334523', 1),
       (2, CURRENT_TIMESTAMP, 'token122343', 2),
       (3, CURRENT_TIMESTAMP, 'token123453', 3),
       (4, CURRENT_TIMESTAMP, 'token1667623', 4);


-- workaround for sequence not being updated
select setval('hibernate_sequence', 1000);