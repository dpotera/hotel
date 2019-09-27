insert into room_type (id, name, capacity) values
  (1, 'small', 4),
  (2, 'medium', 6),
  (3, 'big', 8);

insert into room (id, name, room_type_id) values
  (1, 'pok.1-1', 1),
  (2, 'pok.1-2', 1),
  (3, 'pok.1-3', 1),
  (4, 'pok.2-1', 2),
  (5, 'pok.2-2', 2),
  (6, 'pok.2-3', 3);

insert into user (id, name) values (1, 'test');

insert into reservation (id, user_id, room_id, number_of_people, start_date, end_date) values
  (random_uuid(), 1, 1, 2, '2019-09-10', '2019-09-15'),
  (random_uuid(), 1, 2, 2, '2019-09-10', '2019-09-15'),
  (random_uuid(), 1, 4, 5, '2019-09-10', '2019-09-15');