BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,family) VALUES ('parent','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_PARENT', 0);
INSERT INTO users (username,password_hash,role,family) VALUES ('child','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_CHILD', 1);

COMMIT TRANSACTION;
