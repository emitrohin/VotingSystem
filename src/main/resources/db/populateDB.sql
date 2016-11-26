DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, password, email, first_name, last_name, enabled) VALUES
  ('E_Mitrohin', 'admin', 'emitrohin@icloud.com', 'Евгений', 'Митрохин', TRUE),
  ('D_Uskov', 'uskov', 'duskov@nomail.com', 'Денис', 'Усков', TRUE),
  ('L_Lapteva', 'lapteva', 'llapteva@nomail.com', 'Людмила', 'Лаптева', TRUE),
  ('Y_Mitrohina', 'mitrohina', 'ymitrohina@nomail.com', 'Юлия', 'Митрохина', TRUE),
  ('N_Gimaldinova', 'gimaldinona', 'ngimaldinova@nomail.com', 'Наталья', 'Гимальдинова', TRUE),
  ('A_Ustumov', 'ustimov', 'austimov@nomail.com', 'Александр', 'Устимов', TRUE),
  ('N_Dubanich', 'dubanich', 'ndubanich@nomail.com', 'Николай', 'Дубанич', FALSE),
  ('O_Domashnikov', 'domashnikov', 'odomashnikov@nomail.com', 'Олег', 'Домашников', FALSE);

INSERT INTO user_roles (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100000, 'ROLE_ADMIN'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER'),
  (100003, 'ROLE_USER'),
  (100004, 'ROLE_USER'),
  (100005, 'ROLE_USER'),
  (100006, 'ROLE_USER'),
  (100007, 'ROLE_USER');

INSERT INTO restaurants (name, imageLink) VALUES
  ('Ривьера', 'http://restoranaltona.ru/images/vip%202/IMG_9280.jpg'),
  ('Гранд каньон', 'http://restoranaltona.ru/images/vip%202/IMG_9308.jpg'),
  ('Асеана', 'http://restoranaltona.ru/images/vip%202/IMG_9324.jpg'),
  ('У Ашота', NULL);

INSERT INTO menus (restaurant_id, dateOfMenu) VALUES
  (100008, DATE '2016-11-26'),
  (100009, DATE '2016-11-26'),
  (100010, DATE '2016-11-26');