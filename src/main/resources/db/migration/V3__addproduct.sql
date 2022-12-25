CREATE TABLE if NOT EXISTS product (
    id SERIAL,
    description VARCHAR NOT NULL,
    brand VARCHAR (50) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id)
);