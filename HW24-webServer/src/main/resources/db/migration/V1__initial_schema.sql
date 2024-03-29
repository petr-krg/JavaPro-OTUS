-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);
*/

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence client_SEQ start with 1 increment by 1;
create sequence address_SEQ start with 1 increment by 1;
create sequence phones_SEQ start with 1 increment by 1;

CREATE TABLE client(
    id   bigint NOT NULL,
    name VARCHAR(255),
    login VARCHAR(50),
    password VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE address(
    id bigint generated BY DEFAULT AS IDENTITY,
    street VARCHAR(255) NOT NULL,
    client_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE phones(
    id bigint generated BY DEFAULT AS IDENTITY,
    phone VARCHAR(255) NOT NULL,
    client_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);
