INSERT INTO corrections (id, comment, date, is_print, is_sent, write_off_product, company_id, warehouse_id)
VALUES (1, 'Оприходование 1', '2021-08-10 09:03:00.000000', false, true, false, 1, 1),
       (2, 'Оприходование 2', '2021-08-10 09:03:00.000000', false, false, false, 5, 1),
       (3, 'Оприходование 3', '2021-08-10 09:03:00.000000', true, false, false, 10, 1);
SELECT setval('corrections_id_seq', max(id))
FROM corrections