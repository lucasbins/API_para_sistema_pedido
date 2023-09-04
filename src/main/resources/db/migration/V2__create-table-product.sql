CREATE TABLE product (
    id INTEGER NOT NULL PRIMARY KEY UNIQUE,
    name TEXT NOT NULL,
    price NUMERIC NOT NULL,
    description TEXT
);