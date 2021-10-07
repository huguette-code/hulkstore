#admin (https://bcrypt-generator.com/)
INSERT INTO hs_user (id, date_register, email, password, name, state, surname, username, address)
VALUES (1, curdate(), 'admin@mail.com','$2a$12$BUSUreqdQBPEdmRaRkML9.gFYzuPGsmc6hkWaHX4DLVYWpdkiaLQq', 'NAdmin', 1, 'LAdmin', 'admin123','lejos 123');

#pass
INSERT INTO hs_user (id, date_register, email, password, name, state, surname, username, address)
VALUES (2, curdate(), 'user1@mail.com','$2a$12$/5c6Dv8fw8MBnlPrExfF4eVZNply6suI.JGV7rqvhNIHKk04KCpKm', 'Pedro', 1, 'Perez', 'peter123','pp 123');

INSERT INTO hs_user (id, date_register, email, password, name, state, surname, username, address)
VALUES (3, curdate(), 'user2@mail.com','$2a$12$/5c6Dv8fw8MBnlPrExfF4eVZNply6suI.JGV7rqvhNIHKk04KCpKm', 'Jose', 1, 'Garcia', 'pepe123','jc 123');

INSERT INTO hs_role (id_rol, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO hs_role (id_rol, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_role (id, id_rol) VALUES (1, 1);
INSERT INTO user_role (id, id_rol) VALUES (1, 2);
INSERT INTO user_role (id, id_rol) VALUES (2, 2);
INSERT INTO user_role (id, id_rol) VALUES (3, 2);