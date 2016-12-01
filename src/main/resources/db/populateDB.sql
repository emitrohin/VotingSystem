DELETE FROM dishes;
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
  ('O_Domashnikov', 'domashnikov', 'odomashnikov@nomail.com', 'Олег', 'Домашников', TRUE);

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


INSERT INTO dishes (name, imageLink) VALUES
  ('Картофель, в мундире',
   'http://img07.rl0.ru/eda/c300x300/s2.afisha-eda.ru/Photos/120214125956-120214130233-p-O-kartofel-zapechennij-v-mundire.jpg'),
  ('Рататуй', 'http://img04.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/150810204910-150818154229-p-O-ratatuj.jpg'),
  ('Борщ',
   'http://img08.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/120131084941-120214160730-p-O-kurica-zapechennaja-v-hrustjaschej-korochke-s-imbirem-apelsinami.jpg'),
  ('Гратен дофинуа',
   'http://img08.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/151002075307-151009125656-p-O-graten-dofinua.jpg'),
  ('Чахохбили из курицы',
   'http://img05.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/120213175727-120213180044-p-O-chahohbili-iz-kurici.jpg'),
  ('Жареный рис с яйцом по‑китайски',
   'http://img05.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/150428090447-150505141508-p-O-zharenij-ris-s-jajcom-po-kitajski.jpg'),
  ('Хинкали', 'http://img05.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/120131112107-150527002516-p-O-hinkali.jpg'),
  ('Тикка-масала',
   'http://img04.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/120131082425-130725170057-p-O-tikka-masala.jpg');

INSERT INTO menus_dishes (dish_id, menu_id, price) VALUES
  (100015, 100012, 150.00),
  (100016, 100012, 180.00),
  (100017, 100012, 210.00),
  (100019, 100013, 100.00),
  (100020, 100013, 160.00),
  (100021, 100013, 190.00),
  (100015, 100014, 190.00),
  (100016, 100014, 300.00),
  (100017, 100014, 110.00);

INSERT INTO votes (restaurant_id, user_id, vote_date) VALUES
  (100009, 100000, DATE '2016-11-26'),
  (100009, 100001, DATE '2016-11-26'),
  (100009, 100002, DATE '2016-11-26'),
  (100008, 100003, DATE '2016-11-26'),
  (100008, 100004, DATE '2016-11-26'),
  (100010, 100005, DATE '2016-11-26');