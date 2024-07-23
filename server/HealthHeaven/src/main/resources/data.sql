INSERT INTO health.roles VALUES (1, 'USER'), (2, 'ADMIN');

INSERT INTO health.users(`id`, `email`, `first_name`, `last_name`, `password`)
VALUES
    (1, 'palex@mail.bg', 'Viki', 'Uzunov',1111);

INSERT INTO health.users_roles (`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2);