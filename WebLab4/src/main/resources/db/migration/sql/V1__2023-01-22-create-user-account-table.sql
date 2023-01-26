CREATE SCHEMA IF NOT EXISTS s335089;

CREATE TABLE IF NOT EXISTS s335089.user_account
(
    id            SERIAL PRIMARY KEY,
    login         VARCHAR(64) NOT NULL UNIQUE,
    password_hash BYTEA       NOT NULL
);