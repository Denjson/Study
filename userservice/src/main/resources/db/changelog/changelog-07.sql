--liquibase formatted sql

--changeset DenRod:1
--comment: just a test
CREATE INDEX in1 ON users(id);
--changeset DenRod:2
--comment: just a test
CREATE INDEX in1 ON card_info(number);