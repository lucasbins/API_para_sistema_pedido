CREATE TABLE orders (
    id INTEGER NOT NULL PRIMARY KEY UNIQUE,
    orderNumber BIGINT UNIQUE,
    costumer TEXT,
    amount NUMERIC,
    status TEXT,
    orderDate DATE
);