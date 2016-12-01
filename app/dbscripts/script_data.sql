
-- password=admin
INSERT INTO user(id, username, password, active, initial_date)
VALUES (1, 'adminuser', '21232f297a57a5a743894ae4a801fc3', 1, '2016-11-27');

INSERT INTO user_role (user_id, role) VALUES (1, 'USER');
INSERT INTO user_role (user_id, role) VALUES (1, 'ADMINISTRATOR');