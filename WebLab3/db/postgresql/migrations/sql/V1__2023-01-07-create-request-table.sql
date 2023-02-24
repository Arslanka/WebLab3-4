CREATE SCHEMA IF NOT EXISTS s335089;
CREATE TABLE IF NOT EXISTS s335089.request
(
    id           BIGINT PRIMARY KEY,
    x            DOUBLE PRECISION         NOT NULL,
    y            DOUBLE PRECISION         NOT NULL,
    r            DOUBLE PRECISION         NOT NULL,
    request_time TIMESTAMP WITH TIME ZONE NOT NULL,
    status       BOOLEAN                  NOT NULL
)