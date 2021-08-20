TRUNCATE agent_reports CASCADE;

INSERT INTO agent_reports (id, comitent_sum, commentary, document_type, number, paid, printed, remuniration_sum, sent,
                          status, sum, time, company_id, contractor_id)
VALUES (1, 1, 'Комментарий 1', '.doc', '1', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1),
       (2, 1, 'Комментарий 2', '.doc', '2', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1),
       (3, 1, 'Комментарий 3', '.doc', '3', 1, 1, 1, 1, 'ok', 1, '2015-10-06 06:37', 1, 1);

SELECT setval('agent_reports_id_seq', max(id)) FROM agent_reports;