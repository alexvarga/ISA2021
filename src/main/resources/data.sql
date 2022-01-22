--administrator user/pass: admin/test
--korisnik user/pass: aleksandra.varga0@gmail.com/test

insert into users (address, city, email, first_name, last_name, password, phone_number, state, username, enabled, locked, user_role, verification_code) values
 ('Adresa 1', 'Vršac', 'aleksandra.varga0@gmail.com', 'Aleksandra', 'Varga', '$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum', '0003579100', 'Srbija', null, true, false, 'USER', null ),
 ('Adresa 2', 'Beograd', 'admin', 'admin', 'admin', '$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum', '5551555123', 'Srbija', null, true, false, 'ADMIN', null );

insert into income_rate (entity_name, entity_percent) values ('boat', 20), ('house', 10), ('adventure', 5);

--Da bi stigao odgovor administratora na žalbu i vlasniku, ovde treba upisati ispravanu mail adresu.
insert into boat_owners (address, city, email, first_name, last_name, password) values
 ('Adresa bo1', 'Novi Sad', 'aleksandra.varga@yahoo.com', 'Žarko', 'Žarković', 'test'),
 ('Adresa bo2', 'Subotica', 'bo2@email', 'Darko', 'Darković', 'test');

insert into vacation_house_owners (address, city, email, first_name, last_name, password) values
 ('Adresa vho1', 'Novi Sad', 'vho1@email', 'Ljubica', 'Ljubičić', 'test'),
 ('Adresa vho2', 'Subotica', 'vho2@email', 'Boža', 'Božović', 'test');

insert into instructors (address, city, email, first_name, last_name, password) values
 ('Adresa vho1', 'Vrbas', 'i1@email', 'Ivana', 'Ivanović', 'test'),
 ('Adresa vho2', 'Niš', 'i2@email', 'Jovana', 'Jović', 'test');

insert into adventures (address, avg_rating, image_link, info, misc, name, no_of_persons, no_of_ratings, price, instructor_id) values
 ('Adresa avanture 1', 2.5, '/img/a1.jpg', 'Informacije o avanturi 1', 'prevoz-parking', 'Avantura 1', 6, 2, 60, 1),
 ('Adresa avanture 2', 5, '/img/a2.jpg', 'Informacije o avanturi 2', 'čamac-parking', 'Avantura 2', 4, 2, 150, 1),
 ('Adresa avanture 3', 4, '/img/a3.jpg', 'Informacije o avanturi 3', '', 'Avantura 3', 4, 1, 50, 2);

