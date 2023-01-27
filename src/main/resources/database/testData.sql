    CREATE TABLE if not exists public.purchases (
                                                    id serial not null ,
                                                    age int2,
                                                    name varchar(30),
                                                    purchaseitem varchar(20),
                                                    lastname varchar(30),
                                                    count int2,
                                                    amount numeric (20, 2),
                                                    purchasedate date,
                                                    primary key (id)
    );

    CREATE TABLE if not exists public.items (
                                                    id serial not null ,
                                                    item_name varchar(20),
                                                    primary key (id)
    );

    INSERT INTO public.items (item_name) VALUES ('Телевизор');
    INSERT INTO public.items (item_name) VALUES ('Смартфон');
    INSERT INTO public.items (item_name) VALUES ('Соковыжималка');
    INSERT INTO public.items (item_name) VALUES ('Наушники');
    INSERT INTO public.items (item_name) VALUES ('Клавиатура');

    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (33, 'Клиент1', 'Телевизор', 'ФИО1', 2, 30000, '2023-01-24');
    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (33, 'Клиент1', 'Наушники', 'ФИО1', 6, 20000, '2022-11-22');
    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (33, 'Клиент1', 'Соковыжималка', 'ФИО1', 2, 15000, '2022-10-15');
    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (33, 'Клиент1', 'Клавиатура', 'ФИО1', 2, 15000, '2022-10-15');

    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (18, 'Клиент2', 'Смартфон', 'ФИО2', 2, 30000, '2023-01-24');
    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (18, 'Клиент2', 'Телевизор', 'ФИО2', 2, 60000, '2023-01-21');
    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (18, 'Клиент2', 'Смартфон', 'ФИО2', 2, 30000, '2023-01-22');
    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (18, 'Клиент2', 'Телевизор', 'ФИО2', 2, 60000, '2022-06-23');

    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (18, 'Клиент3', 'Телевизор', 'ФИО3', 2, 60000, '2022-12-31');

    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (35, 'Клиент4', 'Соковыжималка', 'ФИО4', 1, 2000, '2023-01-05');
    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (35, 'Клиент4', 'Клавиатура', 'ФИО4', 1, 5000, '2023-01-09');

    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (30, 'Клиент5', 'Соковыжималка', 'ФИО5', 2, 10000, '2023-01-03');

    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES (35, 'Клиент6', 'Соковыжималка', 'ФИО6', 1, 5000, '2023-01-02');

    INSERT INTO public.purchases (age, name, purchaseitem, lastname, count, amount, purchasedate) VALUES ( 33, 'Клиент7', 'Телевизор', 'ФИО7', 1, 30000, '2023-01-20');


