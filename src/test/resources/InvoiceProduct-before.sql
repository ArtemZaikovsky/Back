ALTER TABLE invoice_product DROP IF EXISTS invoice_product_id;

DELETE FROM invoice_product;


INSERT INTO invoice_product (id,amount,price,invoice_id,product_id)
VALUES (94, 1, 1, 1,1),
       (95, 2, 2, 1,2),
       (96, 3, 3, 1,3),
       (97, 4, 4, 1,4),
       (98, 5, 5, 1,5);

SELECT setval('invoice_product_id_seq', max(id))
FROM invoice_product;