insert into boats (address, avg_rating, image_link, info, misc, name, no_of_persons, no_of_ratings, price, boat_owner_id) values
 ('Adresa broda 1', 5, '/img/b1.jpg', 'Informacije o brodu 1', 'kapetan-bar-wifi', 'Brod 1', 3, 2, 500, 1),
 ('Adresa broda 2', 3, '/img/b2.jpg', 'Informacije o brodu 2', 'wifi', 'Brod 2', 4, 2, 150, 1),
 ('Adresa broda 3', 4, '/img/b3.jpg', 'Informacije o brodu 3', '', 'Brod 3', 4, 1, 50, 2),
 ('Adresa broda 4', 5, '/img/b4.jpg', 'Informacije o brodu 4', '', 'Brod 4', 2, 10, 999, 2);

 insert into vacation_houses (address, avg_rating, image_link, info, misc, name, no_of_persons, no_of_ratings, price, owner_id) values
 ('Adresa vikendice 1', 3.5, '/img/vh1.jpg', 'Informacije o vikendici 1', 'parking-bazen-wifi', 'Vikendica 1', 3, 2, 750, 1),
 ('Adresa vikendice 2', 5, '/img/vh2.jpg', 'Informacije o vikendici 2', 'wifi', 'Vikendica 2', 7, 2, 1040, 1),
 ('Adresa vikendice 3', 1.5, '/img/vh3.jpg', 'Informacije o vikendici 3', 'parking', 'Vikendica 3', 3, 2, 350, 2);

 insert into adventure_reservations (date_end, date_from, price, reservation_time, reservation_type, user_id, adventure_id) values
 ('2022-03-06 00:00:00', '2022-03-01 00:00:00', 300, '2022-01-20 20:30:00', 'ACTIVE', 1, 1),
 ('2022-04-06 00:00:00', '2022-04-01 00:00:00', 250, '2022-01-20 20:30:00', 'ACTIVE', 1, 2),
 ('2021-12-20 00:00:00', '2021-12-10 00:00:00', 650, '2021-11-20 20:30:00', 'ACTIVE', 1, 3),
 ('2022-12-30 00:00:00', '2022-12-24 00:00:00', 250, '2022-01-20 20:30:00', 'DISCOUNTOFFER', null, 2);

 insert into boat_reservations (date_end, date_from, price, reservation_time, reservation_type, user_id, boat_id) values
  ('2022-04-06 00:00:00', '2022-04-01 00:00:00', 300, '2022-01-20 20:30:00', 'ACTIVE', 1, 1),
 ('2022-05-06 00:00:00', '2022-05-01 00:00:00', 150, '2022-01-20 20:30:00', 'CANCELLED', 1, 2),
 ('2021-11-20 00:00:00', '2021-11-10 00:00:00', 1050, '2021-10-20 20:30:00', 'ACTIVE', 1, 3),
 ('2022-12-17 00:00:00', '2022-12-10 00:00:00', 299, '2022-01-20 20:30:00', 'DISCOUNTOFFER', null, 2);

 insert into vacation_house_reservations (date_end, date_from, price, reservation_time, reservation_type, user_id, vacation_house_id) values
  ('2022-05-06 00:00:00', '2022-05-01 00:00:00', 300, '2022-01-20 20:30:00', 'ACTIVE', 1, 1),
 ('2022-06-06 00:00:00', '2022-06-01 00:00:00', 250, '2022-01-20 20:30:00', 'ACTIVE', 1, 2),
 ('2021-10-30 00:00:00', '2021-10-28 00:00:00', 650, '2021-10-20 20:30:00', 'ACTIVE', 1, 3),
 ('2022-12-04 00:00:00', '2022-12-01 00:00:00', 250, '2022-01-20 20:30:00', 'DISCOUNTOFFER', null, 2);

 insert into adventure_reviews(content, rating, review_status, review_time, user_id, adventure_id) values
 ('Sve prošlo u najboljem redu. Preporuka!', 5, 1, '2022-01-20 20:30:00', 1, 1),
 ('Test komentar.', 5, 0, '2022-01-20 07:32:00', 1, 1);
 insert into boat_reviews(content, rating, review_status, review_time, user_id, boat_id) values
  ('Test komentar za brod 1.', 3, 0, '2022-01-20 20:30:00', 1, 1),
  ('Test komentar za brod 1.', 3, 1, '2022-01-20 20:30:00', 1, 1);
 insert into vacation_house_reviews(content, rating, review_status, review_time, user_id, vacation_house_id) values
  ('Sve prošlo u najboljem redu. Preporuka!', 5, 1, '2022-01-20 20:30:00', 1, 1),
  ('Test pending review!', 5, 0, '2022-01-20 20:30:00', 1, 1),
  ('Test review za vikendicu 2!', 5, 1, '2022-01-20 20:30:00', 1, 2),
  ('Test review za vikendicu 3!', 5, 1, '2022-01-20 20:30:00', 1, 3),
  ('Test odbijena recenzija za vikendicu 1', 5, 2, '2022-01-20 20:30:00', 1, 1);

insert into boat_complaints(complaint_date, content, user_id, boat_id) values
('2022-01-21', 'Test žalba za brod 2', 1, 2),
('2022-01-21', 'Test žalba 2 za brod 1', 1, 1);
insert into instructor_complaints(complaint_date, content, user_id, instructor_id) values
('2022-01-21', 'Test žalba za instruktora 2', 1, 2),
('2022-01-21', 'Test žalba 2 za instruktora 1', 1, 1);
insert into vacation_house_complaints(complaint_date, content, user_id, vacation_house_id) values
('2022-01-21', 'Test žalba za vikendicu 2', 1, 2),
('2022-01-21', 'Test žalba 2 za vikendicu 1', 1, 1);


