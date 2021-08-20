INSERT INTO movement (id, comment, date, is_print, is_sent, company_id, warehouse_from_id, warehouse_to_id)
VALUES (1, 'Перемещение 1', '2021-08-10 09:03:00.000000', false, false, 1, 1, 2),
       (2, 'Перемещение 2', '2021-08-10 09:03:00.000000', false, false, 2, 1, 3),
       (3, 'Перемещение 3', '2021-08-10 09:03:00.000000', false, true, 1, 2, 1);
SELECT setval('movement_id_seq', max(id))
FROM movement