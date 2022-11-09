CREATE TABLE if NOT EXISTS asistente (
    id SERIAL,
    nombres VARCHAR (100) NOT NULL,
    email varchar (50) NOT NULL,
    institucion VARCHAR (100) NOT NULL,
    cargo VARCHAR (20) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)

);