DELETE
FROM supplier_accounts;

INSERT INTO supplier_accounts(id, comment, date, is_spend, company_id, contract_id, contractor_id,
                              warehouse_id)
VALUES (1, 'Комментарий 1', '2021-07-23 15:10', false, 1, 1, 1, 1),
       (2, 'Комментарий 2', '2021-07-23 15:10', false, 2, 1, 2, 1),
       (3, 'Комментарий 3', '2021-07-23 15:10', true, 3, 1, 3, 1);