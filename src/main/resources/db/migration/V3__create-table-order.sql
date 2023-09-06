CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    orderNumber INTEGER UNIQUE,
    costumer TEXT,
    amount NUMERIC,
    status TEXT,
    orderDate DATE
);