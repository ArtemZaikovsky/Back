delete from acceptances;

insert into acceptances(id, comment, incoming_number, incoming_number_date, contract_id, contractor_id, warehouse_id)
values

(1, 'comment1', 1, '2021-07-03', 1, 1, 1),
(2, 'comment2', 2, '2021-07-03', 2, 2, 2),
(3, 'comment3', 3, '2021-07-03', 3, 3, 3);

SELECT setval('acceptances_id_seq', max(id)) FROM acceptances;


