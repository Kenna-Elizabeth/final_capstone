BEGIN TRANSACTION;

INSERT INTO families (family_name) VALUES ('Addams');

INSERT INTO users (username,password_hash,role,family_id) VALUES ('parent','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_PARENT', 1);
INSERT INTO users (username,password_hash,role,family_id) VALUES ('child','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_CHILD', 1);

INSERT INTO books (family_id, isbn, title, author, cover_url, note) VALUES (1, '9781784877989', 'Kafka on the Shore', 'Haruki Murakami', 'https://covers.openlibrary.org/b/isbn/9781784877989-M.jpg', 'I liked this one!');
INSERT INTO books (family_id, isbn, title, author, cover_url, note) VALUES (1, '9780356500157', 'The Girl With All the Gifts', 'M.R. Carey', 'https://covers.openlibrary.org/b/isbn/9780356500157-M.jpg', 'Could be interesting');

INSERT INTO users_books (user_id, book_id, completed, recommended) VALUES (1, 1, true, false);
INSERT INTO users_books (user_id, book_id, completed, recommended) VALUES (1, 2, false, true);
INSERT INTO users_books (user_id, book_id, completed, recommended) VALUES (2, 1, false, false);
INSERT INTO users_books (user_id, book_id, completed, recommended) VALUES (2, 2, false, false);

INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (1, 1, 120, 'Paper', '');
INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (1, 1, 180, 'Paper', 'Couldn''t put it down!');
INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (2, 2, 60, 'Read-Aloud (Listener)', '');

COMMIT TRANSACTION;
