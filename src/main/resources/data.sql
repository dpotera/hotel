insert into room_type (id, name, capacity) values
  (1, 'small', 4),
  (2, 'medium', 6),
  (3, 'big', 8);

insert into room (name, room_type_id) values
  ('pok.1-1', 1),
  ('pok.1-2', 1),
  ('pok.1-3', 1),
  ('pok.2-1', 2),
  ('pok.2-2', 2),
  ('pok.2-3', 3);

insert into user (id, name) values (1, 'test');