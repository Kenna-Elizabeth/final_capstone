BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;


CREATE TABLE families (
    family_id SERIAL,
    name varchar(50) DEFAULT '',
    CONSTRAINT PK_families PRIMARY KEY (family_id)
);


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	family_id int NOT NULL,
	CONSTRAINT PK_users PRIMARY KEY (user_id),
	CONSTRAINT FK_users_families FOREIGN KEY (family_id) REFERENCES families (family_id)
);

CREATE TABLE books (
    book_id SERIAL,
    isbn int,
    cover_url varchar(100),
    author varchar(100),
    title varchar(100),
    recommended boolean DEFAULT false,
    CONSTRAINT PK_books PRIMARY KEY (book_id)
);

CREATE TABLE sessions (
    session_id SERIAL,
    user_id int NOT NULL,
    book_id int NOT NULL,
    minutes int DEFAULT 0,
    start_time_date timestamp DEFAULT CURRENT_TIMESTAMP(0),
    format varchar(50),
    note text,
    CONSTRAINT FK_sessions_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_sessions_books FOREIGN KEY (book_id) REFERENCES books (book_id)
);

CREATE TABLE users_books (
    user_id int NOT NULL,
    book_id int NOT NULL,
    completed boolean DEFAULT false,
    recommended boolean DEFAULT false,
    CONSTRAINT FK_users_books_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_users_books_books FOREIGN KEY (book_id) REFERENCES books (book_id)
);

COMMIT TRANSACTION;
