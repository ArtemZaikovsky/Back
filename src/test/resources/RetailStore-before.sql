TRUNCATE retail_stores CASCADE;

INSERT INTO retail_stores (id, activity_status, default_taxation_system, is_active, name, order_taxation_system, revenue, sales_invoice_prefix, company_id)
VALUES (1, 'Был в сети вчера', 'ОСН', true, 'Ozon', 'УСН. Доход', 12000, 'SI', 1),
       (2, 'Онлайн', 'ОСН', true, 'Ozon', 'УСН. Доход', 12000, 'SI', 2),
       (3, 'Был в сети 2 часа назад', 'ОСН', true, 'Ozon', 'УСН. Доход', 12000, 'SI', 3);

SELECT setval('retail_stores_id_seq', max(id))
FROM retail_stores;