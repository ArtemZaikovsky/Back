alter table access_parameters drop if exists department_id;
alter table employees drop if exists department_id;
delete from departments;

insert into departments(id, name, sort_number)values
       (1, 'name1', 'sort num 1'),
       (2, 'name2', 'sort num 2'),
       (3, 'name3', 'sort num 3');

SELECT setval('departments_id_seq', max(id)) FROM departments;
