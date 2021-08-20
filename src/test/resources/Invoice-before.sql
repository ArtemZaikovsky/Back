TRUNCATE invoice CASCADE;

INSERT INTO invoice (id,comment,data,is_spend,type_of_invoice,company_id,contractor_id,warehouse_id)
VALUES (1,'comment 1','2222-11-01 00:01',false,1,1,1,1),
       (2,'comment 2','2222-11-01 00:02',false,1,1,1,1),
       (3,'comment 3','2222-11-01 00:03',false,1,1,1,1);

SELECT setval('invoice_id_seq', max(id))
FROM invoice;