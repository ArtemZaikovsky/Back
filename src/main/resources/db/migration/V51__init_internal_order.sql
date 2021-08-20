INSERT INTO internal_order (id, comment, date, is_print, is_sent, company_id, warehouse_id)
VALUES (1, 'Внутренний заказ 1', '2021-08-10 12:15:00.000000', false, false, 1, 1),
       (2, 'Внутренний заказ 4', '2021-08-10 12:15:00.000000', false, false, 4, 1),
       (3, 'Внутренний заказ 7', '2021-08-10 12:15:00.000000', false, false, 7, 1),
       (4, 'Внутренний заказ 10', '2021-08-10 12:15:00.000000', false, false, 10, 1),
       (5, 'Внутренний заказ 13', '2021-08-10 12:15:00.000000', false, false, 13, 1);
SELECT setval('internal_order_id_seq', max(id))
FROM internal_order