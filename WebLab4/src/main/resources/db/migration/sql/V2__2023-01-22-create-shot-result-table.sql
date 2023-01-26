CREATE TYPE s335089.shot_result_status AS ENUM ('HIT', 'MISS');

CREATE TABLE IF NOT EXISTS s335089.shot_result
(
    id           SERIAL PRIMARY KEY,
    owner_id     INT                        NOT NULL,
    x            DOUBLE PRECISION           NOT NULL,
    y            DOUBLE PRECISION           NOT NULL,
    r            DOUBLE PRECISION           NOT NULL,
    request_time TIMESTAMP WITH TIME ZONE   NOT NULL,
    status       s335089.shot_result_status NOT NULL,

    CONSTRAINT fk_user_account
        FOREIGN KEY (owner_id)
            REFERENCES s335089.user_account (id)
)