--liquibase formatted sql

--changeset DenRod:1
--comment: just a test
CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(20),
  birth_date date,
  email varchar(20),
  PRIMARY KEY (id)
) ;

CREATE TABLE users_seq (
  next_val int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (next_val)
) ;

INSERT INTO users_seq (next_val)
VALUES (1);

INSERT INTO users (name, surname, birth_date, email)
VALUES ("XXX","ManX","12-12-25","come@with.me");
