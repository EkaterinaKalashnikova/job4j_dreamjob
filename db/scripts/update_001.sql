CREATE TABLE if not exists post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP default current_timestamp,
   visible BOOLEAN default false,
   city_id Integer
);

CREATE TABLE if not exists candidate (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP default current_timestamp,
   visible BOOLEAN default false,
   photo bytea
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email varchar(300),
    password varchar(300),
    created TIMESTAMP default current_timestamp
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email)

