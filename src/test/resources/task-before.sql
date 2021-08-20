TRUNCATE task cascade;
insert into task(id, description, creation_Date_Time, deadline_Date_Time, completed, task_Author_id, task_employee_id) values

(1, 'desc1', '2012-04-10 03:09:02', '2012-04-11 03:09:02', true, 1, 1),
(2, 'desc2', '2013-04-10 03:09:02', '2013-04-11 03:09:02', true, 2, 2),
(3, 'desc3', '2014-04-10 03:09:02', '2014-04-11 03:09:02', true, 3, 3);

SELECT setval('task_id_seq', max(id)) FROM task;