delete from remains;

insert into remains(id, name, reserve, sales_cost, sales_sum, sum_of_cost_price, vendor_code) values

(1, 'name1', 37634, 45742, 3464, 45623, 'code1'),
(2, 'name2', 37434, 34634, 3673, 43442, 'code2'),
(3, 'name3', 3865, 8534, 4634, 75625, 'code3');

SELECT setval('remains_id_seq', max(id)) FROM remains;
