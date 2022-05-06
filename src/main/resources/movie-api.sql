use the_movies_db;

DROP table if exists ratings;
drop table if exists movie_genre;
drop table if exists movie_actor;
drop table if exists actors;
drop table if exists genres;
drop table if exists movies;
drop table if exists directors;

create table if not exists directors
(
    id   int unsigned not null auto_increment primary key,
    name varchar(120)
);

describe directors;


create table if not exists movies
(
    id          int unsigned not null auto_increment primary key,
    title       varchar(255),
    year        char(4),
    plot        text,
    poster      text,
    rating      char(1),
    director_id int unsigned not null,
    foreign key (director_id) references directors (id)
);

describe movies;


create table if not exists genres
(
    id   int unsigned not null auto_increment primary key,
    name varchar(120)
);

describe genres;


create table if not exists movie_genre
(
    movie_id int unsigned not null,
    genre_id int unsigned not null,
    foreign key (movie_id) references movies (id),
    foreign key (genre_id) references genres (id)

);

describe movie_genre;

create table if not exists actors
(
    id   int unsigned not null auto_increment primary key,
    name varchar(255)
);

describe actors;

create table if not exists movie_actor
(
    movie_id int unsigned not null,
    actor_id int unsigned not null,
    foreign key (movie_id) references movies (id),
    foreign key (actor_id) references actors (id)
);
describe movie_actor;


CREATE TABLE ratings
(
    id     SERIAL PRIMARY KEY,
    rating TEXT
);

INSERT INTO ratings (rating) VALUES ('G');
INSERT INTO ratings (rating) VALUES ('PG');
INSERT INTO ratings (rating) VALUES ('PG-13');
INSERT INTO ratings (rating) VALUES ('R');

ALTER TABLE movies ADD COLUMN rating_id INTEGER NULL REFERENCES ratings (id);

UPDATE movies SET rating_id = 1 WHERE id in (1,2,3);
UPDATE movies SET rating_id = 2 WHERE id in (4,5,6);
UPDATE movies SET rating_id = 3 WHERE id in (7,8,9);

describe ratings;
