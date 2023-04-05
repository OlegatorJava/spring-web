create table if not exists products (id bigserial primary key, title varchar(255), cost int);

insert into products (title, cost)
values
    ('Banana', 100),
    ('Milk', 80),
    ('Coffee', 90);