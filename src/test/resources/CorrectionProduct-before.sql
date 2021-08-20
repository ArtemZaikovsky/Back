ALTER TABLE corrections_correction_products DROP IF EXISTS correction_products_id;

DELETE FROM correction_products;

INSERT INTO correction_products (id, product_id, amount, price) VALUES
(1, 1, 11.00, 12.00),
(2, 2, 13.00, 14.00),
(3, 3, 15.00, 16.00),
(4, 4, 17.00, 18.00);

SELECT setval('correction_products_id_seq', max(id)) FROM correction_products;
