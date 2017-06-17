CREATE DATABASE wharu;

CREATE USER 'wharu'@'localhost' IDENTIFIED BY 'wharu';

GRANT ALL ON *.* TO 'wharu'@'localhost';

CREATE TABLE IF NOT EXISTS user (
  id              BIGINT UNSIGNED    NOT NULL PRIMARY KEY AUTO_INCREMENT,
  create_time     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mod_time        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status          INT             NOT NULL,
  email           VARCHAR(256)    NOT NULL UNIQUE,
  display_name    VARCHAR(256)    NOT NULL UNIQUE,
  validation_id   VARCHAR(256)    NOT NULL,
  password_salt   VARCHAR(256)    NOT NULL,
  password_md5    VARCHAR(128)    NOT NULL
); 

INSERT INTO user (create_time, mod_time, status, email, display_name, validation_id, password_salt, password_md5)
VALUES (now(), now(), 0, 'mcdaigle1@gmail.com', 'Doog', '1234', 'wharu', '4fb5584245edbb40aa67eed4ebc42fac');

INSERT INTO user (create_time, mod_time, status, email, display_name, validation_id, password_salt, password_md5)
VALUES (now(), now(), 0, 'fooster@domain.com', 'Fooster', '1234', 'wharu', '4fb5584245edbb40aa67eed4ebc42fac');

CREATE TABLE IF NOT EXISTS event (
  id              BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  create_time     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mod_time        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status          INT             NOT NULL,
  owner_id        BIGINT UNSIGNED NOT NULL,
  name            VARCHAR(256)    NOT NULL,
  description     VARCHAR(1024)   NOT NULL DEFAULT '',
  start_time      TIMESTAMP       NULL,
  end_time        TIMESTAMP       NULL
); 

INSERT INTO event (create_time, mod_time, status, owner_id, name, description)
VALUES (now(), now(), 0, 1, 'Test Event', 'Test event for development purposes');

CREATE TABLE IF NOT EXISTS user_event (
  id              BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  create_time     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mod_time        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status          INT             NOT NULL,
  user_id         BIGINT UNSIGNED NOT NULL,
  event_id        BIGINT UNSIGNED NOT NULL,
  color           VARCHAR(128)    NULL
); 

INSERT INTO user_event (create_time, mod_time, status, user_id, event_id, color)
VALUES (now(), now(), 1, 1, 1, 'FF0000');

INSERT INTO user_event (create_time, mod_time, status, user_id, event_id, color)
VALUES (now(), now(), 1, 2, 1, 'FF0000');

CREATE TABLE IF NOT EXISTS map_coordinate (
  id              BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  create_time     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mod_time        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status          INT             NOT NULL,
  user_event_id   BIGINT UNSIGNED NOT NULL,
  name            VARCHAR(256)    NOT NULL,
  description     VARCHAR(1024)   NOT NULL DEFAULT '',
  arrival_time    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  latest_time     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  latitude        DOUBLE          NOT NULL,
  longitude       DOUBLE          NOT NULL
); 

INSERT INTO map_coordinate (create_time, mod_time, status, user_event_id, name, description, arrival_time, latest_time, latitude, longitude)
VALUES (now(), now(), 0, 1, 'Jaymes Joyce', 'Whoo hoo, James Joyce is jumping', now(), now(), 34.417060, -119.696138);

INSERT INTO map_coordinate (create_time, mod_time, status, user_event_id, name, description, arrival_time, latest_time, latitude, longitude)
VALUES (now(), now(), 0, 1, 'Joes', 'Joes is a little dead', now(), now(), 34.417764, -119.696577);

INSERT INTO map_coordinate (create_time, mod_time, status, user_event_id, name, description, arrival_time, latest_time, latitude, longitude)
VALUES (now(), now(), 0, 1, 'Seven', 'Sevens service blows', now(), now(), 34.415215, -119.691784);

INSERT INTO map_coordinate (create_time, mod_time, status, user_event_id, name, description, arrival_time, latest_time, latitude, longitude)
VALUES (now(), now(), 0, 2, 'Ralphs', 'I stopped at the grocery store', now(), now(), 34.420000, -119.704042);

INSERT INTO map_coordinate (create_time, mod_time, status, user_event_id, name, description, arrival_time, latest_time, latitude, longitude)
VALUES (now(), now(), 0, 2, 'Persona', 'I got pizza', now(), now(), 34.420642, -119.701054);

INSERT INTO map_coordinate (create_time, mod_time, status, user_event_id, name, description, arrival_time, latest_time, latitude, longitude)
VALUES (now(), now(), 0, 2, 'Wildcat', 'Now for some fun!', now(), now(), 34.418142, -119.698340);


