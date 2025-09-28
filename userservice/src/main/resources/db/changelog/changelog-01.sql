--liquibase formatted sql

--changeset DenRod:1
--comment: just a test
CREATE TABLE users_test (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(20),
  birth_date date,
  email varchar(20),
  PRIMARY KEY (id)
) ;

--changeset DenRod:2
CREATE TABLE users_test_seq (
  next_val int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (next_val)
) ;

INSERT INTO users_test_seq (next_val)
VALUES (1);

INSERT INTO users_test (name, surname, birth_date, email)
VALUES ("XXX","ManX","12-12-25","come@with.me");

--changeset DenRod:3
INSERT INTO users_test (name, surname, birth_date, email)
VALUES ("YYY","WomanY","11-11-22","mail@me.com");