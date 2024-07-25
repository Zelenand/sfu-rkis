CREATE DATABASE postgres;
\c postgres;
CREATE TABLE bag
(
    id       integer generated always as identity
        constraint key_name
            primary key,
    brand    text,
    cost     integer,
    material text,
    type     text,
    volume   numeric
);