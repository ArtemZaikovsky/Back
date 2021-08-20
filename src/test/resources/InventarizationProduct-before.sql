ALTER TABLE inventarizations_inventarization_products DROP IF EXISTS inventarization_products_id;

DELETE FROM inventarization_products;

INSERT INTO inventarization_products (id, actual_amount, price, product_id)
VALUES (1, 11, 111, 1),
       (2, 22, 222, 2),
       (3, 33, 333, 3);

SELECT setval('inventarization_products_id_seq', max(id))
FROM inventarization_products;

