DELETE FROM corrections_correction_products;
DELETE FROM corrections;

INSERT INTO corrections (id, comment, date, is_print, is_sent, write_off_product, company_id, warehouse_id)
VALUES (1, 'Оприходование 1', '2021-06-23 15:10', false, false, false, 1, 1),
       (2, 'Оприходование 2', '2021-06-23 15:10', false, false, false, 2, 1),
       (3, 'Оприходование 3', '2021-06-23 15:10', false, false, false, 3, 1);

INSERT INTO corrections_correction_products (correction_id, correction_products_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (3, 9);

SELECT setval('corrections_id_seq', max(id))
FROM corrections;

