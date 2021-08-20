TRUNCATE employees CASCADE;

insert into employees(id, last_name, first_name, middle_name, sort_number, phone, inn, description, email, password) values
         (1, 'last_name1', 'first_name1', 'middle_name1', 'sort_number1', 'phone1', '012345678901', 'description1', 'email1', 'password1'),
         (2, 'last_name2', 'first_name2', 'middle_name2', 'sort_number2', 'phone2', '012345678902', 'description2', 'email2', 'password2'),
         (3, 'last_name3', 'first_name3', 'middle_name3', 'sort_number3', 'phone3', '012345678903', 'description3', 'email3', 'password3');

SELECT setval('employees_id_seq', max(id)) FROM employees;