CREATE TABLE if NOT EXISTS invoice (
    id SERIAL,
    asistente_id INT NOT NULL,
    code varchar (50) NOT NULL,
    create_at VARCHAR (100) NOT NULL,
    total Float NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (asistente_id)
    REFERENCES asistente(id)
);