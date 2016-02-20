DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, mail, role, password, birth_date)
  VALUES ('Вася', 'user1@yandex.ru', 0, 'password1', '2016.01.28');
INSERT INTO users (name, mail, role, password, birth_date)
  VALUES ('Коля', 'user2@yandex.ru', 1, 'password2', '2016.01.28');
