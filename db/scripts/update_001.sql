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
