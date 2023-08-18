BEGIN TRANSACTION;

INSERT INTO families (family_name) VALUES ('Addams');

INSERT INTO users (username,password_hash,role,family_id) VALUES ('demoparent','$2a$10$JuIud.XaK6tcptqMnuvdiO2fPz/2esymJm4eJajbSwgCOIblp0msm','ROLE_PARENT', 1);
INSERT INTO users (username,password_hash,role,family_id) VALUES ('demochild','$2a$10$JuIud.XaK6tcptqMnuvdiO2fPz/2esymJm4eJajbSwgCOIblp0msm','ROLE_CHILD', 1);

INSERT INTO books (family_id, isbn, title, author) VALUES (1, '9780684830490', 'The Old Man and the Sea', 'Ernest Hemingway');
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9783125971400', 'Le Petit Prince', 'Antoine de Saint-Exupéry', true);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9780375434549', 'Origin', 'Dan Brown', false);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9780760796801', 'The little engine that could', 'Watty Piper', true);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9781592231515', 'Hiding in the Woods', 'Maurice Pledger', true);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9780007455935', 'The Lorax', 'Dr. Seuss', true);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9780064410939', 'Charlotte''s Web', 'E.B. White', true);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9780671432416', 'The Hitchhiker''s Guide to the Galaxy', 'Douglas Adams', false);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9780458920303', 'The Hobbit', 'J.R.R. Tolkien', true);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '0618126988', 'The Silmarillion', 'J.R.R. Tolkien', true);
INSERT INTO books (family_id, isbn, title, author, for_children) VALUES (1, '9783540256458', 'Quantum Physics', 'Florian Scheck', false);

INSERT INTO users_books (user_id, book_id, completed) VALUES (1, 2, true);

INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (1, 1, 90, 'Paper', '', '2023-8-12');
INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (1, 1, 180, 'Paper', 'Couldn''t put it down!', '2023-8-13');
INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (1, 3, 60, 'Audiobook', '', '2023-8-10');
INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (1, 3, 30, 'Audiobook', '', '2023-8-14');
INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (1, 2, 240, 'Paper', 'Must share this with the kids', '2023-8-2');
INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (2, 2, 60, 'Read-Aloud (Listener)', '', '2023-8-9');
INSERT INTO sessions (user_id, book_id, minutes, format, note, start_date_time) VALUES (2, 4, 25, 'Paper', '', '2023-8-14');

INSERT INTO prizes (family_id, prize_name, description, start_date, end_date) VALUES (1, 'Choose What''s For Dinner', 'Read for ten hours!', '2023-8-17', '2023-8-31');
INSERT INTO prizes (family_id, prize_name, description, for_parents, start_date, end_date, max_prizes, milestone) VALUES (1, 'Sticker Pack', 'This prize is just for kids!', false, '2023-8-15', '2023-8-31', 10, 60);
INSERT INTO prizes (family_id, prize_name, description, start_date, end_date) VALUES (1, 'Zoo Trip', 'This prize isn''t active yet!', '2023-12-1', '2024-1-1');
INSERT INTO prizes (family_id, prize_name, description, start_date, end_date, milestone) VALUES (1, 'Pick the Movie Night', 'This is an old prize!', '2023-8-1', '2023-8-8', 300);
INSERT INTO prizes (family_id, prize_name, description, start_date, end_date) VALUES (1, 'Double Dessert', 'This prize is expired!', '2023-7-14', '2023-7-22');
INSERT INTO prizes (family_id, prize_name, description, for_children, start_date, end_date, milestone) VALUES (1, 'Date Night', 'This prize is for the parents!', false, '2023-8-16', '2023-9-1', 900);

INSERT INTO users_prizes (user_id, prize_id, progress_minutes, completed) VALUES (1, 1, 240, false);
INSERT INTO users_prizes (user_id, prize_id, progress_minutes, completed, completion_timestamp) VALUES (1, 4, 300, true, '2023-8-2');

COMMIT TRANSACTION;
