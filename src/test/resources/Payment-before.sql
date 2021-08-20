TRUNCATE payments CASCADE;

INSERT INTO payments (id, number, payment_methods, sum, time, type_of_payment, company_id, contract_id, contractor_id,
                      project_id)
VALUES (1, '1', 'CASH', 100, '2021-07-30 13:23:24', 'INCOMING', 1, 1, 2, 2),
       (2, '2', 'BANK', 50, '2021-07-30 13:23:24', 'OUTGOING', 1, 2, 2, 3),
       (162, '3', 'CASH', 100, '2021-07-30 13:23:24', 'INCOMING', 2, 3, 1, 3);

SELECT setval('payments_id_seq', max(id))
FROM payments;