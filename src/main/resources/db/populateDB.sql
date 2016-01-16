DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, mail, role, password)
VALUES ('Вася', 'user1@yandex.ru', 1, 'password1');
INSERT INTO users (name, mail, role, password)
VALUES ('Коля', 'user2@yandex.ru', 2, 'password2')
