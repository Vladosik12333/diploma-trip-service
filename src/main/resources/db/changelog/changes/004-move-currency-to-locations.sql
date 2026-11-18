--liquibase formatted sql

--changeset diploma-trip-service:4-move-currency-to-locations

ALTER TABLE trips
    DROP COLUMN currency;

ALTER TABLE locations
    ADD COLUMN currency CHAR(3) NOT NULL;
