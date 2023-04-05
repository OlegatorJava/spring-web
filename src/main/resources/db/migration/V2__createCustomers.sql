create table if not exists customers (id bigserial primary key, name varchar(255)) ;

insert into customers (name)
values
    ('Bob'),
    ('Mike'),
    ('John');