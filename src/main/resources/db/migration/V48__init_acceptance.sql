INSERT INTO acceptances (id, comment, incoming_number, incoming_number_date, is_print, is_sent, contract_id,
                         contractor_id, warehouse_id)
VALUES (1, 'Комментарий 1', '1001', '2021-08-10', false, false, 1, 1, 1),
       (2, 'Комментарий 2', '1002', '2021-08-10', false, false, 2, 2, 2),
       (3, 'Комментарий 3', '1003', '2021-08-10', false, false, 3, 3, 3),
       (4, 'Комментарий 4', '1004', '2021-08-10', false, false, 4, 4, 4);
SELECT setval('acceptances_id_seq', max(id))
FROM acceptances