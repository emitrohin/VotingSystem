DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, password, email, first_name, last_name, enabled) VALUES
  ('admin', '$2a$10$5bpi0FCtWcfNcBgGhi9Peuyr.G.2MEIq4a6HEx5zebePevh3NqoPS', 'admin@icloud.com', 'Евгений',
   'Митрохин', TRUE),
  ('user1', '$2a$10$K9vI/76VvCRYrw3PF8ddruQ7yoKAZ1chZ72UxBGy4wg1L/.t7pkrW', 'user1@nomail.com', 'Денис', 'Усков',
   TRUE),
  ('user2', '$2a$10$snhPy3Fb1NW2DPMNyT837unvZ/tlmf.yNvs3rgTWRck2oAgFGMEMC', 'user2@nomail.com', 'Людмила',
   'Лаптева', TRUE),
  ('user3', '$2a$10$5p87KOjDCFlrA27jQhxgn.it.k./Cp6n/8lmaezMOazNry4oF5lKS', 'user3@nomail.com', 'Юлия',
   'Митрохина', TRUE),
  ('user4', '$2a$10$8rI7OiAhHGrElGjrXDKShura4KsQYPeysdK9PQhYHohgaSRoVJ6o2', 'user4@nomail.com',
   'Наталья', 'Гимальдинова', TRUE),
  ('user5', '$2a$10$MaLNbzeCSVycZlALJEIxdeJrHvlpAfrk5p/qDL82DoidAyAdNH38W', 'user5@nomail.com', 'Александр',
   'Устимов', TRUE),
  ('user6', '$2a$10$bxYH2kDBhVbWy7GyWOfx4eKkcfR88FYmx4FKdLXv1jkJAha58O0xa', 'user6@nomail.com', 'Николай',
   'Дубанич', FALSE),
  ('user7', '$2a$10$cfhDk5kp/THYri2HcoN6c.21/8CgktQMD141UhhpaC.Sm/7oplDDi', 'user7@nomail.com', 'Олег',
   'Домашников', TRUE);

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


INSERT INTO dishes (menu_id, name, price, imageLink) VALUES
  (100012, 'Рататуй', 15000,
   'http://img07.rl0.ru/eda/c300x300/s2.afisha-eda.ru/Photos/120214125956-120214130233-p-O-kartofel-zapechennij-v-mundire.jpg'),
  (100012, 'Картофель, в мундире', 18000,
   'http://img04.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/150810204910-150818154229-p-O-ratatuj.jpg'),
  (100012, 'Борщ', 21000,
   'http://img08.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/120131084941-120214160730-p-O-kurica-zapechennaja-v-hrustjaschej-korochke-s-imbirem-apelsinami.jpg'),
  (100013, 'Гратен дофинуа', 10000,
   'http://img08.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/151002075307-151009125656-p-O-graten-dofinua.jpg'),
  (100013, 'Чахохбили из курицы', 16000,
   'http://img05.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/120213175727-120213180044-p-O-chahohbili-iz-kurici.jpg'),
  (100013, 'Жареный рис с яйцом', 19000,
   'http://img05.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/150428090447-150505141508-p-O-zharenij-ris-s-jajcom-po-kitajski.jpg'),
  (100014, 'Хинкали', 19000,
   'http://img05.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/120131112107-150527002516-p-O-hinkali.jpg'),
  (100014, 'Тикка-масала', 30000,
   'http://img04.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/120131082425-130725170057-p-O-tikka-masala.jpg'),
  (100014, 'НЕ что', 11000, NULL);


INSERT INTO votes (restaurant_id, user_id, vote_timestamp) VALUES
  (100009, 100000, DATE '2016-11-26'),
  (100009, 100001, DATE '2016-11-26'),
  (100009, 100002, DATE '2016-11-26'),
  (100008, 100003, DATE '2016-11-26'),
  (100008, 100004, DATE '2016-11-26'),
  (100010, 100005, DATE '2016-11-26');