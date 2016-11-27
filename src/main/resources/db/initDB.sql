DROP TABLE menu_dishes
IF EXISTS;
DROP TABLE dishes
IF EXISTS;
DROP TABLE menus
IF EXISTS;
DROP TABLE restaurants
IF EXISTS;
DROP TABLE user_roles
IF EXISTS;
DROP TABLE users
IF EXISTS;

DROP SEQUENCE global_seq
IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ
  AS INTEGER
    START WITH 100000;

CREATE TABLE users
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  login      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  enabled    BOOLEAN   DEFAULT TRUE,
  registered TIMESTAMP DEFAULT now()
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (login);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id        INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  imageLink VARCHAR(255) DEFAULT NULL,
  CONSTRAINT restaurants_unique_name_idx UNIQUE (name)
);

CREATE TABLE menus
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  restaurant_id INTEGER NOT NULL,
  dateOfMenu    TIMESTAMP DEFAULT now(),
  CONSTRAINT restaurant_id_created_idx UNIQUE (restaurant_id, dateOfMenu),
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id)
    ON DELETE CASCADE
);

CREATE TABLE dishes
(
  id        INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  imageLink VARCHAR(255) NOT NULL,
  CONSTRAINT dishes_name_idx UNIQUE (name)
);

CREATE TABLE menus_dishes
(
  id      INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  dish_id INTEGER NOT NULL,
  menu_id INTEGER NOT NULL,
  price   DOUBLE  NOT NULL,
  CONSTRAINT dish_id_menu_id_idx UNIQUE (dish_id, menu_id),
  FOREIGN KEY ( dish_id ) REFERENCES dishes (id)
    ON DELETE CASCADE,
  FOREIGN KEY ( menu_id ) REFERENCES menus (id)
    ON DELETE CASCADE
);