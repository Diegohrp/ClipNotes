CREATE TABLE tags(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(20)
);

CREATE TABLE notes_tags(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    note_id BIGSERIAL,
    tag_id BIGSERIAL,
    FOREIGN KEY (note_id) REFERENCES notes(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);