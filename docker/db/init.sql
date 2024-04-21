CREATE DATABASE oblac;
\c oblac;

CREATE TABLE IF NOT EXISTS books (
  book_id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  year INTEGER NOT NULL
);

INSERT INTO books (title, year) VALUES ('The Great Gatsby', 1925);
INSERT INTO books (title, year) VALUES ('The Catcher in the Rye', 1951);
INSERT INTO books (title, year) VALUES ('To Kill a Mockingbird', 1960);
INSERT INTO books (title, year) VALUES ('1984', 1949);
INSERT INTO books (title, year) VALUES ('The Hobbit', 1937);
INSERT INTO books (title, year) VALUES ('Fahrenheit 451', 1953);
INSERT INTO books (title, year) VALUES ('Pride and Prejudice', 1813);
INSERT INTO books (title, year) VALUES ('The Book Thief', 2005);

