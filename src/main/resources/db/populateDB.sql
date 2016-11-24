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