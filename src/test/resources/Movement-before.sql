DELETE FROM movement_movement_products;
DELETE FROM movement;

INSERT INTO movement (id, comment, date, is_print, is_sent, company_id, warehouse_from_id,warehouse_to_id)
VALUES (1, 'Перемещение 1', '2021-07-16 15:10', false, false, 1, 1, 2),
       (2, 'Перемещение 2', '2021-07-16 15:10', false, false, 1, 2, 1),
       (3, 'Перемещение 3', '2021-07-16 15:10', false, false, 1, 1, 2);

INSERT INTO movement_movement_products (movement_id, movement_products_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (3, 9);

SELECT setval('movement_id_seq', max(id))
FROM movement;

