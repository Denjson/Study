--liquibase formatted sql

--changeset DenRod:1
--comment: just a test
CREATE TABLE card_info (
  id int NOT NULL AUTO_INCREMENT,
  user_id int,
  number int,
  holder varchar(15),
  expiration_date date,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
  ON DELETE CASCADE  -- Optional: action on parent row deletion
  ON UPDATE CASCADE  -- Optional: action on parent row update
) ;

CREATE TABLE card_info_seq (
  next_val int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (next_val)
) ;

INSERT INTO card_info_seq (next_val)
VALUES (1);

INSERT INTO card_info (user_id, number, holder, expiration_date)
VALUES (1, 222 ,"Max", "12-12-25");
