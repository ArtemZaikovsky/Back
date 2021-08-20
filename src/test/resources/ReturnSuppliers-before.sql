DELETE
FROM return_suppliers;

INSERT INTO return_suppliers (id, comment, date, is_print, is_send, company_id, contract_id, contractor_id,
                              warehouse_id)
VALUES (1, 'Комментарий 1', '2021-06-23 15:10', false, false, 1, 1, 1, 1),
       (2, 'Комментарий 2', '2021-06-23 15:10', true, false, 2, 1, 2, 1),
       (3, 'Комментарий 3', '2021-06-23 15:10', false, true, 3, 1, 3, 1),
       (4, 'Комментарий 4', '2021-06-23 15:10', true, true, 4, 1, 4, 1);