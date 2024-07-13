CREATE TABLE notes (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    user_id BIGSERIAL NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);