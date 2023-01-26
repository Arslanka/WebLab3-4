CREATE TABLE IF NOT EXISTS s335089.access_token
(
    user_id    INTEGER PRIMARY KEY,
    token      VARCHAR(32) NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    ttl        INTERVAL    NOT NULL,

    CONSTRAINT fk_user_account
        FOREIGN KEY (user_id)
            REFERENCES s335089.user_account (id)
)