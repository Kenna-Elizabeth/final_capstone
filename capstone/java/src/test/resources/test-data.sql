BEGIN TRANSACTION;

INSERT INTO families (family_name) VALUES ('');

INSERT INTO users (username,password_hash,role,family_id) VALUES ('user1','user1','ROLE_USER',1);
INSERT INTO users (username,password_hash,role,family_id) VALUES ('user2','user2','ROLE_USER',1);
INSERT INTO users (username,password_hash,role,family_id) VALUES ('user3','user3','ROLE_USER',1);

COMMIT TRANSACTION;
