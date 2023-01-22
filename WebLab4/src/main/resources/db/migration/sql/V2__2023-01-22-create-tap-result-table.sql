CREATE SEQUENCE IF NOT EXISTS s335089.seq_tap_id
    START 1 INCREMENT 1;

CREATE TYPE s335089.tap_result_status AS ENUM ('HIT', 'MISS');

CREATE TABLE IF NOT EXISTS s335089.tap_result
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('s335089.seq_tap_id'),
    owner_id     BIGINT                    NOT NULL,
    x            DOUBLE PRECISION          NOT NULL,
    y            DOUBLE PRECISION          NOT NULL,
    r            DOUBLE PRECISION          NOT NULL,
    request_time TIMESTAMP WITH TIME ZONE  NOT NULL,
    status       s335089.tap_result_status NOT NULL,

    CONSTRAINT fk_user_account
    FOREIGN KEY (owner_id)
    REFERENCES s335089.user_account (id)

)