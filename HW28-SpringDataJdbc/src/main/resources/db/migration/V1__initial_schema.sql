CREATE TABLE client
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE address
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    address VARCHAR(124),
    client_id bigint REFERENCES client(id)
);

CREATE TABLE phone
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    number VARCHAR(50),
    client_id bigint REFERENCES client(id)
);

ALTER TABLE address ADD CONSTRAINT address_client_id_fk FOREIGN KEY (client_id) REFERENCES client;
ALTER TABLE phone ADD CONSTRAINT phone_client_id_fk FOREIGN KEY (client_id) REFERENCES client;