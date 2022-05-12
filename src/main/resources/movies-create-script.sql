CREATE DATABASE IF NOT EXISTS movies_db;
USE movies_db;

DROP TABLE IF EXISTS movie_genre;
DROP TABLE IF EXISTS movie_actor;
DROP TABLE IF EXISTS actors;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS directors;

CREATE TABLE IF NOT EXISTS directors
(
    id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(120) NOT NULL,
    #INDEX movie_id (name),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movies
(
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title       VARCHAR(120) NOT NULL,
    rating      CHAR(1),
    poster      TEXT,
    year        CHAR(4)      NOT NULL,
    genre       VARCHAR(120) NOT NULL,
    plot        CHAR(255)    NOT NULL,
    actors      VARCHAR(255) NOT NULL,
    director_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (director_id)
        REFERENCES directors (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS genres
(
    id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movie_genre
(
    movie_id INT UNSIGNED NOT NULL,
    genre_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);

CREATE TABLE IF NOT EXISTS actors
(
    id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movie_actor
(
    movie_id INT UNSIGNED NOT NULL,
    actor_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies (id),
    FOREIGN KEY (actor_id) REFERENCES actors (id)
);

DESCRIBE movie_actor;

SELECT *
FROM movies m
WHERE CAST(m.year AS UNSIGNED) >= 1977
  AND CAST(m.year AS UNSIGNED) <= 1980;

INSERT INTO genres (name)
VALUES ('comdey'),
       ('drama'),
       ('action'),
       ('fantasy'),
       ('horror'),
       ('romance'),
       ('hallmark romance'),
       ('thriller');