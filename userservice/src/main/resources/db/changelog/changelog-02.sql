--liquibase formatted sql

--changeset DenRod:1
--comment: just a test
CREATE TABLE one_test (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  surname varchar(20),
  birth_date date,
  email varchar(20),
  PRIMARY KEY (id)
) ;