TRUNCATE acceptance_productions CASCADE;

INSERT INTO acceptance_productions (id, amount,product_id)
VALUES (1, 2, 4),
       (2, 3, 5),
       (3, 4, 6),
       (4, 6, 8),
       (5, 7, 9);

SELECT setval('acceptance_productions_id_seq', max(id))
FROM acceptance_productions;