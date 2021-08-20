TRUNCATE payouts CASCADE;
delete from payouts;

insert into payouts(id, date, retail_store_id, who_was_paid, company_id, is_sent, is_print, comment)
values

(1, '2021-07-19 21:32:57.8', 1, 'who', 1, true, true, 'comment'),
(2, '2020-07-19 21:32:57.8', 2, 'whoWho', 2, false , false , 'commentComment' );