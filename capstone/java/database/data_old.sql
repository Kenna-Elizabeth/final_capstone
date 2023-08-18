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
INSERT INTO prizes (family_id, prize_name, description) VALUES (1, 'Fresh Prize', 'Nobody has made progress on this one!');
INSERT INTO prizes (family_id, prize_name, description, for_parents) VALUES (1, 'Kid Prize', 'This prize is just for kids!', false);
INSERT INTO prizes (family_id, prize_name, description, start_date, end_date) VALUES (1, 'Future Prize', 'This prize isn''t active yet!', '2023-12-1', '2024-1-1');
INSERT INTO prizes (family_id, prize_name, description, start_date, end_date) VALUES (1, 'Past Prize', 'This prize is expired!', '2023-1-1', '2023-2-1');

INSERT INTO users_prizes (user_id, prize_id, progress_minutes, completed) VALUES (1, 1, 200, false);
INSERT INTO users_prizes (user_id, prize_id, progress_minutes, completed, completion_timestamp) VALUES (1, 2, 450, true, '2023-8-1');

COMMIT TRANSACTION;
