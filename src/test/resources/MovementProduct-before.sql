ALTER TABLE movement_movement_products DROP IF EXISTS movement_products_id;

DELETE FROM movement_products;

INSERT INTO movement_products (id, product_id, amount, price) VALUES
(1, 1, 11.00, 12.00),
(2, 2, 13.00, 14.00),
(3, 3, 15.00, 16.00),
(4, 4, 17.00, 18.00);

SELECT setval('movement_products_id_seq', max(id)) FROM movement_products;
