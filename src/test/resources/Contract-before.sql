TRUNCATE contracts CASCADE;

INSERT INTO contracts (id, amount, archive, comment, contract_date, number, bank_account_id, company_id, contractor_id, legal_detail_id)
VALUES (1, 200, false, 'no comments', '2021-08-06', '1', 3, 1, 1, 1),
       (2, 200, false, 'no comments', '2021-08-06', '2', 3, 1, 1, 1),
       (3, 200, false, 'no comments', '2021-08-06', '3', 3, 1, 1, 1);

SELECT setval('contracts_id_seq', max(id))
FROM contracts;