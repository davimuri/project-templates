INSERT INTO role (id, name) VALUES (1, 'USER');
INSERT INTO role (id, name) VALUES (2, 'ADMINISTRATOR');

-- password=admin
INSERT INTO user(id, username, password, active, initial_date)
VALUES (1, 'adminuser', '21232f297a57a5a743894ae4a801fc3', 1, '2016-11-27');

INSERT INTO user_role (role_id, user_id) VALUES (2, 1);
INSERT INTO user_role (role_id, user_id) VALUES (1, 1);