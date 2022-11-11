drop table users cascade;
create table users
(
    id            bigserial primary key,
    nickname      varchar,
    email         varchar,
    sex           boolean,
    weight        real,
    height        real,
    date_of_birth timestamp with time zone,
    password      varchar
);
create table recipe
(
    id            bigserial primary key,
    name          varchar,
    calories      real,
    proteins      real,
    fat           real,
    carbohydrates real
);

create table statistic
(
    id            bigserial primary key,
    calorie       real,
    fat           real,
    carb          real,
    proteins      real,
    last_modified timestamp with time zone
);
create table custom_recipes
(
    id            bigserial primary key,
    name          varchar,
    calories      real,
    proteins      real,
    fat           real,
    carbohydrates real
);

create table user_recipes
(
    id        bigserial primary key,
    user_id   int references users (id),
    recipe_id int references custom_recipes (id)
);
--O2M --
create table user_statistic
(
    id           bigserial primary key,
    user_id      int references users (id),
    statistic_id int references statistic (id)
);
--M2M --

create table preferred_recipes
(
    id        bigserial primary key,
    user_id   int references users (id),
    recipe_id int references recipe (id),
    unique (user_id, recipe_id)
);