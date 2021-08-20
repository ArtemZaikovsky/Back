TRUNCATE legal_details CASCADE;

insert into legal_details(id, comment_to_address, date_of_the_certificate, first_name, inn, kpp, last_name,
                          middle_name, number_of_the_certificate, ogrn, okpo, address_id, type_of_contractor_id)
values

(1,'comment to address', '2020-10-10', 'Михаил', 3664069397, 79271669, 'Иванов', 'Сергеевич', 432145, 1056161351655, 70541561 , 3, 1),
(2,'comment to address', '2019-08-08', 'Андрей', 3664049551, 79165116, 'Сергеев', 'Андреевич', 432165, 1053561651515, 70651615 , 2, 2),
(3,'comment to address', '2018-06-06', 'Сергей', 3661564265, 79196813, 'Михайлов', 'Антонович', 432445, 1051566516515, 70651656 , 1, 3);

SELECT setval('legal_details_id_seq', max(id)) FROM legal_details;