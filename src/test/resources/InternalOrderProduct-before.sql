TRUNCATE internal_order_products CASCADE;

INSERT INTO internal_order_products (id, amount, price, product_id)
VALUES (1, 2, 3, 4),
       (2, 3, 4, 5),
       (3, 4, 5, 6),
       (4, 6, 7, 8),
       (5, 7, 8, 9);

SELECT setval('internal_order_products_id_seq', max(id))
FROM internal_order_products;