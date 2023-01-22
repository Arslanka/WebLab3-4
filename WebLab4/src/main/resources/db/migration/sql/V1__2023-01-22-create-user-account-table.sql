CREATE SCHEMA IF NOT EXISTS s335089;

CREATE SEQUENCE IF NOT EXISTS s335089.seq_user_account_id
    START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS s335089.user_account (
    id              BIGINT          PRIMARY KEY DEFAULT nextval('s335089.seq_user_account_id'),
    login           VARCHAR(64)     NOT NULL UNIQUE,
    password_hash   BYTEA           NOT NULL
);