alter table products
    add created_at timestamp default current_timestamp,
    add update_at timestamp default current_timestamp;