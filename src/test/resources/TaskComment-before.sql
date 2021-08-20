TRUNCATE task_comment cascade;
insert into task_comment(id, comment, published_date_time, publisher_id, task_id) values

(1, 'comm1', '2012-04-10 03:09:02',  1, 1),
(2, 'comm2', '2013-04-10 03:09:02',  2, 2),
(3, 'comm3', '2014-04-10 03:09:02',  3, 3);

SELECT setval('task_comment_id_seq', max(id)) FROM task_comment;