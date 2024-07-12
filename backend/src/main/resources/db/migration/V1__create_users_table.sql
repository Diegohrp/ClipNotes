CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE ,
    email VARCHAR(50) NOT NULL UNIQUE ,
    password VARCHAR(100) NOT NULL
);

INSERT INTO users (name,lastName,username,email,password)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123');
