CREATE SEQUENCE bookcopy_seq START 1 INCREMENT 50;

CREATE TABLE bookcopy (
    book_copy_id BIGSERIAL PRIMARY KEY,
    barcode VARCHAR NOT NULL UNIQUE,
    status VARCHAR NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    format VARCHAR NOT NULL,
    acquired_date DATE NOT NULL,
    book_id BIGINT NOT NULL,
    CONSTRAINT fk_bookcopy_book FOREIGN KEY (book_id) REFERENCES book (book_id)
);