BEGIN TRANSACTION;

INSERT INTO families (family_name) VALUES ('Addams');

INSERT INTO users (username,password_hash,role,family_id) VALUES ('parent','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_PARENT', 1);
INSERT INTO users (username,password_hash,role,family_id) VALUES ('child','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_CHILD', 1);

INSERT INTO books (family_id, isbn, title, author, cover_url, note, for_children) VALUES (1, '9781784877989', 'Kafka on the Shore', 'Haruki Murakami', 'https://covers.openlibrary.org/b/isbn/9781784877989-M.jpg', 'I liked this one!', false);
INSERT INTO books (family_id, isbn, title, author, cover_url, note) VALUES (1, '9780356500157', 'The Girl With All the Gifts', 'M.R. Carey', 'https://covers.openlibrary.org/b/isbn/9780356500157-M.jpg', 'Could be interesting');
INSERT INTO books (family_id, isbn, title, author, cover_url, note) VALUES (1, '9780684830490', 'The Old Man and the Sea', 'Ernest Hemingway', 'https://covers.openlibrary.org/b/isbn/9780684830490-M.jpg', '');
INSERT INTO books (family_id, isbn, title, author, cover_url, note) VALUES (1, '9783125971400', 'Le Petit Prince', 'Antoine de Saint-Exupéry', 'https://covers.openlibrary.org/b/isbn/9783125971400-M.jpg', '');

INSERT INTO users_books (user_id, book_id, completed, recommended) VALUES (1, 1, true, false);
INSERT INTO users_books (user_id, book_id, completed, recommended) VALUES (1, 2, false, false);

INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (1, 1, 120, 'Paper', '');
INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (1, 1, 180, 'Paper', 'Couldn''t put it down!');
INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (1, 3, 60, 'Audiobook', '');
INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (1, 3, 30, 'Audiobook', '');
INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (1, 4, 60, 'Paper', '', '2023-8-1');
INSERT INTO sessions (user_id, book_id, minutes, format, note) VALUES (2, 2, 60, 'Read-Aloud (Listener)', '');

INSERT INTO prizes (family_id, prize_name, description) VALUES (1, 'Example Prize', 'Read for ten hours in two weeks!');
INSERT INTO prizes (family_id, prize_name, milestone, description) VALUES (1, 'Another Prize', 300, 'User parent has completed this one!');

INSERT INTO users_prizes (user_id, prize_id, completed) VALUES (1, 2, true);

COMMIT TRANSACTION;
