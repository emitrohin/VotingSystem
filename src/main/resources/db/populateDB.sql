DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM categories;
DELETE FROM menu_dishes;
DELETE FROM menus;
DELETE FROM restaurants;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, password, email, first_name, last_name, date_of_dirth, enabled) VALUES
  ('E_Mitrohin', 'admin', 'emitrohin@icloud.com', 'Евгений', 'Митрохин', '1988-03-08 10:00:00', TRUE),
  ('D_Uskov', 'uskov', 'duskov@nomail.com', 'Денис', 'Усков', '1969-05-18 10:00:00', TRUE),
  ('L_Lapteva', 'lapteva', 'llapteva@nomail.com', 'Людмила', 'Лаптева', '1987-03-10 10:00:00', TRUE),
  ('Y_Mitrohina', 'mitrohina', 'ymitrohina@nomail.com', 'Юлия', 'Митрохина', '1987-05-24 10:00:00', TRUE),
  ('N_Gimaldinova', 'gimaldinona', 'ngimaldinova@nomail.com', 'Наталья', 'Гимальдинова', '1965-03-10 10:00:00', TRUE),
  ('A_Ustumov', 'ustimov', 'austimov@nomail.com', 'Александр', 'Устимов', '1981-11-09 10:00:00', TRUE),
  ('N_Dubanich', 'dubanich', 'ndubanich@nomail.com', 'Николай', 'Дубанич', '1988-05-05 10:00:00', FALSE),
  ('O_Domashnikov', 'domashnikov', 'odomashnikov@nomail.com', 'Олег', 'Домашников', '1978-01-11 10:00:00', FALSE);

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

INSERT INTO categories (dish_id, category) VALUES
  (100008, 'FOOD'),
  (100009, 'SALAD'),
  (100010, 'SOUP'),
  (100011, 'SWEET'),
  (100012, 'FOOD'),
  (100013, 'FOOD'),
  (100014, 'FOOD'),
  (100015, 'FOOD');

INSERT INTO restaurants (name, imageLink) VALUES
  ('Ривьера', 'http://restoranaltona.ru/images/vip%202/IMG_9280.jpg'),
  ('Гранд каньон', 'http://restoranaltona.ru/images/vip%202/IMG_9308.jpg'),
  ('Асеана', 'http://restoranaltona.ru/images/vip%202/IMG_9324.jpg'),
  ('У Ашота', 'http://restoranaltona.ru/images/vip%202/IMG_9337.jpg');

INSERT INTO menus (restaurant_id, created) VALUES
  (100016, now()),
  (100017, now()),
  (100018, now()),
  (100019, now());

INSERT INTO menu_dishes (dish_id, menu_id, price) VALUES
  (100008, 100020, 150.00),
  (100010, 100020, 180.00),
  (100014, 100020, 210.00),
  (100015, 100021, 100.00),
  (100013, 100021, 160.00),
  (100014, 100021, 190.00),
  (100012, 100022, 190.00),
  (100013, 100022, 300.00),
  (100014, 100022, 110.00),
  (100008, 100023, 290.00),
  (100011, 100023, 500.00),
  (100010, 100023, 100.00);

