CREATE TABLE PUBLIC.persons
(
    id          SERIAL PRIMARY KEY,
    "firstName" VARCHAR,
    "lastName"  VARCHAR
);

INSERT INTO persons VALUES (DEFAULT, 'Ray', 'Neyra');

commit;