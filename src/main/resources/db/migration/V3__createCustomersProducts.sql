create table if not exists customers_products (product_id bigint, customers_id bigint, FOREIGN KEY (product_id) references products(id) , foreign key (customers_id) references customers(id));

insert into customers_products (product_id, customers_id)
values
    (1,1),
    (3,2),
    (4,3),
    (1,3),
    (2,2),
    (1,2);