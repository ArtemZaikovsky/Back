INSERT INTO inventarizations (id, comment, date, status, company_id, warehouse_id)
VALUES (1, 'Инвентаризация 1', '2021-06-29 14:14:00.000000', false, 1, 1),
       (2, 'Инвентаризация 2', '2021-06-29 14:14:00.000000', false, 1, 1),
       (3, 'Инвентаризация 3', '2021-06-29 14:14:00.000000', false, 1, 1);
SELECT setval('inventarizations_id_seq', max(id))
FROM inventarizations