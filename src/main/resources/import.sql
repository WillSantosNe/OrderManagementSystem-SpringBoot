-- Inserindo usuários (sem ID, pois é auto-incremento)
INSERT INTO tb_user (name, email, password) VALUES ('Alice Johnson', 'alice@example.com', 'password123');
INSERT INTO tb_user (name, email, password) VALUES ('Bob Smith', 'bob@example.com', 'password456');

-- Inserindo produtos (sem ID, pois é auto-incremento)
INSERT INTO tb_product (name, description, price) VALUES ('Laptop', 'High performance laptop', 2500.00);
INSERT INTO tb_product (name, description, price) VALUES ('Smartphone', 'Latest model smartphone', 1500.00);
INSERT INTO tb_product (name, description, price) VALUES ('Headphones', 'Noise cancelling headphones', 300.00);

-- Inserindo pedidos
INSERT INTO tb_order (status, order_date, user_id) VALUES (0, CURRENT_TIMESTAMP, 1); -- PENDING
INSERT INTO tb_order (status, order_date, user_id) VALUES (1, CURRENT_TIMESTAMP, 2); -- CONFIRMED


-- Inserindo itens do pedido (IDs de pedidos e produtos devem ser os valores gerados pelo banco)
INSERT INTO tb_order_item (order_id, product_id, quantity, subtotal) VALUES (1, 1, 1, 2500.00);
INSERT INTO tb_order_item (order_id, product_id, quantity, subtotal) VALUES (1, 3, 2, 600.00);
INSERT INTO tb_order_item (order_id, product_id, quantity, subtotal) VALUES (2, 2, 1, 1500.00);
