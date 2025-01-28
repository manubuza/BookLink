-- Flyway Migration Script: Create Schemas for BookLink

-- Create User Table
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    location VARCHAR(255)
);

-- Create Book Table
CREATE TABLE books
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    author       VARCHAR(255) NOT NULL,
    isbn         VARCHAR(13),
    genre        VARCHAR(100),
    condition    VARCHAR(50),
    availability VARCHAR(50)  NOT NULL,
    owner_id     INT          NOT NULL REFERENCES users (id) ON DELETE CASCADE
);

-- Create Transactions Table
CREATE TABLE transactions
(
    id               SERIAL PRIMARY KEY,
    book_id          INT         NOT NULL REFERENCES books (id) ON DELETE CASCADE,
    requester_id     INT         NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    owner_id         INT         NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    type             VARCHAR(50) NOT NULL,
    status           VARCHAR(50) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount           DECIMAL(10, 2)
);

-- Create Wishlist Table
CREATE TABLE wishlists
(
    id         SERIAL PRIMARY KEY,
    user_id    INT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    book_id    INT NOT NULL REFERENCES books (id) ON DELETE CASCADE,
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Reviews Table
CREATE TABLE reviews
(
    id             SERIAL PRIMARY KEY,
    transaction_id INT NOT NULL REFERENCES transactions (id) ON DELETE CASCADE,
    rating         INT CHECK (rating >= 1 AND rating <= 5),
    comment        TEXT,
    reviewer_id    INT NOT NULL REFERENCES users (id) ON DELETE CASCADE
);

-- Create Categories Table
CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Add Genre to Books Table as a Foreign Key
ALTER TABLE books
    ADD COLUMN category_id INT,
ADD CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL;
