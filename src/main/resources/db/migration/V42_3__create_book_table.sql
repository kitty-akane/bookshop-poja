CREATE TABLE IF NOT EXISTS book (
    book_id BIGINT PRIMARY KEY,
    title_book VARCHAR NOT NULL,
    isbn VARCHAR NOT NULL UNIQUE,
    publication_date DATE NOT NULL,
    author VARCHAR NOT NULL,
    publisher VARCHAR NOT NULL,
    language VARCHAR NOT NULL,
    category VARCHAR NOT NULL,
    description VARCHAR NOT NULL
);
